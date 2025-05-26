/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ivan-gallardo
 */

public class Producte {
    private int codi;
    private String nom;
    private Categoria categoria;
    private double preu;
    private String tipusPreu; // unitari / pes / paquet
    private int stock;
    private boolean oferta;
    private int idCategoria;

    public Producte(int codi, String nom, Categoria categoria, double preu, String tipusPreu, int stock, boolean oferta) {
        this.codi = codi;
        this.nom = nom;
        this.categoria = categoria;
        this.preu = preu;
        this.tipusPreu = tipusPreu;
        this.stock = stock;
        this.oferta = oferta;
    }
    public Producte(String nom, int idCategoria, double preu, String tipusPreu, int stock) {
        this.nom = nom;
        this.idCategoria = idCategoria;
        this.preu = preu;
        this.tipusPreu = tipusPreu;
        this.stock = stock;
    }
    public Producte(String nom, Categoria categoria, double preu, String tipusPreu, int stock, boolean oferta) {
    this.nom = nom;
    this.categoria = categoria;
    this.preu = preu;
    this.tipusPreu = tipusPreu;
    this.stock = stock;
    this.oferta = oferta;
}

    public int getIdCategoria() {
        return getCategoria().getId();
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        if (preu < 0) {
            throw new IllegalArgumentException("El preu ha de ser positiu.");
        }
        this.preu = Math.round(preu * 100.0) / 100.0;
    }

    public String getTipusPreu() {
        return tipusPreu;
    }

    public void setTipusPreu(String tipusPreu) {
        this.tipusPreu = tipusPreu;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        return "Producte{" +
                "codi=" + codi +
                ", nom='" + nom + '\'' +
                ", categoria=" + (categoria != null ? categoria.toString() : "null") +
                ", preu=" + preu +
                ", tipusPreu='" + tipusPreu + '\'' +
                ", stock=" + stock +
                ", oferta=" + oferta +
                '}';
    }
}