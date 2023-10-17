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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.silverlink.Main.*;
import static com.silverlink.Utils.Commander.insertCasoABD;
import static com.silverlink.Utils.Querier.queryIdCasos;

public class RegistradorDeCasos {

    Path excelFile;
    Walker johnnie = new Walker();

    public RegistradorDeCasos(String ruta) {
        this.excelFile = Path.of(ruta);
    }

    public void registrarCasos(int anio, int nroOS) {
        System.out.println("IMPORTANTE: ABRIR EL ARCHIVO DESCARGADO Y GUARDARLO COMO .XLSX. (Presiona ENTER para continuar)");
        scanner.nextLine();

//        Buscar archivo .xlsx a trabajar
        try {
            Files.walkFileTree(excelFile, johnnie);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        ArrayList<Caso> casos = leerCasosDesdeExcel(excelFile.toString(), anio, nroOS);
        ArrayList<String> idsCasosYaExistentes = queryIdCasos();
        int i = 1;

        //Ingresar casos a la BD descartando los casos duplicados primero
        for (Caso caso : casos) {
            if(!idsCasosYaExistentes.contains(caso.getIdActividad())) {
                caso.setIdCaso((short) i);
                insertCasoABD(caso);
                i++;
            } else {
                System.out.println("Ya existe el caso de id.: " + caso.getIdActividad());
            }
        }
    }

    //Abrir Excel, leer cada registro y almacenar en una lista
    public ArrayList<Caso> leerCasosDesdeExcel(String rutaArchivo, int anio, int nroOS) {
        ArrayList<Caso> casos = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(rutaArchivo);
            XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { //0-based row index; 0 es el encabezado
                XSSFRow row = sheet.getRow(i);

                //Si estado (14) es "despachada", ignorar el caso
                if(row.getCell(14).getStringCellValue().equals("Despachada")){
                    continue;
                }

                Caso caso = new Caso();
                caso.setAnio((short) anio);
                caso.setNroOS((short) nroOS);

                for (int j = 0; j < row.getLastCellNum(); j++) { //0-based cell index; 0 es la primera columna
                    XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch(j) {
                        case 0: caso.setTipoAtencion(cell.getStringCellValue()); break;
                        case 1: caso.setTipoRegCaso(cell.getStringCellValue()); break;
                        case 2: caso.setIdActividad(cell.getStringCellValue()); break;
                        case 3: caso.setTipoCarta(cell.getStringCellValue()); break;
                        case 4: caso.setNroCaso(cell.getStringCellValue()); break; //Número almacenado como texto
                        case 5: caso.setEstadoCaso(cell.getStringCellValue()); break;
                        case 6: caso.setFecCreacionCaso(getLocalDate(cell.getLocalDateTimeCellValue())); break;
                        case 7: caso.setCorrelativoCarta((short) cell.getNumericCellValue()); break;
                        case 8: caso.setNroSuministro(cell.getStringCellValue()); break; //Número almacenado como texto
                        case 10: caso.setCanalNotificacion(cell.getStringCellValue()); break;
                        case 12: caso.setProvincia(cell.getStringCellValue()); break;
                        case 13: caso.setPrioridad(cell.getStringCellValue()); break;
                        case 14: caso.setEstado(cell.getStringCellValue()); break;
                        case 15: caso.setFecCreacion(getLocalDate(cell.getLocalDateTimeCellValue())); break;
                        //16, 17, 18 son las fechas a obtener de los documentos a revisar.
                        case 19: caso.setFecNotificacionCarta(cell.getLocalDateTimeCellValue()); break;
                        case 20: caso.setFecUltimaModificacion(getLocalDate(cell.getLocalDateTimeCellValue())); break;
                        case 21: caso.setFecha(getLocalDate(cell.getLocalDateTimeCellValue())); break;
                        case 22: caso.setFecVencimientoLegal(cell.getLocalDateTimeCellValue()); break;
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
        return casos;
    }

    private LocalDate getLocalDate(LocalDateTime datetime) {
        if (datetime == null)
            return null;
        return datetime.toLocalDate();
    }

    class Walker extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(file.toString().endsWith(".xlsx")){
                excelFile = file;
            }
            return super.visitFile(file, attrs);
        }
    }
}
