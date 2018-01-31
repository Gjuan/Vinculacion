package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.PerfilDAO;
import com.vinculacion.app.dao.UsuariosDAO;
import com.vinculacion.app.model.Perfil;
import com.vinculacion.app.model.Usuarios;
import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AuthController implements ActionListener{
    
    private Auth auth = new Auth();
    private MenuPrincipal mp = new MenuPrincipal();
    private UsuariosDAO userdao;
    private PerfilDAO perfildao;
    
    public AuthController(Auth au, MenuPrincipal menuP) {
        this.auth = au;
        this.auth.btnEntrar.addActionListener(this);
        this.auth.btnCancelar.addActionListener(this);
        this.mp = menuP;
        userdao = new UsuariosDAO();
        perfildao = new PerfilDAO();
        
        this.auth.comboPerfil.removeAllItems();
        List<Perfil> perfiles = perfildao.AllPerfil();       
        for (Perfil perfil : perfiles) {
            this.auth.comboPerfil.addItem(perfil.getDescripcion());
        }
    }
    
    public void Authenticable (){
        try {       
            Usuarios usuario = userdao.findUsuarioByUserAndPass(this.auth.txtUsuario.getText().toString()
                    , this.auth.txtContrasena.getText().toString());      
            
            if (!usuario.getNom_usuario().equals(this.auth.txtUsuario.getText().toString())) {
                this.auth.mensajeUser.setText("Nombre de usuario incorrecto");
            }else if (!usuario.getContrasena().equals(this.auth.txtContrasena.getText().toString())){
                this.auth.mensajeContrasena.setText("Contraseña incorrecta");                
            }else if(!usuario.getPerfil().getDescripcion().equals(this.auth.comboPerfil.getSelectedItem().toString())){
                this.auth.mensajePefil.setText("Perfil incorrecto");                
            }else{
                this.auth.mensajeUser.setText("");
                this.auth.mensajePefil.setText("");
                this.auth.mensajeContrasena.setText("");
                this.auth.AccesoConcedido.setForeground(Color.BLUE);
                this.auth.AccesoConcedido.setText("Acceso Correcto !!");
                
                if (this.auth.comboPerfil.getSelectedItem().equals("ADMIN")) {
                    mp.menuItemUsuarios.setVisible(true);
                    mp.menuItemSeccion.setVisible(true);
                    mp.menuItemGenero.setVisible(true);
                    mp.menuUtb.setVisible(true);
                    mp.menuItemNivel.setVisible(true); 
                }else{
                    mp.menuItemUsuarios.setVisible(false);
                    mp.menuItemSeccion.setVisible(false);
                    mp.menuItemGenero.setVisible(false);
                    mp.menuUtb.setVisible(false);     
                    mp.menuItemNivel.setVisible(false);
                }
                this.auth.hide();                        
                this.mp.setVisible(true);
                MenuController mc = new MenuController(mp, auth);
            }
        } catch (Exception e) {
            this.auth.AccesoConcedido.setForeground(Color.RED);
            this.auth.AccesoConcedido.setText("Usuario o contraseña incorrecta!!");
        }        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.auth.btnEntrar) {
           Authenticable();
        }
        if (ae.getSource() == this.auth.btnCancelar) {
            this.auth.txtContrasena.setText("");
            this.auth.txtUsuario.setText("");
            this.auth.comboPerfil.setSelectedIndex(0);
            this.auth.txtUsuario.requestFocus();
        }
    }
        
}
