package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Facultad;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface FacultadDaoInterface {
     
    void saveFacultad(Facultad facultad);
    
    List<Facultad>AllFacultad();
    
    void deleteFacultadById(int id);
    
    void updateFacultad(Facultad facultad);
    
    Facultad findFacultadById (int id);
    
    Facultad findFacultadByDescription(String description);
}
