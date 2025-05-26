/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ivan-gallardo
 */
public class ConnectDB {
       private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "GestorProductesIvanG";
    private static final String URL = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DB_NAME);
    private static final String USER = "root";
    private static final String PASSWORD = "1234ivan";

    public static Connection getNewConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexió establerta.");
            return connection;
        } catch (SQLException e) {
            System.out.println("Error de connexió: " + e.getMessage());
            return null;
        }
    }

}
