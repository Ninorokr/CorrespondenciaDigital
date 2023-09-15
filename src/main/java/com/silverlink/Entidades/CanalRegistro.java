package com.silverlink.Entidades;

import static com.silverlink.Main.canalesRegistro;

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

    public static CanalRegistro existeCanalRegistro(String nomCanalRegistro) {
        for (CanalRegistro cr : canalesRegistro) {
            if(cr.getNomCanalRegistro().equals(nomCanalRegistro)) {
                return cr;
            }
        }
        return null;
    }
}
