/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Model.Categoria;

/**
 *
 * @author ivan-gallardo
 */

import Model.Producte;
import Vista.ModificarProducte;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DAO.CategoriaDAO;

public class ModificarControlador {
    private DAO.ProducteDAO producteDAO;
    private Vista.ModificarProducte vista;
    public ModificarControlador(ModificarProducte vista) {
        this.vista = vista;
    }
    private ActionListener BotonModificarProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = vista.getNom();
            String Cat = vista.getCategoria();
            int idCategoria = CategoriaDAO.buscarIdNom(Cat);
            double preu = Double.parseDouble(vista.getPreu());
            String Tipus = vista.getTipusPreu();
            int stock = Integer.parseInt(vista.getStock());
            Producte producteModificat = new Producte(nom, idCategoria, preu, Tipus, stock);
            producteDAO.modificarProducte(producteModificat);
    }
        };
    public void iniciarControlador() {
        vista.getBAcceptar().addActionListener(BotonModificarProducte);
        vista.setVisible(true);
    }
}