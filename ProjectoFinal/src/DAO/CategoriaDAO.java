/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Categoria;

/**
 *
 * @author ivan-gallardo
 */
public class CategoriaDAO {
        public void crearCategoria(Categoria c) {
        String sql = "INSERT INTO categoria (nom, descripcio) VALUES (?, ?)";
        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNom());
            stmt.setString(2, c.getDescripcio());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void modificarCategoria (Categoria c) {
        String sql = "UPDATE categoria SET nom = ?, descripcio = ? WHERE id = ?";

        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNom());
            stmt.setString(2, c.getDescripcio());
            stmt.setInt(3, c.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCategoria (int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";

        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Categoria buscarCategoria (int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Categoria(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("descripcio"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Categoria> llistarCategoria () {
        List<Categoria> llista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("descripcio"));
                llista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }

    public static Integer buscarIdNom(String nombre) {
        String sql = "SELECT id FROM categoria WHERE nom = ?";
        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; 
    }

    public static List<Categoria> llistarCategorias() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDescripcio(rs.getString("descripcio"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    

}
