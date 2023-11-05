package com.example.gestiondepedidos.models.item;

import com.example.gestiondepedidos.domain.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImp implements ItemDAO{

    private static Connection connection;

    private ArrayList<Item> items;
    public ItemDAOImp() {
        connection = DBConnection.getConnection();
        this.items=new ArrayList<>();
    }

    private static final String QUERY_ITEMS = "SELECT * FROM items WHERE codigo = ?";

    private static final String nProductos = "SELECT * FROM productos WHERE id_producto = ?";

    @Override
    public Item load(Long codigo) {
        for (Item item : items) {
            if (item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        return null;
    }

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
