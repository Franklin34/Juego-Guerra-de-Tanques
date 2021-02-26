/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import estado.JuegoActual;
import imagenes_sonidoJuego.Archivos_Imagenes_Sonido;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import modelo.Teclado;

/**
 *
 * @author Usuario
 */
public class VentanaJuego extends JFrame implements Runnable {

    public VentanaJuego(Logeo logeo) {
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.jpg")).getImage());

        canvas = new Canvas();
        teclado = new Teclado();
        setLayout(new BorderLayout());

        this.logeo = logeo;

        canvas.setPreferredSize(new Dimension(1500, 900));
        canvas.setMaximumSize(new Dimension(1500, 900));
        canvas.setMinimumSize(new Dimension(1500, 900));
        canvas.setFocusable(true);

        add(canvas);
        botonsalir = new JButton("SALIR");

        botonsalir.setFont(new Font("times new roman", Font.PLAIN, 12));
        botonsalir.setBackground(Color.BLACK);
        botonsalir.setForeground(Color.cyan);
        botonsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    detener();
                    dispose();
                } catch (Exception e) {
                    System.out.println("hilo en juego");
                }
            }
        });

        add(botonsalir, BorderLayout.SOUTH);

        canvas.addKeyListener(teclado);

    }

    public void cerrarVentana() {
        try{
      
         if(Thread.currentThread() == hilo && hilo.isAlive() == true){
             detener();
             setVisible(false);
         }
         
        }catch(Exception e){
            System.out.println("hilo en juego");
        } catch (Throwable ex) {
            System.out.println("peto");
        }
    }

    private String nombre;
    private Logeo logeo;
    private JButton botonsalir;

    public void iniciarHilo() {
        hilo = new Thread(this);
        hilo.start();
        corriendo = true;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private Thread hilocargado;

    private void iniciar() {

        Archivos_Imagenes_Sonido.iniciar();
        
        
        actual = new JuegoActual(this);
        actual.setNombre(nombre);
        //hilocargado.start();
    }

     public void detener() {

        hilo.interrupt();
        //hilo.stop();

    }

    public String getNombre() {
        return nombre;
    }



    int i = 0;

    @Override
    public void run() {

        while (Thread.currentThread().isInterrupted() == false) {
            long ahora = 0;
            long lasttime = System.nanoTime();

            int frames = 0;
            int time = 0;

            iniciar();

            while (corriendo) {
                while (Thread.currentThread().isInterrupted() == false) {
                    ahora = System.nanoTime();
                    delta += (ahora - lasttime) / TARGETTIME;
                    time += (ahora - lasttime);
                    lasttime = ahora;

                    if (delta >= 1) {
                        actual.aparecerenemigo((float) (delta * TARGETTIME * 0.000001f));
                        actualizar();
                        dibujar();
                        delta--;
                        frames++;
                    }

                    if (time >= 1000000000) {

                        AVERAGEFPS = frames;
                        frames = 0;
                        time = 0;
                    }
                }
            }
            detener();
        }
    }

    public void actualizar() {
        teclado.update();
        actual.setCualjugador(logeo.isCualplayer());
        actual.update();
    }

    public void dibujar() {
        buffer = canvas.getBufferStrategy();

        if (buffer == null) {
            canvas.createBufferStrategy(4);
            return;
        }

        graficos = buffer.getDrawGraphics();

        graficos.setColor(Color.BLACK);

        graficos.fillRect(0, 0, 1500, 900);

        actual.dibujar(graficos);

        graficos.setColor(Color.GREEN);

        graficos.drawString(actual.getJugador().getNombre(), 30, 20);

        graficos.dispose();
        buffer.show();
    }

    public JuegoActual getActual() {
        return actual;
    }
    
    

    public Graphics getGraficos() {
        return graficos;
    }

    public int getPuntos() {
        return actual.getJugador().getPuntos();
    }

    private Canvas canvas;
    private Thread hilo;
    private boolean corriendo = false;

    private BufferStrategy buffer;
    private Graphics graficos;
    private Teclado teclado;

    private final int FPS = 60;
    private double TARGETTIME = 1000000000 / FPS;
    private double delta = 0;//cambio respecto al tiempo
    private int AVERAGEFPS = FPS;
    private JuegoActual actual;
}
