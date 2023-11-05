package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.App;
import com.example.gestiondepedidos.domain.DBConnection;
import com.example.gestiondepedidos.models.pedido.Pedido;
import com.example.gestiondepedidos.models.pedido.PedidoDAO;
import com.example.gestiondepedidos.models.pedido.PedidoDAOImp;
import com.example.gestiondepedidos.models.usuario.Usuario;
import com.example.gestiondepedidos.models.usuario.UsuarioDAO;
import com.example.gestiondepedidos.models.usuario.UsuarioDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static com.example.gestiondepedidos.controllers.LoginController.id_usuario;
import static com.example.gestiondepedidos.controllers.LoginController.nombre_usuario;

public class TablaController implements Initializable{
    @javafx.fxml.FXML
    private Label nombre;
    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private TableView<Pedido> tblPedidos;
    @javafx.fxml.FXML
    private TableColumn<Pedido, Long> id;
    @javafx.fxml.FXML
    private TableColumn<Pedido, Long> codigo;
    @javafx.fxml.FXML
    private TableColumn<Pedido, Date> fecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido, Integer> total;
    @javafx.fxml.FXML
    private Button btnCerrar;

    private final Connection connection = DBConnection.getConnection();
    private static final UsuarioDAO usuarioDAOImp = new UsuarioDAOImp();
    static Usuario usuario = usuarioDAOImp.load(id_usuario);
    private final PedidoDAO pedidoDAO = new PedidoDAOImp();

    static Long codigoProducto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (usuario != null) {
            nombre.setText(nombre_usuario);


            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            total.setCellValueFactory(new PropertyValueFactory<>("total"));

            ArrayList<Pedido> pedidosDelUsuario = pedidoDAO.loadById(id_usuario);

            if(pedidosDelUsuario.isEmpty()){
                info.setText("No se han encontrado pedidos");
                info.setStyle("-fx-text-fill: red");
            }else{
                mostrarPedidosEnTabla(pedidosDelUsuario);
                info.setText("Mostrando pedidos");
                info.setStyle("-fx-text-fill: green");
            }

        }else{
            System.out.println("User not found in database");

        }

    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) throws LoadException {
        System.out.println(usuario.getNombre()+": Logging out");
        nombre.setText("");
        App.loadFXML("views/ventanaLogin.fxml","Log in");
    }

    private void mostrarPedidosEnTabla(ArrayList<Pedido> pedidos) {
        ObservableList<Pedido> data = FXCollections.observableArrayList(pedidos);
        tblPedidos.setItems(data);
    }

    @javafx.fxml.FXML
    public void mostrarPedido(Event event) throws LoadException {
        Pedido p = tblPedidos.getSelectionModel().getSelectedItem();
        if(p!=null){
            codigoProducto = p.getCodigo();
            System.out.println("Selected item: "+p);
            App.loadFXML("views/tablaItems.fxml","Detalles");
        }

    }

}
