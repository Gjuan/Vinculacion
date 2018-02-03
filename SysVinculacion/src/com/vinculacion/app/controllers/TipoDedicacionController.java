package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditTipoDedicacion;
import com.vinculacion.app.views.JFrameNuevoTipoDedicacion;
import com.vinculacion.app.views.JFrameTipoDedicacion;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class TipoDedicacionController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameTipoDedicacion frmtipodedica = new JFrameTipoDedicacion(mp, false);
    JFrameNuevoTipoDedicacion newtipo = new JFrameNuevoTipoDedicacion(mp, false);
    JFrameEditTipoDedicacion edittipo = new JFrameEditTipoDedicacion(mp, false);
    
    public TipoDedicacionController(MenuPrincipal menup, JFrameTipoDedicacion frmtd, JFrameNuevoTipoDedicacion newtipo, JFrameEditTipoDedicacion tipode) {
        this.mp = menup;
        this.frmtipodedica = frmtd;
        this.newtipo = newtipo;
        this.edittipo = tipode;
        
        this.frmtipodedica.btnBuscar.addActionListener(this);
        this.frmtipodedica.btnDarBaja.addActionListener(this);
        this.frmtipodedica.btnEditar.addActionListener(this);
        this.frmtipodedica.btnNuevo.addActionListener(this);
        this.newtipo.btnGuardar.addActionListener(this);
        this.newtipo.btnRegresar.addActionListener(this);
        this.edittipo.btnGuardar.addActionListener(this);
        this.edittipo.btnRegresar.addActionListener(this);
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
    
}
