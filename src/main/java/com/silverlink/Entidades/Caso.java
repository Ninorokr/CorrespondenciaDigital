package com.silverlink.Entidades;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.silverlink.Utils.Commander.*;

public class Caso {

    private short anio;
    private short nroOS;
    private short idCaso;
    private TipoAtencion tipoAtencion;
    private TipoRegCaso tipoRegCaso;
    private String idActividad;
    private TipoCarta tipoCarta;
    private int nroCaso;
    private int nroCarta;
    private int nroActa;
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
    private LocalDateTime fecEmisionDateTime;
    private LocalDateTime fecDespacho;
    private LocalDateTime fecNotificacion;
    private LocalDateTime fecNotificacionCarta;
    private LocalDate fecUltimaModificacion;
    private LocalDate fecha;
    private LocalDateTime fecVencimientoLegal;
    private LocalDate fecDescarga;
    private Usuario creadoPor;
    private CanalRegistro canalRegistro;
    private Usuario propietarioCaso;
    private short diasVencidosPorVencer;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Acta> actas = new ArrayList<>();
    private ArrayList<String> correosCartas = new ArrayList<>();
    private ArrayList<String> correosActas = new ArrayList<>();
    private String correosCartasString;
    private String correosActasString;
    private boolean errorNroCarta;
    private boolean errorCorreoNotif;
    private boolean errorFechas;
    private boolean errorFaltaCartas;
    private boolean errorFaltaActas;
    private boolean descargadoEnSalesforce = false;
    private boolean archivosDescargados = false;
    private boolean errorFaltaFirma = false;
    private String mensajeError;
    private boolean isRevisado;

