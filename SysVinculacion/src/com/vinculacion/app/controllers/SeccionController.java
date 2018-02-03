package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditSeccion;
import com.vinculacion.app.views.JFrameNuevaSeccion;
import com.vinculacion.app.views.JFrameSeccion;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class SeccionController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameSeccion jfrseccion = new JFrameSeccion(mp, false);
    JFrameNuevaSeccion newseccion = new JFrameNuevaSeccion(mp, false);
    JFrameEditSeccion editseccion = new JFrameEditSeccion(mp , false);
    
    public SeccionController(MenuPrincipal menu,JFrameSeccion fseccion, JFrameNuevaSeccion nueva, JFrameEditSeccion edit) {
        this.jfrseccion.btnBuscar.addActionListener(this);
        this.jfrseccion.btnEditar.addActionListener(this);
        this.jfrseccion.btnNuevo.addActionListener(this);
        this.newseccion.btnGuardar.addActionListener(this);
        this.newseccion.btnRegresar.addActionListener(this);
        this.editseccion.btnGuardar.addActionListener(this);
        this.editseccion.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
    
}
