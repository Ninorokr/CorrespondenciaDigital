package com.silverlink.Entidades;

public class Prioridad {

    private short idPrioridad;
    private String nomPrioridad;

    public Prioridad(short idPrioridad, String nomPrioridad) {
        this.idPrioridad = idPrioridad;
        this.nomPrioridad = nomPrioridad;
    }

    public short getIdPrioridad() {
        return idPrioridad;
    }

    public String getNomPrioridad() {
        return nomPrioridad;
    }
}
