package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameAsignaturas;
import com.vinculacion.app.views.JFrameEditAsignatura;
import com.vinculacion.app.views.JFrameNuevaAsignatura;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AsignaturasController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameAsignaturas jfrasig = new JFrameAsignaturas(mp, false);
    JFrameEditAsignatura editasig = new JFrameEditAsignatura(mp, false);
    JFrameNuevaAsignatura nuevaasig = new JFrameNuevaAsignatura(mp, false);
    
    public AsignaturasController(MenuPrincipal menup, JFrameAsignaturas asig, JFrameEditAsignatura edit, JFrameNuevaAsignatura nuevo) {
        this.mp = menup;
        this.jfrasig = asig;
        this.editasig = edit;
        this.nuevaasig = nuevo;
        
        this.jfrasig.btnBuscar.addActionListener(this);
        this.jfrasig.btnDarBaja.addActionListener(this);
        this.jfrasig.btnEditar.addActionListener(this);
        this.jfrasig.btnNuevo.addActionListener(this);
        
        this.editasig.btnGuardar.addActionListener(this);
        this.editasig.btnRegresar.addActionListener(this);
        
        this.nuevaasig.btnGuardar.addActionListener(this);
        this.nuevaasig.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrasig.btnNuevo) {
            this.nuevaasig.setVisible(true);
            this.jfrasig.dispose();
        }
        if (e.getSource() == this.nuevaasig.btnRegresar) {
            this.nuevaasig.dispose();
            this.jfrasig.setVisible(true);
        }
    }   
}
