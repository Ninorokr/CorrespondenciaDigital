package com.silverlink.Entidades;

public class Estado {

    private short idEstado;
    private String nomEstado;

    public Estado(short idEstado, String nomEstado) {
        this.idEstado = idEstado;
        this.nomEstado = nomEstado;
    }

    public short getIdEstado() {
        return idEstado;
    }

    public String getNomEstado() {
        return nomEstado;
    }
}
