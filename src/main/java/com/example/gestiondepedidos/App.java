package com.example.gestiondepedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Alvaro Bonilla Lledo
 * @version 2023-11-05
 *
 * Esta clase es el punto de entrada principal de la aplicación.
 * Se encarga de cargar el archivo FXML de la ventana de login y mostrarlo.
 * También proporciona un método para cargar otros archivos FXML.
 */
public class App extends Application {

    private static Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {
        myStage=stage;
        // Cargar la ventana de login
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/ventanaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),900,800);
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Carga un archivo FXML especificado y muestra la ventana correspondiente.
     *
     * @param fxml El nombre del archivo FXML a cargar.
     * @param titulo El título de la ventana a mostrar.
     */
    public static void loadFXML(String fxml,String titulo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(),900,800);
            myStage.setScene(scene);
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