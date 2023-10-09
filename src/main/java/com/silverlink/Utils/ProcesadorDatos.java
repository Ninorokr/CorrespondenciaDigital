package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import static com.silverlink.Main.*;

public class ProcesadorDatos {

//    String os1Path = "D:\\002\\23\\0001\\";
    static int cuentaArchivos;
    ArrayList<PDDocument> archivosEnItem;

//    public void descargarYEncarpetarCasos(ArrayList<Caso> casos) {
//
//        if(!isDriverOpen) {
//            nav = new Navegador();
//            nav.abrirSesionSalesforce();
//        }
//
//        for (int i = 0; i < casos.size(); i++) {
//            Caso caso = casos.get(i);
//            int cantArchivos = nav.descargarArchivosCaso(caso.getIdActividad());
//            try {
//                while(true) {
//                    if(isArchivosCompletos(cantArchivos))
//                        break;
//                }
//                encarpetarArchivos(i, cantArchivos);
//
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//        }
//    }

    public void recolectarDatosDeArchivos(ArrayList<Caso> casos) {
        AnalistaPDF analista;

        for (int i = 0; i < casos.size(); i++) {
            archivosEnItem = new ArrayList<>();
            String item =  String.format("%04d", i+1);
            Path rutaItem = Path.of("D:\\002\\23\\0001\\" + item);
            try {
                Files.walkFileTree(rutaItem, new RevisadorArchivos());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            System.out.println((i+1) + ". ");
            analista = new AnalistaPDF();
            analista.reconocerActaOCarta(archivosEnItem, casos.get(i));
        }
    }

    public static boolean isArchivosCompletos(int cantArchivos) throws IOException {
        //Debe dar OK si cantArchivos = nroArchivos en carpeta
        cuentaArchivos = 0;
        Files.walkFileTree(Path.of(tempPath), new ContadorArchivos());
        return cantArchivos == cuentaArchivos;
    }

    public static void encarpetarArchivos(Caso caso, int cantArchivos) throws IOException {
        //Encarpetar archivos en "temp" a la carpeta de su respectivo item
        String nroOS = String.format("%04d", caso.getNroOS());
        String item =  String.format("%04d", caso.getIdCorrelativoCaso());
        Path destino = Files.createDirectories(Path.of(rootFolder + caso.getAnio() + "\\" + nroOS + "\\" + item));
//        Encarpetador encarpetador = new Encarpetador(destino.toString(), cantArchivos);
//        Files.walkFileTree(Path.of(tempPath), encarpetador);
        Files.walkFileTree(Path.of(tempPath), new Encarpetador(destino, cantArchivos));
    }

    static class Encarpetador extends SimpleFileVisitor<Path> {

        Path destino;
        int cantArchivos;

        public Encarpetador (Path destino, int cantArchivos) {
            this.destino = destino;
            this.cantArchivos = cantArchivos;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.move(file, Path.of(destino + "\\" + file.getName(file.getNameCount()-1)), StandardCopyOption.ATOMIC_MOVE);
            return super.visitFile(file, attrs);
        }
    }

    static class ContadorArchivos extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (!(file.toString().endsWith(".crdownload") || file.toString().endsWith(".tmp") ||
                (file.toString().endsWith(".CRDOWNLOAD") || file.toString().endsWith(".TMP")))) {
                cuentaArchivos++;
//                System.out.println("cuenta archivos: " + ++cuentaArchivos + " | " + file);
            }
            return super.visitFile(file, attrs);
        }
    }

    class RevisadorArchivos extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            archivosEnItem.add(Loader.loadPDF(file.toFile()));
            return super.visitFile(file, attrs);
        }
    }


}




