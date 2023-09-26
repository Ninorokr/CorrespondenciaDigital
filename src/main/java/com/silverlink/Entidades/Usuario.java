package com.silverlink.Entidades;

import static com.silverlink.Utils.Commander.updateCodUsuario;
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

    public static Usuario existeUsuario (String codUsuario, String nomUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNomUsuario().equalsIgnoreCase(nomUsuario)) {
                if (usuario.getCodUsuario() == null) {
                    updateCodUsuario(codUsuario, nomUsuario);
                }
                return usuario;
            }
        }

        for (Usuario usuario : usuarios) {
            if (usuario.codUsuario == null){ continue; }
            if (usuario.getCodUsuario().equalsIgnoreCase(codUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public static Usuario existeUsuarioCodigo (String codUsuario) {
        for (Usuario usuario : usuarios) {
            if(usuario.getCodUsuario().equals(codUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public static Usuario existeUsuarioNombre (String nomUsuario) {
        for (Usuario usuario : usuarios) {
            if(usuario.getNomUsuario().equalsIgnoreCase(nomUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return codUsuario + " | " + nomUsuario;
    }
}
