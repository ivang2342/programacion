package Controlador;

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

    // Acció per al botó Acceptar
    private final ActionListener bAcceptarCat = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = vista.getnomCat().trim();
            String descripcio = vista.getCDescripcio().trim();

            if (!nom.isEmpty() && !descripcio.isEmpty()) {
                Categoria categoria = new Categoria(nom, descripcio);
                CategoriaDAO daoCategoria = new CategoriaDAO();
                daoCategoria.crearCategoria(categoria);

                // Missatge de confirmació (opcional)
                javax.swing.JOptionPane.showMessageDialog(vista, "Categoria afegida correctament.");

                // Opcional: tancar la finestra
                vista.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(vista, "Omple tots els camps.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    };

    // Acció per al botó Cancel·lar
    private final ActionListener bCancelarCat = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            vista.dispose();  // Tanca la finestra
        }
    };

    // Mètode per iniciar els listeners
    public void iniciarControlador() {
        vista.bAcceptarCat().addActionListener(bAcceptarCat);
        vista.bCancelarCat().addActionListener(bCancelarCat);
        vista.setVisible(true);
    }
}
