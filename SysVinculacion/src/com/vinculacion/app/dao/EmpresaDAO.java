package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EmpresaDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Empresa;
import java.util.List;
/**
 *
 * @author jorge
 */
public class EmpresaDAO extends FactorFactory implements EmpresaDaoInterface{

    public EmpresaDAO() {
        super();
    }
        
    @Override
    public void saveEmpresa(Empresa empresa) {
    
    }

    @Override
    public List<Empresa> AllEmpresas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEmpresa(Empresa empresa) {
    
    }

    @Override
    public void deleteEmpresa(int id) {
    
    }

    @Override
    public Empresa findEmpresaById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empresa findEmpresaByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
