package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.domain.DBConnection;
import com.example.gestiondepedidos.models.usuario.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.*;


/**
 * Controlador para la pantalla de inicio de sesión de la aplicación.
 */
public class LoginController implements Initializable {
    @FXML
    private Label info;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnAcceder;


    String email="";
    String password="";

    static String nombre_usuario;
    static Long id_usuario;
    Connection connection = DBConnection.getConnection();

    /**
     * Método que se ejecuta al inicializar el controlador.
     *
     * @param url La URL del archivo FXML.
     * @param resourceBundle El paquete de recursos.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                info.setText("Conexión a la base de datos exitosa");
                info.setStyle("-fx-text-fill: green");
            } else {
                info.setText("No se pudo establecer la conexión a la base de datos");
                info.setStyle("-fx-text-fill: red");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Método que se ejecuta al hacer clic en el botón de inicio de sesión.
     *
     * @param actionEvent El evento de acción.
     */
    @FXML
    public void iniciarSesion(ActionEvent actionEvent) {

        email = "juan@example.com";
        password = "1234";

//        email = txtEmail.getText();
//        password = txtPass.getText();

        if (!Objects.equals(email, "") && !Objects.equals(password, "")) {
            UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();
            Usuario usuario = UsuarioDAOImp.login(email, password);

            if (usuario != null) {
                System.out.println(usuario.getNombre()+": Logging in");
                id_usuario = usuario.getId();
                nombre_usuario = usuario.getNombre();

                try{
                    App.loadFXML("views/tablaPrincipal.fxml","Productos");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("User not found");
                alerta.setContentText("El nombre o la contraseña no coinciden");
                alerta.showAndWait();

                txtEmail.setText("");
                txtPass.setText("");
            }

        } else {
            info.setText("Campos vacíos !!!");
            info.setStyle("-fx-text-fill: red");
        }

    }
}