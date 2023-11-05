package com.example.gestiondepedidos.models.item;

import com.example.gestiondepedidos.domain.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que implementa la interfaz `ItemDAO` y proporciona métodos para realizar operaciones con items en la base de datos.
 */
public class ItemDAOImp implements ItemDAO{

    //Conexion a la base de datos.
    private static Connection connection;

    //Lista de items cargados de la base de datos.
    private ArrayList<Item> items;

    //Constructor que inicializa la conexión a la base de datos y la lista de items.
    public ItemDAOImp() {
        connection = DBConnection.getConnection();
        this.items=new ArrayList<>();
    }

    //Consulta SQL para cargar items de la base de datos por su código.
    private static final String QUERY_ITEMS = "SELECT * FROM items WHERE codigo = ?";

    //Consulta SQL para cargar el nombre de un producto de la base de datos por su identificador.
    private static final String nProductos = "SELECT * FROM productos WHERE id_producto = ?";

    /**
     * Método que carga los items de un pedido de la base de datos por su código.
     *
     * @param codigo Código del pedido.
     * @return Lista de items del pedido cargados de la base de datos o una lista vacía si no se encuentran items para el pedido especificado.
     */
    @Override
    public ArrayList<Item> loadById(Long codigo) {
        ArrayList<Item> items = new ArrayList<>();

        try(PreparedStatement pst = connection.prepareStatement(QUERY_ITEMS)) {
            pst.setLong(1, codigo);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getLong("id_item"));
                item.setCodigo(resultSet.getLong("codigo"));
                item.setCantidad(resultSet.getInt("cantidad"));
                item.setNombre_producto(resultSet.getString("producto"));

                items.add(item);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }


    /**
     * Método que carga el nombre de un producto de la base de datos por su identificador.
     *
     * @param id Identificador del producto.
     * @return Nombre del producto o `null` si no se encuentra el producto con el identificador especificado.
     */
    public String loadName(Long id){
        try(PreparedStatement pst = connection.prepareStatement(nProductos)){
            pst.setLong(1,id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("nombre");
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
