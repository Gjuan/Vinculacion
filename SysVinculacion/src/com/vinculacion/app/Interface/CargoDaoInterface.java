package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Cargo;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface CargoDaoInterface {
    
    void saveCargo (Cargo cargo);
    
    List<Cargo> AllCargos();
    
    void updateCargo(Cargo cargo);
    
    void deleteCargo(int id);
    
    Cargo findCargoByDescription(String description);
    
    Cargo findCargoById(int id);
    
}
