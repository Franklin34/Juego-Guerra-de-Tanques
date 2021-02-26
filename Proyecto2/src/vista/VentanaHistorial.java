/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import base.GestorJugador;
import imagenes_sonidoJuego.Archivos_Imagenes_Sonido;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.Jugador;
import modelo.Vector2D;

/**
 *
 * @author Usuario
 */
public class VentanaHistorial extends JFrame {

    FondoPanel fondo = new FondoPanel();
    
    public VentanaHistorial(VentanaJuego ventana,int puntaje) {
        setTitle("");
        setSize(685, 650);
        setResizable(true);
        this.setContentPane(fondo);
        gestor = new GestorJugador();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.ventana = ventana;
        puntos = puntaje;
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ajustarComponentes();
    
    }
    
    public void ajustarComponentes(){
        try {
            JLabel gameover = new JLabel("GAMEOVER");
            gameover.setForeground(Color.WHITE);
            gameover.setFont(new Font("times new roman", Font.PLAIN, 40));
            gameover.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            add(gameover);
            this.getContentPane().add(Box.createVerticalStrut(30));
            
            JPanel panel1 = new JPanel();
            panel1.setOpaque(false);
            panel1.setLayout(new FlowLayout());
            
            JLabel jugador = new JLabel("JUGADOR: ");
            jugador.setForeground(Color.WHITE);
            jugador.setFont(new Font("times new roman", Font.PLAIN, 40));
            panel1.add(jugador);
            
            JLabel nombre = new JLabel(ventana.getNombre());
            nombre.setForeground(Color.WHITE);
            nombre.setFont(new Font("times new roman", Font.PLAIN, 40));
            panel1.add(nombre);
            
            panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            add(panel1);
             this.getContentPane().add(Box.createVerticalStrut(10));
            
            JPanel panel2 = new JPanel();
            panel2.setOpaque(false);
            panel2.setLayout(new FlowLayout());
            
            JLabel Puntaje = new JLabel("PUNTAJE: ");
            Puntaje.setForeground(Color.WHITE);
            Puntaje.setFont(new Font("times new roman", Font.PLAIN, 40));
            panel2.add(Puntaje);
            
            String a = Integer.toString(puntos);
            
            JLabel puntos1 = new JLabel(a);
            puntos1.setForeground(Color.WHITE);
            puntos1.setFont(new Font("times new roman", Font.PLAIN, 40));
            panel2.add(puntos1);
            
            panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            add(panel2);
            this.getContentPane().add(Box.createVerticalStrut(10));
            
            JButton boton = new JButton("ACEPTAR");
            boton.setFont(new Font("times new roman", Font.PLAIN, 20));
            
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    dispose();
                    inicio = new PantallaInicio();
                    inicio.setVisible(true);
                }
            });
            
            add(boton);
             this.getContentPane().add(Box.createVerticalStrut(30));
            
            
            Jugador jugador1 = new Jugador(new Vector2D(), Archivos_Imagenes_Sonido.jugador , new Vector2D(), 5, ventana.getActual());
            jugador1.setNombre(ventana.getNombre());
            jugador1.setPuntos(puntos);
            
            
            gestor.guardar(jugador1);
            
        } catch (Exception ex) {
            Logger.getLogger(VentanaHistorial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private GestorJugador gestor;
    private PantallaInicio inicio;
    private VentanaJuego ventana;
    private int puntos;
    
}
class FondoPanel extends JPanel {

    private Image imagen;

    @Override
    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource("/imagenes/fondoSI.jpg")).getImage();

        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        setOpaque(false);
        super.paint(g);

    }
}
