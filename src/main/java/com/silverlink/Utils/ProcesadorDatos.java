package com.silverlink.Utils;

import com.silverlink.Entidades.Acta;
import com.silverlink.Entidades.Carta;
import com.silverlink.Entidades.Caso;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import static com.silverlink.Main.*;
import static com.silverlink.Utils.AnalistaPDF.reconocerActaOCarta;

public class ProcesadorDatos {

//    String os1Path = "D:\\002\\23\\0001\\";
    static int cuentaArchivos;
//    static ArrayList<PDDocument> archivosEnItem;

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

//    public static void recolectarDatosDeArchivos(ArrayList<Caso> casos) {
//
//        for (int i = 0; i < casos.size(); i++) {
//            archivosEnItem = new ArrayList<>();
//            Caso caso = casos.get(i);
//            String nroOS = String.format("%04d", caso.getNroOS());
//            String item =  String.format("%04d", caso.getIdCorrelativoCaso());
//            Path rutaItem = Path.of(rootFolder + caso.getAnio() + "\\" + nroOS + "\\" + item);
//            try {
//                Files.walkFileTree(rutaItem, new RevisadorArchivos());
//            } catch (IOException ioe) {
//                System.out.println(ioe.getMessage());
//            }
//            System.out.println((i+1) + ". ");
////            analista = new AnalistaPDF();
//            reconocerActaOCarta(archivosEnItem, casos.get(i));
//        }
//    }

//    public static void recolectarDatosDeArchivos(ArrayList<Caso> casos) {
//
//        for(Caso caso : casos) {
//            String nroOS = String.format("%04d", caso.getNroOS());
//            String item =  String.format("%04d", caso.getIdCorrelativoCaso());
//            Path rutaItem = Path.of(rootFolder + caso.getAnio() + "\\" + nroOS + "\\" + item);
//
//            try {
//                Files.walkFileTree(rutaItem, new RevisadorArchivos(caso));
//            } catch (IOException ioe) {
//                System.out.println(ioe.getMessage());
//            }
//
//            //VERIFICADOR: Archivos completos
//            caso.setErrorFaltaCartas(caso.getCartas().size() == 0); //Activar flag si faltan cartas
//            caso.setErrorFaltaActas(caso.getActas().size() == 0); //Activar flag si faltan actas
//
//            if(caso.isErrorFaltaCartas() || caso.isErrorFaltaActas()){
//                caso.getEstado().setIdEstado((short) 5); //RECHAZADO
//            }
//
//            //TODO Colocar aqui el comparador de datos entre cartas y actas
//            //VERIFICADOR: Nro. de carta
//            caso.setErrorNroCarta(!nroCartaOK(caso));
//
//            //TODO Separar los revisadores en 2 o más métodos distintos, actualizando la lista de casos
//            //TODO para trabajar sólo con los casos pendientes y no los rechazados
//
//            Commander.updateCasosRevisados(caso);
//        }
//    }

    public static void recolectarDatosDeArchivos(ArrayList<Caso> casos) {
        for(Caso caso : casos) {
            String nroOS = String.format("%04d", caso.getNroOS());
            String item =  String.format("%04d", caso.getIdCorrelativoCaso());
            Path rutaItem = Path.of(rootFolder + caso.getAnio() + "\\" + nroOS + "\\" + item);

            try {
                Files.walkFileTree(rutaItem, new RevisadorArchivos(caso));
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            verificarDocumentosCompletos(caso);
            verificarNrosDeCartaCorrectos(caso);
        }
    }

    public static void verificarDocumentosCompletos(Caso caso) {
        //VERIFICADOR: Archivos completos
            caso.setErrorFaltaCartas(caso.getCartas().size() == 0); //Activar flag si faltan cartas
            caso.setErrorFaltaActas(caso.getActas().size() == 0); //Activar flag si faltan actas

            if(caso.isErrorFaltaCartas() || caso.isErrorFaltaActas()){
                caso.getEstado().setIdEstado((short) 5); //RECHAZADO
            }
            Commander.updateCasosRevisados(caso);
    }

    public static void verificarNrosDeCartaCorrectos(Caso caso) {
        //VERIFICADOR: Nro. de carta
            caso.setErrorNroCarta(!nroCartaOK(caso));
            Commander.updateCasosRevisados(caso);
    }

    public static boolean nroCartaOK(Caso caso) {
        for (Carta carta : caso.getCartas()) {
            for(Acta acta : caso.getActas()) {
                if(carta.getNroCarta() != acta.getNroActa()) {
                    caso.getEstado().setIdEstado((short) 5); //RECHAZADO
                    return false;
                }
            }
        }
        return true;
    }

//    public static boolean correoNotificacionOK(Caso caso) {
//
//    }

    public static boolean isDescargaArchivosCompletada(int cantArchivos) throws IOException {
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
            Files.move(file, Path.of(destino + "\\" + file.getName(file.getNameCount()-1)),
                    StandardCopyOption.REPLACE_EXISTING);
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

//    static class RevisadorArchivos extends SimpleFileVisitor<Path> {
//
//        @Override
//        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//            archivosEnItem.add(Loader.loadPDF(file.toFile()));
//            return super.visitFile(file, attrs);
//        }
//    }

    static class RevisadorArchivos extends SimpleFileVisitor<Path> {

        Caso caso;

        public RevisadorArchivos(Caso caso) {
            this.caso = caso;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            reconocerActaOCarta(file.toFile(), caso);
            return super.visitFile(file, attrs);
        }
    }


}




