/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.ControlAplicacion;
import estado.JuegoActual;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Usuario
 */
public class Logeo extends JFrame {

    FondoPanel fondo = new FondoPanel();

    public Logeo(PantallaInicio inicio) {
        setSize(1500, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setContentPane(fondo);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.jpg")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.inicio = inicio;
        cualplayer = false;

        juego = new VentanaJuego(this);

        ajustarComponentes();

    }

    public boolean isCualplayer() {
        return cualplayer;
    }

    public void ajustarComponentes() {
        JPanel panelArriba = new JPanel();
        panelArriba.setOpaque(false);
        panelArriba.setLayout(new FlowLayout());

        JButton botonAtras = new JButton("ATRAS");
        botonAtras.setFont(new Font("Bodoni MT", Font.PLAIN, 30));
        panelArriba.add(Box.createHorizontalStrut(150));
        panelArriba.add(botonAtras, FlowLayout.LEFT);

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                inicio.setVisible(true);
            }
        });

        JLabel eti1 = new JLabel("Selecciona tu Personaje: ");
        eti1.setFont(new Font("Bodoni MT", Font.PLAIN, 60));
        eti1.setForeground(Color.WHITE);
        panelArriba.add(eti1);
        panelArriba.add(Box.createVerticalStrut(300));
        add(panelArriba, BorderLayout.NORTH);

        JPanel PANELCENTRO = new JPanel();
        PANELCENTRO.setOpaque(false);
        PANELCENTRO.setLayout(new BoxLayout(PANELCENTRO, BoxLayout.PAGE_AXIS));

        JPanel panelCentro = new JPanel();
        panelCentro.setOpaque(false);
        panelCentro.setLayout(new FlowLayout());

        JPanel panel1_centro = new JPanel();
        panel1_centro.setOpaque(false);
        panel1_centro.setLayout(new BoxLayout(panel1_centro, BoxLayout.PAGE_AXIS));

        ImageIcon imagen2 = new ImageIcon("hombre2.png");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setSize(350, 350);
        etiqueta2.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH)));
        panel1_centro.add(etiqueta2);

        JCheckBox botonelegir1 = new JCheckBox("ELEGIR");
        botonelegir1.setFont(new Font("times new roman", Font.PLAIN, 20));
        botonelegir1.setFont(new Font("times new roman", Font.PLAIN, 20));
        botonelegir1.setMinimumSize(new Dimension(20, 20));
        botonelegir1.setMaximumSize(new Dimension(350, 40));
        botonelegir1.setPreferredSize(new Dimension(350, 40));
        panel1_centro.add(botonelegir1);
        panel1_centro.add(Box.createHorizontalStrut(50));
        panelCentro.add(panel1_centro);

        JPanel panel2_centro = new JPanel();
        panel2_centro.setOpaque(false);
        panel2_centro.setLayout(new BoxLayout(panel2_centro, BoxLayout.PAGE_AXIS));

        ImageIcon imagen3 = new ImageIcon("mujer.png");
        JLabel etiqueta3 = new JLabel();
        etiqueta3.setSize(350, 350);
        etiqueta3.setIcon(new ImageIcon(imagen3.getImage().getScaledInstance(etiqueta3.getWidth(), etiqueta3.getHeight(), Image.SCALE_SMOOTH)));
        panel2_centro.add(etiqueta3);

        JCheckBox botonelegir2 = new JCheckBox("ELEGIR");
        botonelegir2.setFont(new Font("times new roman", Font.PLAIN, 20));
        botonelegir2.setMinimumSize(new Dimension(20, 20));
        botonelegir2.setMaximumSize(new Dimension(350, 40));
        botonelegir2.setPreferredSize(new Dimension(350, 40));
        panel2_centro.add(botonelegir2);
        panel2_centro.add(Box.createHorizontalStrut(250));
        panelCentro.add(panel2_centro);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(botonelegir1);
        grupo.add(botonelegir2);

        panelCentro.add(Box.createVerticalStrut(300));
        panelCentro.add(Box.createHorizontalStrut(700));

        PANELCENTRO.add(panelCentro);

        JPanel panelAbajo = new JPanel();
        panelAbajo.setOpaque(false);
        panelAbajo.setLayout(new FlowLayout());

        JButton botonAceptar = new JButton("ACEPTAR");
        botonAceptar.setEnabled(false);
        botonAceptar.setFont(new Font("times new roman", Font.PLAIN, 30));

        JLabel etiNombre = new JLabel("Digite su nombre: ");
        etiNombre.setFont(new Font("times new roman", Font.PLAIN, 30));
        etiNombre.setForeground(Color.WHITE);

        cajaNombre = new JTextField(20);

        panelAbajo.add(Box.createVerticalStrut(250));
        panelAbajo.add(Box.createHorizontalStrut(50));

        panelAbajo.add(etiNombre);
        panelAbajo.add(cajaNombre);
        panelAbajo.add(Box.createHorizontalStrut(25));
        panelAbajo.add(botonAceptar);

        PANELCENTRO.add(panelAbajo);

        add(PANELCENTRO, BorderLayout.SOUTH);

        cajaNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("")) {
                    botonAceptar.setEnabled(false);
                } else {
                    botonAceptar.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("")) {
                    botonAceptar.setEnabled(false);
                } else {
                    botonAceptar.setEnabled(true);

                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("")) {
                    botonAceptar.setEnabled(false);
                } else {
                    botonAceptar.setEnabled(true);

                }
            }
        });

        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (botonelegir1.isSelected() == true) {
                    cualplayer = false;
                }
                if (botonelegir2.isSelected() == true) {
                    cualplayer = true;
                }

                ingresarJugador();
                dispose();
                juego.setVisible(true);
                juego.iniciarHilo();

            }
        });
    }

    public void ingresarJugador() {
        juego.setNombre(cajaNombre.getText());

    }

    private JTextField cajaNombre;
    private PantallaInicio inicio;
    private ControlAplicacion gestor;
    private VentanaJuego juego;
    private boolean cualplayer;

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondo2.jpg")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }
}
