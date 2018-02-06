package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameCargo;
import com.vinculacion.app.views.JFrameEditCargo;
import com.vinculacion.app.views.JFrameNuevoCargo;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CargoController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameCargo jfrcargo = new JFrameCargo(mp, false);
    JFrameNuevoCargo jfrnuevocargo = new JFrameNuevoCargo(mp, false);
    JFrameEditCargo editcargo = new JFrameEditCargo(mp, false);
    
    public CargoController(MenuPrincipal menup, JFrameCargo cargo, JFrameNuevoCargo newcargo, JFrameEditCargo edcargo) {
        this.mp = menup;
        this.jfrcargo = cargo;
        this.jfrnuevocargo = newcargo;
        this.editcargo = edcargo;
        
        this.jfrcargo.btnBuscar.addActionListener(this);
        this.jfrcargo.btnDarBaja.addActionListener(this);
        this.jfrcargo.btnEditar.addActionListener(this);
        this.jfrcargo.btnNuevo.addActionListener(this);
        
        this.jfrnuevocargo.btnGuardar.addActionListener(this);
        this.jfrnuevocargo.btnRegresar.addActionListener(this);
        
        this.editcargo.btnGuardar.addActionListener(this);
        this.editcargo.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrcargo.btnBuscar) {
            
        }
        if (e.getSource() == this.jfrcargo.btnDarBaja) {
            
        }
        if (e.getSource() == this.jfrcargo.btnEditar) {
            
        }
        if (e.getSource() == this.jfrcargo.btnNuevo) {
            
        }
        if (e.getSource() == this.jfrnuevocargo.btnGuardar) {
            
        }
        if (e.getSource() == this.jfrnuevocargo.btnRegresar) {
            
        }
    }   
}
