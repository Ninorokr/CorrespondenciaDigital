package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import com.silverlink.Utils.Navegador;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesadorDatos {

    String tempPath = "D:\\Temp";
    String os1Path = "D:\\002\\23\\0001\\";
    int cuentaArchivos;

    public void procesarCasos(ArrayList<Caso> casos) {
        Navegador navegador = new Navegador();
        Encarpetador encarpetador;

        for (int i = 0; i < casos.size(); i++) {
            Caso caso = casos.get(i);
            int cantArchivos = navegador.descargarArchivos(caso.getIdActividad());
            try {
                while(true) {
                    if(isArchivosCompletos(cantArchivos))
                        break;
                }

                //Mover los archivos
                String item =  String.format("%04d", i+1);
                Path destino = Files.createDirectory(Path.of(os1Path + item));
                encarpetador = new Encarpetador(destino.toString(), cantArchivos);
                Files.walkFileTree(Path.of(tempPath), encarpetador);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public boolean isArchivosCompletos(int cantArchivos) throws IOException{
        //TODO Debe dar OK si cantArchivos = nroArchivos en carpeta
        cuentaArchivos = 0;
        Files.walkFileTree(Path.of(tempPath), new ContadorArchivos());
        return cantArchivos == cuentaArchivos;
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


}




