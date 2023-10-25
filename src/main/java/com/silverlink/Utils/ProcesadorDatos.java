package com.silverlink.Utils;

import com.silverlink.Entidades.Acta;
import com.silverlink.Entidades.Carta;
import com.silverlink.Entidades.Caso;
import com.silverlink.Entidades.Estado;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.silverlink.Main.*;
import static com.silverlink.Utils.AnalistaPDF.reconocerActaOCarta;

public class ProcesadorDatos {

    static int cuentaArchivos;
    static String correosCarta;
    static String correosActa;

    public void recolectarYVerificarDatos(ArrayList<Caso> casos) {

        for (Caso caso : casos) {
            String nroOS = String.format("%04d", caso.getNroOS());
            String item =  String.format("%04d", caso.getIdCaso());
            Path rutaItem = Path.of(rootFolder + caso.getAnio() + "\\" + nroOS + "\\" + item);
            System.out.println("\n002-23-" + nroOS + "-" + item);

            try {
                Files.walkFileTree(rutaItem, new VerificadorArchivos(caso));
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }

            if (docsOK(caso)) {
                boolean nroCartaOK = nroCartaOK(caso);
                boolean correoOK = correoOK(caso);
                boolean fechasOK = fechasOK(caso);
                if (!nroCartaOK || !correoOK || !fechasOK) {
                    caso.getEstado().setIdEstado((short) 5); //RECHAZADA
                    caso.setNroCarta(caso.getCartas());
                } else {
                    caso.getEstado().setIdEstado((short) 4); //DESCARGADA
                }
                Commander.updateCasosVerificadosCompletos(caso);
            } else {
                caso.getEstado().setIdEstado((short) 5); //RECHAZADA
                Commander.updateCasosVerificadosIncompletos(caso);
            }
//            SaveImagesInPdf printer = new SaveImagesInPdf(caso);
        }
    }

    public static String createMensajeRechazo(Caso caso) {
        String msj = null;

            if (caso.isErrorFaltaCartas()) {
                msj = "Falta adjuntar carta";
            } else if (caso.isErrorFaltaActas()) {
                msj = "Falta adjuntar acta";
            } else if (caso.isErrorFaltaCartas() && caso.isErrorFaltaActas()) {
                msj = "No hay archivos adjuntos";
            } else {
                if (caso.isErrorNroCarta()) {
                    msj += "Error con el nro. de carta";
                } else if (caso.isErrorCorreoNotif()) {
                    msj += "Error en el correo de notificación";
                } else if (caso.isErrorFechas()) {
                    msj += "La fecha de emisión de la carta ";
                }
            }
        return msj;
    }

//    private String getMensajeRechazo(Caso caso) {
//        String msj = null;
//
//        if (caso.getEstado().getIdEstado() == 5) {
//            if (caso.isErrorFaltaCartas()) {
//                msj = "Falta adjuntar carta";
//            } else if (caso.isErrorFaltaActas()) {
//                msj = "Falta adjuntar acta";
//            } else if (caso.isErrorFaltaCartas() && caso.isErrorFaltaActas()) {
//                msj = "No hay archivos adjuntos";
//            } else {
//                if (caso.isErrorNroCarta()) {
//                    msj += "Error con el nro. de carta";
//                } else if (caso.isErrorCorreoNotif()) {
//                    msj += "Error en el correo de notificación";
//                } else if (caso.isErrorFechas()) {
//                    msj += "La fecha de emisión de la carta ";
//                }
//
//            }
//        }
//        return msj;
//    }


