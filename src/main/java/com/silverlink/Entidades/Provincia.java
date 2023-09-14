package com.silverlink.Entidades;

public class Provincia {

    private short idProvincia;
    private String nomProvincia;

    public Provincia(short idProvincia, String nomProvincia) {
        this.idProvincia = idProvincia;
        this.nomProvincia = nomProvincia;
    }

    public short getIdProvincia() {
        return idProvincia;
    }

    public String getNomProvincia() {
        return nomProvincia;
    }
}
