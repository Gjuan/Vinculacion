package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Escuela;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface EscuelaDaoInterface {
    
    void saveEscuela(Escuela escuela);
    
    List<Escuela> AllEscuelas();
    
    void deleteEscuelaById(int id);
    
    void updateEscuela(Escuela escuela);
    
    Escuela findEscuelaById (int id);
    
    Escuela findEscuelaByName (String name);
    
    List<Escuela> findEscuelaByFacultad (String description);
}
