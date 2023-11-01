package com.example.gestiondepedidos.models.producto;

import java.util.ArrayList;

public interface ProductoDAO {
    public Producto load(Long id);

    public ArrayList<Producto> loadAll();

    ArrayList<Producto> loadById(Long idUsuario);
}
