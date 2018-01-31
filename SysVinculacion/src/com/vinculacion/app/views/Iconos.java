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
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/vinculacion/app/img/logo.png"));
        frame.setIconImage(icono);
    }
    public static void setLogo(JDialog frame){
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/vinculacion/app/img/logo.png"));
        frame.setIconImage(icono);
    }
}
