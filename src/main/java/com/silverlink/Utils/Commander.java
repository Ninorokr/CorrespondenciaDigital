package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.silverlink.Main.*;
import static com.silverlink.Utils.Querier.*;
import static com.silverlink.Utils.Datasource.conn;


public class Commander {

    public static void registrarNuevaOS(int anio, int nroOS) {
        String insertNuevaOSQuery = "INSERT INTO [digi].[OSs] ([anio], [nroOS], [fecCreacion]) VALUES (?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(insertNuevaOSQuery)){
            ps.setShort(1, (short) anio);
            ps.setShort(2, (short) nroOS);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo crear la nueva OS " + String.format("%04d", nroOS));
            sqle.printStackTrace();
            return;
        }
        System.out.println("Se creó la nueva OS: " + String.format("%04d", nroOS));
    }


    public static void insertTipoAtencion(String nomTipoAtencion){
        String insertTipoAtencionQuery = "INSERT INTO [digi].[tipoAtencion] ([nomTipoAtencion]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertTipoAtencionQuery)){
            ps.setString(1, nomTipoAtencion);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el tipo de atención a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo tipo de atención: " + nomTipoAtencion);

        tiposAtencion = queryTiposAtencion();
    }

    public static void insertTipoRegCaso(String nomTipoRegCaso){
        String insertTipoRegCasoQuery = "INSERT INTO [digi].[tipoRegCaso] ([nomTipoRegCaso]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertTipoRegCasoQuery)){
            ps.setString(1, nomTipoRegCaso);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el tipo de registro (caso) a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo tipo de registro de caso: " + nomTipoRegCaso);

        tiposRegCaso = queryTiposRegCaso();
    }

    public static void insertTipoCarta(String nomTipoCarta){
        String insertCartaQuery = "INSERT INTO [digi].[tipoCarta] ([nomTipoCarta]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertCartaQuery)){
            ps.setString(1, nomTipoCarta);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el tipo de carta a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo tipo de carta: " + nomTipoCarta);

        tiposCarta = queryTiposCarta();
    }

    public static void insertEstadoCaso(String nomEstadoCaso){
        String insertEstadoCasoQuery = "INSERT INTO [digi].[estadoCaso] ([nomEstadoCaso]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertEstadoCasoQuery)){
            ps.setString(1, nomEstadoCaso);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el estado (caso) a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo estado (caso) carta: " + nomEstadoCaso);

        estadosCaso = queryEstadosCaso();
    }

    public static void insertCanalNotificacion(String nomCanalNotificacion){
        String insertCanalNotificacionQuery = "INSERT INTO [digi].[canalNotificacion] ([nomCanalNotificacion]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertCanalNotificacionQuery)){
            ps.setString(1, nomCanalNotificacion);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el canal de notificación a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo canal de notificación carta: " + nomCanalNotificacion);

        canalesNotificacion = queryCanalesNotificacion();
    }

    public static void insertProvincia(String nomProvincia){
        String insertProvinciaQuery = "INSERT INTO [digi].[provincia] ([nomProvincia]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertProvinciaQuery)){
            ps.setString(1, nomProvincia);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la provincia a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó la nueva provincia: " + nomProvincia);

        provincias = queryProvincias();
    }

    public static void insertPrioridad(String nomPrioridad){
        String insertPrioridadQuery = "INSERT INTO [digi].[prioridad] ([nomPrioridad]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertPrioridadQuery)){
            ps.setString(1, nomPrioridad);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar la prioridad a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó la nueva prioridad: " + nomPrioridad);

        prioridades = queryPrioridades();
    }

    public static void insertEstado (String nomEstado){
        String insertEstadoQuery = "INSERT INTO [digi].[estado] ([nomEstado]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertEstadoQuery)){
            ps.setString(1, nomEstado);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el estado a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo estado: " + nomEstado);

        estados = queryEstados();
    }

    public static void insertCanalRegistro(String nomCanalRegistro) {
        String insertCanalRegistro = "INSERT INTO [digi].[canalRegistro] ([nomCanalRegistro]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertCanalRegistro)){
            ps.setString(1, nomCanalRegistro);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el estado a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo canal de registro: " + nomCanalRegistro);

        canalesRegistro = queryCanalesRegistro();
    }

    public static void insertUsuarioCompleto (String codUsuario, String nomUsuario){
        String insertUsuarioQuery = "INSERT INTO [digi].[usuario] ([codUsuario], [nomUsuario]) VALUES (?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(insertUsuarioQuery)){
            ps.setString(1, codUsuario);
            ps.setString(2, nomUsuario);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el usuario a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo usuario: " + nomUsuario);

        usuarios = queryUsuarios();
    }

    public static void insertUsuarioIncompleto (String nomUsuario){
        String insertUsuarioQuery = "INSERT INTO [digi].[usuario] ([nomUsuario]) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(insertUsuarioQuery)){
            ps.setString(1, nomUsuario);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo ingresar el usuario a la BD");
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se insertó el nuevo usuario: " + nomUsuario);

        usuarios = queryUsuarios();
    }

    public static void updateCodUsuario(String codUsuario, String nomUsuario) {
        String updateUsuarioQuery = "UPDATE [digi].[usuario] SET codUsuario = ? WHERE nomUsuario = ?";

        try(PreparedStatement ps = conn.prepareStatement(updateUsuarioQuery)){
            ps.setString(1, codUsuario);
            ps.setString(2, nomUsuario);
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo actualizar el código de usuario para " + nomUsuario);
            sqle.printStackTrace();
            System.exit(0);
        }
        System.out.println("Se actualizó el código de usuario: " + codUsuario + " | " + nomUsuario);

        usuarios = queryUsuarios();
    }

    public static void insertCasoABD(Caso caso) {

        //El excel tendrá campos vacíos, cuando lleguen acá, cada campo vacío lanzará una excepción :O!
        //Se puede reemplazar con setObject
        //TODO cambiar nombre de idCaso a "idCaso"
        String insertCasoQuery = "INSERT INTO [digi].[casosCorrespondenciaDigital] ([anio], [nroOS], [idCasoCorrespondenciaDigital], " +
                "[idTipoAtencion], [idTipoRegCaso], [idActividad], [idTipoCarta], [nroCaso], [idEstadoCaso], [fechaCreacionCaso], " +
                "[correlativoCarta], [nroSuministro], [idCanalNotificacion], [idProvincia], [idPrioridad], [idEstado], " +
                "[fecCreacion], [fecNotificacionCarta], [fecUltimaModificacion], [fecha], [fecVencimientoLegal], [idCreadoPor], " +
                "[canalRegistro], [idPropietarioCaso], [diasVencidosPorVencer], [descargadoEnSalesforce], [isArchivosDescargados]) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insertCasoQuery)) {
            ps.setShort(1, caso.getAnio());
            ps.setShort(2, caso.getNroOS());
            ps.setShort(3, caso.getIdCaso());
            ps.setShort(4, caso.getTipoAtencion().getIdTipoAtencion());
            ps.setShort(5, caso.getTipoRegCaso().getIdTipoRegCaso());
            ps.setString(6, caso.getIdActividad());
            ps.setShort(7, caso.getTipoCarta().getIdTipoCarta());
            ps.setInt(8, caso.getNroCaso());
            ps.setShort(9, caso.getEstadoCaso().getIdEstadoCaso());
            ps.setDate(10, getDateValue(caso.getFecCreacionCaso()));
            ps.setShort(11, caso.getCorrelativoCarta());
            ps.setInt(12, caso.getNroSuministro());
            ps.setInt(13, caso.getCanalNotificacion().getIdCanalNotificacion());
            ps.setShort(14, caso.getProvincia().getIdProvincia());
            ps.setShort(15, caso.getPrioridad().getIdPrioridad());
            ps.setShort(16, caso.getEstado().getIdEstado());
            ps.setDate(17, getDateValue(caso.getFecCreacion()));
            ps.setTimestamp(18, getTimeStampValue(caso.getFecNotificacionCarta()));
            ps.setDate(19, getDateValue(caso.getFecUltimaModificacion()));
            ps.setDate(20, getDateValue(caso.getFecha()));
            ps.setTimestamp(21, getTimeStampValue(caso.getFecVencimientoLegal()));
            ps.setShort(22, caso.getCreadoPor().getIdUsuario());
            ps.setShort(23, caso.getCanalRegistro().getIdCanalRegistro());
            ps.setShort(24, caso.getPropietarioCaso().getIdUsuario());
            ps.setInt(25, caso.getDiasVencidosPorVencer());
            ps.setBoolean(26, caso.isDescargadoEnSalesforce()); //redundante (DEFAULT en BD al momento de insert)
            ps.setBoolean(27, caso.isArchivosDescargados()); //redundante (DEFAULT en BD al momento de insert)

//            ps.setObject(26, caso.getPropietarioCaso().getIdUsuario(), JDBCType.INTEGER);
            ps.execute();
            System.out.println("002-23-" + caso.getNroOS() + "-" + caso.getIdCaso() +
                    " | " + caso.getIdActividad() + " | " + caso.getNroCaso());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }



