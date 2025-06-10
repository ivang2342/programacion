package Controlador;

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
    private final Runnable refrescarTablaCallback;  // callback para refrescar tabla

    public ProducteControlador(AfegirProducte vista, Runnable refrescarTablaCallback) {
        this.vista = vista;
        this.refrescarTablaCallback = refrescarTablaCallback;
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

                javax.swing.JOptionPane.showMessageDialog(vista, "Producte afegit correctament.");

                vista.dispose();

                if (refrescarTablaCallback != null) {
                    refrescarTablaCallback.run();
                }

            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(vista, "Preu o stock no vàlids.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    private final ActionListener BotonCancelar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            vista.dispose();
            if (refrescarTablaCallback != null) {
                refrescarTablaCallback.run();
            }
        }
    };

    public void iniciarControlador() {
        vista.getBAcceptar().addActionListener(BotonAñadirProducte);
        vista.getBCancelar().addActionListener(BotonCancelar);
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
