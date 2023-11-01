module com.example.gestiondepedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    opens com.example.gestiondepedidos.models.producto to javafx.base;
    opens com.example.gestiondepedidos to javafx.fxml;
    exports com.example.gestiondepedidos;
    exports com.example.gestiondepedidos.domain;
    opens com.example.gestiondepedidos.domain to javafx.fxml;
    exports com.example.gestiondepedidos.controllers;
    opens com.example.gestiondepedidos.controllers to javafx.fxml;
}