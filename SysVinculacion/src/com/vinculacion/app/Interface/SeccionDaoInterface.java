package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Seccion;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface SeccionDaoInterface {
   
    void saveSeccion (Seccion seccion);
    
    List<Seccion> AllSecciones();
    
    void deleteSeccion(int id);
    
    void updateSeccion(Seccion seccion);
    
    Seccion findSeccionById(int id);
    
    Seccion findSeccionByDescription(String description);
    
}
