/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import modelo.Bala;
import modelo.BalaEnemigo;
import modelo.Jugador;
import modelo.TanqueEnemigo;
import modelo.Vector2D;

/**
 *
 * @author Usuario
 */
    public abstract class ObjetosMovibles extends ClaseGeneral{

    public ObjetosMovibles(Vector2D posicion, BufferedImage imagen,Vector2D velocidad,double maxV,JuegoActual actual) {
        super(posicion, imagen);
        this.velocidad = velocidad;
        angulo = 0;
        maxVel = maxV;
        this.textura = imagen;
        this.actual = actual;
        this.posicion = posicion; 
    }

    protected void colision(){
        ArrayList<ObjetosMovibles> objetos = actual.getObjetosMov();
        try{
         for(int i =0;i<objetos.size();i++){
             ObjetosMovibles obj = objetos.get(i);
             
             if(obj.equals(this)){
                 continue;
             }
             
             double distancia = obj.getCentro().restarV(getCentro()).getMagnitud();
             
             if(distancia < obj.textura.getWidth()/2 + obj.textura.getWidth()/2 && objetos.contains(this)){
                 colisionObject(obj, this);
             }
             
         }
        }catch(Exception e){
            
        }
    }
                                                                                                                        
    private void colisionObject(ObjetosMovibles obj1,ObjetosMovibles obj2){
        
        if(obj1 instanceof Jugador && ((Jugador)obj1).isApareciendo()){
            return;
        }
        if(obj2 instanceof Jugador && ((Jugador)obj2).isApareciendo()){
            return;
        }
        
        if(!(obj1 instanceof Jugador && obj2 instanceof Bala
                || obj1 instanceof Bala && obj2 instanceof Jugador  || obj1 instanceof TanqueEnemigo && obj2 instanceof BalaEnemigo ||
                obj1 instanceof BalaEnemigo && obj2 instanceof TanqueEnemigo || obj1 instanceof TanqueEnemigo && obj2 instanceof TanqueEnemigo)){
            actual.playExplosion(getCentro());
            obj1.destruir();
            obj2.destruir();
        }
    }
    
    protected void destruir(){
       actual.getObjetosMov().remove(this);
       
    }
    
    protected Vector2D getCentro(){
        return new Vector2D(posicion.getX() + (textura.getWidth()/2),posicion.getY() + (textura.getHeight()/2));
    }
    
    protected Vector2D velocidad;
    protected AffineTransform at;
    protected double angulo;
    protected double maxVel;
    protected JuegoActual actual;
}
