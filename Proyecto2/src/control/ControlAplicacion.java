/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import modelo.Jugador;
import modelo.Modelo;

/**
 *
 * @author Usuario
 */
public class ControlAplicacion {
    
    public ControlAplicacion(Modelo nuevoModelo) {
        datos = nuevoModelo;
    }
    
    public ControlAplicacion() {
        this(new Modelo());
    }
    
    public void ingresarJugador(Jugador jugador){
         datos.insertarJugador(jugador);
    }
    
    private Modelo datos;
}
