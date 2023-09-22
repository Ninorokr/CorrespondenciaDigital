package com.silverlink;

import com.silverlink.Entidades.*;
import com.silverlink.Utils.Datasource;
import com.silverlink.Utils.Navegador;


import java.util.ArrayList;


import static com.silverlink.Querier.*;

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

    public static void main(String[] args) throws InterruptedException {

        new Navegador().abrirSesion();
//        new Navigator().descargarReporte();
        ArrayList<Caso> casos = new RegistradorDeCasos().registrarCasos();
        new ProcesadorDatos().procesarCasos(casos);

    }
}
