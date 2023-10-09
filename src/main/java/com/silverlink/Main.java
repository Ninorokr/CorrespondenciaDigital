package com.silverlink;

import com.silverlink.Entidades.*;
import com.silverlink.Utils.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static com.silverlink.Utils.ProcesadorDatos.encarpetarArchivos;
import static com.silverlink.Utils.ProcesadorDatos.isArchivosCompletos;
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
    public static final String tempPath = rootFolder + "Temp\\";
    public static Scanner scanner = new Scanner(System.in);
    public static String nuevaCarpeta;
    public static Navegador nav;

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
        while(true){
            System.out.println("1. Descargar casos nuevos\n" +
                    "2. Procesar casos pendientes\n" +
                    "3. Descargar casos en Salesforce\n" +
                    "9. Salir");

            switch (scanner.nextInt()) {
                case 1: descargarCasosNuevos(); break;
                case 2: procesarCasosPendientes(); break;
                case 9: System.exit(0);
            }
        }
    }

    // Menú op. 1
    public static void descargarCasosNuevos() {
        //TODO 1. Si el Chromedriver ya se encuentra abierto, utilizar la instancia actual
        //TODO 2. En caso haya fallado la descarga, ofrecer skippear la descarga automatica
        //TODO y leer un archivo descargado manualmente

        scanner.nextLine(); //Line handler
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
        } catch (IOException ioe) {
            System.out.println("No se pudo crear la carpeta en " + nuevaCarpeta);
            return;
        }

        //1d. Descarga el listado de casos
        nav = new Navegador();
        if(!isDriverOpen) {
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

    public static void procesarCasosPendientes() {
        //Jalar casos pendientes de la BD (solo los datos relevantes)
        ArrayList<Caso> casos = queryCasosPendientes();

        //Recorrer caso por caso, abrir página en salesforce y descargar los archivos
        for (int i = 0; i < casos.size(); i++) {
            Caso caso = casos.get(i);

            if(caso.isArchivosDescargados()) //Si ya se descargaron previamente los archivos, continuar con el sgte.
                continue;

            //Si el navegador está cerrado, abrir nueva instancia y sesión en Salesforce
            if (nav == null) {
                nav = new Navegador();
                nav.abrirSesionSalesforce();
            }

            int cantArchivos = nav.descargarArchivosCaso(caso.getIdActividad());
            try {
                while(true) {
                    if(isArchivosCompletos(cantArchivos)) {
                        break;
                    }
//                    try {Thread.sleep(5000);} catch (InterruptedException ie) {}
                }
                encarpetarArchivos(caso, cantArchivos);
                caso.setArchivosDescargados(true); //Marcar archivos como descargados
                Commander.updateArchivosDescargados(caso);

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }

        //TODO agregar constraint DEFAULT a campos descargadoEnSalesforce y isArchivosDescargados en la BD
        //TODO preguntar por la característica única de los idActividad

        //TODO Cargar los casos pendientes de la BD y almacenarlos en una lista

    }

}
