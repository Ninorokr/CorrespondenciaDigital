package com.silverlink.Utils;

import com.silverlink.Entidades.Caso;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.silverlink.Main.*;
import static com.silverlink.Utils.Querier.*;
import static com.silverlink.Utils.Datasource.conn;


public class Commander {

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
        String updateUsuarioQuery = "UPDATE [digi].[usuario] SET codUsuario = ? WHERE nomUsuario = " + nomUsuario;

        try(PreparedStatement ps = conn.prepareStatement(updateUsuarioQuery)){
            ps.setString(1, codUsuario);
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

        // TODO : El excel tendrá campos vacíos, cuando lleguen acá, cada campo vacío lanzará una excepción :O!

        String insertCasoQuery = "INSERT INTO [digi].[casosCorrespondenciaDigital] ([anio], [nroOS], [idCasoCorrespondenciaDigital], " +
                "[idTipoAtencion], [idTipoRegCaso], [idActividad], [idTipoCarta], [nroCaso], [idEstadoCaso], [fechaCreacionCaso], " +
                "[correlativoCarta], [nroSuministro], [idCanalNotificacion], [idProvincia], [idPrioridad], [idEstado], " +
                "[fecCreacion], [fecNotificacionCarta], [fecUltimaModificacion], [fecha], [fecVencimientoLegal], [idCreadoPor], " +
                "[canalRegistro], [idPropietarioCaso], [diasVencidosPorVencer]) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insertCasoQuery)) {
            ps.setShort(1, caso.getAnio());
            ps.setShort(2, caso.getNroOS());
            ps.setShort(3, caso.getIdCorrelativoCaso());
            ps.setShort(4, caso.getTipoAtencion().getIdTipoAtencion());
            ps.setShort(5, caso.getTipoRegCaso().getIdTipoRegCaso());
            ps.setString(6, caso.getIdActividad());
            ps.setShort(7, caso.getTipoCarta().getIdTipoCarta());
            ps.setInt(8, caso.getNroCaso());
            ps.setShort(9, caso.getEstadoCaso().getIdEstadoCaso());
            ps.setDate(10, Date.valueOf(caso.getFecCreacionCaso()));
            ps.setShort(11, caso.getCorrelativoCarta());
            ps.setInt(12, caso.getNroSuministro());
            ps.setInt(13, caso.getCanalNotificacion().getIdCanalNotificacion());
            ps.setShort(14, caso.getProvincia().getIdProvincia());
            ps.setShort(15, caso.getPrioridad().getIdPrioridad());
            ps.setShort(16, caso.getEstado().getIdEstado());
            ps.setDate(17, Date.valueOf(caso.getFecCreacion()));
            ps.setTimestamp(18, Timestamp.valueOf(caso.getFecNotificacionCarta()));
            ps.setDate(19, Date.valueOf(caso.getFecUltimaModificacion()));
            ps.setDate(20, Date.valueOf(caso.getFecha()));
            ps.setTimestamp(21, Timestamp.valueOf(caso.getFecVencimientoLegal()));
            ps.setShort(22, caso.getCreadoPor().getIdUsuario());
            ps.setShort(23, caso.getCanalRegistro().getIdCanalRegistro());
            ps.setShort(24, caso.getPropietarioCaso().getIdUsuario());
            ps.setInt(25, caso.getDiasVencidosPorVencer());
            ps.execute();
            System.out.println("Se insertó caso: OS: " + caso.getNroOS() + "-" + caso.getIdCorrelativoCaso() +
                    " | " + caso.getIdActividad() + " | " + caso.getNroCaso());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }






}
