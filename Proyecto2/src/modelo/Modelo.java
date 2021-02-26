/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Modelo {

    public Modelo() {
        listaJugador = new ArrayList<Jugador>();
    }

    public void insertarJugador(Jugador player) {
        listaJugador.add(player);
        System.out.println(listaJugador.toString());
    }

    private List<Jugador> listaJugador;

}
