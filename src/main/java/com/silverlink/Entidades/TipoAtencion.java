package com.silverlink.Entidades;

import static com.silverlink.Main.tiposAtencion;

public class TipoAtencion {

    private short idTipoAtencion;
    private String nomTipoAtencion;

    public TipoAtencion(short idTipoAtencion, String nomTipoAtencion) {
        this.idTipoAtencion = idTipoAtencion;
        this.nomTipoAtencion = nomTipoAtencion;
    }

    public short getIdTipoAtencion() {
        return idTipoAtencion;
    }

    public String getNomTipoAtencion() {
        return nomTipoAtencion;
    }

    public static TipoAtencion existeTipoAtencion(String nomTipoAtencion) {
        for (TipoAtencion tipoAtencion : tiposAtencion) {
            if(tipoAtencion.getNomTipoAtencion().equals(nomTipoAtencion)) {
                return tipoAtencion;
            }
        }
        return null;
    }
}
