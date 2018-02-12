package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Empleados;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface EmpleadosDaoInterface {
    
    void saveEmpleados(Empleados empleado);
    
    List<Empleados> AllEmpleados();
        
    void deleteEmpleado (int id);
    
    void updateEmpleado(Empleados empleado);
    
    Empleados findEmpleadoByCedula (String cedula);
    
    Empleados findEmpleadoById(int id);
    
    List<Empleados> findEmpleadosByDepartamentos(String nombreDepartamento);
    
    List<Empleados> findEmpleadosByCargos(String descripcionCargo);
    
    Empleados findEmpleadosByLastNameAndName (String name, String lastName);
}
