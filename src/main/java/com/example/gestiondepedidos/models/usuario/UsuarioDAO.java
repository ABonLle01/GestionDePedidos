package com.example.gestiondepedidos.models.usuario;

import java.util.ArrayList;

public interface UsuarioDAO {
    public Usuario load(Long id);

    public ArrayList<Usuario> loadAll();

}
