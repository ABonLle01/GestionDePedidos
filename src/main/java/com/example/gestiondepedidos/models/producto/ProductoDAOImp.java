package com.example.gestiondepedidos.models.producto;

import com.example.gestiondepedidos.domain.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class ProductoDAOImp implements ProductoDAO{
    private static Connection connection;
    private ArrayList<Producto> productos;

    public ProductoDAOImp() {
        this.connection = DBConnection.getConnection();
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

    @Override
    public ArrayList<Producto> loadAll() {
        return new ArrayList<>(productos);
    }

    @Override
    public ArrayList<Producto> loadById(Long idUsuario) {
        ArrayList<Producto> productosById = new ArrayList<>();

        try {
            String sqlPedidos = "SELECT id_pedido FROM pedidos WHERE id_usuario = ?";
            PreparedStatement pstPedidos = connection.prepareStatement(sqlPedidos);
            pstPedidos.setLong(1, idUsuario);
            ResultSet rsPedidos = pstPedidos.executeQuery();

            while (rsPedidos.next()) {
                long idPedido = rsPedidos.getLong("id_pedido");

                String sqlProductos = "SELECT p.* FROM productos p INNER JOIN items i ON p.id_producto = i.producto WHERE i.codigo = ?";
                PreparedStatement pstProductos = connection.prepareStatement(sqlProductos);
                pstProductos.setLong(1, idPedido);
                ResultSet rsProductos = pstProductos.executeQuery();

                while (rsProductos.next()) {
                    Producto producto = new Producto();
                    producto.setId(rsProductos.getLong("id_producto"));
                    producto.setNombre(rsProductos.getString("nombre"));
                    producto.setPrecio(rsProductos.getInt("precio"));
                    producto.setCantidad_disponible(rsProductos.getInt("cantidad_disponible"));
                    productosById.add(producto);
                }

                rsProductos.close();
                pstProductos.close();
            }

            rsPedidos.close();
            pstPedidos.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productosById;
    }

}
