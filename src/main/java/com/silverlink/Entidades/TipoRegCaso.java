package com.silverlink.Entidades;

import static com.silverlink.Main.tiposCarta;
import static com.silverlink.Main.tiposRegCaso;

public class TipoRegCaso {

    private short idTipoRegCaso;
    private String nomTipoRegCaso;

    public TipoRegCaso(short idTipoAtencion, String nomTipoAtencion) {
        this.idTipoRegCaso = idTipoAtencion;
        this.nomTipoRegCaso = nomTipoAtencion;
    }

    public short getIdTipoRegCaso() {
        return idTipoRegCaso;
    }

    public String getNomTipoRegCaso() {
        return nomTipoRegCaso;
    }

    public static TipoRegCaso existeTipoRegCaso(String nomTipoRegCaso) {
        for (TipoRegCaso tipoRegCaso : tiposRegCaso) {
            if(tipoRegCaso.getNomTipoRegCaso().equals(nomTipoRegCaso)) {
                return tipoRegCaso;
            }
        }
        return null;
    }

    public static TipoRegCaso getTipoRegCaso(short idTipoRegCaso) {
        for (TipoRegCaso tipoRegCaso : tiposRegCaso) {
            if(tipoRegCaso.getIdTipoRegCaso() == idTipoRegCaso) {
                return tipoRegCaso;
            }
        }
        return null;
    }
}
