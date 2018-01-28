package com.vinculacion.app.views;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author jorge
 */
public class Iconos {
    
    public static void setIcono(JFrame frame){
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/vinculacion/app/img/logo.png"));
        frame.setIconImage(icono);
    }
}
