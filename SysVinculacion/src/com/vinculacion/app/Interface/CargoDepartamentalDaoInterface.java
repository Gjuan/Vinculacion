package com.vinculacion.app.Interface;

import com.vinculacion.app.model.CargoDepartamental;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface CargoDepartamentalDaoInterface {
    
    void saveCargoDepartamental (CargoDepartamental cargo);
    
    List<CargoDepartamental> AllCargosDepartamental();
    
    void updateCargoDepartamental(CargoDepartamental cargo);
    
    void deleteCargoDepartamental(int id);
    
    CargoDepartamental findCargoDepartamentalByDescription(String description);
    
    CargoDepartamental findCargoDepartamentalById(int id);
    
}
