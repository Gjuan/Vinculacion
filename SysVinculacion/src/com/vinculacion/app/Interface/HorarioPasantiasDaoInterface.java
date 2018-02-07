package com.vinculacion.app.Interface;

import com.vinculacion.app.model.HorarioPasantias;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface HorarioPasantiasDaoInterface {
    
    void saveHorarioPasantias (HorarioPasantias horario);
    
    List<HorarioPasantias> AllHorariosPasantias();
    
    void deleteHorariosPasantias(int id);
    
    void updateHorarioPasantia(HorarioPasantias horario);
    
    HorarioPasantias findHorarioPasantiaById(int id);
    
    HorarioPasantias findHorarioPasantiaByDescription(String description);
    
}
