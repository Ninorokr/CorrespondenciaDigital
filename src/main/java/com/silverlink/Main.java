package com.silverlink;

import com.silverlink.Entidades.*;
import com.silverlink.Utils.Datasource;
import com.silverlink.Utils.Navegador;
import com.silverlink.Utils.ProcesadorDatos;
import com.silverlink.Utils.RegistradorDeCasos;


import java.util.ArrayList;
import java.util.Scanner;


import static com.silverlink.Utils.Querier.*;

public class Main {

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

    public static final String tempPath = "Z:\\Servicios ENEL\\002 - Correspondencia digital\\temp";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        imprimirMenuPrincipal();

        Navegador nav = new Navegador();
        nav.abrirSesionSalesforce();
        nav.descargarReporte();
        nav.rechazarLightningExp();

        ArrayList<Caso> casos = new RegistradorDeCasos().registrarCasos();


        ProcesadorDatos pro = new ProcesadorDatos();
        pro.descargarYEncarpetarCasos(casos);
        pro.recolectarDatosDeArchivos(casos);

    }

    private static void imprimirMenuPrincipal() {
        //1. Elimina "all" lo que existe en la carpeta "Temp"
        //Crea una carpeta con el nro. de OS
        //Descarga el listado de casos y los registra en estado "Pendiente" a la BD.
        //Todos los casos se registran con un nro. de OS y con estado 1 - "Pendiente"
        //Después guarda el archivo .xls original en la carpeta de la OS

        //2a. Debe consultar todos los casos "pendientes" en la BD y almacenarlos en un ArrayList
        //Descarga y encarpeta los casos en la ruta de la OS que le corresponde. (consulta el campo OS)
        //Por ej.: "Z:\Servicios ENEL\002 - Correspondencia digital\23\00XX
        //TODO agregar campo "fecha de descarga" a la tabla de casos en la BD y al objeto Caso DEFAULT = fecha actual
        //TODO "fecha de descarga" debe ser exactamente igual a la fecha en que se descargó el caso en Salesforce

        //2b. Recolectar todos los datos de los documentos de cada caso
        System.out.println("1. Descargar listado de casos\n" +
                            "2. Procesar casos pendientes\n");
    }

}
