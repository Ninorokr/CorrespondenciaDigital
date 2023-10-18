package com.silverlink.Entidades;

import static com.silverlink.Main.canalesNotificacion;

public class CanalNotificacion {

    private short idCanalNotificacion;
    private String nomCanalNotificacion;

    public CanalNotificacion(short idCanalNotificacion, String nomCanalNotificacion) {
        this.idCanalNotificacion = idCanalNotificacion;
        this.nomCanalNotificacion = nomCanalNotificacion;
    }

    public short getIdCanalNotificacion() {
        return idCanalNotificacion;
    }

    public String getNomCanalNotificacion() {
        return nomCanalNotificacion;
    }

    public static CanalNotificacion existeCanalNotificacion(String nomCanalNotificacion) {
        for (CanalNotificacion cn : canalesNotificacion) {
            if(cn.getNomCanalNotificacion().equals(nomCanalNotificacion)) {
                return cn;
            }
        }
        return null;
    }

    public static CanalNotificacion getCanalNotificacion(short idCanalNotificacion) {
        for (CanalNotificacion cn : canalesNotificacion) {
            if(cn.getIdCanalNotificacion() == idCanalNotificacion) {
                return cn;
            }
        }
        return null;
    }
}
