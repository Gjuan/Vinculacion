package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameCarreras;
import com.vinculacion.app.views.JFrameEditCarrera;
import com.vinculacion.app.views.JFrameNuevaCarrera;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class CarrerasController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameCarreras jfrcarrera = new JFrameCarreras(mp, false);
    JFrameNuevaCarrera jfrnuevacarrera = new JFrameNuevaCarrera(mp, false);
    JFrameEditCarrera jfreditcarrera = new JFrameEditCarrera(mp, false);
    
    public CarrerasController(MenuPrincipal menup, JFrameCarreras carrera, JFrameNuevaCarrera nueva, JFrameEditCarrera edit) {
        this.mp = menup;
        this.jfrcarrera = carrera;
        this.jfrnuevacarrera = nueva;
        this.jfreditcarrera = edit;
        
        this.jfrcarrera.btnBuscar.addActionListener(this);
        this.jfrcarrera.btnDarBaja.addActionListener(this);
        this.jfrcarrera.btnNueva.addActionListener(this);
        this.jfrcarrera.btnEditar.addActionListener(this);
        
        this.jfrnuevacarrera.btnGuardar.addActionListener(this);
        this.jfrnuevacarrera.btnRegresar.addActionListener(this);
        
        this.jfreditcarrera.btnGuardar.addActionListener(this);
        this.jfreditcarrera.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrcarrera.btnBuscar) {
            
        }
        if (e.getSource() == this.jfrcarrera.btnDarBaja) {
            
        }
        if (e.getSource() == this.jfrcarrera.btnEditar) {
            
        }
        if (e.getSource() == this.jfrcarrera.btnNueva) {
            
        }
        if (e.getSource() == this.jfrnuevacarrera.btnGuardar) {
            
        }
        if (e.getSource() == this.jfrnuevacarrera.btnRegresar) {
            
        }
        /*EDITAR CARRERAS*/
        if (e.getSource() == this.jfreditcarrera.btnGuardar) {
            
        }
        if (e.getSource() == this.jfreditcarrera.btnRegresar) {
            
        }
    }    
}
