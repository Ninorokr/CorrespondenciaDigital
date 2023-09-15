package com.silverlink.Entidades;

import static com.silverlink.Main.tiposRegCaso;
import static com.silverlink.Main.usuarios;

public class Usuario {

    private short idUsuario;
    private String codUsuario;
    private String nomUsuario;

    public Usuario(short idUsuario, String codUsuario, String nomUsuario) {
        this.idUsuario = idUsuario;
        this.codUsuario = codUsuario;
        this.nomUsuario = nomUsuario;
    }

    public short getIdUsuario() {
        return idUsuario;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public static Usuario existeUsuario (String codUsuario) {
        for (Usuario usuario : usuarios) {
            if(usuario.getNomUsuario().equals(codUsuario)) {
                return usuario;
            }
        }
        return null;
    }
}
