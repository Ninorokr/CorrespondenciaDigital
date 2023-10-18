package com.silverlink.Entidades;

import static com.silverlink.Main.estadosCaso;

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

    public static EstadoCaso existeEstadoCaso(String nomEstadoCaso) {
        for (EstadoCaso estadoCaso : estadosCaso) {
            if(estadoCaso.getNomEstadoCaso().equals(nomEstadoCaso)) {
                return estadoCaso;
            }
        }
        return null;
    }

    public static EstadoCaso getEstadoCaso(short idEstadoCaso) {
        for (EstadoCaso estadoCaso : estadosCaso) {
            if(estadoCaso.getIdEstadoCaso() == idEstadoCaso) {
                return estadoCaso;
            }
        }
        return null;
    }
}
