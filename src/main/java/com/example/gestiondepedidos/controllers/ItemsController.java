package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.models.item.Item;
import com.example.gestiondepedidos.models.item.ItemDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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

/**
 * Controlador para la pantalla de detalles de los items de un pedido.
 */
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

    /**
     * Método que se ejecuta al inicializar el controlador.
     *
     * @param url La URL del archivo FXML.
     * @param resourceBundle El paquete de recursos.
     */
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

    /**
     * Método que muestra los items del pedido en la tabla.
     *
     * @param items Los items del pedido.
     */
    private void mostrarItem(ArrayList<Item> items) {
        ObservableList<Item> data = FXCollections.observableArrayList(items);
        tabla.setItems(data);
    }

    /**
     * Método que vuelve a la pantalla de pedidos.
     *
     * @param actionEvent El evento de acción.
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent){
        App.loadFXML("views/tablaPrincipal.fxml","Pedidos");
    }

    /**
     * Método que cierra la aplicación.
     *
     * @param actionEvent El evento de acción.
     */
    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent){
        System.out.println(nombre_usuario+": Logging out");
        App.loadFXML("views/ventanaLogin.fxml","Log in");
    }


}