//    public static void insertCasoABDMejorado(Caso caso) {
//
//    }

    public static void setArchivosDescargadosToTrue(Caso caso) {
        String setArchivosDescargadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET isArchivosDescargados = 1 " +
                "WHERE idActividad = ?";

//        setArchivosDescargadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET isArchivosDescargados = 1" +
//                "WHERE idActividad = ?";

        try(PreparedStatement ps = conn.prepareStatement(setArchivosDescargadosQuery)){
            ps.setString(1, caso.getIdActividad());
            ps.execute();
        } catch (SQLException sqle) {
            System.out.println("No se pudo registrar archivos descargados como \"TRUE\"");
            sqle.printStackTrace();
        }
    }

    public static void updateCasosVerificadosIncompletos(Caso caso) {
        String updateCasosVerificadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET " +
                "errorFaltaCarta = ?, errorFaltaActa = ?, idEstado = ? " +
                "WHERE idActividad = ?";

        try(PreparedStatement ps = conn.prepareStatement(updateCasosVerificadosQuery)){
            ps.setBoolean(1, caso.isErrorFaltaCartas());
            ps.setBoolean(2, caso.isErrorFaltaActas());
            ps.setShort(3, caso.getEstado().getIdEstado());
            ps.setString(4, caso.getIdActividad());
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo registrar caso con archivos incompletos");
            sqle.printStackTrace();
        }
    }

    public static void updateCasosVerificadosIncompletos2(Caso caso) {
        String updateCasosVerificadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital2] SET " +
                "errorFaltaCarta = ?, errorFaltaActa = ?, idEstado = ? " +
                "WHERE idActividad = ?";

        try(PreparedStatement ps = conn.prepareStatement(updateCasosVerificadosQuery)){
            ps.setBoolean(1, caso.isErrorFaltaCartas());
            ps.setBoolean(2, caso.isErrorFaltaActas());
            ps.setShort(3, caso.getEstado().getIdEstado());
            ps.setString(4, caso.getIdActividad());
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo registrar caso con archivos incompletos");
            sqle.printStackTrace();
        }
    }

    public static void updateCasosVerificadosCompletos(Caso caso) {
        String updateCasosVerificadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET " +
                "errorFaltaCarta = ?, errorFaltaActa = ?, idEstado = ?, errorNroCarta = ?, " +
                "errorCorreoNotif = ?, errorFechas = ?, dirCorreoCarta = ?, dirCorreoActa = ?," +
                "fecEmision = ?, fecDespacho = ?, fecNotificacion = ? " +
                "WHERE idActividad = ?";

        try(PreparedStatement ps = conn.prepareStatement(updateCasosVerificadosQuery)){
            ps.setBoolean(1, caso.isErrorFaltaCartas());
            ps.setBoolean(2, caso.isErrorFaltaActas());
            ps.setShort(3, caso.getEstado().getIdEstado());
            ps.setBoolean(4, caso.isErrorNroCarta());
            ps.setBoolean(5, caso.isErrorCorreoNotif());
            ps.setBoolean(6, caso.isErrorFechas());
            ps.setString(7, caso.concatCorreosCartasString());
            ps.setString(8, caso.concatCorreosActasString());
//            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.of(caso.getFecEmision(),
//                                                        caso.getFecNotificacion().toLocalTime())));
            ps.setTimestamp(9, getTimeStampValue(timeStampValueFrom(caso.getFecEmision(),
                    caso.getFecNotificacion().toLocalTime())));
            ps.setTimestamp(10, Timestamp.valueOf(caso.getFecDespacho()));
            ps.setTimestamp(11, Timestamp.valueOf(caso.getFecNotificacion()));
            ps.setString(12, caso.getIdActividad());
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo registrar caso verificado");
            sqle.printStackTrace();
        }
    }

    public static void updateCasosVerificadosCompletos2(Caso caso) {
        String updateCasosVerificadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital2] SET " +
                "errorFaltaCarta = ?, errorFaltaActa = ?, idEstado = ?, errorNroCarta = ?, " +
                "errorCorreoNotif = ?, errorFechas = ?, dirCorreoCarta = ?, dirCorreoActa = ?," +
                "fecEmision = ?, fecDespacho = ?, fecNotificacion = ? " +
                "WHERE idActividad = ?";

        try(PreparedStatement ps = conn.prepareStatement(updateCasosVerificadosQuery)){
            ps.setBoolean(1, caso.isErrorFaltaCartas());
            ps.setBoolean(2, caso.isErrorFaltaActas());
            ps.setShort(3, caso.getEstado().getIdEstado());
            ps.setBoolean(4, caso.isErrorNroCarta());
            ps.setBoolean(5, caso.isErrorCorreoNotif());
            ps.setBoolean(6, caso.isErrorFechas());
            ps.setString(7, caso.concatCorreosCartasString());
            ps.setString(8, caso.concatCorreosActasString());
//            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.of(caso.getFecEmision(),
//                    caso.getFecNotificacion().toLocalTime())));
            ps.setTimestamp(9, getTimeStampValue(timeStampValueFrom(caso.getFecEmision(),
                    caso.getFecNotificacion().toLocalTime())));
            ps.setTimestamp(10, Timestamp.valueOf(caso.getFecDespacho()));
            ps.setTimestamp(11, Timestamp.valueOf(caso.getFecNotificacion()));
            ps.setString(12, caso.getIdActividad());
            ps.execute();
        } catch (SQLException sqle){
            System.out.println("No se pudo registrar caso verificado");
            sqle.printStackTrace();
        }
    }

    public static void updateCasosRevisados(Caso caso) {
        String updateCasosRevisadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET " +
                "idEstado = ?, fecEmision = ?, fecDespacho = ?, fecNotificacion = ?, dirCorreoCarta = ?, " +
                "dirCorreoActa = ?, errorNroCarta = ?, errorCorreoNotif = ?, errorFechas = ?, errorFaltaFirma = ?, " +
                "errorFaltaCarta = ?, errorFaltaActa = ?, mensajeError = ?, revisado = ? " +
                "WHERE anio = ? AND nroOS = ? AND idCasoCorrespondenciaDigital = ?";

//        System.out.println(caso.getNroOS() + "-" + caso.getIdCaso());

        try (PreparedStatement ps = conn.prepareStatement(updateCasosRevisadosQuery)) {
            ps.setShort(1, caso.getEstado().getIdEstado());
            ps.setTimestamp(2, caso.getFecEmisionDateTimeTimestamp());
            ps.setTimestamp(3, caso.getFecDespachoTimestamp());
            ps.setTimestamp(4, caso.getFecNotificacionTimestamp());
            ps.setString(5, caso.getCorreosCartasString());
            ps.setString(6, caso.getCorreosActasString());
            ps.setBoolean(7, caso.isErrorNroCarta());
            ps.setBoolean(8, caso.isErrorCorreoNotif());
            ps.setBoolean(9, caso.isErrorFechas());
            ps.setBoolean(10, caso.isErrorFaltaFirma());
            ps.setBoolean(11, caso.isErrorFaltaCartas());
            ps.setBoolean(12, caso.isErrorFaltaActas());
            ps.setString(13, caso.getMensajeError());
            ps.setBoolean(14, true);
            ps.setShort(15, caso.getAnio());
            ps.setShort(16, caso.getNroOS());
            ps.setShort(17, caso.getIdCaso());
            ps.execute();
        } catch (SQLException sqle) {
            System.out.println("No se pudo actualizar caso revisado");
        }
    }

    public static void updateCasosRevisados2(Caso caso) {
        String updateCasosRevisadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital2] SET " +
                "idEstado = ?, fecEmision = ?, fecDespacho = ?, fecNotificacion = ?, dirCorreoCarta = ?, " +
                "dirCorreoActa = ?, errorNroCarta = ?, errorCorreoNotif = ?, errorFechas = ?, errorFaltaFirma = ?, " +
                "errorFaltaCarta = ?, errorFaltaActa = ?, mensajeError = ?, revisado = ? " +
                "WHERE anio = ? AND nroOS = ? AND idCasoCorrespondenciaDigital = ?";

        System.out.println(caso.getNroOS() + "-" + caso.getIdCaso());

        try (PreparedStatement ps = conn.prepareStatement(updateCasosRevisadosQuery)) {
            ps.setShort(1, caso.getEstado().getIdEstado());
            ps.setTimestamp(2, caso.getFecEmisionDateTimeTimestamp());
            ps.setTimestamp(3, caso.getFecDespachoTimestamp());
            ps.setTimestamp(4, caso.getFecNotificacionTimestamp());
            ps.setString(5, caso.getCorreosCartasString());
            ps.setString(6, caso.getCorreosActasString());
            ps.setBoolean(7, caso.isErrorNroCarta());
            ps.setBoolean(8, caso.isErrorCorreoNotif());
            ps.setBoolean(9, caso.isErrorFechas());
            ps.setBoolean(10, caso.isErrorFaltaFirma());
            ps.setBoolean(11, caso.isErrorFaltaCartas());
            ps.setBoolean(12, caso.isErrorFaltaActas());
            ps.setString(13, caso.getMensajeError());
            ps.setBoolean(14, true);
            ps.setShort(15, caso.getAnio());
            ps.setShort(16, caso.getNroOS());
            ps.setShort(17, caso.getIdCaso());
            ps.execute();
        } catch (SQLException sqle) {
            System.out.println("No se pudo actualizar caso revisado");
        }
    }

    public static void updateDescargadoEnSalesforce(Caso caso) {
        String updateCasosRevisadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET " +
                "descargadoEnSalesforce = ? " +
                "WHERE anio = ? AND nroOS = ? AND idCasoCorrespondenciaDigital = ?";

        try (PreparedStatement ps = conn.prepareStatement(updateCasosRevisadosQuery)) {
            ps.setBoolean(1, true);
            ps.setShort(2, caso.getAnio());
            ps.setShort(3, caso.getNroOS());
            ps.setShort(4, caso.getIdCaso());
            ps.execute();
        } catch (SQLException sqle) {
            System.out.println("No se pudo actualizar caso revisado");
        }
    }

    public static void updateCasoEstadoCasoCerrado(Caso caso) {
        String updateCasosRevisadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET " +
                "idEstado = ? " +
                "WHERE anio = ? AND nroOS = ? AND idCasoCorrespondenciaDigital = ?";

        try (PreparedStatement ps = conn.prepareStatement(updateCasosRevisadosQuery)) {
            ps.setShort(1, (short) 3);
            ps.setShort(2, caso.getAnio());
            ps.setShort(3, caso.getNroOS());
            ps.setShort(4, caso.getIdCaso());
            ps.execute();
        } catch (SQLException sqle) {
            System.out.println("No se pudo actualizar estado de caso a \"despachada\"");
        }
    }

    private static Date getDateValue(LocalDate fecha) {
        if(fecha == null)
            return null;
        return Date.valueOf(fecha);
    }

    private static Timestamp getTimeStampValue(LocalDateTime fechaYHora) {
        if(fechaYHora == null)
            return null;
        return Timestamp.valueOf(fechaYHora);
    }

    private static LocalDateTime timeStampValueFrom(LocalDate fecha, LocalTime hora) {
        if (fecha == null || hora == null)
            return null;
        return LocalDateTime.of(fecha, hora);
    }



