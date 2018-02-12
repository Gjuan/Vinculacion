package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.PerfilDAO;
import com.vinculacion.app.dao.UsuariosDAO;
import com.vinculacion.app.model.Perfil;
import com.vinculacion.app.model.Usuarios;
import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class AuthController implements ActionListener{
    
    private Auth auth = new Auth();
    private MenuPrincipal mp = new MenuPrincipal();
    private UsuariosDAO userdao;
    private PerfilDAO perfildao;
    public static int id;
    
    public AuthController(Auth au, MenuPrincipal menuP) {
        this.auth = au;
        this.auth.btnEntrar.addActionListener(this);
        this.auth.btnCancelar.addActionListener(this);
        this.mp = menuP;
        perfildao = new PerfilDAO();        
        userdao = new UsuariosDAO();
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
                JOptionPane.showMessageDialog(auth, "Nombre de usuario incorrecto");
            }else if (!usuario.getContrasena().equals(this.auth.txtContrasena.getText().toString())){
                JOptionPane.showMessageDialog(auth, "Contraseña incorrecta"); 
            }else if(!usuario.getPerfil().getDescripcion().equals(this.auth.comboPerfil.getSelectedItem().toString())){
                JOptionPane.showMessageDialog(auth, "Perfil incorrecto");
            }else{
                if (this.auth.comboPerfil.getSelectedItem().equals("ADMIN")) {
                    mp.menuItemUsuarios.setVisible(true);
                    mp.menuItemSeccion.setVisible(true);
                    mp.menuItemGenero.setVisible(true);
                    mp.menuUtb.setVisible(true);
                    mp.menuItemNivel.setVisible(true); 
                    mp.menuItemPerfil.setVisible(true);
                }else{
                    mp.menuItemPerfil.setVisible(false);
                    mp.menuItemUsuarios.setVisible(false);
                    mp.menuItemSeccion.setVisible(false);
                    mp.menuItemGenero.setVisible(false);
                    mp.menuUtb.setVisible(false);     
                    mp.menuItemNivel.setVisible(false);
                }
                JOptionPane.showMessageDialog(auth, "Bienvenido usuario " + usuario.getNombres() + " " + usuario.getApellidos());
                this.mp.setVisible(true);
                id = usuario.getId_usuario();
                this.mp.menuItemNomUser.setText(usuario.getNombres() +" " + usuario.getApellidos());
                this.auth.hide();                                        
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(auth,"Usuario o contraseña incorrecta, aségurese que este usuario exista en el sistema o que esté activo");
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
