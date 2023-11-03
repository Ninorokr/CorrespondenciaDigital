package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

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
        sheet.setZoom(70);
        XSSFRow encabezado = sheet.createRow(0);

        XSSFCellStyle headerStyle = wb.createCellStyle();
        XSSFFont headerFont = wb.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        XSSFCellStyle dateCellStyle = wb.createCellStyle();
        dateCellStyle.setDataFormat((short) 14);

        XSSFCellStyle dateTimeCellStyle = wb.createCellStyle();
        CreationHelper helper = wb.getCreationHelper();
        short format = helper.createDataFormat().getFormat("dd/mm/yyyy hh:mm");
        dateTimeCellStyle.setDataFormat(format);

        XSSFCellStyle trueCellStyle = wb.createCellStyle();
        trueCellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
        XSSFFont trueFont = wb.createFont();
        trueFont.setColor(IndexedColors.DARK_RED.getIndex());
        trueCellStyle.setFont(trueFont);

            for (int i = 0; i < titulosEncabezado.length; i++) {
                XSSFCell cell = encabezado.createCell(i, CellType.STRING);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(titulosEncabezado[i]);
            }

            for (int i = 0; i < casos.size(); i++) {
                Caso caso = casos.get(i);
                XSSFRow rowCaso = sheet.createRow(i+1);
                for (int j = 0; j < titulosEncabezado.length; j++) {
                    XSSFCell cellDato = rowCaso.createCell(j, CellType.STRING);
                    switch (j) {
                        case 0: cellDato.setCellValue(caso.getAnio());
                                sheet.setColumnHidden(j, true); break;
                        case 1: cellDato.setCellValue(caso.getNroOS());
                                sheet.setColumnHidden(j, true); break;
                        case 2: cellDato.setCellValue(caso.getIdCaso());
                            sheet.setColumnWidth(j, 256 * 4); break;
                        case 3: cellDato.setCellValue(caso.getTipoAtencion().getNomTipoAtencion());
                                sheet.setColumnHidden(j, true); break;
                        case 4: cellDato.setCellValue(caso.getTipoRegCaso().getNomTipoRegCaso());
                                sheet.setColumnHidden(j, true); break;
                        case 5: cellDato.setCellValue(caso.getIdActividad());
                                sheet.setColumnWidth(j, 256 * 20); break;
                        case 6: cellDato.setCellValue(caso.getTipoCarta().getNomTipoCarta());
                                sheet.setColumnHidden(j, true); break;
                        case 7: cellDato.setCellValue(caso.getNroCaso());
                            sheet.setColumnWidth(j, 256 * 12); break;
                        case 8: cellDato.setCellValue(caso.getEstadoCaso().getNomEstadoCaso());
                                sheet.setColumnHidden(j, true); break;
                        case 9: cellDato.setCellType(CellType.NUMERIC);
                                cellDato.setCellValue(caso.getFecCreacionCaso());
                                cellDato.setCellStyle(dateCellStyle);
                                sheet.setColumnHidden(j, true); break;
                        case 10: cellDato.setCellValue(caso.getCorrelativoCarta());
                                sheet.setColumnHidden(j, true); break;
                        case 11: cellDato.setCellValue(caso.getNroSuministro());
                                sheet.setColumnHidden(j, true); break;
                        case 12: cellDato.setCellValue("Correspondencia Digital");
                                sheet.setColumnHidden(j, true); break;
                        case 13: cellDato.setCellValue(caso.getCanalNotificacion().getNomCanalNotificacion());
                                sheet.setColumnHidden(j, true); break;
                        case 14: cellDato.setCellValue("Proveedor Mensajeria");
                                sheet.setColumnHidden(j, true); break;
                        case 15: cellDato.setCellValue(caso.getProvincia().getNomProvincia());
                                sheet.setColumnHidden(j, true); break;
                        case 16: cellDato.setCellValue(caso.getPrioridad().getNomPrioridad());
                                sheet.setColumnHidden(j, true); break;
                        case 17: cellDato.setCellValue(caso.getEstado().getNomEstado());
                                sheet.autoSizeColumn(j); break;
                        case 18: cellDato.setCellType(CellType.NUMERIC);
                                cellDato.setCellValue(caso.getFecCreacion());
                                cellDato.setCellStyle(dateCellStyle);
                                sheet.setColumnHidden(j, true); break;
                        case 19: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellStyle(dateCellStyle);
                            cellDato.setCellValue(caso.getFecEmisionDateTime());
                            sheet.setColumnWidth(j, 256 * 12); break;
                        case 20: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellStyle(dateCellStyle);
                            cellDato.setCellValue(caso.getFecDespacho());
                            sheet.setColumnWidth(j, 256 * 12); break;
                        case 21: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellStyle(dateCellStyle);
                            cellDato.setCellValue(caso.getFecNotificacion());
                            sheet.setColumnWidth(j, 256 * 12);
                            sheet.setColumnHidden(j, true); break;
                        case 22: cellDato.setCellValue(caso.getCorreosCartasString());
                            sheet.setColumnWidth(j, 256 * 38); break;
                        case 23: cellDato.setCellValue(caso.getCorreosActasString());
                            sheet.setColumnWidth(j, 256 * 38); break;
                        case 24: cellDato.setCellValue(caso.isErrorNroCarta());
                            if (caso.isErrorNroCarta()) {
                                cellDato.setCellStyle(trueCellStyle);
                            } break;
                        case 25: cellDato.setCellValue(caso.isErrorCorreoNotif());
                            if (caso.isErrorNroCarta()) {
                                cellDato.setCellStyle(trueCellStyle);
                            } break;
                        case 26: cellDato.setCellValue(caso.isErrorFechas());
                            if (caso.isErrorNroCarta()) {
                                cellDato.setCellStyle(trueCellStyle);
                            } break;
                        case 27: cellDato.setCellValue(caso.isErrorFaltaFirma());
                            if (caso.isErrorNroCarta()) {
                                cellDato.setCellStyle(trueCellStyle);
                            } break;
                        case 28: cellDato.setCellValue(caso.isErrorFaltaCartas());
                            if (caso.isErrorNroCarta()) {
                                cellDato.setCellStyle(trueCellStyle);
                            } break;
                        case 29: cellDato.setCellValue(caso.isErrorFaltaActas());
                            if (caso.isErrorNroCarta()) {
                                cellDato.setCellStyle(trueCellStyle);
                            } break;
                        case 30: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecNotificacionCarta());
                            cellDato.setCellStyle(dateTimeCellStyle);
                                sheet.setColumnHidden(j, true); break;
                        case 31: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecUltimaModificacion());
                            cellDato.setCellStyle(dateCellStyle);
                                sheet.setColumnHidden(j, true); break;
                        case 32: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecha());
                            cellDato.setCellStyle(dateCellStyle);
                                sheet.setColumnHidden(j, true); break;
                        case 33: cellDato.setCellType(CellType.NUMERIC);
                            cellDato.setCellValue(caso.getFecVencimientoLegal());
                            cellDato.setCellStyle(dateTimeCellStyle);
                                sheet.setColumnHidden(j, true); break;
                        case 34: cellDato.setCellValue(caso.getCreadoPor().getNomUsuario());
                                sheet.setColumnHidden(j, true); break;
                        case 35: cellDato.setCellValue(caso.getCanalRegistro().getNomCanalRegistro());
                                sheet.setColumnHidden(j, true); break;
                        case 36: cellDato.setCellValue(caso.getPropietarioCaso().getNomUsuario());
                                sheet.setColumnHidden(j, true); break;
                        case 37: cellDato.setCellValue(caso.getPropietarioCaso().getCodUsuario());
                                sheet.setColumnHidden(j, true); break;
                        case 38: cellDato.setCellType(CellType.NUMERIC);
                         cellDato.setCellValue(caso.getDiasVencidosPorVencer());
                                sheet.setColumnHidden(j, true); break;
                        case 39: cellDato.setCellValue("https://enelsud.my.salesforce.com/" + caso.getIdActividad());
                            sheet.setColumnHidden(j, false); break;
                        case 40: cellDato.setCellValue(caso.getMensajeError()); break;
//                        case 40: cellDato.setCellValue(getMensajeRechazo(caso)); break;
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
