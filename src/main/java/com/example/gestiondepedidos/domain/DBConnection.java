package com.example.gestiondepedidos.domain;

import com.example.gestiondepedidos.HelloApplication;

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

            cfg.load(HelloApplication.class.getClassLoader().getResourceAsStream("bbdd.properties"));
            logger.info("Configuración cargada");
            url="jdbc:mysql://"+cfg.getProperty("host")+":"+cfg.getProperty("port")+"/"+cfg.getProperty("dbname");
            logger.info(url);

            user = cfg.getProperty("user");
            password = cfg.getProperty("pass");

        } catch (IOException e) {
            logger.severe("Error procesando configuración");
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            logger.info("Error al conectar a la base de datos");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
