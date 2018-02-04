package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameDocente;
import com.vinculacion.app.views.JFrameEditDocente;
import com.vinculacion.app.views.JFrameNuevoDocente;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocentesController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameDocente jfrdocente = new JFrameDocente(mp, false);
    JFrameEditDocente editdocent = new JFrameEditDocente(mp, false);
    JFrameNuevoDocente nuevodocent = new JFrameNuevoDocente(mp, false);
    
    public DocentesController(MenuPrincipal menup, JFrameDocente docent, JFrameEditDocente edit, JFrameNuevoDocente nuevo) {
        this.jfrdocente = docent;
        this.mp = menup;
        this.editdocent = edit;
        this.nuevodocent = nuevo;
        this.jfrdocente.btnNuevo.addActionListener(this);
        this.jfrdocente.btnBuscar.addActionListener(this);
        this.jfrdocente.btnImprimir.addActionListener(this);
        this.jfrdocente.btnEditar.addActionListener(this);
        this.jfrdocente.btnEliminar.addActionListener(this);
        
        this.nuevodocent.btnGuardar.addActionListener(this);
        this.nuevodocent.btnLimpiar.addActionListener(this);
        this.nuevodocent.btnRegresar.addActionListener(this);
        this.nuevodocent.btnAsignarCargo.addActionListener(this);
        this.nuevodocent.btnAsignarAsignatura.addActionListener(this);
        
        this.editdocent.btnGuardar.addActionListener(this);
        this.editdocent.btnRegresar.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrdocente.btnNuevo) {
            this.nuevodocent.setVisible(true);
            this.jfrdocente.dispose();
        }
        if (e.getSource() == this.jfrdocente.btnBuscar) {
            
        }
        if (e.getSource() == this.jfrdocente.btnImprimir) {
            
        }
        if (e.getSource() == this.jfrdocente.btnEditar) {
            
        }
        if (e.getSource() == this.jfrdocente.btnEliminar) {
            
        }
        if (e.getSource() == this.nuevodocent.btnLimpiar) {
            
        }
        if (e.getSource() == this.nuevodocent.btnRegresar) {
            this.nuevodocent.dispose();
            this.jfrdocente.setVisible(true);
        }
        if (e.getSource() == this.editdocent.btnGuardar) {
            
        }
        if (e.getSource() == this.editdocent.btnRegresar) {
            this.editdocent.dispose();
            this.jfrdocente.setVisible(true);
        }
    }   
}
