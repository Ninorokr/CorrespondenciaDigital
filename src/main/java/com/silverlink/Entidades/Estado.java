package com.silverlink.Entidades;

import static com.silverlink.Main.estados;

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

    public static Estado existeEstado(String nomEstado) {
        for (Estado estado : estados) {
            if(estado.getNomEstado().equals(nomEstado)) {
                return estado;
            }
        }
        return null;
    }

}
