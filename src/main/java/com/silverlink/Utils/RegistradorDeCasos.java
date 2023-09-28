package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

import static com.silverlink.Main.scanner;
import static com.silverlink.Main.tempPath;

public class RegistradorDeCasos {

//    String tempPath = "Z:\\Servicios ENEL\\002 - Correspondencia digital\\temp";
//    Scanner scanner = new Scanner(System.in);
    ArrayList<Caso> casos;
    Path excelFile;

    Walker johnnie = new Walker();

    public ArrayList<Caso> registrarCasos() {
        System.out.println("IMPORTANTE: ABRIR EL ARCHIVO DESCARGADO Y GUARDARLO COMO .XLSX. (Presiona ENTER para continuar)");
        scanner.nextLine();

        casos = new ArrayList<>();

        //Buscar archivo .xlsx a trabajar
        try {
            Files.walkFileTree(Path.of(tempPath), johnnie);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        leerCasosDesdeExcel(excelFile);
        //TODO Ingresar casos a la BD

        return casos;
    }

    //Abrir Excel, leer cada registro y almacenar en una lista
    public void leerCasosDesdeExcel(Path rutaArchivo) {
        //TODO obtener archivo de carpeta temp, mover archivo a carpeta de OS,
        // a su vez dentro de OTRA carpeta 'listas' con su nombre y correlativo

        try(FileInputStream fis = new FileInputStream(rutaArchivo.toFile());
            XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { //0-based row index; 0 es el encabezado
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
                        case 5: caso.setEstadoCaso(cell.getStringCellValue()); break;
                        case 6: caso.setFecCreacionCaso(cell.getLocalDateTimeCellValue().toLocalDate()); break;
                        case 7: caso.setCorrelativoCarta((short) cell.getNumericCellValue()); break;
                        case 8: caso.setNroSuministro(cell.getStringCellValue()); break; //Número almacenado como texto
                        case 10: caso.setCanalNotificacion(cell.getStringCellValue()); break;
                        case 12: caso.setProvincia(cell.getStringCellValue()); break;
                        case 13: caso.setPrioridad(cell.getStringCellValue()); break;
                        case 14: caso.setEstado(cell.getStringCellValue()); break;
                        case 15: caso.setFecCreacion(cell.getLocalDateTimeCellValue().toLocalDate()); break;
                        //16, 17, 18 son las fechas a obtener de los documentos a revisar.
                        case 19: caso.setFecNotificacionCarta(cell.getLocalDateTimeCellValue()); break;
                        case 20: caso.setFecUltimaModificacion(cell.getLocalDateTimeCellValue().toLocalDate()); break;
                        case 21: caso.setFecha(cell.getLocalDateTimeCellValue().toLocalDate()); break;
                        case 22: caso.setFecVencimientoLegal(cell.getLocalDateTimeCellValue().toLocalDate()); break;
//                        case 20: caso.setCreadoPor(cell.getStringCellValue()); break;
                        case 24: caso.setCanalRegistro(cell.getStringCellValue()); break;
                        case 26: caso.setPropietarioCaso(cell.getStringCellValue(),
                                                        row.getCell(25).getStringCellValue());
                                caso.setCreadoPor(row.getCell(23).getStringCellValue()); break;
                        case 27: caso.setDiasVencidosPorVencer((short) cell.getNumericCellValue()); break;
                    }
                }
                System.out.println(i + ". " + caso.getIdActividad() + " | " + caso.getNroCaso());
                casos.add(caso);
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    class Walker extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            //TODO Encontrar archivo excel y moverlo a la carpeta de la OS
            if(file.toString().endsWith(".xlsx")){
                excelFile = file;
            }
            return super.visitFile(file, attrs);
        }
    }
}
