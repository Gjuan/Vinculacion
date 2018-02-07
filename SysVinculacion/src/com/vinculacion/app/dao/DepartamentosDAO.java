package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DepartamentosDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Departamentos;
import java.util.List;

/**
 *
 * @author jorge
 */
public class DepartamentosDAO extends FactorFactory implements DepartamentosDaoInterface{

    public DepartamentosDAO() {
        super();
    }    
    
    @Override
    public void saveDepartamentos(Departamentos departamento) {
    
    }

    @Override
    public List<Departamentos> AllDepartamentos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDepartamentos(Departamentos departamento) {
    
    }

    @Override
    public void deleteDepartamentos(int id) {
    
    }

    @Override
    public Departamentos findDepartamentoByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Departamentos findDepartamentoById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Departamentos> AllDepartamentosByEmpresa(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
