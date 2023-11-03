package com.silverlink;

import com.silverlink.Entidades.*;
import com.silverlink.Utils.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static com.silverlink.Utils.Commander.*;
import static com.silverlink.Utils.ProcesadorDatos.*;
import static com.silverlink.Utils.Querier.*;

public class Main {

    public static boolean isDriverOpen = false;
    public static boolean listadoEncarpetado = false;

    static {
        Datasource.open();
    }

    public static ArrayList<CanalNotificacion> canalesNotificacion = queryCanalesNotificacion();
    public static ArrayList<CanalRegistro> canalesRegistro = queryCanalesRegistro();
    public static ArrayList<Estado> estados = queryEstados();
    public static ArrayList<EstadoCaso> estadosCaso = queryEstadosCaso();
    public static ArrayList<Prioridad> prioridades = queryPrioridades();
    public static ArrayList<Provincia> provincias = queryProvincias();
    public static ArrayList<TipoAtencion> tiposAtencion = queryTiposAtencion();
    public static ArrayList<TipoCarta> tiposCarta = queryTiposCarta();
    public static ArrayList<TipoRegCaso> tiposRegCaso = queryTiposRegCaso();
    public static ArrayList<Usuario> usuarios = queryUsuarios();

    public static final String rootFolder = "Z:\\Servicios ENEL\\002 - Correspondencia digital\\";
//    public static final String rootFolder = "D:\\Servicios ENEL\\002 - Correspondencia digital\\";
    public static final String tempPath = rootFolder + "Temp\\";
    public static Scanner scanner = new Scanner(System.in);
    public static String nuevaCarpeta;
    public static Navegador nav;

    public static LocalDateTime fechaYHoraActual = LocalDateTime.now();

    public static void main(String[] args) throws InterruptedException {

        imprimirMenuPrincipal();

//        Navegador nav = new Navegador();
//        nav.abrirSesionSalesforce();
//        nav.descargarReporte();
//        nav.rechazarLightningExp();
//
//        ArrayList<Caso> casos = new RegistradorDeCasos().registrarCasos();
//
//        ProcesadorDatos pro = new ProcesadorDatos();
//        pro.descargarYEncarpetarCasos(casos);
//        pro.recolectarDatosDeArchivos(casos);

    }

    //Menú
    private static void imprimirMenuPrincipal() {
        //1a. Elimina "all" lo que existe en la carpeta "Temp"
        //1b. Crea una carpeta con el nro. de OS
        //1c. Después guarda el archivo .xls original en la carpeta de la OS
        //1d. Descarga el listado de casos y los registra en estado "Pendiente" a la BD.
        //Consultar y descartar "id. de actividad" duplicados
        //Todos los casos se registran con un nro. de OS y con estado 1 - "Pendiente"
        //HECHO

        //2a. Debe consultar todos los casos "pendientes" en la BD y almacenarlos en un ArrayList
        //Descarga y encarpeta los casos en la ruta de la OS que le corresponde. (consulta el campo OS)
        //Si archivo ya existe, chancar el existente
        //Por ej.: "Z:\Servicios ENEL\002 - Correspondencia digital\23\00XX

        //2b. Recolectar todos los datos de los documentos de cada caso
        //Validar que los datos coincidan
        //Si coinciden cambiar a estado 4 - "Descargada"
        //Si no coinciden, marcar flag del dato que no coincide, cambiar a estado 5 - "Rechazada"
        //Colocar 'falso' en el flag "descargado en salesforce"

        //3. Navega caso por caso en Salesforce y descarga según los datos recogidos.
        //

        //TODO Implementar una opción para cambiar un dato de un caso que aún no haya sido descargado en Salesforce
        while(true){
            System.out.println("1. Descargar casos nuevos\n" +
                    "2. Procesar casos pendientes\n" +
                    "3. Exportar casos pendientes de descarga en Salesforce\n" +
                    "9431. Descargar casos en Salesforce\n" +
                    "9. Salir");

            switch (scanner.nextInt()) {
                case 1: descargarCasosNuevos(); break;
                case 2: procesarCasosPendientes(); break;
                case 3: revisarCasosPendientesDescarga(); break;
                case 9431: descargarCasosEnSalesforce(); break;
                case 4: probarMensajeDeError(); break;
                case 9: System.exit(0);
            }
        }
    }

