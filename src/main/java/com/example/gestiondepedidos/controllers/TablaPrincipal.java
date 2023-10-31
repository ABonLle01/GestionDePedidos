package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TablaPrincipal implements Initializable {
    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private Label nombre;
    @javafx.fxml.FXML
    private Button btnCerrar;

    private String nombreUsuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(nombreUsuario);
        nombre.setText(nombreUsuario);

    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        System.out.println("Logging out");

        App.loadFXML("views/ventanaLogin.fxml");
    }
}
