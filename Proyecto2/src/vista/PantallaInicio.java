/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import base.GestorJugador;
import control.ControlAplicacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JTextArea;

/**
 *
 * @author Franklin/Sofia
 */
public class PantallaInicio extends JFrame {

    FondoPanel fondo = new FondoPanel();

    public PantallaInicio() {
        setSize(1500, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        gestorJugador = new GestorJugador();
        this.setContentPane(fondo);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.jpg")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ventana = new Logeo(this);
        instrucciones = new PantallaInstrucciones(this);
        ajustarComponentes();
    }

    public void init() {
        setVisible(true);
    }

    private void ajustarComponentes() {
        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new FlowLayout());

        JLabel etiquetaTitulo = new JLabel("FIRE OF TANKS");
        etiquetaTitulo.setFont(new Font("Bodoni MT", Font.PLAIN, 60));
        etiquetaTitulo.setForeground(new Color(255, 102, 0));
        panel1.add(etiquetaTitulo);
        panel1.add(Box.createVerticalStrut(300));
        add(panel1, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        panel2.setOpaque(false);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));

        JButton botonJugar = new JButton("JUGAR");

        botonJugar.setFont(new Font("Bodoni MT", Font.PLAIN, 40));
        botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonJugar.setBackground(Color.BLACK);
        botonJugar.setForeground(new Color(153, 51, 0));
        panel2.add(botonJugar);

        JButton botonInstrucciones = new JButton("INSTRUCCIONES");

        botonInstrucciones.setFont(new Font("Bodoni MT", Font.PLAIN, 40));
        botonInstrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonInstrucciones.setBackground(Color.BLACK);
        botonInstrucciones.setForeground(new Color(153, 51, 0));
        panel2.add(Box.createVerticalStrut(100));
        panel2.add(botonInstrucciones);

        JButton botonHistorial = new JButton("HISTORIAL");

        botonHistorial.setFont(new Font("Bodoni MT", Font.PLAIN, 40));
        botonHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonHistorial.setBackground(Color.BLACK);
        botonHistorial.setForeground(new Color(153, 51, 0));
        panel2.add(Box.createVerticalStrut(100));

        panel2.add(botonHistorial);

        botonHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Historial histo = new Historial();
                histo.setVisible(true);
            }
        });

        JButton botonSalir = new JButton("SALIR");

        botonSalir.setFont(new Font("Bodoni MT", Font.PLAIN, 40));
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.setBackground(Color.BLACK);
        botonSalir.setForeground(new Color(153, 51, 0));
        panel2.add(Box.createVerticalStrut(75));

        panel2.add(botonSalir);

        add(panel2, BorderLayout.CENTER);

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                ventana.setVisible(true);
            }
        });

        botonInstrucciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                instrucciones.setVisible(true);
            }
        });
    }

    private Logeo ventana;
    private PantallaInstrucciones instrucciones;
    private ControlAplicacion gestor;
    private GestorJugador gestorJugador;

    class Historial extends JFrame {

        public Historial() {
            setSize(600, 800);
            setResizable(false);
            setLocationRelativeTo(null);
            this.getContentPane().setBackground(Color.darkGray);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
            ajustarComponentes1();
        }

        public void ajustarComponentes1() {

            JLabel labeltitulo = new JLabel("PUNTUACIONES: ");
            labeltitulo.setFont(new Font("times new roman", Font.PLAIN, 40));
            labeltitulo.setForeground(Color.white);
            labeltitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

            add(labeltitulo);

            JTextArea areatexto = new JTextArea();
            areatexto.setEditable(false);
            areatexto.setMinimumSize(new Dimension(500, 650));
            areatexto.setMaximumSize(new Dimension(500, 650));
            areatexto.setPreferredSize(new Dimension(500, 650));
            
            areatexto.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            try {
                gestorJugador.consultar(areatexto);
            } catch (Exception ex) {
                Logger.getLogger(PantallaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            add(areatexto);
            
            
          
        }
    }

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }
}
