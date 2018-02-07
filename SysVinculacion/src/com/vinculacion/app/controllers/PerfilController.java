package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditPerfil;
import com.vinculacion.app.views.JFrameNuevoPerfil;
import com.vinculacion.app.views.JFramePerfil;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class PerfilController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFramePerfil jfrperfil = new JFramePerfil(mp, false);
    JFrameNuevoPerfil jfrnuevo = new JFrameNuevoPerfil(mp, false);
    JFrameEditPerfil jfredit = new JFrameEditPerfil(mp, false);
    
    public PerfilController(MenuPrincipal menup, JFramePerfil perfil, JFrameNuevoPerfil nuevo, JFrameEditPerfil edit) {
        this.mp = menup;
        this.jfrperfil = perfil;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrperfil.btnBuscar.addActionListener(this);
        this.jfrperfil.btnDarBaja.addActionListener(this);
        this.jfrperfil.btnEditar.addActionListener(this);
        this.jfrperfil.btnNuevo.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrperfil.btnBuscar) {
            
        }
        if (e.getSource() == this.jfrperfil.btnDarBaja) {
            
        }
        if (e.getSource() == this.jfrperfil.btnEditar) {
            
        }
        if (e.getSource() == this.jfrperfil.btnNuevo) {
            
        }
        /*NUEVO PERFIL*/
        if (e.getSource() == this.jfrnuevo.btnGuardar) {
            
        }
        if (e.getSource() == this.jfrnuevo.btnRegresar) {
            
        }
        /*EDITA PERFIL*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            
        }
    }
}
