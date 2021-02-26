/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import estado.JuegoActual;
import estado.ObjetosMovibles;
import imagenes_sonidoJuego.Archivos_Imagenes_Sonido;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Usuario
 */
public class Bala extends ObjetosMovibles{

    
    public Bala(Vector2D posicion, BufferedImage imagen, Vector2D velocidad,double angulo, double maxV,JuegoActual actual) {
        super(posicion, imagen, velocidad, maxV,actual);
        this.posicion = posicion;
        this.angulo = angulo;
        this.velocidad = velocidad.escalar(maxV);
        this.textura = imagen;
        this.actual = actual;
    }

    @Override
    public void update() {
        posicion = posicion.anadir(velocidad);
        
       if(posicion.getX() <0 || posicion.getX() > 1500 || posicion.getY() < 0||posicion.getY() > 900){
            actual.getObjetosMov().remove(this);
        }
        colision();    
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D grafics2D = (Graphics2D)g;
        
        at = AffineTransform.getTranslateInstance(posicion.getX() - textura.getWidth()/2, posicion.getY());
        
        at.rotate(angulo,textura.getWidth()/2,0d);
        
        grafics2D.drawImage(textura,at,null);
    }
    
    @Override
     public Vector2D getCentro(){
        return new Vector2D(posicion.getX() + (textura.getWidth()/2),posicion.getY() + (textura.getWidth()/2));
    }
}
