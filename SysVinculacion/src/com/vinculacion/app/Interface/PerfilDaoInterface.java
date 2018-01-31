package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Perfil;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface PerfilDaoInterface {
    
    void savePerfil(Perfil perfil);
    
    List<Perfil>AllPerfil();
    
    void deletePerfilById(int id);
    
    void updatePerfil(Perfil perfil);
    
    Perfil findPerfilById (int id);
}
