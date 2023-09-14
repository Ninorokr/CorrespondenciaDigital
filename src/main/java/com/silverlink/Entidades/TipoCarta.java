package com.silverlink.Entidades;

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
}
