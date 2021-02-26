/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import estado.Chronometer;
import estado.JuegoActual;
import estado.ObjetosMovibles;
import imagenes_sonidoJuego.Archivos_Imagenes_Sonido;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class TanqueEnemigo extends ObjetosMovibles implements Runnable {

    public TanqueEnemigo(Vector2D posicion, BufferedImage imagen, Vector2D velocidad, double maxV, JuegoActual actual, ArrayList<Vector2D> path) {
        super(posicion, imagen, velocidad, maxV, actual);
        this.textura = imagen;
        this.posicion = posicion;
        this.velocidad = velocidad;
        hiloTanque = new Thread(this);
        camino = path;
        index = 0;
        following = true;
        aceleracion = new Vector2D();
        rotacion = new Vector2D(0, 1);
        tiempo = 0;
        lasttime = System.currentTimeMillis();
        fireRate = new Chronometer();
        fireRate.run(1000);
        hiloTanque.start();
        tieneBalas = true;
        this.actual = actual;
        actual.setTanquesenemigos(1);
        
    }

    private Vector2D aceleracion;
    private long tiempo, lasttime;
    private boolean tieneBalas;

    public boolean isTieneBalas() {
        return tieneBalas;
    }



    public void getHiloTanque() {
        hiloTanque.start();
    }

    private Vector2D siguiendoCamino() {
        currentNode = camino.get(index);

        double distanciaalnodo = currentNode.restarV(getCentro()).getMagnitud();

        if (distanciaalnodo < 160) {
            index++;
            if (index >= camino.size()) {
                following = false;
            }
        }

        return seekForce(currentNode);
    }

    private Vector2D seekForce(Vector2D objetivo) {
        Vector2D velocidaddeseada = objetivo.restarV(getCentro());
        velocidaddeseada = velocidaddeseada.normalizar().escalar(maxVel);
        return velocidaddeseada.restarV(velocidad);
    }

    public void detenerHilo() {
        try {
            hiloTanque.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TanqueEnemigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void run() {
        while (hiloTanque == Thread.currentThread()) {
            try {

                Vector2D siguiendoCamino;

                if (following) {
                    siguiendoCamino = siguiendoCamino();
                } else {
                    siguiendoCamino = new Vector2D();
                }

                velocidad = velocidad.anadir(siguiendoCamino);

                velocidad = velocidad.limitar(maxVel);
                posicion = posicion.anadir(velocidad);

   
                if (posicion.getX() > 1500) {
                    posicion.setX(0);
                }
                if (posicion.getY() > 900) {
                    posicion.setY(0);
                }
                if (posicion.getX() < 0) {
                    posicion.setX(1500);
                }
                if (posicion.getY() < 0) {
                    posicion.setY(0);
                }

         
                if (tieneBalas == true) {
                    if (!fireRate.isRunning()) {
                        Vector2D alJugador = actual.getJugador().getCentro().restarV(getCentro());

                        alJugador = alJugador.normalizar();

                        double actualangulo = alJugador.getangulo();

                        actualangulo += Math.random() * Math.PI / 2 - Math.PI / 2;

                        if (alJugador.getX() < 0) {
                            actualangulo = -actualangulo * Math.PI;
                        }

                        alJugador = alJugador.getDireccion(actualangulo);

                        BalaEnemigo balae = new BalaEnemigo(getCentro().anadir(alJugador.escalar(textura.getWidth())),
                                Archivos_Imagenes_Sonido.bala2, alJugador,
                                actualangulo + Math.PI / 2,
                                5, actual);

                        actual.getObjetosMov().add(0, balae);
                        angulo += getCentro().anadir(alJugador.escalar(textura.getWidth())).getX() + getCentro().anadir(alJugador.escalar(textura.getWidth())).getY();
                        fireRate.run(1000);
                    }
               }

                colision();
                fireRate.update();
                sleep(20);

            } catch (InterruptedException ex) {
                Logger.getLogger(TanqueEnemigo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private boolean disparar() {
       boolean disparar = false;
        for (int i = 0; i < actual.getObjetosMov().size(); i++) {
            if (actual.getObjetosMov().get(i) != this) {
                disparar = true;
            }
            if (actual.getObjetosMov().get(i) == this) {
                disparar = false;
            }
        }

        return disparar;

    }

    @Override
    public Vector2D getCentro() {
        return new Vector2D(posicion.getX() + (textura.getWidth() / 2), posicion.getY() + (textura.getHeight() / 2));
    }

    @Override
    public void destruir() {
        actual.anadirPuntaje(2);
        tieneBalas = false;
        actual.setTanquesenemigos(-1);
        actual.dibujarscoreenemigo((int)posicion.getX(),(int)posicion.getY());
        super.destruir();
    }

    private int x;
    private int y;
    private Vector2D rotacion;
    private ArrayList<Vector2D> camino;
    private Vector2D currentNode;
    private int index;
    private boolean following;
    private Chronometer fireRate;

    private Thread hiloTanque;

    @Override
    public void update() {

    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D grafics2D = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());

        at.rotate(angulo, Archivos_Imagenes_Sonido.enemigo.getWidth() / 2, Archivos_Imagenes_Sonido.enemigo.getHeight() / 2);

        grafics2D.drawImage(Archivos_Imagenes_Sonido.enemigo, at, null);

        /*for (int i = 0; i < camino.size(); i++) {
            g.drawRect((int) camino.get(i).getX(), (int) camino.get(i).getY(), 5, 5);
        }*/
    }

}