    // Menú op. 1

    private static void descargarCasosNuevos() {
        scanner.nextLine(); //Line handler
        boolean flag = true;
        while(flag) {
            System.out.println("¿Descargar reporte de casos automáticamente? (s/n)");
            switch (scanner.nextLine().toLowerCase()) {
                case "s": descargarCasosNuevosAutomaticamente(); flag = false; break;
                case "n": descargarCasosNuevosManualmente(); flag = false; break;
            }
        }
    }

    private static void descargarCasosNuevosAutomaticamente() {
        //1. Si el Chromedriver ya se encuentra abierto, utilizar la instancia actual

        //Elimina "all" en la carpeta "Temp" antes de empezar
        try {
            Files.walkFileTree(Path.of(tempPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException ioe) {
            System.out.println("No se pudo acceder a la carpeta Temp");
            return;
        }

        //Consultar cual sería el nuevo número de OS
        int anio = LocalDateTime.now().getYear()-2000;
        int nroOS = Querier.queryUltNroOS(anio) + 1;

        //Crea una nueva carpeta con el nro. de OS que corresponde
        nuevaCarpeta = rootFolder + anio + "\\" + String.format("%04d", nroOS);
        try {
            Files.createDirectories(Path.of(nuevaCarpeta));
            registrarNuevaOS(anio, nroOS);
        } catch (IOException ioe) {
            System.out.println("No se pudo crear la carpeta en " + nuevaCarpeta);
            return;
        }

        //1d. Descarga el listado de casos
        if(!isDriverOpen) {
            nav = new Navegador();
            nav.abrirSesionSalesforce();
        }
        nav.descargarReporte();

        listadoEncarpetado = false;
        //Enviar el archivo de excel a la carpeta con el nro. de OS que le corresponde
        while(true){
            try { Thread.sleep(3000); } catch (InterruptedException ie) {}
            try {
                Files.walkFileTree(Path.of(tempPath), new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if(file.toString().endsWith(".xls")){
                            listadoEncarpetado = true;
                            Files.move(file, Path.of(nuevaCarpeta + "\\" + file.getName(file.getNameCount()-1)));
                        }
                        return super.visitFile(file, attrs);
                    }
                });
                if(listadoEncarpetado)
                    break;
            } catch (IOException ioe) {
                System.out.println("No se pudo acceder a la carpeta Temp");
                System.out.println(ioe.getMessage());
                ioe.printStackTrace();
            }
        }

        //Consultar y descartar "id. de actividad" duplicados
//        ArrayList<String> idCasos = queryIdCasos();
        RegistradorDeCasos registrador = new RegistradorDeCasos(nuevaCarpeta);
        registrador.registrarCasos(anio, nroOS);
        //1d. Descarga el listado de casos y los registra en estado "Pendiente" a la BD.
        //Todos los casos se registran con un nro. de OS y con estado 1 - "Pendiente"

    }

