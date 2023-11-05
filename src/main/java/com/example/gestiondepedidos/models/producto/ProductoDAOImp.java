package com.example.gestiondepedidos.models.producto;

import com.example.gestiondepedidos.domain.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class ProductoDAOImp implements ProductoDAO{
    private static Connection connection;
    private ArrayList<Producto> productos;

    public ProductoDAOImp() {
        connection = DBConnection.getConnection();
        this.productos = new ArrayList<>();
    }

    @Override
    public Producto load(Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }


}
