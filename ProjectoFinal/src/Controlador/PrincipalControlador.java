/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author ivan-gallardo
 */

import DAO.ProducteDAO;
import DAO.CategoriaDAO;
import Model.Producte;
import Vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.List;

public class PrincipalControlador {

    private final PrincipalFrame vista;
    private final ProducteDAO producteDAO;
    private final CategoriaDAO categoriaDAO;

    public PrincipalControlador() {
        this.vista = new PrincipalFrame();
        this.producteDAO = new ProducteDAO();
        this.categoriaDAO = new CategoriaDAO();
        afegirListeners();
    }

    public PrincipalControlador(PrincipalFrame vista) {
        this.vista = vista;
        this.producteDAO = new ProducteDAO();
        this.categoriaDAO = new CategoriaDAO();
        afegirListeners();
    }

    public void iniciarControlador() {
        vista.setVisible(true);
        carregarTaulaProductes();
    }

    private void afegirListeners() {
        vista.getJButtonAfegir().addActionListener(e -> {
            AfegirProducte finestra = new AfegirProducte();
            ProducteControlador controlador = new ProducteControlador(finestra);
            controlador.iniciarControlador();
        });

        vista.getJButtonAfegirCategoria().addActionListener(e -> {
            AfegirCategoria finestra = new AfegirCategoria();
            ControladorCategoria controlador = new ControladorCategoria(finestra);
            controlador.iniciarControlador();
        });

        vista.getJButtonModificar().addActionListener(e -> {
            ModificarProducte finestra = new ModificarProducte();
            ModificarControlador controlador = new ModificarControlador(finestra);
            controlador.iniciarControlador();
        });

        vista.getJButtonEliminar().addActionListener(e -> eliminarProducteSeleccionat());

     
    }

    private void carregarTaulaProductes() {
        List<Producte> productes = producteDAO.llistarProducte();

        DefaultTableModel model = (DefaultTableModel) vista.getJTable1().getModel();
        model.setRowCount(0); 

        for (Producte p : productes) {
            Object[] fila = new Object[] {
                    p.getCodi(),
                    p.getNom(),
                    p.getCategoria() != null ? p.getCategoria().getNom() : "No categoría",
                    p.getPreu(),
                    p.getTipusPreu(),
                    p.getStock(),
                    p.isOferta()
            };
            model.addRow(fila);
        }
    }

    private void eliminarProducteSeleccionat() {
        JTable taula = vista.getJTable1();
        int filaSeleccionada = taula.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int codiProducte = (int) taula.getValueAt(filaSeleccionada, 0);
            producteDAO.eliminarProducte(codiProducte);
            carregarTaulaProductes();
        } else {
            JOptionPane.showMessageDialog(vista, "Selecciona un producte per eliminar-lo.", "Advertència",
                    JOptionPane.WARNING_MESSAGE);
        }
    }


}