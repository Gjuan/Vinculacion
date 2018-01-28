package com.vinculacion.app.controllers;

import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();

    public MenuController(MenuPrincipal menu) {
        this.mp = menu;
        this.mp.menuItemCerrar.addActionListener(this);
        this.mp.menuItemSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.mp.menuItemCerrar){
            this.mp.hide();
            Auth auth = new Auth();
            auth.setVisible(true);
            AuthController ac = new AuthController(auth);
        }
        if (e.getSource()== this.mp.menuItemSalir) {
            System.exit(0);
        }
    } 
    
}
