package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.models.usuario.*;
import com.example.gestiondepedidos.models.producto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.gestiondepedidos.controllers.LoginController.id_usuario;

public class TablaController implements Initializable{
    @javafx.fxml.FXML
    private Label nombre;
    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private TableView<Producto> tblProductos;
    @javafx.fxml.FXML
    private Button btnCerrar;

    private UsuarioDAO usuarioDAOImp = new UsuarioDAOImp();
    private Usuario usuario = usuarioDAOImp.load(id_usuario);

    private ProductoDAO productoDAO = new ProductoDAOImp();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (usuario != null) {
            nombre.setText(usuario.getNombre());
            info.setText(usuario.toString());

            ArrayList<Producto> productosDelUsuario = productoDAO.loadById(id_usuario);
            mostrarProductosEnTabla(productosDelUsuario);

        }else{
            System.out.println("User not found in database");
        }

    }


    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        System.out.println(usuario.getNombre()+": Logging out");

        App.loadFXML("views/ventanaLogin.fxml");
    }

    private void mostrarProductosEnTabla(ArrayList<Producto> productos) {
        ObservableList<Producto> data = FXCollections.observableArrayList(productos);
        tblProductos.setItems(data);
    }

}
