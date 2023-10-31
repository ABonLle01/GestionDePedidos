package com.example.gestiondepedidos.domain;

import com.example.gestiondepedidos.App;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {

    private static Connection connection;

    private static Logger logger;

    static{
        logger = Logger.getLogger(Database.class.getName());

        String url;
        String user;
        String password;

        var cfg = new Properties();
        try {

            cfg.load(App.class.getClassLoader().getResourceAsStream("bbdd.properties"));
            logger.info("Configuración cargada");
            url="jdbc:mysql://"+cfg.getProperty("host")+":"+cfg.getProperty("port")+"/"+cfg.getProperty("dbname");
            logger.info(url);

            user = cfg.getProperty("user");
            password = cfg.getProperty("pass");

        } catch (IOException e) {
            logger.severe("Error processing configuration");
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(url, user, password);

            logger.info("Successful connection to database");
        } catch (SQLException e) {
            logger.info("Error trying to connect to database");
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
