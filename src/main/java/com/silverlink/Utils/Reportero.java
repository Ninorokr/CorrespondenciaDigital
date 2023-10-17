package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;

public class Reportero {

    public void exportarCasosPorOS(ArrayList<Caso> casos) {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet();
            XSSFRow encabezado = sheet.createRow(0);

            for(Caso caso : casos) {

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
