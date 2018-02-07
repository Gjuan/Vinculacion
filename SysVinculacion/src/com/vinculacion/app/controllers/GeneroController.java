package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditGenero;
import com.vinculacion.app.views.JFrameGenero;
import com.vinculacion.app.views.JFrameNuevoGenero;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class GeneroController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameGenero jfrgenero = new JFrameGenero(mp, false);
    JFrameNuevoGenero jfrnuevo = new JFrameNuevoGenero(mp, false);
    JFrameEditGenero jfredit = new JFrameEditGenero(mp, false);
    
    public GeneroController(MenuPrincipal menup, JFrameGenero genero, JFrameNuevoGenero nuevo, JFrameEditGenero edit) {
        this.mp = menup;
        this.jfrgenero = genero;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrgenero.btnBuscar.addActionListener(this);
        this.jfrgenero.btnDarBaja.addActionListener(this);
        this.jfrgenero.btnEditar.addActionListener(this);
        this.jfrgenero.btnNuevo.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrgenero.btnBuscar) {
            
        }
        if (e.getSource() == this.jfrgenero.btnDarBaja) {
            
        }
        if (e.getSource() == this.jfrgenero.btnEditar) {
            
        }
        if (e.getSource() == this.jfrgenero.btnNuevo) {
            
        }
        /*NUEVO GENERO*/
        if (e.getSource() == this.jfrnuevo.btnGuardar) {
            
        }
        if (e.getSource() == this.jfrnuevo.btnRegresar) {
            
        }
        /*EDITAR GÉNERO*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            
        }
    }    
}