    public static boolean docsOK(Caso caso) {
        //VERIFICADOR: Archivos completos
            caso.setErrorFaltaCartas(caso.getCartas().size() == 0); //Activar flag si faltan cartas
            caso.setErrorFaltaActas(caso.getActas().size() == 0); //Activar flag si faltan actas

        return !caso.isErrorFaltaCartas() && !caso.isErrorFaltaActas();
    }

//    public static boolean nroCartaOK(Caso caso) {
//        //VERIFICADOR: Nro. de carta
//        ArrayList<Acta> actas = caso.getActas();
//        for (Carta carta : caso.getCartas()) {
//            for (Acta acta : actas) {
//                if (carta.getNroCarta() != acta.getNroActa()) {
//                    caso.setErrorNroCarta(true);
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public static boolean nroCartaOK(Caso caso) {
        //VERIFICADOR: Nro. de carta
        ArrayList<Acta> actas = caso.getActas();
        for (Carta carta : caso.getCartas()) {
            for (Acta acta : actas) {
                if (carta.getNroCarta() != acta.getNroActa() ||
                    carta.getNroCarta() != caso.getNroCaso() ||
                    acta.getNroActa() != caso.getNroCaso()) {
                    caso.setErrorNroCarta(true);
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean correoOK(Caso caso) {
        //VERIFICADOR: Correo de notificación
        correosCarta = null;
        correosActa = null;
        boolean ok = true;

        ArrayList<Carta> cartas = caso.getCartas();
        for (Acta acta : caso.getActas()) {
            if(!caso.getCorreosActas().contains(acta.getCorreoDestinatario())){
                caso.getCorreosActas().add(acta.getCorreoDestinatario());
            }
//            if (caso.getCorreosActas() != null) {
//                if (caso.getCorreosActas().contains(acta.getCorreoDestinatario()))
//                    caso.getCorreosActas().add(acta.getCorreoDestinatario());
//            } else {
//                caso.getCorreosActas().add(acta.getCorreoDestinatario());
//            }

            for (Carta carta : cartas) {
                if(!caso.getCorreosCartas().contains(carta.getCorreoDestinatario())){
                    caso.getCorreosCartas().add(carta.getCorreoDestinatario());
                }
//                if (caso.getCorreosCartas() != null) {
//                    if (caso.getCorreosCartas().contains(acta.getCorreoDestinatario()))
//                        caso.getCorreosCartas().add(acta.getCorreoDestinatario());
//                } else {
//                    caso.getCorreosCartas().add(acta.getCorreoDestinatario());
//                }

                if (!acta.getCorreoDestinatario().equalsIgnoreCase(carta.getCorreoDestinatario())) {
                    caso.setErrorCorreoNotif(true);
//                    caso.getEstado().setIdEstado((short) 5); //RECHAZADO
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean fechasOK(Caso caso) {
        //VERIFICADOR: Fechas
        ArrayList<Acta> actas = caso.getActas();
        for (Carta carta : caso.getCartas()) {
            caso.setFecEmision(carta.getFechaEmision());
            for (Acta acta : actas) {
                caso.setFecDespacho(acta.getFechaEntrega());
                caso.setFecNotificacion(acta.getFechaEntrega());
                if (carta.getFechaEmision().isAfter(acta.getFechaEntrega().toLocalDate()) ||
                    acta.getFechaEntrega().toLocalDate().minusDays(7).isEqual(carta.getFechaEmision()) ||
                    acta.getFechaEntrega().toLocalDate().minusDays(7).isAfter(carta.getFechaEmision())) {
                    caso.setErrorFechas(true);
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<Caso> procesarCasosRevisados(String inputPath) {
        //Recoger los casos del excel revision.xlsx para actualizar los datos en la BD
        ArrayList<Caso> casos = new ArrayList<>();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputPath));
                XSSFWorkbook wb = new XSSFWorkbook(bis)) {
            wb.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            XSSFSheet sheet = wb.getSheetAt(0);
            Caso caso;
            for(int i = 1; i <= sheet.getLastRowNum(); i++) {
                caso = new Caso();
                XSSFRow row = sheet.getRow(i);
                caso.setAnio((short) row.getCell(0).getNumericCellValue());
                caso.setNroOS((short) row.getCell(1).getNumericCellValue());
                caso.setIdCaso((short) row.getCell(2).getNumericCellValue());
                caso.setEstado(Estado.getEstado(row.getCell(17).getStringCellValue()));
                caso.setFecEmisionDateTime(row.getCell(19).getLocalDateTimeCellValue());
                caso.setFecDespacho(row.getCell(20).getLocalDateTimeCellValue());
                caso.setFecNotificacion(row.getCell(21).getLocalDateTimeCellValue());
                caso.setCorreosCartasString(row.getCell(22).getStringCellValue());
                caso.setCorreosActasString(row.getCell(23).getStringCellValue());
                caso.setErrorNroCarta(row.getCell(24).getBooleanCellValue());
                caso.setErrorCorreoNotif(row.getCell(25).getBooleanCellValue());
                caso.setErrorFechas(row.getCell(26).getBooleanCellValue());
                caso.setErrorFaltaFirma(row.getCell(27).getBooleanCellValue());
                caso.setErrorFaltaCartas(row.getCell(28).getBooleanCellValue());
                caso.setErrorFaltaActas(row.getCell(29).getBooleanCellValue());
                caso.setMensajeError(row.getCell(40).getStringCellValue());
                casos.add(caso);
                System.out.println(i + ". ");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return casos;
    }

//    static private LocalDateTime timestampToLocalDateTime(Timestamp datetime) {
//        if (datetime == null)
//            return null;
//        return datetime.toLocalDateTime();
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
        String item =  String.format("%04d", caso.getIdCaso());
        Path destino = Files.createDirectories(Path.of(rootFolder + caso.getAnio() + "\\" + nroOS + "\\" + item));
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

//    static class VerificadorArchivos extends SimpleFileVisitor<Path> {
//
//        @Override
//        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//            archivosEnItem.add(Loader.loadPDF(file.toFile()));
//            return super.visitFile(file, attrs);
//        }
//    }

    static class VerificadorArchivos extends SimpleFileVisitor<Path> {

        Caso caso;

        public VerificadorArchivos(Caso caso) {
            this.caso = caso;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            reconocerActaOCarta(file.toFile(), caso);
            return super.visitFile(file, attrs);
        }
    }

//    class SaveImagesInPdf extends PDFStreamEngine {
//
////        public int docNum;
////        public int pageNum;
//        public int imageNumber = 1;
//        Caso caso;
//
//        SaveImagesInPdf (Caso caso) {
////            this.docNum = docNum;
////            this.pageNum = pageNum;
//            this.caso = caso;
//        }
//
//        @Override
//        public void processOperator(Operator operator, List<COSBase> operands) throws IOException {
//            String operation = operator.getName();
//            if ("Do".equals(operation)) {
//                COSName objectName = (COSName) operands.get(0);
//                PDXObject xobject = getResources().getXObject(objectName);
//                if (xobject instanceof PDImageXObject) {
//                    PDImageXObject image = (PDImageXObject) xobject;
//
//                    // same image to local
//                    BufferedImage bImage = image.getImage();
//                    String nroOS = String.format("%04d", caso.getNroOS());
//                    String nroID = String.format("%04d", caso.getIdCorrelativoCaso());
//                    String exportPath = rootFolder + caso.getAnio() + "\\" + nroOS + "\\" + nroID + "\\";
//
////                        DataBuffer dataBuffer = bImage.getData().getDataBuffer();
////
////                        // Each bank element in the data buffer is a 32-bit integer
////                        long sizeBytes = ((long) dataBuffer.getSize()) * 4L;
////                        long sizeMB = sizeBytes / (1024L * 1024L);
//
////                        System.out.println(imageNumber + ", size in: ");
////                        System.out.println("Bytes: " + sizeBytes + " bytes");
////                        System.out.println("MBytes: " + sizeMB + " MBs");
//
//                ImageIO.write(bImage,"PNG", new File(exportPath + "firma_" + caso.getNroCaso() +
//                        "_" + imageNumber + ".png"));
////                System.out.println("Image saved.");
//                imageNumber++;
//                }
//                else if(xobject instanceof PDFormXObject) {
//                    PDFormXObject form = (PDFormXObject)xobject;
//                    showForm(form);
//                }
//            }
//            else {
//                super.processOperator(operator, operands);
//            }
//
//        }
//    }


}




