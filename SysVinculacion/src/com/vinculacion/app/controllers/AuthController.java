package com.vinculacion.app.controllers;

import com.vinculacion.app.factory.FactorFactory;
import com.vinculacion.app.model.Perfil;
import com.vinculacion.app.model.Usuarios;
import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class AuthController extends FactorFactory implements ActionListener{
    
    Auth auth = new Auth();
    
    public AuthController(Auth au) {
        this.auth = au;
        this.auth.btnEntrar.addActionListener(this);
        this.auth.btnCancelar.addActionListener(this);
        
        List<Perfil> perfiles = this.getSession().createQuery("FROM Perfil").list();
        for (Perfil perfil : perfiles) {
            this.auth.comboPerfil.addItem(perfil.getDescripcion());
        }
    }
    
    public int Authenticable (){
        int result = 0;
        try {       
            Usuarios usuario = (Usuarios) this.getSession().createQuery("FROM Usuarios WHERE nom_usuario = :nom_user AND contrasena = :contra")
                    .setParameter("nom_user",this.auth.txtUsuario.getText().toString())
                    .setParameter("contra", this.auth.txtContrasena.getText().toString())
                    .uniqueResult();
            if (!usuario.getNom_usuario().equals(this.auth.txtUsuario.getText().toString())) {
                result = 1;
            }else if (!usuario.getContrasena().equals(this.auth.txtContrasena.getText().toString())){
                result = 2;
            }else if(!usuario.getPerfil().getDescripcion().equals(this.auth.comboPerfil.getSelectedItem().toString())){
                result = 3;
            }else{
                result = 4;
            }
        } catch (Exception e) {
            result = 5;
        }
        return result;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.auth.btnEntrar) {
            switch(Authenticable()){
                case 1:
                    JOptionPane.showMessageDialog(auth,"Nombre de usuario incorrecto");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(auth,"Contraseña incorrecta");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(auth,"Asegúrese que el perfil que seleccionó sea el correcto");
                    break;
                case 4:
                        MenuPrincipal mp = new MenuPrincipal();
                        JOptionPane.showMessageDialog(auth,"Bienvenido Usuario " + this.auth.txtUsuario.getText().toString());
                        mp.setVisible(true);                
                        this.auth.hide();
                        MenuController mc = new MenuController(mp);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(auth,"Usuario o contraseña incorrecta!!");
                    break;
            }          
        }
        if (ae.getSource() == this.auth.btnCancelar) {
            this.auth.txtContrasena.setText("");
            this.auth.txtUsuario.setText("");
            this.auth.comboPerfil.setSelectedIndex(0);
            this.auth.txtUsuario.requestFocus();
        }
    }
        
}
