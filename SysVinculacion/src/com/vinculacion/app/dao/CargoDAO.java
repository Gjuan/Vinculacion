package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.CargoDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Cargo;
import java.util.List;

/**
 *
 * @author jorge
 */
public class CargoDAO extends FactorFactory implements CargoDaoInterface{

    public CargoDAO() {
        super();
    }
        
    @Override
    public void saveCargo(Cargo cargo) {
   
    }

    @Override
    public List<Cargo> AllCargos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCargo(Cargo cargo) {
    
    }

    @Override
    public void deleteCargo(int id) {
    
    }

    @Override
    public Cargo findCargoByDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cargo findCargoById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
