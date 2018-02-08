package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.CargoDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Cargo;
import java.util.List;
import javax.persistence.EntityManager;

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
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(cargo);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Cargo> AllCargos() {
        EntityManager manager = emf.createEntityManager();        
        List<Cargo> lcargo = (List<Cargo>)manager.createQuery("FROM Cargo WHERE ESTADO = 'ACTIVO' order by ID_CARGO desc")
                .getResultList();
        manager.close();
        return lcargo;
    }

    @Override
    public void updateCargo(Cargo cargo) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(cargo);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteCargo(int id) {
        Cargo cargo = findCargoById(id);
        if (cargo != null) {
            cargo.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(cargo);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Cargo findCargoByDescription(String description) {
        EntityManager manager = emf.createEntityManager();        
        Cargo cargo = (Cargo) manager.createQuery("FROM Cargo WHERE ESTADO = 'ACTIVO' and DESCRIPCION = :des")
                .setParameter("des", description)
                .getSingleResult();
        manager.close();
        return cargo;    
    }

    @Override
    public Cargo findCargoById(int id) {
        EntityManager manager = emf.createEntityManager();        
        Cargo cargo = manager.find(Cargo.class, id);
        manager.close();
        return cargo;
    }
}
