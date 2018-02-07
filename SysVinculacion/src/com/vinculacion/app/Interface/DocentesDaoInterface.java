package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.TipoDedicacion;
import java.util.List;

public interface DocentesDaoInterface {
    
    void saveDocente(Docente docente);
    
    List<Docente> AllDocente();
    
    void updateDocente(Docente docente);
    
    void deleteDocente(int id);
    
    Docente findDocenteById(int id);
    
    Docente findDocenteByCedula(String cedula);
    
    List<Docente> findDocenteByTipoDedicacion (TipoDedicacion tipoDedicacion);
    
    List<Docente> findDocenteByCarrera (Carreras carrera);    
    
}
