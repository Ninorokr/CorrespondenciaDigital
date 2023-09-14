package com.silverlink.Entidades;

public class TipoRegCaso {

    private short idTipoRegCaso;
    private String nomTipoRegCaso;

    public TipoRegCaso(short idTipoAtencion, String nomTipoAtencion) {
        this.idTipoRegCaso = idTipoAtencion;
        this.nomTipoRegCaso = nomTipoAtencion;
    }

    public short getIdTipoRegCaso() {
        return idTipoRegCaso;
    }

    public String getNomTipoRegCaso() {
        return nomTipoRegCaso;
    }
}
