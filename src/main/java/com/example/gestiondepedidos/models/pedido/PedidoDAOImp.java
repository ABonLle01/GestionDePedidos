package com.example.gestiondepedidos.models.pedido;

import com.example.gestiondepedidos.domain.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Clase que implementa la interfaz `PedidoDAO` y proporciona métodos para realizar operaciones con pedidos en la base de datos.
 */
public class PedidoDAOImp implements PedidoDAO {

    //Conexion a la base de datos.
    private static Connection connection;

    //Constructor que inicializa la conexion a la base de datos.
    public PedidoDAOImp() {
        this.connection = DBConnection.getConnection();
    }

    //Consulta SQL para cargar los pedidos de la base de datos por el identificador del usuario.
    private static final String QUERY_PEDIDOS = "SELECT * FROM pedidos WHERE id_usuario = ?";

    /**
     * Método que carga los pedidos de un usuario de la base de datos por su identificador.
     *
     * @param idUsuario Identificador del usuario.
     * @return Lista de pedidos del usuario cargados de la base de datos o una lista vacía si no se encuentran pedidos para el usuario especificado.
     */
    @Override
    public ArrayList<Pedido> loadById(Long idUsuario) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try(PreparedStatement pst = connection.prepareStatement(QUERY_PEDIDOS)) {
            pst.setLong(1, idUsuario);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setCodigo(resultSet.getLong("codigo_pedido"));
                pedido.setFecha(String.valueOf(resultSet.getDate("fecha")));
                pedido.setTotal(resultSet.getInt("total"));

                pedidos.add(pedido);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }


}
