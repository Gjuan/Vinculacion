package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Nivel;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface NivelDaoInterface {
    
    void saveNivel (Nivel nivel);
    
    List<Nivel> AllNiveles();
    
    void updateNivel (Nivel nivel);
    
    void deleteNivel(int id);
    
    Nivel findNivelById (int id);
    
    Nivel findNivelBySemestre(String semestre);
    
}
