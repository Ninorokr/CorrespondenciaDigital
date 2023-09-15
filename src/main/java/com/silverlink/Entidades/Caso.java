package com.silverlink.Entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.silverlink.Commander.insertTipoAtencion;
import static com.silverlink.Commander.insertTipoRegCaso;
import static com.silverlink.Main.tiposAtencion;

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
    private Usuario CreadoPor;
    private CanalRegistro canalRegistro;
    private Usuario PropietarioCaso;
    private short diasVencidosPorVencer;

    public Caso(){

    }

    public Caso(TipoAtencion tipoAtencion, TipoRegCaso tipoRegCaso, String idActividad, TipoCarta tipoCarta, int nroCaso, EstadoCaso estadoCaso, CanalNotificacion canalNotificacion, LocalDate fecCreacionCaso, short correlativoCarta, int nroSuministro, Provincia provincia, Prioridad prioridad, LocalDate fecCreacion, LocalDateTime fecNotificacionCarta, LocalDate fecUltimaModificacion, LocalDate fecha, LocalDate fecVencimientoLegal, Usuario creadoPor, CanalRegistro canalRegistro, Usuario propietarioCaso, short diasVencidosPorVencer) {
        //Constructor para caso obtenido desde Excel para importar a BD
        this.tipoAtencion = tipoAtencion;
        this.tipoRegCaso = tipoRegCaso;
        this.idActividad = idActividad;
        this.tipoCarta = tipoCarta;
        this.nroCaso = nroCaso;
        this.estadoCaso = estadoCaso;
        this.canalNotificacion = canalNotificacion;
        this.fecCreacionCaso = fecCreacionCaso;
        this.correlativoCarta = correlativoCarta;
        this.nroSuministro = nroSuministro;
        this.provincia = provincia;
        this.prioridad = prioridad;
        this.fecCreacion = fecCreacion;
        this.fecNotificacionCarta = fecNotificacionCarta;
        this.fecUltimaModificacion = fecUltimaModificacion;
        this.fecha = fecha;
        this.fecVencimientoLegal = fecVencimientoLegal;
        CreadoPor = creadoPor;
        this.canalRegistro = canalRegistro;
        PropietarioCaso = propietarioCaso;
        this.diasVencidosPorVencer = diasVencidosPorVencer;
    }

    public Caso(TipoAtencion tipoAtencion, TipoRegCaso tipoRegCaso, String idActividad, TipoCarta tipoCarta, int nroCaso, EstadoCaso estadoCaso, CanalNotificacion canalNotificacion, LocalDate fecCreacionCaso, short correlativoCarta, int nroSuministro, Provincia provincia, Prioridad prioridad, Estado estado, LocalDate fecCreacion, LocalDate fecEmision, LocalDateTime fecDespacho, LocalDateTime fecNotificiacion, LocalDateTime fecNotificacionCarta, LocalDate fecUltimaModificacion, LocalDate fecha, LocalDate fecVencimientoLegal, Usuario creadoPor, CanalRegistro canalRegistro, Usuario propietarioCaso, short diasVencidosPorVencer) {
        //Constructor para caso obtenido a BD para exportar a reporte
        this.tipoAtencion = tipoAtencion;
        this.tipoRegCaso = tipoRegCaso;
        this.idActividad = idActividad;
        this.tipoCarta = tipoCarta;
        this.nroCaso = nroCaso;
        this.estadoCaso = estadoCaso;
        this.canalNotificacion = canalNotificacion;
        this.fecCreacionCaso = fecCreacionCaso;
        this.correlativoCarta = correlativoCarta;
        this.nroSuministro = nroSuministro;
        this.provincia = provincia;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fecCreacion = fecCreacion;
        this.fecEmision = fecEmision;
        this.fecDespacho = fecDespacho;
        this.fecNotificiacion = fecNotificiacion;
        this.fecNotificacionCarta = fecNotificacionCarta;
        this.fecUltimaModificacion = fecUltimaModificacion;
        this.fecha = fecha;
        this.fecVencimientoLegal = fecVencimientoLegal;
        CreadoPor = creadoPor;
        this.canalRegistro = canalRegistro;
        PropietarioCaso = propietarioCaso;
        this.diasVencidosPorVencer = diasVencidosPorVencer;
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
                insertTipoRegCaso(nomtipoRegCaso);
            } else {
                flag = false;
            }
        }
        this.tipoRegCaso = tipoRegCaso;
    }

    public int getNroCaso() {
        return nroCaso;
    }

    public void setNroCaso(int nroCaso) {
        this.nroCaso = nroCaso;
    }

    public EstadoCaso getEstadoCaso() {
        return estadoCaso;
    }

    public void setEstadoCaso(EstadoCaso estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    public CanalNotificacion getCanalNotificacion() {
        return canalNotificacion;
    }

    public void setCanalNotificacion(CanalNotificacion canalNotificacion) {
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

    public void setNroSuministro(int nroSuministro) {
        this.nroSuministro = nroSuministro;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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
        return CreadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        CreadoPor = creadoPor;
    }

    public CanalRegistro getCanalRegistro() {
        return canalRegistro;
    }

    public void setCanalRegistro(CanalRegistro canalRegistro) {
        this.canalRegistro = canalRegistro;
    }

    public Usuario getPropietarioCaso() {
        return PropietarioCaso;
    }

    public void setPropietarioCaso(Usuario propietarioCaso) {
        PropietarioCaso = propietarioCaso;
    }

    public short getDiasVencidosPorVencer() {
        return diasVencidosPorVencer;
    }

    public void setDiasVencidosPorVencer(short diasVencidosPorVencer) {
        this.diasVencidosPorVencer = diasVencidosPorVencer;
    }
}
