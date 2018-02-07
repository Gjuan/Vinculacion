package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Asignaturas;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface AsignaturaDaoInterface {
    
    void saveAsignatura (Asignaturas asignatura);
    
    List<Asignaturas> AllAsignaturas();
    
    void updateAsignatura(Asignaturas asignatura);
    
    void deleteAsignatura(int id);
    
    Asignaturas findAsignaturaByName(String name);
    
    Asignaturas findAsignaturaById(int id);

}
