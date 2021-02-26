/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import estado.Chronometer;
import estado.ClaseGeneral;
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
public class Jugador extends ObjetosMovibles{

    public Jugador(Vector2D posicion, BufferedImage imagen, Vector2D velocidad,double max,JuegoActual actual) {
        super(posicion, imagen, velocidad,max,actual);
        this.posicion = posicion;
        this.imagen = imagen;
        this.velocidad = velocidad;
        nombre = "";
        vida = 10;
        puntos = 0;
        rotacion = new Vector2D(0,1);
        rotacion2 = new Vector2D(0,1);
        aceleracion = new Vector2D();
        this.actual = actual;
        
        tiempo = 0;
        lasttime = System.currentTimeMillis();
        tiempoApareciendo = new Chronometer();
        tiempoparpadeo = new Chronometer();
        gameover  = new Chronometer();
                
    }

  
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s",
                nombre, vida, puntos);
    }
    

    private String nombre;
    private int vida;
    private int puntos;
    private Vector2D posicion;
    private BufferedImage imagen;
    private Vector2D rotacion;
    private Vector2D aceleracion;
    private final double ACC = 0.08;
    private Vector2D rotacion2;
    private long tiempo,lasttime;
    private boolean apareciendo,visible;
    private Chronometer tiempoApareciendo,tiempoparpadeo,gameover;

    public boolean isApareciendo() {
        return apareciendo;
    }
    
    private void resetearValores(){
        angulo = 0;
        velocidad = new Vector2D();
        posicion = new Vector2D(1500/2 - Archivos_Imagenes_Sonido.jugador.getWidth()/2,
        900 - Archivos_Imagenes_Sonido.jugador.getHeight()/2);
                
    }

    @Override
    public void update() {
  
        tiempo += System.currentTimeMillis() - lasttime; 
        lasttime = System.currentTimeMillis();
        
        Bala bala = new Bala(                   
                    getCentro().anadir(rotacion.escalar(imagen.getWidth()/2+40)),
                    Archivos_Imagenes_Sonido.bala1,
                    rotacion,
                    angulo,
                    10,
                    actual
            );
        
        if(!tiempoApareciendo.isRunning()){
            apareciendo = false;
            visible = true;
        }
        
        if(apareciendo){
            if(!tiempoparpadeo.isRunning()){
                tiempoparpadeo.run(200);
                visible = !visible;
            }
        }
        
        
        if(Teclado.SHOOT && tiempo >150 && !apareciendo) {
            actual.getObjetosMov().add(bala);
            tiempo = 0;
        }
   
        if(Teclado.RIGHT){
            angulo += Math.PI/30;
           aceleracion = rotacion2.escalar(-ACC);
        }
        if(Teclado.LEFT){
            angulo -= Math.PI/30;
            aceleracion = rotacion2.escalar(ACC);
        }
        if(Teclado.UP){
           aceleracion = rotacion.escalar(ACC);
        }
        else{
            if(velocidad.getMagnitud() != 0){
                aceleracion = (velocidad.escalar(-1).normalizar()).escalar(ACC);
            }
        }
        if(Teclado.DOWN){ 
            aceleracion = rotacion.escalar(-ACC);
        }
        
        velocidad = velocidad.anadir(aceleracion);
        
        velocidad = velocidad.limitar(maxVel);
        
        rotacion = rotacion.getDireccion(angulo - Math.PI/2);

        posicion = posicion.anadir(velocidad); 

        if(posicion.getX() > 1500){
            posicion.setX(0);        
        }
        if(posicion.getY() > 900){
            posicion.setY(0);
        }
        if(posicion.getX() < 0){
            posicion.setX(1500);
        }
        if(posicion.getY() < 0){
            posicion.setY(0);
        }
        bala.update();
        tiempoApareciendo.update();
        tiempoparpadeo.update();
       
        colision();
    }
     
    @Override
    public void destruir(){
        apareciendo = true;
        tiempoApareciendo.run(3000);
        
        if(actual.perdio() == true){
            actual.gameovermensaje();
            actual.restarvidas();
            super.destruir();
            gameover.run(3000);
            gameover.update();
            gameover.setMori(true);
        }
        else{
        actual.restarvidas();
        }
    }

    public Chronometer getGameover() {
        return gameover;
    }
    
    @Override
    public void dibujar(Graphics g) {
        
        if(!visible){
            return;
        }
        
        
        Graphics2D grafics2D = (Graphics2D)g;
        
        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        
        at.rotate(angulo, Archivos_Imagenes_Sonido.jugador.getWidth()/2,Archivos_Imagenes_Sonido.jugador.getHeight()/2);
        
        grafics2D.drawImage(Archivos_Imagenes_Sonido.jugador,at,null);
    }
    
    @Override
     public Vector2D getCentro(){
        return new Vector2D(posicion.getX() + (textura.getWidth()/2),posicion.getY() + (textura.getHeight()/2));
    }
   
}
