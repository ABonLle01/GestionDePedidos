package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.models.item.Item;
import com.example.gestiondepedidos.models.item.ItemDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.gestiondepedidos.controllers.LoginController.nombre_usuario;
import static com.example.gestiondepedidos.controllers.TablaController.codigoProducto;
import static com.example.gestiondepedidos.controllers.TablaController.usuario;

public class ItemsController implements Initializable {


    @javafx.fxml.FXML
    private TableView<Item> tabla;
    @javafx.fxml.FXML
    private TableColumn<Item, Long> id;
    @javafx.fxml.FXML
    private TableColumn<Item, Long> codigo;
    @javafx.fxml.FXML
    private TableColumn<Item, Integer> cantidad;
    @javafx.fxml.FXML
    private TableColumn<Item, String> nProducto;
    @javafx.fxml.FXML
    private Label nombre;
    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private Button btnVolver;
    @javafx.fxml.FXML
    private Button btnCerrar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nombre.setText(nombre_usuario);
        info.setText("");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        nProducto.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));

        ItemDAOImp itemDAO = new ItemDAOImp();

        ArrayList<Item> items = itemDAO.loadById(codigoProducto);

        for (Item item : items) {
            String nombreProducto = itemDAO.loadName(item.getCodigo());
            if (nombreProducto != null) {
                item.setNombre_producto(nombreProducto);
            }
        }

        mostrarItem(items);

    }

    private void mostrarItem(ArrayList<Item> items) {
        ObservableList<Item> data = FXCollections.observableArrayList(items);
        tabla.setItems(data);
    }

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) throws LoadException {
        App.loadFXML("views/tablaPrincipal.fxml","Pedidos");
    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) throws LoadException {
        System.out.println(nombre_usuario+": Logging out");
        App.loadFXML("views/ventanaLogin.fxml","Log in");
    }


}
