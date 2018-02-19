package com.vinculacion.app.views;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author jorge
 */
public class Iconos {
    
    public static void setLogo(JFrame frame){
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/vinculacion/app/img/logo.PNG"));
        frame.setIconImage(icono);
    }
    public static void setLogo(JDialog frame){
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/vinculacion/app/img/logo.PNG"));
        frame.setIconImage(icono);
    }
    
    public static void setSameDefault(JDialog frame, String titulo){
        frame.setTitle(titulo);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        setLogo(frame);
    }
    public static int hex( String color_hex ){
        return Integer.parseInt(color_hex,  16 );
    }
}
