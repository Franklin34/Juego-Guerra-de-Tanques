/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextArea;
import modelo.Jugador;

/**
 *
 * @author Usuario
 */
public class GestorJugador {

    public GestorJugador() {
    }

    public void guardar(Jugador nuevoJugador) throws Exception {
        Connection cnx
                = GestorBD.obtenerInstancia().obtenerConexion();

        PreparedStatement stm
                = cnx.prepareStatement(COMANDO_INSERTAR);
        stm.clearParameters();
        stm.setString(1, nuevoJugador.getNombre());
        stm.setInt(2, nuevoJugador.getPuntos());

        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

        GestorBD.obtenerInstancia().cerrarConexion();
    }

    public void consultar(JTextArea area) throws Exception {

        Connection cnx
                = GestorBD.obtenerInstancia().obtenerConexion();

        Statement stm = cnx.createStatement();

        // Contiene los datos recuperados.
        ResultSet rs = stm.executeQuery(COMANDO_CONSULTAR);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            int puntaje = rs.getInt("puntaje");
            
            area.append("Nombre: " + nombre + " " + "Puntaje: " + puntaje + '\n');
            
  
        }

        GestorBD.obtenerInstancia().cerrarConexion();

    }

    private static final String COMANDO_INSERTAR
            = "INSERT INTO jugador "
            + "(nombre, puntaje) "
            + "VALUES(?, ?); ";

    private static final String COMANDO_CONSULTAR
            = "SELECT * FROM jugador; ";
}
