package com.silverlink.Entidades;

import static com.silverlink.Main.prioridades;

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

    public static Prioridad existePrioridad(String nomPrioridad) {
        for (Prioridad prioridad : prioridades) {
            if(prioridad.getNomPrioridad().equals(nomPrioridad)) {
                return prioridad;
            }
        }
        return null;
    }

    public static Prioridad getPrioridad(short idPrioridad) {
        for (Prioridad prioridad : prioridades) {
            if(prioridad.getIdPrioridad() == idPrioridad) {
                return prioridad;
            }
        }
        return null;
    }
}
