package com.silverlink.Entidades;

public class TipoAtencion {

    private short idTipoAtencion;
    private String nomTipoAtencion;

    public TipoAtencion(short idTipoAtencion, String nomTipoAtencion) {
        this.idTipoAtencion = idTipoAtencion;
        this.nomTipoAtencion = nomTipoAtencion;
    }

    public short getIdTipoAtencion() {
        return idTipoAtencion;
    }

    public String getNomTipoAtencion() {
        return nomTipoAtencion;
    }
}
