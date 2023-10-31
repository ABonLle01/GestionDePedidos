package com.example.gestiondepedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {
        myStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/ventanaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void loadFXML(String fxml) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(),800,700);
            myStage.setScene(scene);
            myStage.setMaximized(true);
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        launch();
    }
}