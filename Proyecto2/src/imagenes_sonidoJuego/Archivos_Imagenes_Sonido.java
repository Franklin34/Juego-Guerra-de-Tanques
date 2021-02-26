/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes_sonidoJuego;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 *
 * @author Usuario
 */
public class Archivos_Imagenes_Sonido {
    
    
    public static boolean cargado = false;
    public static float contador =0;
    public static float MAX_CONT = 46;
         
    public static BufferedImage jugador;
    public static BufferedImage bala1;
    public static BufferedImage bala2;
    public static BufferedImage bala3;
    
    public static BufferedImage enemigo;
    public static BufferedImage[] explosiones= new BufferedImage[4];
    public static BufferedImage[] vidas = new BufferedImage[11];
    public static BufferedImage iconoHombre;
    public static BufferedImage iconoMujer;
    public static BufferedImage puntaje;
    public static BufferedImage enemigos;
    public static BufferedImage gameOver;
    public static BufferedImage life;
    public static Font fuentegGrande;
    public static Font fuentegPeq;
    
    public static void iniciar(){
        jugador = cargarimagen("/imagenes/tanks3.png");
        
        bala1 = cargarimagen("/imagenes/shotRed.png");
        
        bala2 = cargarimagen("/imagenes/shotOrange.png");
        
        bala3 = cargarimagen("/imagenes/shotLarge.png");
        
        enemigo = cargarimagen("/imagenes/Tanks1.png");
        
        fuentegGrande = cargarfuente("/fuente/futureFont.ttf", 42);
       
        fuentegPeq = cargarfuente("/fuente/futureFont.ttf", 20);
        
        for(int i =0;i<explosiones.length;i++){
            explosiones[i] =  cargarimagen("/imagenes/" + i+".png");
        }
        for(int i = 0;i<vidas.length;i++){
            vidas[i] =  cargarimagen("/vidas1/" + i +".png");
        }
        
        
        iconoHombre = cargarimagen("/imagenes/iconoHombre.png");
        iconoMujer = cargarimagen("/imagenes/iconMujer.jpg");
        puntaje = cargarimagen("/imagenes/scores2.png");
        enemigos = cargarimagen("/imagenes/emegyfrase.png");
        gameOver = cargarimagen("/imagenes/gameOver.png");
        life = cargarimagen("/imagenes/lifes.png");
        
        cargado = true;
    }
    
    public static BufferedImage cargarimagen(String ruta){
        contador++;
        return Cargar.cargadorDeImagenes(ruta);
    }
    
    public static Font cargarfuente(String ruta,int tam){
        contador++;
        return Cargar.loadFont(ruta,tam);
    }
    
}
