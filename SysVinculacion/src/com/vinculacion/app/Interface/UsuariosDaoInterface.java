package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Usuarios;
import java.util.List;

public interface UsuariosDaoInterface {
    
    void saveUsuarios(Usuarios usuario);
    
    List<Usuarios>AllUsuarios();
    
    void deleteUsuarioById(int id);
    
    void updateUsuario(Usuarios usuario);
    
    Usuarios findUsuarioById (int id);
    
    Usuarios findUsuarioByUserAndPass (String nom_user, String pass);
    
    List<Usuarios> findUserByPerfil(String descriptionPerfil);
    
}
