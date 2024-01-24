module com.example.gestiondepedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    exports com.example.gestiondepedidos;
    exports com.example.gestiondepedidos.domain;
    exports com.example.gestiondepedidos.controllers;

    opens com.example.gestiondepedidos.models.usuario to javafx.base;
    opens com.example.gestiondepedidos.models.item to javafx.base;
    opens com.example.gestiondepedidos.models.pedido to javafx.base;
    opens com.example.gestiondepedidos to javafx.fxml;
    opens com.example.gestiondepedidos.domain to javafx.fxml;
    opens com.example.gestiondepedidos.controllers to javafx.fxml;
}