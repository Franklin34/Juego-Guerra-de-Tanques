/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

import java.awt.image.BufferedImage;
import modelo.Vector2D;

/**
 *
 * @author Usuario
 */
public class Animacion {
    
    private BufferedImage[] frames;
    private int velocidad;
    private int index;
    private boolean running;
    private Vector2D posicion;
    private long time,lastime;

    public Animacion(BufferedImage[] frames, int velocidad, Vector2D posicion) {
        this.frames = frames;
        this.velocidad = velocidad;
        this.posicion = posicion;
        index = 0;
        running =true;
        time =0;
        lastime = System.currentTimeMillis();
    }
    
    public void update(){
        time+=System.currentTimeMillis() - lastime;
        lastime = System.currentTimeMillis();
        
        if(time > velocidad){
            time =0;
            index ++;
            if(index >= frames.length){
                running = false;
            }
        }
    }

    public BufferedImage getFrameActual(){
        return frames[index];
    }
    
    public boolean isRunning() {
        return running;
    }

    public Vector2D getPosicion() {
        return posicion;
    }
    
    
}
