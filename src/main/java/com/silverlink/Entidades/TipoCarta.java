package com.silverlink.Entidades;

import static com.silverlink.Main.tiposAtencion;
import static com.silverlink.Main.tiposCarta;

public class TipoCarta {

    private short idTipoCarta;
    private String nomTipoCarta;

    public TipoCarta(short idTipoCarta, String nomTipoCarta) {
        this.idTipoCarta = idTipoCarta;
        this.nomTipoCarta = nomTipoCarta;
    }

    public short getIdTipoCarta() {
        return idTipoCarta;
    }

    public String getNomTipoCarta() {
        return nomTipoCarta;
    }

    public static TipoCarta existeTipoCarta(String nomTipoCarta) {
        for (TipoCarta tipoCarta : tiposCarta) {
            if (tipoCarta.getNomTipoCarta().equals(nomTipoCarta)) {
                return tipoCarta;
            }
        }
        return null;
    }

    public static TipoCarta getTipoCarta(short idTipoCarta){
        for (TipoCarta tipoCarta : tiposCarta) {
            if (tipoCarta.getIdTipoCarta() == idTipoCarta) {
                return tipoCarta;
            }
        }
        return null;
    }
}
