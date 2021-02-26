/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes_sonidoJuego;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Usuario
 */
public class Cargar {

    public static BufferedImage cargadorDeImagenes(String ruta) {//BufferedImage es la manera en la que java guarda las imagenes en memoria
        try {
            return ImageIO.read(Cargar.class.getResource(ruta));

        } catch (IOException ex) {
            System.out.println("Error al cargar la imagen...");
        }
        return null;

    }

    public static Font loadFont(String path, int size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
