package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "GestorProductesIvanG";
    private static final String URL = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", HOST, PORT, DB_NAME);
    private static final String URL_SENSE_DB = String.format("jdbc:mysql://%s:%s/?serverTimezone=UTC", HOST, PORT);
    private static final String USER = "root";
    private static final String PASSWORD = "Ivan1234";

    public static Connection getNewConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexió establerta a la base de dades " + DB_NAME);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error de connexió: " + e.getMessage());
            return null;
        }
    }

    // Nou mètode per connexió sense especificar base de dades, per crear-la
    public static Connection getConnectionSenseBD() {
        try {
            Connection connection = DriverManager.getConnection(URL_SENSE_DB, USER, PASSWORD);
            System.out.println("Connexió establerta sense especificar base de dades");
            return connection;
        } catch (SQLException e) {
            System.out.println("Error de connexió sense base de dades: " + e.getMessage());
            return null;
        }
    }
}
