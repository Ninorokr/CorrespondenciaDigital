package com.silverlink.Entidades;

public class CanalNotificacion {

    private short idCanalNotificacion;
    private String nomCanalNotificacion;

    public CanalNotificacion(short idCanalNotificacion, String nomCanalNotificacion) {
        this.idCanalNotificacion = idCanalNotificacion;
        this.nomCanalNotificacion = nomCanalNotificacion;
    }

    public short getIdCanalNotificacion() {
        return idCanalNotificacion;
    }

    public String getNomCanalNotificacion() {
        return nomCanalNotificacion;
    }
}
