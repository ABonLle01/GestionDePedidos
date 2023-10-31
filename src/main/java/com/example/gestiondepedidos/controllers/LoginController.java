package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.domain.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.*;



public class LoginController implements Initializable {
    @FXML
    private Label info;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnAcceder;

    String nombreUsuario="";
    String email="";
    String password="";

    Connection connection;
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
            //connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void iniciarSesion(ActionEvent actionEvent) {

        email = txtEmail.getText();
        password = txtPass.getText();


        if(!Objects.equals(email, "") && !Objects.equals(password, "")){
            try {
                connection = DBConnection.getConnection();
                if(connection != null){
                    String query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, password);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        System.out.println("Logging in");

                        nombreUsuario = resultSet.getString("nombre");
                        System.out.println(nombreUsuario);
                        App.loadFXML("views/tablaPrincipal.fxml");

                        resultSet.close();
                        preparedStatement.close();
                        connection.close();

                    } else {
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("User not found");
                        alerta.setContentText("El nombre o la contraseña no coinciden");
                        alerta.showAndWait();

                        txtEmail.setText("");
                        txtPass.setText("");

                    }


                }else{
                    System.out.println("Connection is null");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            info.setText("Campos vacios !!!");
            info.setStyle("-fx-text-fill: red");
        }


    }


}