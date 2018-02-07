package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditHorarioPasantias;
import com.vinculacion.app.views.JFrameHorarioPasantias;
import com.vinculacion.app.views.JFrameNuevoHorarioPasantias;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class HorarioPasantiasController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameHorarioPasantias jfrhorario = new JFrameHorarioPasantias(mp, false);
    JFrameNuevoHorarioPasantias jfrnuevo = new JFrameNuevoHorarioPasantias(mp, false);
    JFrameEditHorarioPasantias jfredit = new JFrameEditHorarioPasantias(mp, false);
    
    public HorarioPasantiasController(MenuPrincipal menup, JFrameHorarioPasantias horario, JFrameNuevoHorarioPasantias nuevo, JFrameEditHorarioPasantias edit) {
        this.mp = menup;
        this.jfrhorario = horario;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrhorario.btnBuscar.addActionListener(this);
        this.jfrhorario.btnEditar.addActionListener(this);
        this.jfrhorario.btnNuevoHorario.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
    }  
}
