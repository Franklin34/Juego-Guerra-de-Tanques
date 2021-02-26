/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Usuario
 */
public class Texto {

    public static void drawText(Graphics g, String text, Vector2D pos, boolean center, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);
        Vector2D position = new Vector2D(pos.getX(), pos.getY());

        if (center) {
            FontMetrics fm = g.getFontMetrics();
            position.setX(position.getX() - fm.stringWidth(text) / 2);
            position.setY(position.getY() - fm.getHeight() / 2);

        }

        g.drawString(text, (int) position.getX(), (int) position.getY());

    }
}
