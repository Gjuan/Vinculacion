package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.SeccionDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Seccion;
import java.util.List;

/**
 *
 * @author jorge
 */
public class SeccionDAO extends FactorFactory implements SeccionDaoInterface{

    public SeccionDAO() {
        super();
    }
        
    @Override
    public void saveSeccion(Seccion seccion) {
    
    }

    @Override
    public List<Seccion> AllSecciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSeccion(int id) {
    
    }

    @Override
    public void updateSeccion(Seccion seccion) {
    
    }

    @Override
    public Seccion findSeccionById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seccion findSeccionByDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
