package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditUsuario;
import com.vinculacion.app.views.JFrameNuevoUsuario;
import com.vinculacion.app.views.JFrameUsuarios;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuariosController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameUsuarios jfrusuarios = new JFrameUsuarios(mp, false);
    JFrameNuevoUsuario jfrnuevouser = new JFrameNuevoUsuario(mp, false);
    JFrameEditUsuario jfredituser= new JFrameEditUsuario(mp, false);
    
    public UsuariosController(MenuPrincipal menup, JFrameUsuarios juser, JFrameNuevoUsuario jnew, JFrameEditUsuario jedit) {
        this.mp = menup;
        this.jfrusuarios = juser;
        this.jfrnuevouser = jnew;
        this.jfredituser = jedit;
        
        this.jfrusuarios.btnBuscar.addActionListener(this);
        this.jfrusuarios.btnDarBaja.addActionListener(this);
        this.jfrusuarios.btnEditar.addActionListener(this);
        this.jfrusuarios.btnNuevo.addActionListener(this);
        
        this.jfrnuevouser.btnGuardar.addActionListener(this);
        this.jfrnuevouser.btnRegresar.addActionListener(this);
        
        this.jfredituser.btnGuardar.addActionListener(this);
        this.jfredituser.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrusuarios.btnBuscar) {
            
        }
        if (e.getSource() == this.jfrusuarios.btnDarBaja) {
            
        }
        if (e.getSource() == this.jfrusuarios.btnEditar) {
            
        }
        if (e.getSource() == this.jfrusuarios.btnNuevo) {
            
        }
        /*NUEVO USUARIO*/
        if (e.getSource() == this.jfrnuevouser.btnGuardar) {
            
        }
        if (e.getSource() == this.jfrnuevouser.btnRegresar) {
            
        }
        /*EDITAR USUARIO*/
        if (e.getSource() == this.jfredituser.btnGuardar) {
            
        }
        if (e.getSource() == this.jfredituser.btnRegresar) {
            
        }
    }  
}
