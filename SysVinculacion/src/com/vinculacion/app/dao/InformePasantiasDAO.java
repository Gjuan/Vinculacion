package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.InformePasantiasDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.InformePasantias;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class InformePasantiasDAO extends FactorFactory implements InformePasantiasDaoInterface{

    public InformePasantiasDAO() {
        super();
    }

    @Override
    public void saveInformePasantias(InformePasantias informe) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(informe);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<InformePasantias> AllInformes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInformPasantia(InformePasantias informe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteInformePasantia(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InformePasantias> findInformeByCedulaEstudiante(String cedulaEstudiante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InformePasantias> findInformesByFechaEntrega(String fecha_entrega) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
