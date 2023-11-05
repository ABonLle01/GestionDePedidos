package com.example.gestiondepedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {
        myStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/ventanaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),900,800);
        stage.setTitle("Log in");
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();
    }

    public static void loadFXML(String fxml,String titulo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(),900,800);
            myStage.setScene(scene);
            myStage.setMaximized(false);
            //myStage.setMaximized(true);
            myStage.setTitle(titulo);
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        launch();
    }
}