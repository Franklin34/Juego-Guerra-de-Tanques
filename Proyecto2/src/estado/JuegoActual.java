/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

import imagenes_sonidoJuego.Archivos_Imagenes_Sonido;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import modelo.Jugador;
import modelo.Mensaje;
import modelo.TanqueEnemigo;
import modelo.Vector2D;
import vista.VentanaHistorial;
import vista.VentanaJuego;

/**
 *
 * @author Usuario
 */
public class JuegoActual {

    private Jugador jugador;
    private TanqueEnemigo enemigo;
    private TanqueEnemigo enemigo2;
    private TanqueEnemigo enemigo3;
    private ArrayList<ObjetosMovibles> objetosMov = new ArrayList<ObjetosMovibles>();
    private ArrayList<Animacion> explosiones = new ArrayList<Animacion>();
    private ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    private int puntaje = 0;
    private int tanquesenemigos = 0;
    private int vidasJugador = 5;
    private VentanaJuego ventana;

    public JuegoActual(VentanaJuego ventana) {

        spawnEnemigo = 0;
        this.ventana = ventana;
        jugador = new Jugador(new Vector2D(100, 500), Archivos_Imagenes_Sonido.jugador, new Vector2D(), 5, this);
        objetosMov.add(jugador);

        int rand = (int) (Math.random() * 2);

        double x = rand == 0 ? (Math.random() * 1500) : 1500;
        double y = rand == 0 ? 900 : (Math.random() * 900);

        ArrayList<Vector2D> path = new ArrayList<Vector2D>();

        double posX, posY;

        posX = Math.random() * 1500 / 2;
        posY = Math.random() * 900 / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * (1500 / 2) + 1500 / 2;
        posY = Math.random() * 900 / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * 1500 / 2;
        posY = Math.random() * (900 / 2) + 900 / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * (1500 / 2) + 1500 / 2;
        posY = Math.random() * (900 / 2) + 900 / 2;
        path.add(new Vector2D(posX, posY));

        objetosMov.add(new TanqueEnemigo(new Vector2D(x, y), Archivos_Imagenes_Sonido.enemigo, new Vector2D(), 5, this, path));

    }

    public TanqueEnemigo getEnemigo2() {
        return enemigo2;
    }

    public TanqueEnemigo getEnemigo3() {
        return enemigo3;
    }

    public TanqueEnemigo getEnemigo() {
        return enemigo;
    }

    public void anadirPuntaje(int valor) {
        puntaje += valor;
    }

    private void dibujarPuntaje(Graphics g) {
        Vector2D posicion = new Vector2D(1410, 20);
        g.drawImage(Archivos_Imagenes_Sonido.puntaje, 1300, 15, null);
        String puntajeaString = Integer.toString(puntaje);

        for (int i = 0; i < puntajeaString.length(); i++) {
            g.drawImage(Archivos_Imagenes_Sonido.vidas[Integer.parseInt(puntajeaString.substring(i, i + 1))],
                    (int) posicion.getX(),
                    (int) posicion.getY(),
                    null);
            posicion.setX(posicion.getX() + 20);
        }
    }

    public void setTanquesenemigos(int tanquesenemigos) {
        this.tanquesenemigos += tanquesenemigos;
    }

    private void dibujarcantEnemigos(Graphics g) {

        g.drawImage(Archivos_Imagenes_Sonido.enemigos, 1280, 820, null);

        Vector2D posicion = new Vector2D(1430, 825);

        String puntajeaString = Integer.toString(tanquesenemigos);

        for (int i = 0; i < puntajeaString.length(); i++) {

            g.drawImage(Archivos_Imagenes_Sonido.vidas[Integer.parseInt(puntajeaString.substring(i, i + 1))],
                    (int) posicion.getX(),
                    (int) posicion.getY(),
                    null);
            posicion.setX(posicion.getX() + 20);
        }
    }

    public void aparecerenemigo(float spawn) {

        spawnEnemigo += spawn;

        int rand = (int) (Math.random() * 2);

        double x = rand == 0 ? (Math.random() * 1500) : 1500;
        double y = rand == 0 ? 900 : (Math.random() * 900);

        ArrayList<Vector2D> path = new ArrayList<Vector2D>();

        double posX, posY;

        posX = Math.random() * 1500 / 2;
        posY = Math.random() * 900 / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * (1500 / 2) + 1500 / 2;
        posY = Math.random() * 900 / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * 1500 / 2;
        posY = Math.random() * (900 / 2) + 900 / 2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random() * (1500 / 2) + 1500 / 2;
        posY = Math.random() * (900 / 2) + 900 / 2;
        path.add(new Vector2D(posX, posY));

        if (tanquesenemigos <= 8) {
            if (spawnEnemigo > 2500) {
                objetosMov.add(new TanqueEnemigo(new Vector2D(x, y), Archivos_Imagenes_Sonido.enemigo, new Vector2D(), 5, this, path));
                spawnEnemigo = 0;
            }
        }
    }

