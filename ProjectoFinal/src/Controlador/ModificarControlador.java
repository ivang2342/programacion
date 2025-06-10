package Controlador;

import Model.Categoria;
import Model.Producte;
import Vista.ModificarProducte;
import DAO.CategoriaDAO;
import DAO.ProducteDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ModificarControlador {

    private ProducteDAO producteDAO;
    private ModificarProducte vista;

    public ModificarControlador(ModificarProducte vista) {
        this.vista = vista;
        this.producteDAO = new ProducteDAO();
    }

    private final ActionListener BotonModificarProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nom = vista.getNom();
                String Cat = vista.getCategoria();
                int idCategoria = CategoriaDAO.buscarIdNom(Cat);

                Categoria categoria = new Categoria();
                categoria.setId(idCategoria);
                categoria.setNom(Cat);

                double preu = Double.parseDouble(vista.getPreu());
                String Tipus = vista.getTipusPreu();
                int stock = Integer.parseInt(vista.getStock());
                boolean oferta = vista.getOferta().equalsIgnoreCase("Oferta");

                Producte producteModificat = new Producte(nom, categoria, preu, Tipus, stock, oferta);
                producteDAO.modificarProducte(producteModificat);

                javax.swing.JOptionPane.showMessageDialog(vista, "Producte modificat correctament.");
                vista.dispose();

            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(vista, "Preu o stock no vàlids.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    public void iniciarControlador() {
        vista.getBAcceptar().addActionListener(BotonModificarProducte);
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