    private static void descargarCasosNuevosManualmente() {

        try {
            Files.walkFileTree(Path.of(tempPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException ioe) {
            System.out.println("No se pudo acceder a la carpeta Temp");
            return;
        }

        //Consultar cual sería el nuevo número de OS
        int anio = LocalDateTime.now().getYear()-2000;
        int nroOS = Querier.queryUltNroOS(anio) + 1;

        //Crea una nueva carpeta con el nro. de OS que corresponde
        nuevaCarpeta = rootFolder + anio + "\\" + String.format("%04d", nroOS);
        try {
            Files.createDirectories(Path.of(nuevaCarpeta));
            registrarNuevaOS(anio, nroOS);
        } catch (IOException ioe) {
            System.out.println("No se pudo crear la carpeta en " + nuevaCarpeta);
            return;
        }

        RegistradorDeCasos registrador = new RegistradorDeCasos(nuevaCarpeta);
        registrador.registrarCasos(anio, nroOS);
    }

    //Menú op. 2
    private static void procesarCasosPendientes() {
        //Jalar casos pendientes de la BD (solo los datos relevantes)
        ArrayList<Caso> casos = queryCasosNuevos();
//        ArrayList<Caso> casos = queryCasosNuevos();
        boolean isTempFolderEmpty = true;

        //Recorrer caso por caso, abrir página en salesforce y descargar los archivos
        for (Caso caso : casos) {
            String anio = String.valueOf(caso.getAnio());
            String nroOS = String.format("%04d", caso.getNroOS());
            String item = String.format("%04d", caso.getIdCaso());
            String itemPath = rootFolder + anio + "\\" + nroOS + "\\" + item;
            //Si la carpeta existe, pasar al siguiente caso (continue)
            if (Files.exists(Path.of(itemPath))) {
                continue;
            }

            //Si el navegador está cerrado, abrir nueva instancia y sesión en Salesforce
            if (nav == null) {
                nav = new Navegador();
                nav.abrirSesionSalesforce();
            }
//            File folder = new File(tempPath);
//            while (!isTempFolderEmpty) {
//                if (folder.list().length == 0) {
//                    isTempFolderEmpty = true;
//                }
//            }

            ArrayList<String> nomsArchivos = nav.descargarArchivosCasoPreciso(caso);

            for(String nomArchivo : nomsArchivos) {
                String rutaArchivo = tempPath + nomArchivo;
                Path rutaArchivoPath = Path.of(rutaArchivo);
                while(!Files.exists(rutaArchivoPath)) {
                }
                try {
                    Files.createDirectories(Path.of(itemPath));
                    Files.move(rutaArchivoPath, Path.of(itemPath + "\\" + nomArchivo));
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            isTempFolderEmpty = false;
                caso.setArchivosDescargados(true); //Marcar archivos como descargados
                Commander.setArchivosDescargadosToTrue(caso);

        }

        ProcesadorDatos pro = new ProcesadorDatos();
        pro.recolectarYVerificarDatos(queryCasosNuevos());

    }

    //Menú op. 3
    private static void revisarCasosPendientesDescarga() {
        Reportero reportero = new Reportero();
        //Exporta los casos verificados y pendientes de descarga en salesforce
        String outputPath = reportero.exportarCasosPorOS(queryCasosPendientesDescargaSalesforce());
//        String outputPath = reportero.exportarCasosPorOS(queryCasosPendientesDescargaSalesforce());
        System.out.println("REVISAR y CORREGIR el archivo de Excel antes de continuar (Presionar ENTER para continuar)");
        scanner.nextLine();
        //Captura los casos revisados del excel y los actualiza en la BD
//        ArrayList<Caso> casosRevisadosPorDescargar = procesarCasosRevisados(outputPath);
        for(Caso caso : procesarCasosRevisados(outputPath)) {
            updateCasosRevisados(caso);
        }
        System.out.println("Se actualizaron los casos revisados.\n");
    }

    //Menú op. 9431

    private static void descargarCasosEnSalesforce() {
        System.out.println("¿Estás seguro que deseas descargar los casos revisados? (S/N)");
        boolean flag = true;
        while(flag) {
            switch(scanner.nextLine().toLowerCase()) {
                case "s": flag = false; break;
                case "n": System.exit(0);
            }
        }

        //Obtener todos los casos revisadosEnSalesforce y descargar uno por uno en Salesforce
        for(Caso caso : queryCasosPorDescargarSalesforce()) {
            if (nav == null) {
                nav = new Navegador();
                nav.abrirSesionSalesforce();
            }
            nav.descargarCasoEnSalesforce(caso);
            System.out.println("Se descargó el caso: " + caso.getIdCaso() + ". " + caso.getIdActividad() + " | " + caso.getNroCaso());
        }
    }

    private static void probarMensajeDeError() {
        ArrayList<Caso> casosRechazados = queryAllCasosRechazados();

        for (Caso caso : casosRechazados) {
            System.out.println(createMensajeRechazo(caso));
            System.out.println(caso.getFormattedId() + ": " + caso.getMensajeError());
        }
    }

}
