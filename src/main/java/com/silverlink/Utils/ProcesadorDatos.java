package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class ProcesadorDatos {

    String tempPath = "D:\\Temp";
    String os1Path = "D:\\002\\23\\0001\\";
    int cuentaArchivos;
    ArrayList<PDDocument> archivosEnOS;

    public void descargarYEncarpetarCasos(ArrayList<Caso> casos) {

        Navegador navegador = new Navegador();

        for (int i = 0; i < casos.size(); i++) {
            Caso caso = casos.get(i);
            int cantArchivos = navegador.descargarArchivosCaso(caso.getIdActividad());
            try {
                while(true) {
                    if(isArchivosCompletos(cantArchivos))
                        break;
                }
                encarpetarArchivos(i, cantArchivos);

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void recolectarDatosDeArchivos(ArrayList<Caso> casos) {
        AnalistaPDF analista;

        for (int i = 0; i < casos.size(); i++) {
            archivosEnOS = new ArrayList<>();
            String item =  String.format("%04d", i+1);
            Path rutaItem = Path.of("D:\\002\\23\\0001\\" + item);
            try {
                Files.walkFileTree(rutaItem, new RevisadorArchivos());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            System.out.println((i+1) + ". ");
            analista = new AnalistaPDF();
            analista.reconocerActaOCarta(archivosEnOS, casos.get(i));
        }
    }

    public boolean isArchivosCompletos(int cantArchivos) throws IOException{
        //Debe dar OK si cantArchivos = nroArchivos en carpeta
        cuentaArchivos = 0;
        Files.walkFileTree(Path.of(tempPath), new ContadorArchivos());
        return cantArchivos == cuentaArchivos;
    }

    public void encarpetarArchivos(int i, int cantArchivos) throws IOException{
        //Encarpetar archivos en "temp" a la carpeta de su respectivo item
        String item =  String.format("%04d", i+1);
        Path destino = Files.createDirectory(Path.of(os1Path + item));
        Encarpetador encarpetador = new Encarpetador(destino.toString(), cantArchivos);
        Files.walkFileTree(Path.of(tempPath), encarpetador);
    }

    class Encarpetador extends SimpleFileVisitor<Path> {

        String destino;
        int cantArchivos;

        public Encarpetador (String destino, int cantArchivos) {
            this.destino = destino;
            this.cantArchivos = cantArchivos;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.move(file, Path.of(destino + "\\" + file.getName(file.getNameCount()-1)));
//            }
            return super.visitFile(file, attrs);
        }
    }

    class ContadorArchivos extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(!(file.toString().endsWith(".crdownload") || file.toString().endsWith(".tmp"))){
                cuentaArchivos++;
            }
            return super.visitFile(file, attrs);
        }
    }

    class RevisadorArchivos extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            archivosEnOS.add(Loader.loadPDF(file.toFile()));
            return super.visitFile(file, attrs);
        }
    }


}




