package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.CargoDepartamentalDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.CargoDepartamental;
import java.util.List;

/**
 *
 * @author jorge
 */
public class CargoDepartamentalDAO extends FactorFactory implements CargoDepartamentalDaoInterface{

    public CargoDepartamentalDAO() {
        super();
    }
        
    @Override
    public void saveCargoDepartamental(CargoDepartamental cargo) {
    
    }

    @Override
    public List<CargoDepartamental> AllCargosDepartamental() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCargoDepartamental(CargoDepartamental cargo) {
    
    }

    @Override
    public void deleteCargoDepartamental(int id) {
    
    }

    @Override
    public CargoDepartamental findCargoDepartamentalByDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CargoDepartamental findCargoDepartamentalById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
