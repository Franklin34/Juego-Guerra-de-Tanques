/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class PantallaInstrucciones extends JDialog {
    
    
    public PantallaInstrucciones(PantallaInicio inicio){
        super(inicio,true);
        setTitle("");
        setSize(685, 650);
        setResizable(true);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ajustarComponentes();
        
    }
    
    public void ajustarComponentes(){
        
        JLabel instrucciones = new JLabel("INSTRUCCIONES DEL JUEGO");
        instrucciones.setForeground(Color.WHITE);
        instrucciones.setFont(new Font("times new roman", Font.PLAIN, 40));
        instrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(instrucciones);
        
        JPanel panelTeclas = new JPanel();
        panelTeclas.setOpaque(false);
        panelTeclas.setLayout(new FlowLayout());
        
        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.PAGE_AXIS));
        
       ImageIcon imagen = new ImageIcon("Teclas.png");
       JLabel etiqueta2 = new JLabel(imagen);
       etiqueta2.setSize(260,200);
       etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH)));      

       panel1.add(etiqueta2);
       
       JLabel etiqueta3 = new JLabel("     Movimiento");
       etiqueta3.setForeground(Color.WHITE);
       etiqueta3.setFont(new Font("times new roman", Font.PLAIN, 30));
       panel1.add(etiqueta3);

       panelTeclas.add(panel1);
       
       JPanel panel2 = new JPanel();
       panel2.setOpaque(false);
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.PAGE_AXIS));
        
       ImageIcon imagen2 = new ImageIcon("arriba3.png");
       JLabel etiqueta4 = new JLabel(imagen2);
       etiqueta4.setSize(200,200);
       etiqueta4.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(etiqueta4.getWidth(), etiqueta4.getHeight(), Image.SCALE_SMOOTH)));      

       panel2.add(etiqueta4);
       
       JLabel etiqueta5 = new JLabel("        Disparar");
       etiqueta5.setForeground(Color.WHITE);
       etiqueta5.setFont(new Font("times new roman", Font.PLAIN, 30));
       panel2.add(etiqueta5);
       
       panelTeclas.add(panel2);
       
       panelTeclas.setAlignmentX(Component.CENTER_ALIGNMENT);
       
       add(panelTeclas);
       
       /////////////////////////////////////////////////////////
       
        JPanel panelTanque = new JPanel();
        panelTanque.setOpaque(false);
        panelTanque.setLayout(new FlowLayout());
        
        JPanel panel3 = new JPanel();
        panel3.setOpaque(false);
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.PAGE_AXIS));
        
       ImageIcon imagen5 = new ImageIcon("tanks3.png");
       JLabel tanqjugador = new JLabel(imagen5);
       tanqjugador.setSize(220,190);
       tanqjugador.setIcon(new ImageIcon(imagen5.getImage().getScaledInstance(tanqjugador.getWidth(), tanqjugador.getHeight(), Image.SCALE_SMOOTH)));      

       panel3.add(tanqjugador);
       
       JLabel etiqueta6 = new JLabel(" Tanque Jugador");
       etiqueta6.setForeground(Color.WHITE);
       etiqueta6.setFont(new Font("times new roman", Font.PLAIN, 30));
       panel3.add(etiqueta6);

       panelTanque.add(panel3);
       
       JPanel panel4 = new JPanel();
       panel4.setOpaque(false);
        panel4.setLayout(new BoxLayout(panel4,BoxLayout.PAGE_AXIS));
        
       ImageIcon imagen6 = new ImageIcon("Tanks1.png");
       
       JLabel etiqueta8 = new JLabel(imagen6);
       etiqueta8.setSize(220,190);
       etiqueta8.setIcon(new ImageIcon(imagen6.getImage().getScaledInstance(etiqueta8.getWidth(), etiqueta8.getHeight(), Image.SCALE_SMOOTH)));      

       panel4.add(etiqueta8);
       
       JLabel etiqueta9 = new JLabel(" Tanque Enemigo");
       etiqueta9.setForeground(Color.WHITE);
       etiqueta9.setFont(new Font("times new roman", Font.PLAIN, 30));
       panel4.add(etiqueta9);
       
       panelTanque.add(panel4);
       
       panelTanque.setAlignmentX(Component.CENTER_ALIGNMENT);
       
       add(panelTanque);
    }
}
