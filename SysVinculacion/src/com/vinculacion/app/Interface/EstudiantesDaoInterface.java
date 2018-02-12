package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Estudiantes;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface EstudiantesDaoInterface {
    
    void saveEstudiante (Estudiantes estudiante);
    
    List<Estudiantes> AllEstudiantes ();
    
    void updateEstudiante (Estudiantes estudiante);
    
    void deleteEstudiante (int id);
    
    Estudiantes findEstudianteByCedula (String cedula);   
    
}
