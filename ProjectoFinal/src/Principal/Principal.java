/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Principal;

/**
 *
 * @author ivan-gallardo
 */


 import Controlador.PrincipalControlador;
 import DAO.CrearDB;
 
 public class Principal {
 
     /**
      * @param args the command line arguments
      */  public static void main(String[] args) {
         CrearDB.IniciarBD();
         PrincipalControlador controlador = new PrincipalControlador();
         controlador.iniciarControlador();
     }
 }
     
 