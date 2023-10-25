package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.silverlink.Main.rootFolder;
import static com.silverlink.Main.scanner;

public class Reportero {

    public String exportarCasosPorOS(ArrayList<Caso> casos) {
        //Reporte para revisar PREVIO a la descarga automática en salesforce
        //exportar todos los casos que sean descargadoEnSalesforce = FALSE

        String outputPath = null;
        String[] titulosEncabezado = {"Año", "OS", "Id", "Tipo de atención", "Tipo de registro del caso",
                "Id. de actividad", "Tipo de carta", "Número del caso", "Estado del caso", "Caso: Fecha de creación",
                "Correlativo de carta", "Número Suministro", "Asunto", "Canal de notificación", "Asignado",
                "Provincia", "Prioridad", "Estado", "Fecha de creación", "Fecha de emisión", "Fecha de despacho",
                "Fecha de notificación", "Correo Carta", "Correo Acta", "errorNroCarta", "errorCorreoNotif",
                "errorFechas", "ErrorFaltaFirma", "ErrorFaltaCarta", "ErrorFaltaActa", "Fecha de notificación de la carta",
                "Fecha de la última modificación", "Fecha", "Fecha de vencimiento legal", "Creado por", "Canal de registro",
                "Propietario del caso", "Propietario del caso", "Días Vencidos / Por Vencer", "Enlace web", "Mensaje"};

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet();
            XSSFRow encabezado = sheet.createRow(0);
            for (int i = 0; i < titulosEncabezado.length; i++) {
                XSSFCell cell = encabezado.createCell(i, CellType.STRING);
                cell.setCellValue(titulosEncabezado[i]);
            }

            for (int i = 0; i < casos.size(); i++) {
                Caso caso = casos.get(i);
                XSSFRow rowCaso = sheet.createRow(i+1);
                for (int j = 0; j < titulosEncabezado.length; j++) {
                    XSSFCell cellDato = rowCaso.createCell(j, CellType.STRING);
                    switch (j) {
                        case 0: cellDato.setCellValue(caso.getAnio()); break;
                        case 1: cellDato.setCellValue(caso.getNroOS()); break;
                        case 2: cellDato.setCellValue(caso.getIdCaso()); break;
                        case 3: cellDato.setCellValue(caso.getTipoAtencion().getNomTipoAtencion()); break;
                        case 4: cellDato.setCellValue(caso.getTipoRegCaso().getNomTipoRegCaso()); break;
                        case 5: cellDato.setCellValue(caso.getIdActividad()); break;
                        case 6: cellDato.setCellValue(caso.getTipoCarta().getNomTipoCarta()); break;
                        case 7: cellDato.setCellValue(caso.getNroCaso()); break;
                        case 8: cellDato.setCellValue(caso.getEstadoCaso().getNomEstadoCaso()); break;
                        case 9: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecCreacionCaso()); break;
                        case 10: cellDato.setCellValue(caso.getCorrelativoCarta()); break;
                        case 11: cellDato.setCellValue(caso.getNroSuministro()); break;
                        case 12: cellDato.setCellValue("Correspondencia Digital"); break;
                        case 13: cellDato.setCellValue(caso.getCanalNotificacion().getNomCanalNotificacion()); break;
                        case 14: cellDato.setCellValue("Proveedor Mensajeria"); break;
                        case 15: cellDato.setCellValue(caso.getProvincia().getNomProvincia()); break;
                        case 16: cellDato.setCellValue(caso.getPrioridad().getNomPrioridad()); break;
                        case 17: cellDato.setCellValue(caso.getEstado().getNomEstado()); break;
                        case 18: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecCreacion()); break;
                        case 19: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecEmisionDateTime()); break;
                        case 20: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecDespacho()); break;
                        case 21: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecNotificacion()); break;
                        case 22: cellDato.setCellValue(caso.getCorreosCartasString()); break;
                        case 23: cellDato.setCellValue(caso.getCorreosActasString()); break;
                        case 24: cellDato.setCellValue(caso.isErrorNroCarta()); break;
                        case 25: cellDato.setCellValue(caso.isErrorCorreoNotif()); break;
                        case 26: cellDato.setCellValue(caso.isErrorFechas()); break;
                        case 27: cellDato.setCellValue(caso.isErrorFaltaFirma()); break;
                        case 28: cellDato.setCellValue(caso.isErrorFaltaCartas()); break;
                        case 29: cellDato.setCellValue(caso.isErrorFaltaActas()); break;
                        case 30: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecNotificacionCarta()); break;
                        case 31: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecUltimaModificacion()); break;
                        case 32: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecha()); break;
                        case 33: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecVencimientoLegal()); break;
                        case 34: cellDato.setCellValue(caso.getCreadoPor().getNomUsuario()); break;
                        case 35: cellDato.setCellValue(caso.getCanalRegistro().getNomCanalRegistro()); break;
                        case 36: cellDato.setCellValue(caso.getPropietarioCaso().getNomUsuario()); break;
                        case 37: cellDato.setCellValue(caso.getPropietarioCaso().getCodUsuario()); break;
                        case 38: cellDato.setCellType(CellType.NUMERIC);
                         cellDato.setCellValue(caso.getDiasVencidosPorVencer()); break;
                        case 39: cellDato.setCellValue("https://enelsud.my.salesforce.com/" + caso.getIdActividad());
//                        case 37: cellDato.setCellValue(getMensajeRechazo(caso)); break;
                    }
                }
            }
        try {
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(rootFolder + "revision.xlsx"));
//            wb.write(bos);
            String nroOS = String.format("%04d", casos.get(0).getNroOS());
            outputPath = rootFolder + casos.get(0).getAnio() + "\\" + nroOS + "\\revision.xlsx";

            try(FileOutputStream fos = new FileOutputStream(outputPath)) {
                wb.write(fos);
                scanner.nextLine();
                System.out.println("Se creó el archivo en " + outputPath);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return outputPath;
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

//    private String msjErrorCorreo(Caso caso) {
//        String msj = null;
//
//        if (caso.getCorreosCartasString().equals(null)) {
//            msj = "La carta no incluye correo de notifiación para constatar con el acta";
//        } else if (caso.getCorreosActasString().equals(null)) {
//            msj = "El acta no incluye correo de notifiación para constatar con la carta";
//        } else if (caso.getCorreosCartasString().equals(null) || caso.getCorreosActasString().equals(null)) {
//            msj = "Ni el correo ni el acta tienen correos para constatar entre ellos";
//        } else if (caso.getCorreosCartasString().equals(caso.getCorreosActasString())) {
//            msj = "Correo de notificación incorrecto, correo de la carta no coincide con el del acta, " +
//                    "la carta indica " + caso.getCorreosCartasString() + ", el acta indica " + caso.getCorreosActasString();
//        }
//        return msj;
//    }
}
