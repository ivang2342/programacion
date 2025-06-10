package Controlador;

import Vista.AfegirCategoria;
import Model.Categoria;
import DAO.CategoriaDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControladorCategoria {
    private AfegirCategoria vista;
    private final Runnable refrescarTablaCallback;  // callback para refrescar

    // Nuevo constructor con callback
    public ControladorCategoria(AfegirCategoria vista, Runnable refrescarTablaCallback) {
        this.vista = vista;
        this.refrescarTablaCallback = refrescarTablaCallback;
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

                javax.swing.JOptionPane.showMessageDialog(vista, "Categoria afegida correctament.");

                vista.dispose();

                if (refrescarTablaCallback != null) {
                    refrescarTablaCallback.run();  // refrescar tabla al cerrar
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(vista, "Omple tots els camps.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    };

    // Acció per al botó Cancel·lar
    private final ActionListener bCancelarCat = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            vista.dispose();
            if (refrescarTablaCallback != null) {
                refrescarTablaCallback.run();  // refrescar también al cancelar
            }
        }
    };

    // Mètode per iniciar els listeners
    public void iniciarControlador() {
        vista.bAcceptarCat().addActionListener(bAcceptarCat);
        vista.bCancelarCat().addActionListener(bCancelarCat);
        vista.setVisible(true);
    }
}
