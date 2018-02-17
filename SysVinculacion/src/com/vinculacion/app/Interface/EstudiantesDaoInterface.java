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
    
    Estudiantes findEstudianteById(String id);
    
    void updateEstudiante (Estudiantes estudiante);
    
    void deleteEstudiante (String id);
    
    Estudiantes findEstudianteByCedula (String cedula);   
    
    Estudiantes findEstudianteByNameAndLastName (String name, String lastName);   
        
}
