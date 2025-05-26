/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author ivan-gallardo
 */

import Vista.AfegirProducte;
import Model.Categoria;
import Model.Producte;
import DAO.ProducteDAO;
import DAO.CategoriaDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProducteControlador {
    private AfegirProducte vista;

    public ProducteControlador(AfegirProducte vista) {
        this.vista = vista;
    }

    private final ActionListener BotonAñadirProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nom = vista.getPNomAfegir();
                String nomCategoria = vista.getPCategoriaAfegir();
                int idCategoria = CategoriaDAO.buscarIdNom(nomCategoria);

                Categoria categoria = new Categoria();
                categoria.setId(idCategoria);
                categoria.setNom(nomCategoria);

                double preu = Double.parseDouble(vista.getPPreuAfegir());
                String tipus = vista.getPTipusPreuAfegir();
                int stock = Integer.parseInt(vista.getPStockAfegir());
                boolean oferta = vista.getPAfegirOferta().equalsIgnoreCase("Oferta");

                Producte producte = new Producte(nom, categoria, preu, tipus, stock, oferta);

                ProducteDAO daoProducte = new ProducteDAO();
                daoProducte.crearProducte(producte);

                System.out.println("Producte afegit correctament: " + producte);

            } catch (NumberFormatException ex) {
                System.err.println("Error: preu o stock no vàlids.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    public void iniciarControlador() {
        vista.getBAcceptar().addActionListener(BotonAñadirProducte);
        cargarCategoriasEnCombo();
        vista.setVisible(true);
    }

    public void cargarCategoriasEnCombo() {
        List<Categoria> categorias = CategoriaDAO.llistarCategorias();
        vista.getComboBoxCategoria().removeAllItems();
        for (Categoria c : categorias) {
            vista.getComboBoxCategoria().addItem(c.getNom());
        }
    }
}