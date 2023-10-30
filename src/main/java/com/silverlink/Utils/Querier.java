package com.silverlink.Utils;

import com.silverlink.Entidades.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                "idEstado, errorNroCarta, errorCorreoNotif, errorFechas, isArchivosDescargados, errorFaltaCarta, " +
                "errorFaltaActa " +
                "FROM [digi].[casosCorrespondenciaDigital] " +
                "WHERE idEstado = 1";
        ArrayList<Caso> casosPendientes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(casosPendientesQuery)) {
            ResultSet rs = ps.executeQuery();
            Caso caso;
            while(rs.next()){
                caso = new Caso();
                caso.setAnio(rs.getShort(1));
                caso.setNroOS(rs.getShort(2));
                caso.setIdCaso(rs.getShort(3));
                caso.setIdActividad(rs.getString(4));
                caso.setNroCaso(rs.getInt(5));
                caso.setEstado(new Estado(rs.getShort(6)));
                caso.setErrorNroCarta(rs.getBoolean(7));
                caso.setErrorCorreoNotif(rs.getBoolean(8));
                caso.setErrorFechas(rs.getBoolean(9));
                caso.setArchivosDescargados(rs.getBoolean(10));
                caso.setErrorFaltaCartas(rs.getBoolean(11));
                caso.setErrorFaltaActas(rs.getBoolean(12));
                casosPendientes.add(caso);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudieron consultar los ids y nros. de los casos");
        }
        return casosPendientes;
    }

    public static ArrayList<Caso> queryCasosNuevos() {
        String casosPendientesQuery = "SELECT anio, nroOS, idCasoCorrespondenciaDigital, idActividad, nroCaso, " +
                "idEstado, errorNroCarta, errorCorreoNotif, errorFechas, isArchivosDescargados, errorFaltaCarta, " +
                "errorFaltaActa " +
                "FROM [digi].[casosCorrespondenciaDigital] " +
                "WHERE idEstado = 1 OR idEstado = 6";
        ArrayList<Caso> casosPendientes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(casosPendientesQuery)) {
            ResultSet rs = ps.executeQuery();
            Caso caso;
            while(rs.next()){
                caso = new Caso();
                caso.setAnio(rs.getShort(1));
                caso.setNroOS(rs.getShort(2));
                caso.setIdCaso(rs.getShort(3));
                caso.setIdActividad(rs.getString(4));
                caso.setNroCaso(rs.getInt(5));
                caso.setEstado(new Estado(rs.getShort(6)));
                caso.setErrorNroCarta(rs.getBoolean(7));
                caso.setErrorCorreoNotif(rs.getBoolean(8));
                caso.setErrorFechas(rs.getBoolean(9));
                caso.setArchivosDescargados(rs.getBoolean(10));
                caso.setErrorFaltaCartas(rs.getBoolean(11));
                caso.setErrorFaltaActas(rs.getBoolean(12));
                casosPendientes.add(caso);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudieron consultar los ids y nros. de los casos");
        }
        return casosPendientes;
    }

    public static ArrayList<Caso> queryCasosPendientesDescargaSalesforce() {
        ArrayList<Caso> casos = new ArrayList<>();

        String casosPendientesDescargaSalesforceQuery = "SELECT [anio], [nroOS], [idCasoCorrespondenciaDigital], " +
                "[idTipoAtencion], [idTipoRegCaso], [idActividad], [idTipoCarta], [nroCaso], [idEstadoCaso], " +
                "[fechaCreacionCaso], [correlativoCarta], [nroSuministro], [idCanalNotificacion], [idProvincia], " +
                "[idPrioridad], [idEstado], [fecCreacion], [fecEmision], [fecDespacho], [fecNotificacion], " +
                "[fecNotificacionCarta], [fecUltimaModificacion], [fecha], [fecVencimientoLegal], [idCreadoPor], " +
                "[canalRegistro], [idPropietarioCaso], [diasVencidosPorVencer], [dirCorreoCarta], [dirCorreoActa], " +
                "[errorNroCarta], [errorCorreoNotif], [errorFechas], " +
                "[errorFaltaCarta], [errorFaltaActa], [errorFaltaFirma], [mensajeError] " +
                "FROM [digi].[casosCorrespondenciaDigital] " +
                "WHERE descargadoEnSalesforce = 0";

        try (PreparedStatement ps = conn.prepareStatement(casosPendientesDescargaSalesforceQuery)) {
            Caso caso;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                caso = new Caso();
                caso.setAnio(rs.getShort(1));
                caso.setNroOS(rs.getShort(2));
                caso.setIdCaso(rs.getShort(3));
                caso.setTipoAtencion(TipoAtencion.getTipoAtencion(rs.getShort(4)));
                caso.setTipoRegCaso(TipoRegCaso.getTipoRegCaso(rs.getShort(5)));
                caso.setIdActividad(rs.getString(6));
                caso.setTipoCarta(TipoCarta.getTipoCarta(rs.getShort(7)));
                caso.setNroCaso(rs.getInt(8));
                caso.setEstadoCaso(EstadoCaso.getEstadoCaso(rs.getShort(9)));
                caso.setFecCreacionCaso(dateToLocalDate(rs.getDate(10)));
                caso.setCorrelativoCarta(rs.getShort(11));
                caso.setNroSuministro(rs.getString(12));
                caso.setCanalNotificacion(CanalNotificacion.getCanalNotificacion(rs.getShort(13)));
                caso.setProvincia(Provincia.getProvincia(rs.getShort(14)));
                caso.setPrioridad(Prioridad.getPrioridad(rs.getShort(15)));
                caso.setEstado(Estado.getEstado(rs.getShort(16)));
                caso.setFecCreacion(dateToLocalDate(rs.getDate(17)));
                caso.setFecEmisionDateTime(timestampToLocalDateTime(rs.getTimestamp(18)));
                caso.setFecDespacho(timestampToLocalDateTime(rs.getTimestamp(19)));
                caso.setFecNotificacion(timestampToLocalDateTime(rs.getTimestamp(20)));
                caso.setFecNotificacionCarta(timestampToLocalDateTime(rs.getTimestamp(21)));
                caso.setFecUltimaModificacion(dateToLocalDate(rs.getDate(22))); //TODO bota error aqui, por NULL
                caso.setFecha(dateToLocalDate(rs.getDate(23)));
                caso.setFecVencimientoLegal(timestampToLocalDateTime(rs.getTimestamp(24)));
                caso.setCreadoPor(Usuario.getUsuario(rs.getShort(25)));
                caso.setCanalRegistro(CanalRegistro.getCanalRegistro(rs.getShort(26)));
                caso.setPropietarioCaso(Usuario.getUsuario(rs.getShort(27)));
                caso.setDiasVencidosPorVencer(rs.getShort(28));
                caso.setCorreosCartasString(rs.getString(29));
                caso.setCorreosActasString(rs.getString(30));
                caso.setErrorNroCarta(rs.getBoolean(31));
                caso.setErrorCorreoNotif(rs.getBoolean(32));
                caso.setErrorFechas(rs.getBoolean(33));
                caso.setErrorFaltaCartas(rs.getBoolean(34));
                caso.setErrorFaltaActas(rs.getBoolean(35));
                caso.setErrorFaltaFirma(rs.getBoolean(36));
                caso.setMensajeError(rs.getString(37));
                casos.add(caso);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar el caso pendiente de descarga en Salesforce.");
        }
        return casos;
    }

    static public LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    static public LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        if(timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    public static ArrayList<Caso> queryCasosPorDescargarSalesforce() {
        ArrayList<Caso> casos = new ArrayList<>();

        String casosPendientesDescargaSalesforceQuery = "SELECT [anio], [nroOS], [idCasoCorrespondenciaDigital], " +
                "[idActividad], [nroCaso], [idEstado], [fecEmision], [fecDespacho], [fecNotificacion], [dirCorreoActa]," +
                "[mensajeError], [errorFaltaCarta], [errorFaltaActa] " +
                "FROM [digi].[casosCorrespondenciaDigital] " +
                "WHERE descargadoEnSalesforce = 0 AND revisado = 1";

        try (PreparedStatement ps = conn.prepareStatement(casosPendientesDescargaSalesforceQuery)) {
            Caso caso;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                caso = new Caso();
                caso.setAnio(rs.getShort(1));
                caso.setNroOS(rs.getShort(2));
                caso.setIdCaso(rs.getShort(3));
                caso.setIdActividad(rs.getString(4));
                caso.setNroCaso(rs.getInt(5));
                caso.setEstado(Estado.getEstado(rs.getShort(6)));
                caso.setFecEmisionDateTime(timestampToLocalDateTime(rs.getTimestamp(7)));
                caso.setFecDespacho(timestampToLocalDateTime(rs.getTimestamp(8)));
                caso.setFecNotificacion(timestampToLocalDateTime(rs.getTimestamp(9)));
                caso.setCorreosActasString(rs.getString(10));
                caso.setMensajeError(rs.getString(11));
                caso.setErrorFaltaCartas(rs.getBoolean(12));
                caso.setErrorFaltaActas(rs.getBoolean(13));
                casos.add(caso);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar el caso pendiente de descarga en Salesforce.");
        }
        return casos;
    }

    public static ArrayList<Caso> queryAllCasosRechazados() {
        ArrayList<Caso> casosRechazados = new ArrayList<>();

        String casosPendientesDescargaSalesforceQuery = "SELECT [anio], [nroOS], [idCasoCorrespondenciaDigital], " +
                "[idActividad], [nroCaso], [idEstado], [fecEmision], [fecDespacho], [fecNotificacion], " +
                "[dirCorreoCarta], [dirCorreoActa], [errorNroCarta], [errorCorreoNotif], [errorFechas], " +
                "[errorFaltaCarta], [errorFaltaActa], [errorFaltaFirma] " +
                "FROM [digi].[casosCorrespondenciaDigital] " +
                "WHERE idEstado = 5";

        try (PreparedStatement ps = conn.prepareStatement(casosPendientesDescargaSalesforceQuery)) {
            Caso caso;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                caso = new Caso();
                caso.setAnio(rs.getShort(1));
                caso.setNroOS(rs.getShort(2));
                caso.setIdCaso(rs.getShort(3));
                caso.setIdActividad(rs.getString(4));
                caso.setNroCaso(rs.getInt(5));
                caso.setEstado(Estado.getEstado(rs.getShort(6)));
                caso.setFecEmisionDateTime(timestampToLocalDateTime(rs.getTimestamp(7)));
                caso.setFecDespacho(timestampToLocalDateTime(rs.getTimestamp(8)));
                caso.setFecNotificacion(timestampToLocalDateTime(rs.getTimestamp(9)));
                caso.setCorreosCartasString(rs.getString(10));
                caso.setCorreosActasString(rs.getString(11));
                caso.setErrorNroCarta(rs.getBoolean(12));
                caso.setErrorCorreoNotif(rs.getBoolean(13));
                caso.setErrorFechas(rs.getBoolean(14));
                caso.setErrorFaltaCartas(rs.getBoolean(15));
                caso.setErrorFaltaActas(rs.getBoolean(16));
                caso.setErrorFaltaFirma(rs.getBoolean(17));
                casosRechazados.add(caso);
            }
        } catch (SQLException sqle) {
            System.out.println("No se pudo consultar el caso pendiente de descarga en Salesforce.");
        }
        return casosRechazados;
    }
}
