package com.vinculacion.app.controllers;

import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.JFrameDocente;
import com.vinculacion.app.views.JFrameEditDocente;
import com.vinculacion.app.views.JFrameEditFacultad;
import com.vinculacion.app.views.JFrameFacultad;
import com.vinculacion.app.views.JFrameNuevaFacultad;
import com.vinculacion.app.views.JFrameNuevoDocente;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    Auth auth = new Auth();            
    JFrameFacultad facultad;
    JFrameNuevaFacultad newFacultad;
    JFrameEditFacultad editfacultad;
    JFrameDocente jfrdocent;
    JFrameEditDocente jfreditdocent;
    JFrameNuevoDocente jfrNuevoDocent;
    
    public MenuController(MenuPrincipal menu, Auth a) {
        this.mp = menu;
        this.auth = a;
        this.mp.menuItemCerrar.addActionListener(this);
        this.mp.menuItemSalir.addActionListener(this);
        this.mp.menuItemFacultad.addActionListener(this);
        this.mp.menuItemDocentes.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.mp.menuItemCerrar){
            this.mp.hide();
            this.auth.setVisible(true);
        }
        if (e.getSource()== this.mp.menuItemSalir) {
            System.exit(0);
        }
        if (e.getSource() == this.mp.menuItemFacultad) {
            this.facultad = new JFrameFacultad(this.mp, false);
            this.facultad.setVisible(true);
            this.newFacultad = new JFrameNuevaFacultad(mp, false);
            this.editfacultad = new JFrameEditFacultad(mp, false);
            FacultadController fcontrol = new FacultadController(mp, newFacultad, facultad, editfacultad);
        }
        if (e.getSource() == this.mp.menuItemDocentes) {
            this.jfrdocent = new JFrameDocente(mp, false);
            this.jfrNuevoDocent = new JFrameNuevoDocente(mp, false);
            this.jfreditdocent = new JFrameEditDocente(mp, false);
            this.jfrdocent.setVisible(true);
            DocentesController docente = new DocentesController(mp, jfrdocent, jfreditdocent, jfrNuevoDocent);
        }
    } 
    
}
