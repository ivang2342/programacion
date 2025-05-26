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
import Model.Producte;

/**
 *
 * @author ivan-gallardo
 */
public class ProducteDAO {
        public void crearProducte(Producte p) {
        String sql = "INSERT INTO producte (nom, categoria, preu, tipus_preu, stock, oferta) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNom());
            stmt.setInt(2, p.getCategoria().getId()); 
            stmt.setDouble(3, p.getPreu());
            stmt.setString(4, p.getTipusPreu());
            stmt.setInt(5, p.getStock());
            stmt.setBoolean(6, p.isOferta());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarProducte(Producte p) {
        String sql = "UPDATE producte SET nom = ?, categoria = ?, preu = ?, tipus_preu = ?, stock = ?, oferta = ? " +
                "WHERE codi = ?";

        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNom());
            stmt.setInt(2, p.getCategoria().getId());
            stmt.setDouble(3, p.getPreu());
            stmt.setString(4, p.getTipusPreu());
            stmt.setInt(5, p.getStock());
            stmt.setBoolean(6, p.isOferta());
            stmt.setInt(7, p.getCodi());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducte(int codi) {
        String sql = "DELETE FROM producte WHERE codi = ?";
        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codi);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Producte buscarProducte(int codi) {
        String sql = "SELECT p.*, cat.nom AS catnom, cat.descripcio AS catdesc " +
                "FROM producte p JOIN categoria cat ON p.categoria = cat.id WHERE p.codi = ?";
        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codi);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("categoria"),
                        rs.getString("catnom"),
                        rs.getString("catdesc"));

                return new Producte(
                        rs.getInt("codi"),
                        rs.getString("nom"),
                        categoria,
                        rs.getDouble("preu"),
                        rs.getString("tipus_preu"),
                        rs.getInt("stock"),
                        rs.getBoolean("oferta"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

        public List<Producte> llistarProducte() {
        List<Producte> llista = new ArrayList<>();
        String sql = "SELECT p.*, cat.nom AS catnom, cat.descripcio AS catdesc " +
                "FROM producte p JOIN categoria cat ON p.categoria = cat.id";

        try (Connection conn = ConnectDB.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("categoria"),
                        rs.getString("catnom"),
                        rs.getString("catdesc"));

                Producte p = new Producte(
                        rs.getInt("codi"),
                        rs.getString("nom"),
                        categoria,
                        rs.getDouble("preu"),
                        rs.getString("tipus_preu"),
                        rs.getInt("stock"),
                        rs.getBoolean("oferta"));

                llista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }


}
