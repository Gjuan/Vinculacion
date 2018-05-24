package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Departamentos;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface DepartamentosDaoInterface {
    
    void saveDepartamentos (Departamentos departamento);
    
    List<Departamentos> AllDepartamentos();
    
    void updateDepartamentos(Departamentos departamento);
    
    void deleteDepartamentos(int id);
    
    Departamentos findDepartamentoByName(String name, String nameEmpresa);
            
    Departamentos findDepartamentoById(int id);
    
    List<Departamentos> AllDepartamentosByEmpresa(String name);
    
}
