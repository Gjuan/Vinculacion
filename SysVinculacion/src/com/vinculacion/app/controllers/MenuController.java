package com.vinculacion.app.controllers;

import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.JFrameFacultad;
import com.vinculacion.app.views.JFrameNuevaFacultad;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    Auth auth = new Auth();            
    JFrameFacultad facultad;
    JFrameNuevaFacultad newFacultad;
    
    public MenuController(MenuPrincipal menu, Auth a) {
        this.mp = menu;
        this.auth = a;
        this.mp.menuItemCerrar.addActionListener(this);
        this.mp.menuItemSalir.addActionListener(this);
        this.mp.menuItemFacultad.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.mp.menuItemCerrar){
            this.mp.hide();
            this.auth.setVisible(true);
            AuthController ac = new AuthController(auth, mp);
        }
        if (e.getSource()== this.mp.menuItemSalir) {
            System.exit(0);
        }
        if (e.getSource() == this.mp.menuItemFacultad) {
            this.facultad = new JFrameFacultad(this.mp, false);
            this.facultad.setVisible(true);
            this.newFacultad = new JFrameNuevaFacultad(mp, true);
            FacultadController fcontrol = new FacultadController(mp, newFacultad, facultad);
        }
    } 
    
}
