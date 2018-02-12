package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Pasantias;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface PasantiasDaoInterface {
    
    void savePasantias(Pasantias pasantia);
    
    List<Pasantias> AllPasantias();
    
    Pasantias findPasantiaById(int id);
    
    List<Pasantias> findPasantiaByFechaInicio (String fecha_inicio);
    
    List<Pasantias> findPasantiaByFechaFin (String fecha_fin);
    
    void updatePasantia (Pasantias pasantia);
    
    void deletePasantia (int id);
    
}
