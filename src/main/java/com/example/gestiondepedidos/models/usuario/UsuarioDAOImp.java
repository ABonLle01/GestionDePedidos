package com.example.gestiondepedidos.models.usuario;

import com.example.gestiondepedidos.domain.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que implementa la interfaz `UsuarioDAO` y proporciona métodos para realizar operaciones con usuarios en la base de datos.
 */
public class UsuarioDAOImp implements UsuarioDAO{
    //Conexion a la base de datos
    private static Connection connection;

    //Constructor que inicializa la conexion a la base de datos
    public UsuarioDAOImp() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Método que carga un usuario de la base de datos por su identificador.
     *
     * @param id Identificador del usuario.
     * @return Usuario cargado de la base de datos o `null` si no se encuentra ningún usuario.
     */
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

    /**
     * Método que autentica a un usuario por su correo electrónico y contraseña.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return Usuario autenticado o `null` si no se encuentra ningún usuario con el correo electrónico y contraseña especificados.
     */
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
