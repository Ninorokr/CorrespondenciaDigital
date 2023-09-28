package com.silverlink.Entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.silverlink.Utils.Commander.*;

public class Caso {

    private TipoAtencion tipoAtencion;
    private TipoRegCaso tipoRegCaso;
    private String idActividad;
    private TipoCarta tipoCarta;
    private int nroCaso;
    private EstadoCaso estadoCaso;
    private CanalNotificacion canalNotificacion;
    private LocalDate fecCreacionCaso;
    private short correlativoCarta;
    private int nroSuministro;
    private Provincia provincia;
    private Prioridad prioridad;
    private Estado estado;
    private LocalDate fecCreacion;
    private LocalDate fecEmision;
    private LocalDateTime fecDespacho;
    private LocalDateTime fecNotificiacion;
    private LocalDateTime fecNotificacionCarta;
    private LocalDate fecUltimaModificacion;
    private LocalDate fecha;
    private LocalDate fecVencimientoLegal;
    private Usuario creadoPor;
    private CanalRegistro canalRegistro;
    private Usuario propietarioCaso;
    private short diasVencidosPorVencer;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Acta> actas = new ArrayList<>();

    public Caso(){

    }

    public TipoAtencion getTipoAtencion() {
        return tipoAtencion;
    }

    public void setTipoAtencion(TipoAtencion tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }

    public void setTipoAtencion(String nomTipoAtencion) {
        boolean flag = true;
        TipoAtencion tipoAtencion = null;
        while(flag){
            tipoAtencion = TipoAtencion.existeTipoAtencion(nomTipoAtencion);
            if(tipoAtencion == null){
                insertTipoAtencion(nomTipoAtencion);
            } else {
                flag = false;
            }
        }
        this.tipoAtencion = tipoAtencion;
    }

    public TipoRegCaso getTipoRegCaso() {
        return tipoRegCaso;
    }

    public void setTipoRegCaso(TipoRegCaso tipoRegCaso) {
        this.tipoRegCaso = tipoRegCaso;
    }

