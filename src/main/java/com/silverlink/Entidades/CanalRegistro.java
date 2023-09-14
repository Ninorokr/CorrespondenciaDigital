package com.silverlink.Entidades;

public class CanalRegistro {

    private short idCanalRegistro;
    private String nomCanalRegistro;

    public CanalRegistro(short idCanalRegistro, String nomCanalRegistro) {
        this.idCanalRegistro = idCanalRegistro;
        this.nomCanalRegistro = nomCanalRegistro;
    }

    public short getIdCanalRegistro() {
        return idCanalRegistro;
    }

    public String getNomCanalRegistro() {
        return nomCanalRegistro;
    }
}
