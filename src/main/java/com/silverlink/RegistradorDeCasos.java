package com.silverlink;

import com.silverlink.Entidades.Caso;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RegistradorDeCasos {
    //Abrir Excel, leer cada registro y almacenar en una lista
    public static void registrarCasos() {
        //TODO obtener archivo de carpeta temp, mover archivo a carpeta de OS,
        // a su vez dentro de OTRA carpeta 'listas' con su nombre y correlativo

        try(FileInputStream fis = new FileInputStream("Z:\\Servicios ENEL\\002 - Correspondencia digital\\" +
                "temp\\report1694036567856.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheetAt(0);

            //TODO si dice "despachada" ignorar el caso

            ArrayList<Caso> casos = new ArrayList<>();

            for (int i = 1; i < sheet.getLastRowNum(); i++) { //0-based row index; 0 es el encabezado
                XSSFRow row = sheet.getRow(i);

                //Si estado (14) es "despachada", ignorar el caso
                if(row.getCell(14).getStringCellValue().equals("Despachada")){
                    continue;
                }

                Caso caso = new Caso();
                for (int j = 0; j < row.getLastCellNum(); j++) { //0-based cell index; 0 es la primera columna
                    XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch(j) {
                        case 0: caso.setTipoAtencion(cell.getStringCellValue()); break;
                        case 1: caso.setTipoRegCaso(cell.getStringCellValue()); break;
                        case 2: caso.setIdActividad(cell.getStringCellValue()); break;
                        case 3: caso.setTipoCarta(cell.getStringCellValue()); break;
                        case 4: caso.setNroCaso(cell.getStringCellValue()); break; //Número almacenado como texto
                        case 5: caso.setFecCreacionCaso(cell.getDateCellValue()); break;
                        case 6: caso.setCorrelativoCarta((short) cell.getNumericCellValue()); break;
                        case 7: caso.setNroSuministro(cell.getStringCellValue()); break; //Número almacenado como texto
                        case 8: caso.setCanalNotificacion(cell.getStringCellValue()) break;
                        case 9: caso.setProvincia(cell.getStringCellValue()); break;
                        case 10: caso.setPrioridad(cell.getStringCellValue()); break;
                        case 11: caso.setEstado(cell.getStringCellValue()); break;
                        case 12: caso.setFecCreacion(cell.getDateCellValue()); break;
                        //13, 14, 15 son las fechas a obtener de los documentos a revisar.
                        case 16: caso.setFecNotificacionCarta(cell.getDateCellValue()); break;
                        case 17: caso.setFecUltimaModificacion(cell.getDateCellValue()); break;
                        case 18: caso.setFecha(cell.getDateCellValue()); break;
                        case 19: caso.setFecVencimientoLegal(cell.getDateCellValue()); break;
//                        case 20: caso.setCreadoPor(cell.getStringCellValue()); break;
                        case 21: caso.setCanalRegistro(cell.getStringCellValue()); break;
                        case 23: caso.setPropietarioCaso(cell.getStringCellValue(),
                                                        row.getCell(22).getStringCellValue());
                                caso.setCreadoPor(row.getCell(20).getStringCellValue()); break;
                        case 24: caso.setDiasVencidosPorVencer((short) cell.getNumericCellValue()); break;

                    }
                }
            }


//
//
//
//            System.out.println(row.getCell(0).getStringCellValue());
//            System.out.println(row.getCell(1).getStringCellValue());
//            System.out.println(row.getCell(2).getStringCellValue());
//            System.out.println(row.getCell(3).getStringCellValue());
//            System.out.println(row.getCell(4).getStringCellValue());
//            System.out.println(row.getCell(5).getStringCellValue());
//            System.out.println(row.getCell(6).getDateCellValue());
//            System.out.println(row.getCell(7).getNumericCellValue());
//            System.out.println(row.getCell(8).getNumericCellValue());
//            System.out.println(row.getCell(9).getStringCellValue());
//            System.out.println(row.getCell(10).getStringCellValue());
//            System.out.println(row.getCell(11).getStringCellValue());
//            System.out.println(row.getCell(12).getStringCellValue());
//            System.out.println(row.getCell(13).getStringCellValue());
//            System.out.println(row.getCell(14).getStringCellValue());
//            System.out.println(row.getCell(15).getDateCellValue());
//            System.out.println(row.getCell(16).getDateCellValue());
//            System.out.println(row.getCell(17).getDateCellValue());
//            System.out.println(row.getCell(18).getDateCellValue());
//            System.out.println(row.getCell(19).getDateCellValue());
//            System.out.println(row.getCell(20).getDateCellValue());
//            System.out.println(row.getCell(21).getDateCellValue());
//            System.out.println(row.getCell(22).getDateCellValue());
//            System.out.println(row.getCell(23).getStringCellValue());
//            System.out.println(row.getCell(24).getStringCellValue());
//            System.out.println(row.getCell(25).getStringCellValue());
//            System.out.println(row.getCell(26).getStringCellValue());
//            System.out.println(row.getCell(27).getNumericCellValue());


        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
