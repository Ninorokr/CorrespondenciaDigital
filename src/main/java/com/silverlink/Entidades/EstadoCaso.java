package com.silverlink.Entidades;

public class EstadoCaso {

    private short idEstadoCaso;
    private String nomEstadoCaso;

    public EstadoCaso(short idEstadoCaso, String nomEstadoCaso) {
        this.idEstadoCaso = idEstadoCaso;
        this.nomEstadoCaso = nomEstadoCaso;
    }

    public short getIdEstadoCaso() {
        return idEstadoCaso;
    }

    public String getNomEstadoCaso() {
        return nomEstadoCaso;
    }
}
