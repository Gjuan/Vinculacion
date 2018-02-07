package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.HorarioPasantiasDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.HorarioPasantias;
import java.util.List;

/**
 *
 * @author jorge
 */
public class HorariosPasantiasDAO extends FactorFactory implements HorarioPasantiasDaoInterface {

    public HorariosPasantiasDAO() {
        super();
    }
        
    @Override
    public void saveHorarioPasantias(HorarioPasantias horario) {
    
    }

    @Override
    public List<HorarioPasantias> AllHorariosPasantias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteHorariosPasantias(int id) {
    
    }

    @Override
    public void updateHorarioPasantia(HorarioPasantias horario) {
    
    }

    @Override
    public HorarioPasantias findHorarioPasantiaById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HorarioPasantias findHorarioPasantiaByDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
