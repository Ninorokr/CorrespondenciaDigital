package com.silverlink.Entidades;

import static com.silverlink.Main.provincias;

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

    public static Provincia existeProvincia(String nomProvincia) {
        for (Provincia provincia : provincias) {
            if(provincia.getNomProvincia().equals(nomProvincia)) {
                return provincia;
            }
        }
        return null;
    }

    public static Provincia getProvincia(short idProvincia) {
        for (Provincia provincia : provincias) {
            if(provincia.getIdProvincia() == idProvincia) {
                return provincia;
            }
        }
        return null;
    }
}
