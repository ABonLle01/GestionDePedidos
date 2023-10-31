package com.example.gestiondepedidos;

import com.example.gestiondepedidos.domain.DBConnection;
import com.example.gestiondepedidos.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.*;
import java.util.*;



public class LoginController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnAcceder;

    String email="";
    String password="";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Connection connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conexión a la base de datos exitosa.");
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void iniciarSesion(ActionEvent actionEvent) {
        email = txtEmail.getText();
        password = txtPass.getText();

        try {
            Connection connection = DBConnection.getConnection();

            if(connection != null){
                String query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Logging in");
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("User not found");
                    alerta.showAndWait();
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            }else{
                System.out.println("Connection is null");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}