package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.PasantiasDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Pasantias;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class PasantiasDAO extends FactorFactory implements PasantiasDaoInterface{

    public PasantiasDAO() {
        super();
    }    

    @Override
    public void savePasantias(Pasantias pasantia) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(pasantia);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Pasantias> AllPasantias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pasantias findPasantiaById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pasantias> findPasantiaByFechaInicio(String fecha_inicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pasantias> findPasantiaByFechaFin(String fecha_fin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePasantia(Pasantias pasantia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePasantia(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
