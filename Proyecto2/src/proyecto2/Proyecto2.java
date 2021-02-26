/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import control.ControlAplicacion;
import vista.PantallaInicio;

/**
 *
 * @author Usuario
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  
        
        ControlAplicacion gestor = new ControlAplicacion();
        PantallaInicio inicio = new PantallaInicio();
        
        inicio.init();
      
    }
    
}
