package com.example.gestiondepedidos.models.usuario;

import com.example.gestiondepedidos.domain.DBConnection;

import java.sql.*;

import java.util.ArrayList;

public class UsuarioDAOImp implements UsuarioDAO{

    private static Connection connection;


    public UsuarioDAOImp() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public Usuario load(Long id) {
        try {
            String query = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setLong(1, id);

            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ArrayList<Usuario> loadAll() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            String query = "SELECT * FROM usuarios";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Usuario usuario = new Usuario(
                    resultSet.getLong("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }


    public static Usuario login(String email, String password) {
        try {
            String query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Usuario(
                    resultSet.getLong("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
