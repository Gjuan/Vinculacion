package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.CargoDepartamentalDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.CargoDepartamental;
import java.util.List;
import javax.persistence.EntityManager;

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
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(cargo);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<CargoDepartamental> AllCargosDepartamental() {
        EntityManager manager = emf.createEntityManager();        
        List<CargoDepartamental> lcargos = (List<CargoDepartamental>)manager.createQuery("FROM CargoDepartamental WHERE ESTADO = 'ACTIVO' order by ID_CARGO_EMPRESARIAL desc")
                .getResultList();
        manager.close();
        return lcargos;
    }

    @Override
    public void updateCargoDepartamental(CargoDepartamental cargo) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(cargo);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteCargoDepartamental(int id) {
        CargoDepartamental cargos = findCargoDepartamentalById(id);
        if (cargos != null) {
            cargos.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(cargos);
            manager.getTransaction().commit();
            manager.close();
        }       
    }

    @Override
    public CargoDepartamental findCargoDepartamentalByDescription(String description) {
        EntityManager manager = emf.createEntityManager();
        CargoDepartamental cargos = (CargoDepartamental)  manager.createQuery("From CargoDepartamental where DESCRIPCION = :des and ESTADO = 'ACTIVO'")
                .setParameter("des", description)
                .getSingleResult();
        manager.close();
        return cargos;
    }

    @Override
    public CargoDepartamental findCargoDepartamentalById(int id) {
        EntityManager manager = emf.createEntityManager();
        CargoDepartamental cargos = manager.find(CargoDepartamental.class, id);
        manager.close();
        return cargos;
    }   
}
