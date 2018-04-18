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
    
    List<Estudiantes> EstudiantesByTutorDocente (String nameDocente, String lastNameDocente);
    
    List<Estudiantes> EstudiantesByTutorDocente (String nameDocente, String lastNameDocente, String fechaInicioPeriodo);
    
    List<Estudiantes> EstudiantesByTutorEmpresarial (String nameTutorEmp, String lastNameTutorEmp);
    
    List<Estudiantes> EstudiantesByTutorEmpresarial (String nameTutorEmp, String lastNameTutorEmp, String fechaInicioPeriodo);
     
    List<Estudiantes> EstudiantesByCarrera (String descripcionCarrera);
    
    List<Estudiantes> EstudiantesByCarrera (String descripcionCarrera, String fechaInicioPeriodo);
    
    List<Estudiantes> EstudiantesByTipoDocPracticas (String descripcionTCP);
    
    List<Estudiantes> EstudiantesByTipoDocPracticas (String descripcionTCP, String fechaInicioPeriodo);
    
    List<Estudiantes> EstudiantesByPeriodoAcadémico (String fechaInicioPeriodo);
}
