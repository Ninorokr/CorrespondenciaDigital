package com.silverlink;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class RegistradorDeCasos {
    //Abrir Excel, leer cada registro y almacenar en una lista
    public static void abrirExcel() {
        //TODO obtener archivo de carpeta temp, mover archivo a carpeta de OS,
        // a su vez dentro de OTRA carpeta 'listas' con su nombre y correlativo

        try(FileInputStream fis = new FileInputStream("Z:\\Servicios ENEL\\002 - Correspondencia digital\\" +
                "temp\\report1694036567856.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFRow row = sheet.getRow(1);
            System.out.println(row.getCell(0).getStringCellValue());
            System.out.println(row.getCell(1).getStringCellValue());
            System.out.println(row.getCell(2).getStringCellValue());
            System.out.println(row.getCell(3).getStringCellValue());
            System.out.println(row.getCell(4).getStringCellValue());
            System.out.println(row.getCell(5).getStringCellValue());
            System.out.println(row.getCell(6).getDateCellValue());
            System.out.println(row.getCell(7).getNumericCellValue());
            System.out.println(row.getCell(8).getNumericCellValue());
            System.out.println(row.getCell(9).getStringCellValue());
            System.out.println(row.getCell(10).getStringCellValue());
            System.out.println(row.getCell(11).getStringCellValue());
            System.out.println(row.getCell(12).getStringCellValue());
            System.out.println(row.getCell(13).getStringCellValue());
            System.out.println(row.getCell(14).getStringCellValue());
            System.out.println(row.getCell(15).getDateCellValue());
            System.out.println(row.getCell(16).getDateCellValue());
            System.out.println(row.getCell(17).getDateCellValue());
            System.out.println(row.getCell(18).getDateCellValue());
            System.out.println(row.getCell(19).getDateCellValue());
            System.out.println(row.getCell(20).getDateCellValue());
            System.out.println(row.getCell(21).getDateCellValue());
            System.out.println(row.getCell(22).getDateCellValue());
            System.out.println(row.getCell(23).getStringCellValue());
            System.out.println(row.getCell(24).getStringCellValue());
            System.out.println(row.getCell(25).getStringCellValue());
            System.out.println(row.getCell(26).getStringCellValue());
            System.out.println(row.getCell(27).getNumericCellValue());



//            for(int i = 1; true; i++) {
//
//            }


        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
