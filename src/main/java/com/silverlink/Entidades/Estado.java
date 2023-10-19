package com.silverlink.Entidades;

import static com.silverlink.Main.estados;

public class Estado {

    private short idEstado;
    private String nomEstado;

    public Estado(short idEstado) {
        this.idEstado = idEstado;
    }

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

    public void setIdEstado(short idEstado) {
        this.idEstado = idEstado;
    }

    public static Estado getEstado(String nomEstado) {
        for (Estado estado : estados) {
            if(estado.getNomEstado().equals(nomEstado)) {
                return estado;
            }
        }
        return null;
    }

    public static Estado getEstado(short idEstado) {
        for (Estado estado : estados) {
            if(estado.getIdEstado() == idEstado) {
                return estado;
            }
        }
        return null;
    }

}
