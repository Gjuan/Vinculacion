package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditNivel;
import com.vinculacion.app.views.JFrameNivel;
import com.vinculacion.app.views.JFrameNuevoNivel;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class NivelController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameNivel jfrnivel = new JFrameNivel(mp, false);
    JFrameNuevoNivel jfrnuevo = new JFrameNuevoNivel(mp, false);
    JFrameEditNivel jfredit = new JFrameEditNivel(mp, false);
    
    public NivelController(MenuPrincipal menup, JFrameNivel nivel, JFrameNuevoNivel nuevo, JFrameEditNivel edit) {
        this.mp = menup;
        this.jfrnivel = nivel;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrnivel.btnBuscar.addActionListener(this);
        this.jfrnivel.btnDarBaja.addActionListener(this);
        this.jfrnivel.btnEditar.addActionListener(this);
        this.jfrnivel.btnNuevo.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
}
