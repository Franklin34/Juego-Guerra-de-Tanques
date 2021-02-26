/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import modelo.Vector2D;

/**
 *
 * @author Usuario
 */
public abstract class ClaseGeneral {
    protected BufferedImage textura;
    protected Vector2D posicion;
    
    
    public ClaseGeneral(Vector2D posicion, BufferedImage imagen){
        this.textura = imagen;
        this.posicion = posicion;
    }
    
    public abstract void update();
    public abstract void dibujar(Graphics g);

    public Vector2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2D posicion) {
        this.posicion = posicion;
    }
}
