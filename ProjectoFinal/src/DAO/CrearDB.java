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

    public static void crearBaseDatos() {
        String sql = "CREATE DATABASE IF NOT EXISTS GestorProductesIvanG ";
        try (Connection conn = ConnectDB.getConnectionSenseBD(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Base de dades 'GestorProductesIvanG' creada o ja existia.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearProductes() {
        String sql = "CREATE TABLE IF NOT EXISTS producte ("
                + "codi INT AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(100) NOT NULL CHECK (LENGTH(nom) >= 2),"
                + "categoria INT NOT NULL,"
                + "preu DECIMAL(10, 2) NOT NULL CHECK (preu > 0),"
                + "tipus_preu VARCHAR(10) NOT NULL CHECK (tipus_preu IN ('unitari', 'pes', 'paquet')),"
                + "stock INT NOT NULL CHECK (stock >= 0),"
                + "oferta BOOLEAN NOT NULL,"
                + "FOREIGN KEY (categoria) REFERENCES categoria(id)"
                + ");";

        try (Connection conn = ConnectDB.getNewConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearCategoria() {
        String sql = "CREATE TABLE IF NOT EXISTS categoria ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(100) NOT NULL UNIQUE CHECK (LENGTH(nom) >= 2),"
                + "descripcio VARCHAR(100)"
                + ");";

        try (Connection conn = ConnectDB.getNewConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserirDadesExemple() {
        String sqlCategories = """
        INSERT INTO categoria (nom, descripcio) VALUES
        ('Fruites', 'Fruites fresques i naturals'),
        ('Verdures', 'Verdures de temporada'),
        ('Begudes', 'Refrescos i sucs'),
        ('Làctics', 'Productes làctics'),
        ('Forn', 'Productes de forn'),
        ('Congelats', 'Aliments congelats')
        ON DUPLICATE KEY UPDATE nom=nom;
        """;

        String sqlProductes = """
        INSERT INTO producte (nom, categoria, preu, tipus_preu, stock, oferta) VALUES
        ('Poma', 1, 0.50, 'unitari', 100, false),
        ('Plàtan', 1, 0.30, 'unitari', 150, true),
        ('Enciam', 2, 1.20, 'unitari', 80, false),
        ('Pastanaga', 2, 0.80, 'unitari', 60, false),
        ('Aigua Mineral', 3, 0.60, 'paquet', 200, false),
        ('Llet sencera', 4, 1.10, 'unitari', 90, true),
        ('Pa integral', 5, 1.50, 'paquet', 50, false),
        ('Gelat de vainilla', 6, 3.50, 'paquet', 40, true),
        ('Suc de taronja', 3, 1.80, 'unitari', 70, false),
        ('Formatge manxec', 4, 4.50, 'paquet', 30, false)
        ON DUPLICATE KEY UPDATE nom=nom;
        """;

        try (Connection conn = ConnectDB.getNewConnection(); Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sqlCategories);
            stmt.executeUpdate(sqlProductes);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void IniciarBD() {
        crearBaseDatos();
        crearCategoria();
        crearProductes();
        inserirDadesExemple();//usar solo una vez para el ejemplo
    }

}
