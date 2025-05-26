/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author ivan-gallardo
 */
public class CrearDB {
    public static void crearProductes() {
        String sql = "USE GestorProductesIvanG;"
                + "CREATE TABLE IF NOT EXISTS producte ("
                + "codi INT AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(100) NOT NULL CHECK (LENGTH(nom) >= 2),"
                + "categoria INT NOT NULL,"
                + "preu DECIMAL(10, 2) NOT NULL CHECK (preu > 0),"
                + "tipus_preu VARCHAR(10) NOT NULL CHECK (tipus_preu IN ('unitari', 'pes', 'paquet')),"
                + "stock INT NOT NULL CHECK (stock >= 0),"
                + "oferta BOOLEAN NOT NULL,"
                + "FOREIGN KEY (categoria) REFERENCES categoria(id)"
                + ");";

        try (Connection conn = ConnectDB.getNewConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void crearCategoria() {
        String sql = "USE GestorProductesIvanG;"
                + "CREATE TABLE IF NOT EXISTS categoria ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(100) NOT NULL UNIQUE CHECK (LENGTH(nom) >= 2),"
                + "descripcio VARCHAR(100)"
                + ");";

        try (Connection conn = ConnectDB.getNewConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void IniciarBD() {
        crearProductes();
        crearCategoria();
    }

}
