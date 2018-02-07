package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Empresa;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface EmpresaDaoInterface {
    
    void saveEmpresa(Empresa empresa);
    
    List<Empresa> AllEmpresas();
    
    void updateEmpresa (Empresa empresa);
    
    void deleteEmpresa (int id);
    
    Empresa findEmpresaById (int id);
    
    Empresa findEmpresaByName (String name);
    
}
