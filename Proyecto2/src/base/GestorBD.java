/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Usuario
 */
public class GestorBD {
        private GestorBD(
            String protocolo,
            String url,
            String baseDatos,
            String usuario,
            String clave) {
        this.cnx = null;
        this.protocolo = protocolo;
        this.url = url;
        this.baseDatos = baseDatos;
        this.usuario = usuario;
        this.clave = clave;
    }

    private GestorBD() throws Exception {
        this(PROTOCOLO, URL, BASE_DATOS, USUARIO, CLAVE);
    }

    public static GestorBD obtenerInstancia() {
        if (instancia == null) {
            try {
                instancia = new GestorBD();
            } catch (Exception exc) {
            }
        }
        return instancia;
    }

    public Connection obtenerConexion() throws Exception {
        try {
            cnx = DriverManager.getConnection(
                    protocolo + url + baseDatos,
                    usuario,
                    clave);
        } catch (Exception exc) {
            throw exc;
        } finally {
        }
        return cnx;
    }

    public void cerrarConexion() {
        try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }
    }
    
    private static GestorBD instancia = null;
    private static String PROTOCOLO = "jdbc:mysql:";
    private static String URL = "//localhost:3306/";
    private static String BASE_DATOS = "baseproyecto";
    private static String USUARIO = "root";
    private static String CLAVE = "ZXCvbn1218";
    private Connection cnx = null;
    
    String protocolo;
    String url;
    String baseDatos;
    String usuario;
    String clave;
}