    private long spawnEnemigo;
    private int i = 0;

    public void update() {

        try {
            if (!jugador.getGameover().isRunning() && jugador.getGameover().getMori() == true && i == 0) {
                System.out.println("dawed");

                historial = new VentanaHistorial(ventana, puntaje);

                ventana.cerrarVentana();
                historial.setVisible(true);
                System.out.println("i: " + i);
                i++;
                System.out.println("i: " + i);
                return;
            }
        } catch (Exception e) {
            System.out.println("no crasheo");
        }

        try {
            for (int i = 0; i < objetosMov.size(); i++) {
                objetosMov.get(i).update();
            }
        } catch (Exception e) {

        }

        try {
            for (int i = 0; i < explosiones.size(); i++) {
                Animacion anim = explosiones.get(i);
                anim.update();
                if (!anim.isRunning()) {
                    explosiones.remove(i);
                }
            }
        } catch (Exception e) {

        }

    }

    private void drawLives(Graphics g) {

        if (vidasJugador < 1) {
            return;
        }

        Vector2D livePosition = new Vector2D(15, 25);

        if (cualjugador == false) {
            g.drawImage(Archivos_Imagenes_Sonido.iconoHombre, (int) livePosition.getX(), (int) livePosition.getY(), null);
        } else {
            g.drawImage(Archivos_Imagenes_Sonido.iconoMujer, (int) livePosition.getX(), (int) livePosition.getY(), null);
        }

        g.drawImage(Archivos_Imagenes_Sonido.vidas[10], (int) livePosition.getX() + 55,
                (int) livePosition.getY() + 15, null);

        String livesToString = Integer.toString(vidasJugador);

        Vector2D pos = new Vector2D(livePosition.getX() + 14, livePosition.getY() + 10);

        for (int i = 0; i < livesToString.length(); i++) {

            int number = Integer.parseInt(livesToString.substring(i, i + 1));

            if (number <= 0) {
                break;
            }

            g.drawImage(Archivos_Imagenes_Sonido.vidas[number],
                    (int) pos.getX() + 60, (int) pos.getY() + 5, null);

            pos.setX(pos.getX() + 20);

        }
    }

    public void dibujarmenosvidas() {
        mensajes.add(new Mensaje(new Vector2D(1500 / 2, 900 / 2),
                true, "-1 VIDA", Color.white, true, Archivos_Imagenes_Sonido.fuentegGrande, this));
    }

    public void dibujarscoreenemigo(int x, int y) {
        System.out.println(x);
        mensajes.add(new Mensaje(new Vector2D(x, y),
                true, "+2 SCORE", Color.white, true, Archivos_Imagenes_Sonido.fuentegPeq, this));
    }

    public void dibujar(Graphics g) {

        try {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            for (int i = 0; i < mensajes.size(); i++) {
                mensajes.get(i).draw(g2d);
            }

            for (int i = 0; i < objetosMov.size(); i++) {
                objetosMov.get(i).dibujar(g);
            }

            for (int i = 0; i < explosiones.size(); i++) {
                Animacion anim = explosiones.get(i);
                g.drawImage(anim.getFrameActual(), (int) anim.getPosicion().getX(), (int) anim.getPosicion().getY(), null);
            }

            dibujarPuntaje(g);
            drawLives(g);
            dibujarcantEnemigos(g);
        } catch (Exception e) {
            System.out.println("peto");
        }

    }

    public void detenerhilo() {
        ventana.detener();
    }

    public void playExplosion(Vector2D posicion) {
        explosiones.add(new Animacion(Archivos_Imagenes_Sonido.explosiones,
                50,
                posicion.restarV(new Vector2D(Archivos_Imagenes_Sonido.explosiones[0].getWidth() / 2, Archivos_Imagenes_Sonido.explosiones[0].getHeight() / 2))));
    }

    public ArrayList<ObjetosMovibles> getObjetosMov() {
        return objetosMov;
    }

    public void setObjetosMov(ArrayList<ObjetosMovibles> objetosMov) {
        this.objetosMov = objetosMov;
    }

    public void setNombre(String nombre) {
        jugador.setNombre(nombre);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setCualjugador(boolean cualjugador) {
        this.cualjugador = cualjugador;
    }

    public void restarvidas() {
        vidasJugador--;
        if (vidasJugador != 1) {
            dibujarmenosvidas();
        }
    }

    public boolean perdio() {
        if (vidasJugador == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void gameovermensaje() {
        mensajes.add(new Mensaje(new Vector2D(1500 / 2, 900 / 2),
                false, "GAME OVER", Color.white, true, Archivos_Imagenes_Sonido.fuentegGrande, this));
    }

    
    
    private String nombre;
    private boolean cualjugador;
    private VentanaHistorial historial;
    
}
