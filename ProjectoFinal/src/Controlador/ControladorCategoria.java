/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author ivan-gallardo
 */
import Vista.AfegirCategoria;
import Model.Categoria;
import DAO.CategoriaDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControladorCategoria {
    private AfegirCategoria vista;

    public ControladorCategoria(AfegirCategoria vista) {
        this.vista = vista;
    }

    private ActionListener BotonAñadirCategoria = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = vista.getCNomAfegir();
            String descripcio = vista.getDescripcioAfegir();
            Categoria categoria = new Categoria(nom, descripcio);
            CategoriaDAO daoCategoria = new CategoriaDAO();
            daoCategoria.crearCategoria(categoria);
        }
    };
    public void iniciarControlador() {
        vista.getBAfegir().addActionListener(BotonAñadirCategoria);
        vista.setVisible(true);
    }
    
}