    public Caso(){

    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public short getNroOS() {
        return nroOS;
    }

    public void setNroOS(short nroOS) {
        this.nroOS = nroOS;
    }

    public short getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(short idCaso) {
        this.idCaso = idCaso;
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

    public void setNroCaso(int nroCaso) {
        this.nroCaso = nroCaso;
    }

    public void setNroCaso(String nroCaso) {
        this.nroCaso = Integer.parseInt(nroCaso);
    }

    public int getNroCarta() {
        return nroCarta;
    }

    public void setNroCarta(int nroCarta) {
        this.nroCarta = nroCarta;
    }

    public int getNroActa() {
        return nroActa;
    }

    public void setNroActa(int nroActa) {
        this.nroActa = nroActa;
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
        while (flag) {
            estado = Estado.getEstado(nomEstado);
            if (estado == null) {
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

    public LocalDateTime getFecEmisionDateTime() {
        return fecEmisionDateTime;
    }

    public Timestamp getFecEmisionDateTimeTimestamp() {
        if(fecEmisionDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(fecEmisionDateTime);
    }

    public void setFecEmisionDateTime(LocalDateTime dateTime) {
        this.fecEmisionDateTime = dateTime;
    }

    public LocalDateTime getFecDespacho() {
        return fecDespacho;
    }

    public Timestamp getFecDespachoTimestamp() {
        if(fecDespacho == null) {
            return null;
        }
        return Timestamp.valueOf(fecDespacho);
    }

    public void setFecDespacho(LocalDateTime fecDespacho) {
        this.fecDespacho = fecDespacho;
    }

    public LocalDateTime getFecNotificacion() {
        return fecNotificacion;
    }

    public Timestamp getFecNotificacionTimestamp() {
        if(fecNotificacion == null) {
            return null;
        }
        return Timestamp.valueOf(fecNotificacion);
    }

    public void setFecNotificacion(LocalDateTime fecNotificacion) {
        this.fecNotificacion = fecNotificacion;
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

    public LocalDateTime getFecVencimientoLegal() {
        return fecVencimientoLegal;
    }

    public void setFecVencimientoLegal(LocalDateTime fecVencimientoLegal) {
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

    public ArrayList<String> getCorreosCartas() {
        return correosCartas;
    }

    public String concatCorreosCartasString() {
        StringBuilder sb = new StringBuilder();
        if(correosCartas.size() == 1) {
            sb.append(correosCartas.get(0));
        } else {
            for(String correo : correosCartas) {
                if(correo.equals(correosCartas.get(correosCartas.size()-1)))
                    sb.append(correo);
                else
                    sb.append(correo).append("/");
            }
        }
        return sb.toString();
    }

    public String concatCorreosActasString() {
        StringBuilder sb = new StringBuilder();
        if(correosActas.size() == 1) {
            sb.append(correosActas.get(0));
        } else {
            for(String correo : correosActas) {
                if(correo.equals(correosActas.get(correosActas.size()-1)))
                    sb.append(correo);
                else
                    sb.append(correo).append("/");
            }
        }
        return sb.toString();
    }

    public void setCorreosCartas(ArrayList<String> correosCartas) {
        this.correosCartas = correosCartas;
    }

    public ArrayList<String> getCorreosActas() {
        return correosActas;
    }

    public void setCorreosActas(ArrayList<String> correosActas) {
        this.correosActas = correosActas;
    }

    public String getCorreosCartasString() {
        return correosCartasString;
    }

    public void setCorreosCartasString(String correosCartasString) {
        this.correosCartasString = correosCartasString;
    }

    public String getCorreosActasString() {
        return correosActasString;
    }

    public void setCorreosActasString(String correosActasString) {
        this.correosActasString = correosActasString;
    }

    public boolean isErrorNroCarta() {
        return errorNroCarta;
    }

    public void setErrorNroCarta(boolean errorNroCarta) {
        this.errorNroCarta = errorNroCarta;
    }

    public boolean isErrorCorreoNotif() {
        return errorCorreoNotif;
    }

    public void setErrorCorreoNotif(boolean errorCorreoNotif) {
        this.errorCorreoNotif = errorCorreoNotif;
    }

    public boolean isErrorFechas() {
        return errorFechas;
    }

    public void setErrorFechas(boolean errorFechas) {
        this.errorFechas = errorFechas;
    }

    public boolean isDescargadoEnSalesforce() {
        return descargadoEnSalesforce;
    }

    public void setDescargadoEnSalesforce(boolean descargadoEnSalesforce) {
        this.descargadoEnSalesforce = descargadoEnSalesforce;
    }

    public boolean isArchivosDescargados() {
        return archivosDescargados;
    }

    public void setArchivosDescargados(boolean archivosDescargados) {
        this.archivosDescargados = archivosDescargados;
    }

    public boolean isErrorFaltaCartas() {
        return errorFaltaCartas;
    }

    public void setErrorFaltaCartas(boolean errorFaltaCartas) {
        this.errorFaltaCartas = errorFaltaCartas;
    }

    public boolean isErrorFaltaActas() {
        return errorFaltaActas;
    }

    public void setErrorFaltaActas(boolean errorFaltaActas) {
        this.errorFaltaActas = errorFaltaActas;
    }

    public boolean isErrorFaltaFirma() {
        return errorFaltaFirma;
    }

    public void setErrorFaltaFirma(boolean errorFaltaFirma) {
        this.errorFaltaFirma = errorFaltaFirma;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public boolean isRevisado() {
        return isRevisado;
    }

    public void setRevisado(boolean revisado) {
        isRevisado = revisado;
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
                "Fecha Notif.: " + fecNotificacion + "\n" +
                "Fecha fec. Notif. Carta: " + fecNotificacionCarta + "\n" +
                "Fecha Ult. Modificacion: " + fecUltimaModificacion + "\n" +
                "Fecha: " + fecha + "\n" +
                "Fecha Vcto. Legal: " + fecVencimientoLegal + "\n" +
                "Creado Por: " + creadoPor + "\n" +
                "Canal Registro: " + canalRegistro.getNomCanalRegistro() + "\n" +
                "Propietario Caso: " + propietarioCaso + "\n" +
                "Días Vencidos / Por Vencer: " + diasVencidosPorVencer + "\n";
    }

    public String getIdCompleto() {
        String nroOS = String.format("%04d", this.nroOS);
        String item = String.format("%04d", this.idCaso);

        return "002" + this.anio + nroOS + item;
    }

    public String getFormattedId() {
        String nroOS = String.format("%04d", this.nroOS);
        String item = String.format("%04d", this.idCaso);

        return "002-" + this.anio + "-" + nroOS + "-" + item;
    }
}