//    public static void updateCasosRevisados(Caso caso) {
//        String setArchivosDescargadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET " +
//                "errorFaltaCarta = ?, errorFaltaActa = ?, idEstado = ?, errorNroCarta = ?, " +
//                "errorCorreoNotif = ?, errorFechas = ? " +
//                "WHERE idActividad = ?";
//
////        setArchivosDescargadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET isArchivosDescargados = 1" +
////                "WHERE idActividad = ?";
//
//        try(PreparedStatement ps = conn.prepareStatement(setArchivosDescargadosQuery)){
//            ps.setBoolean(1, caso.isErrorFaltaCartas());
//            ps.setBoolean(2, caso.isErrorFaltaActas());
//            ps.setShort(3, caso.getEstado().getIdEstado());
//            ps.setBoolean(4, caso.isErrorNroCarta());
//            ps.setBoolean(5, caso.isErrorCorreoNotif());
//            ps.setBoolean(6, caso.isErrorFechas());
//            ps.setString(7, caso.getIdActividad());
//            ps.execute();
//        } catch (SQLException sqle){
//            System.out.println("No se pudo registrar archivos descargados como \"TRUE\"");
//            sqle.printStackTrace();
//        }
//    }

//    public static void updateCasosRevisados(Caso caso) {
//        String setArchivosDescargadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET " +
//                "errorFaltaCarta = ?, errorFaltaActa = ?, idEstado = ? " +
//                "WHERE idActividad = ?";
//
////        setArchivosDescargadosQuery = "UPDATE [digi].[casosCorrespondenciaDigital] SET isArchivosDescargados = 1" +
////                "WHERE idActividad = ?";
//
//        try(PreparedStatement ps = conn.prepareStatement(setArchivosDescargadosQuery)){
//            ps.setBoolean(1, caso.isErrorFaltaCartas());
//            ps.setBoolean(2, caso.isErrorFaltaActas());
//            ps.setShort(3, caso.getEstado().getIdEstado());
//            ps.setString(4, caso.getIdActividad());
//            ps.execute();
//        } catch (SQLException sqle){
//            System.out.println("No se pudo registrar archivos descargados como \"TRUE\"");
//            sqle.printStackTrace();
//        }
//    }

}