    public void setTipoRegCaso(String nomtipoRegCaso) {
        boolean flag = true;
        TipoRegCaso tipoRegCaso = null;
        while(flag){
            tipoRegCaso = TipoRegCaso.existeTipoRegCaso(nomtipoRegCaso);
            if(tipoRegCaso == null){
                insertTipoRegCaso(nomtipoRegCaso);
            } else {
                flag = false;
            }
        }
        this.tipoRegCaso = tipoRegCaso;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public TipoCarta getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(TipoCarta tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public void setTipoCarta(String nomTipoCarta) {
        boolean flag = true;
        TipoCarta tipoCarta = null;
        while(flag){
            tipoCarta = TipoCarta.existeTipoCarta(nomTipoCarta);
            if(tipoCarta == null){
                insertTipoCarta(nomTipoCarta);
            } else {
                flag = false;
            }
        }
        this.tipoCarta = tipoCarta;
    }

    public int getNroCaso() {
        return nroCaso;
    }

    public void setNroCaso(String nroCaso) {
        this.nroCaso = Integer.parseInt(nroCaso);
    }

    public EstadoCaso getEstadoCaso() {
        return estadoCaso;
    }

    public void setEstadoCaso(EstadoCaso estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    public void setEstadoCaso(String nomEstadoCaso) {
        boolean flag = true;
        EstadoCaso estadoCaso = null;
        while(flag){
            estadoCaso = EstadoCaso.existeEstadoCaso(nomEstadoCaso);
            if(estadoCaso == null){
                insertEstadoCaso(nomEstadoCaso);
            } else {
                flag = false;
            }
        }
        this.estadoCaso = estadoCaso;
    }

    public CanalNotificacion getCanalNotificacion() {
        return canalNotificacion;
    }

    public void setCanalNotificacion(CanalNotificacion canalNotificacion) {
        this.canalNotificacion = canalNotificacion;
    }

    public void setCanalNotificacion(String nomCanalNotificacion) {
        boolean flag = true;
        CanalNotificacion canalNotificacion = null;
        while(flag){
            canalNotificacion = CanalNotificacion.existeCanalNotificacion(nomCanalNotificacion);
            if(canalNotificacion == null){
                insertCanalNotificacion(nomCanalNotificacion);
            } else {
                flag = false;
            }
        }
        this.canalNotificacion = canalNotificacion;
    }

    public LocalDate getFecCreacionCaso() {
        return fecCreacionCaso;
    }

    public void setFecCreacionCaso(LocalDate fecCreacionCaso) {
        this.fecCreacionCaso = fecCreacionCaso;
    }

    public short getCorrelativoCarta() {
        return correlativoCarta;
    }

    public void setCorrelativoCarta(short correlativoCarta) {
        this.correlativoCarta = correlativoCarta;
    }

    public int getNroSuministro() {
        return nroSuministro;
    }

    public void setNroSuministro(String nroSuministro) {
        if(nroSuministro.equals("")) {
            this.nroSuministro = 0;
        } else {
            this.nroSuministro = Integer.parseInt(nroSuministro);
        }
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public void setProvincia(String nomProvincia) {
        boolean flag = true;
        Provincia provincia = null;
        while(flag){
            provincia = Provincia.existeProvincia(nomProvincia);
            if(provincia == null){
                insertProvincia(nomProvincia);
            } else {
                flag = false;
            }
        }
        this.provincia = provincia;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setPrioridad(String nomPrioridad) {
        boolean flag = true;
        Prioridad prioridad = null;
        while(flag) {
            prioridad = Prioridad.existePrioridad(nomPrioridad);
            if(prioridad == null){
                insertPrioridad(nomPrioridad);
            } else {
                flag = false;
            }
        }
        this.prioridad = prioridad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setEstado(String nomEstado) {
        boolean flag = true;
        Estado estado = null;
        while(flag) {
            estado = Estado.existeEstado(nomEstado);
            if(estado == null){
                insertEstado(nomEstado);
            } else {
                flag = false;
            }
        }
        this.estado = estado;
    }

    public LocalDate getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(LocalDate fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public LocalDate getFecEmision() {
        return fecEmision;
    }

    public void setFecEmision(LocalDate fecEmision) {
        this.fecEmision = fecEmision;
    }

    public LocalDateTime getFecDespacho() {
        return fecDespacho;
    }

    public void setFecDespacho(LocalDateTime fecDespacho) {
        this.fecDespacho = fecDespacho;
    }

    public LocalDateTime getFecNotificiacion() {
        return fecNotificiacion;
    }

    public void setFecNotificiacion(LocalDateTime fecNotificiacion) {
        this.fecNotificiacion = fecNotificiacion;
    }

    public LocalDateTime getFecNotificacionCarta() {
        return fecNotificacionCarta;
    }

    public void setFecNotificacionCarta(LocalDateTime fecNotificacionCarta) {
        this.fecNotificacionCarta = fecNotificacionCarta;
    }

    public LocalDate getFecUltimaModificacion() {
        return fecUltimaModificacion;
    }

    public void setFecUltimaModificacion(LocalDate fecUltimaModificacion) {
        this.fecUltimaModificacion = fecUltimaModificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecVencimientoLegal() {
        return fecVencimientoLegal;
    }

    public void setFecVencimientoLegal(LocalDate fecVencimientoLegal) {
        this.fecVencimientoLegal = fecVencimientoLegal;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    public void setCreadoPor(String nomCreadoPor) {
        boolean flag = true;
        Usuario usuario = null;
        while(flag) {
            usuario = Usuario.existeUsuarioNombre(nomCreadoPor);
            if(usuario == null){
                insertUsuarioIncompleto(nomCreadoPor);
            } else {
                flag = false;
            }
        }
        this.creadoPor = usuario;
    }

    public CanalRegistro getCanalRegistro() {
        return canalRegistro;
    }

    public void setCanalRegistro(CanalRegistro canalRegistro) {
        this.canalRegistro = canalRegistro;
    }

    public void setCanalRegistro(String nomCanalRegistro) {
        boolean flag = true;
        CanalRegistro canalRegistro = null;
        while(flag) {
            canalRegistro = CanalRegistro.existeCanalRegistro(nomCanalRegistro);
            if(canalRegistro == null){
                insertCanalRegistro(nomCanalRegistro);
            } else {
                flag = false;
            }
        }
        this.canalRegistro = canalRegistro;
    }

    public Usuario getPropietarioCaso() {
        return propietarioCaso;
    }

    public void setPropietarioCaso(Usuario propietarioCaso) {
        this.propietarioCaso = propietarioCaso;
    }

    public void setPropietarioCaso(String codPropietarioCaso, String nomPropietarioCaso) {
        boolean flag = true;
        Usuario usuario = null;
        while(flag) {
            usuario = Usuario.existeUsuario(codPropietarioCaso, nomPropietarioCaso);
            if(usuario == null){
                insertUsuarioCompleto(codPropietarioCaso, nomPropietarioCaso);
            } else {
                flag = false;
            }
        }
        this.propietarioCaso = usuario;
    }

    public short getDiasVencidosPorVencer() {
        return diasVencidosPorVencer;
    }

    public void setDiasVencidosPorVencer(short diasVencidosPorVencer) {
        this.diasVencidosPorVencer = diasVencidosPorVencer;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public ArrayList<Acta> getActas() {
        return actas;
    }

    @Override
    public String toString() {
        return "Tipo Atención: " + tipoAtencion.getNomTipoAtencion() + "\n" +
                "Tipo Reg Caso: " + tipoRegCaso.getNomTipoRegCaso() + "\n" +
                "ID Actividad: " + idActividad + "\n" +
                "Tipo carta: " + tipoCarta.getNomTipoCarta() + "\n" +
                "Nro Caso: " + nroCaso + "\n" +
                "Estado Caso: " + estadoCaso.getNomEstadoCaso() + "\n" +
                "Canal Notificacion: " + canalNotificacion.getNomCanalNotificacion() + "\n" +
                "Correlativo: " + correlativoCarta + "\n" +
                "Nro. Suministro: " + nroSuministro + "\n" +
                "Provincia: " + provincia.getNomProvincia() + "\n" +
                "Prioridad: " + prioridad.getNomPrioridad() + "\n" +
                "Estado: " + estado.getNomEstado() + "\n" +
                "Fecha Creación: " + fecCreacion + "\n" +
                "Fecha Emisión: " + fecEmision + "\n" +
                "Fecha Despacho: " + fecDespacho + "\n" +
                "Fecha Notif.: " + fecNotificiacion + "\n" +
                "Fecha fec. Notif. Carta: " + fecNotificacionCarta + "\n" +
                "Fecha Ult. Modificacion: " + fecUltimaModificacion + "\n" +
                "Fecha: " + fecha + "\n" +
                "Fecha Vcto. Legal: " + fecVencimientoLegal + "\n" +
                "Creado Por: " + creadoPor + "\n" +
                "Canal Registro: " + canalRegistro.getNomCanalRegistro() + "\n" +
                "Propietario Caso: " + propietarioCaso + "\n" +
                "Días Vencidos / Por Vencer: " + diasVencidosPorVencer + "\n";
    }
}
