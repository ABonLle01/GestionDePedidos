package com.example.gestiondepedidos.models.pedido;

import com.example.gestiondepedidos.domain.DBConnection;
import com.example.gestiondepedidos.models.producto.Producto;

import java.sql.*;
import java.util.ArrayList;

public class PedidoDAOImp implements PedidoDAO {

    private static Connection connection;

    public PedidoDAOImp() {
        this.connection = DBConnection.getConnection();
    }

    private static final String QUERY_PEDIDOS = "SELECT * FROM pedidos WHERE id_usuario = ?";

    @Override
    public ArrayList<Pedido> loadById(Long idUsuario) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try(PreparedStatement pst = connection.prepareStatement(QUERY_PEDIDOS)) {
            pst.setLong(1, idUsuario);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getLong("id_pedido"));
                pedido.setCodigo(resultSet.getLong("codigo_pedido"));
                pedido.setFecha(String.valueOf(resultSet.getDate("fecha")));
                pedido.setTotal(resultSet.getInt("total"));

//                ArrayList<Producto> productos = loadProductos(pedido.getId());
//                pedido.setProductos(productos);

                pedidos.add(pedido);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }



    private ArrayList<Producto> loadProductos(Long pedidoId) {
        ArrayList<Producto> productos = new ArrayList<>();

        try (PreparedStatement pst = connection.prepareStatement("SELECT pr.* FROM productos pr " +
                "INNER JOIN items pi ON pr.id = pi.id_producto " +
                "WHERE pi.id = ?")) {
            pst.setLong(1, pedidoId);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setId(resultSet.getLong("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setPrecio(resultSet.getInt("precio"));
                producto.setCantidad_disponible(resultSet.getInt("cantidad_disponible"));

                productos.add(producto);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }


}
