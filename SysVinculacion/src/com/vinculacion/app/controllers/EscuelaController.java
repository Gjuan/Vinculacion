package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditEscuela;
import com.vinculacion.app.views.JFrameEscuela;
import com.vinculacion.app.views.JFrameNuevaEscuela;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class EscuelaController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameEscuela jfrescuela = new JFrameEscuela(mp, false);
    JFrameNuevaEscuela jfrnueva = new JFrameNuevaEscuela(mp, false);
    JFrameEditEscuela jfredit = new JFrameEditEscuela(mp, false);
    
    public EscuelaController(MenuPrincipal menup, JFrameEscuela escuela, JFrameNuevaEscuela nueva, JFrameEditEscuela edit)  {
        this.mp = menup;
        this.jfrescuela = escuela;
        this.jfrnueva = nueva;
        this.jfredit = edit;
        
        this.jfrescuela.btnBuscar.addActionListener(this);
        this.jfrescuela.btnDarBaja.addActionListener(this);
        this.jfrescuela.btnEditar.addActionListener(this);
        this.jfrescuela.btnNueva.addActionListener(this);
        
        this.jfrnueva.btnGuardar.addActionListener(this);
        this.jfrnueva.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
}
