package com.example.gestiondepedidos.models.usuario;

import java.util.ArrayList;

public class UsuarioDAOImp implements UsuarioDAO{

    private ArrayList<Usuario> usuarios;

    public UsuarioDAOImp(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public Usuario load(Long id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> loadAll() {
        return usuarios;
    }

}
