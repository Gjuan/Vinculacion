package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditPeriodoAcademico;
import com.vinculacion.app.views.JFrameNuevoPeriodoAcademico;
import com.vinculacion.app.views.JFramePeriodoAcademico;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeriodoAcademicoController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFramePeriodoAcademico jfrperiodo = new JFramePeriodoAcademico(mp, false);
    JFrameNuevoPeriodoAcademico jfrnuevoperiodo = new JFrameNuevoPeriodoAcademico(mp, false);
    JFrameEditPeriodoAcademico editperiodo = new JFrameEditPeriodoAcademico(mp, false);
    
    public PeriodoAcademicoController(MenuPrincipal menup, JFramePeriodoAcademico periodo, JFrameNuevoPeriodoAcademico nuevo, JFrameEditPeriodoAcademico edit) {
        this.mp = menup;
        this.jfrperiodo = periodo;
        this.jfrnuevoperiodo = nuevo;
        this.editperiodo = edit;
        
        this.jfrperiodo.btnBuscar.addActionListener(this);
        this.jfrperiodo.btnBuscarByFechaInicio.addActionListener(this);
        this.jfrperiodo.btnDarBaja.addActionListener(this);
        this.jfrperiodo.btnEditar.addActionListener(this);
        this.jfrperiodo.btnNuevo.addActionListener(this);
        
        this.jfrnuevoperiodo.btnGuardar.addActionListener(this);
        this.jfrnuevoperiodo.btnRegresar.addActionListener(this);
        
        this.editperiodo.btnGuardar.addActionListener(this);
        this.editperiodo.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrperiodo.btnBuscar) {
            
        }
        if (e.getSource() == this.jfrperiodo.btnBuscarByFechaInicio) {
            
        }
        if (e.getSource() == this.jfrperiodo.btnDarBaja) {
            
        }
        if (e.getSource() == this.jfrperiodo.btnEditar) {
            
        }
        if (e.getSource() == this.jfrperiodo.btnNuevo) {
            
        }
        /***NUEVO PERIODO***/
        if (e.getSource() == this.jfrnuevoperiodo.btnGuardar) {
            
        }
        if (e.getSource() == this.jfrnuevoperiodo.btnRegresar) {
            this.jfrperiodo.setVisible(true);
            this.jfrnuevoperiodo.dispose();
        }
        /***EDITAR PERIODO***/
        if (e.getSource() == this.editperiodo.btnGuardar) {
            
        }
        if (e.getSource() == this.editperiodo.btnRegresar) {
            this.jfrperiodo.setVisible(true);
            this.editperiodo.dispose();
        }
    }   
}
