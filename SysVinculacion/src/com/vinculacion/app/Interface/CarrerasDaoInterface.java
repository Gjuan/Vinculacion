package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Escuela;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface CarrerasDaoInterface {
    
    void saveCarreras(Carreras carrera);
    
    List<Carreras> AllCarreras();
    
    void deleteCarreraById(int id);
    
    void updateCarrera(Carreras carrera);
    
    Carreras findCarreraById(int id);
    
    Carreras findCarreraByDescription(String descripcion);
    
}
