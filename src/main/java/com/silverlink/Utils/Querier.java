package com.silverlink.Utils;

import com.silverlink.Entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.silverlink.Utils.Datasource.conn;

public class Querier {

    public static ArrayList<CanalNotificacion> queryCanalesNotificacion(){
        String canalesNotificacionQuery = "SELECT [idCanalNotificacion], [nomCanalNotificacion] FROM [TEST].[digi].[canalNotificacion]";
        ArrayList<CanalNotificacion> canalesNotificacion = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(canalesNotificacionQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                canalesNotificacion.add(new CanalNotificacion(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los canales de notificación");
            sqle.printStackTrace();
        }
        return canalesNotificacion;
    }

    public static ArrayList<CanalRegistro> queryCanalesRegistro(){
        String canalesRegistroQuery = "SELECT [idCanalRegistro], [nomCanalRegistro] FROM [TEST].[digi].[canalRegistro]";
        ArrayList<CanalRegistro> canalesRegistro = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(canalesRegistroQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                canalesRegistro.add(new CanalRegistro(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los canales de registro");
            sqle.printStackTrace();
        }
        return canalesRegistro;
    }

    public static ArrayList<Estado> queryEstados(){
        String estadoQuery = "SELECT [idEstado], [nomEstado] FROM [TEST].[digi].[estado]";
        ArrayList<Estado> estados = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(estadoQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                estados.add(new Estado(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los estados");
            sqle.printStackTrace();
        }
        return estados;
    }

    public static ArrayList<EstadoCaso> queryEstadosCaso(){
        String estadoCasoQuery = "SELECT [idEstadoCaso], [nomEstadoCaso] FROM [TEST].[digi].[estadoCaso]";
        ArrayList<EstadoCaso> estadosCaso = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(estadoCasoQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                estadosCaso.add(new EstadoCaso(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los estados de caso");
            sqle.printStackTrace();
        }
        return estadosCaso;
    }

    public static ArrayList<Prioridad> queryPrioridades(){
        String prioridadQuery = "SELECT [idPrioridad], [nomPrioridad] FROM [TEST].[digi].[prioridad]";
        ArrayList<Prioridad> prioridades = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(prioridadQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                prioridades.add(new Prioridad(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar las prioridades");
            sqle.printStackTrace();
        }
        return prioridades;
    }

    public static ArrayList<Provincia> queryProvincias(){
        String prioridadQuery = "SELECT [idProvincia], [nomProvincia] FROM [TEST].[digi].[provincia]";
        ArrayList<Provincia> provincias = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(prioridadQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                provincias.add(new Provincia(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar las provincias");
            sqle.printStackTrace();
        }
        return provincias;
    }

    public static ArrayList<TipoAtencion> queryTiposAtencion(){
        String tipoAtencionQuery = "SELECT [idTipoAtencion], [nomTipoAtencion] FROM [TEST].[digi].[tipoAtencion]";
        ArrayList<TipoAtencion> tiposAtencion = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(tipoAtencionQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tiposAtencion.add(new TipoAtencion(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los tipos de atención");
            sqle.printStackTrace();
        }
        return tiposAtencion;
    }

    public static ArrayList<TipoCarta> queryTiposCarta(){
        String tipoCartaQuery = "SELECT [idTipoCarta], [nomTipoCarta] FROM [TEST].[digi].[tipoCarta]";
        ArrayList<TipoCarta> tiposCarta = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(tipoCartaQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tiposCarta.add(new TipoCarta(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los tipos de carta");
            sqle.printStackTrace();
        }
        return tiposCarta;
    }

    public static ArrayList<TipoRegCaso> queryTiposRegCaso(){
        String tipoRegCasoQuery = "SELECT [idTipoRegCaso], [nomTipoRegCaso] FROM [TEST].[digi].[tipoRegCaso]";
        ArrayList<TipoRegCaso> tiposRegCaso = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(tipoRegCasoQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tiposRegCaso.add(new TipoRegCaso(rs.getShort(1), rs.getString(2)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar los tipos de Registro de caso");
            sqle.printStackTrace();
        }
        return tiposRegCaso;
    }

    public static ArrayList<Usuario> queryUsuarios(){
        String usuarioQuery = "SELECT [idUsuario], [codUsuario], [nomUsuario] FROM [TEST].[digi].[usuario]";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(usuarioQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getShort(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar a los usuarios");
            sqle.printStackTrace();
        }
        return usuarios;
    }

    public static int queryUltNroOS(int anio) {
        String ultimoNroOSQuery = "SELECT MAX(nroOS) FROM [digi].[casosCorrespondenciaDigital] GROUP BY anio HAVING anio = ?";

        try (PreparedStatement ps = conn.prepareStatement(ultimoNroOSQuery)) {
            ps.setInt(1, anio);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getShort(1);
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar el último número de OS");
        }
        return 0;
    }

    public static ArrayList<String> queryIdCasos() {
        String idCasosQuery = "SELECT idActividad FROM [digi].[casosCorrespondenciaDigital]";
        ArrayList<String> ids = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(idCasosQuery)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ids.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudieron consultar los números de id.");
        }
        return ids;
    }

    public static ArrayList<Caso> queryCasosPendientes() {
        String casosPendientesQuery = "SELECT anio, nroOS, idCasoCorrespondenciaDigital, idActividad, nroCaso, " +
                "isArchivosDescargados FROM [digi].[casosCorrespondenciaDigital] " +
                "WHERE idEstado = 1";
        ArrayList<Caso> casosPendientes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(casosPendientesQuery)) {
            ResultSet rs = ps.executeQuery();
            Caso caso;
            while(rs.next()){
                caso = new Caso();
                caso.setAnio(rs.getShort(1));
                caso.setNroOS(rs.getShort(2));
                caso.setIdCorrelativoCaso(rs.getShort(3));
                caso.setIdActividad(rs.getString(4));
                caso.setNroCaso(rs.getInt(5));
                caso.setArchivosDescargados(rs.getBoolean(6));
                casosPendientes.add(caso);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudieron consultar los ids y nros. de los casos");
        }
        return casosPendientes;
    }


}
