package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.FacultadDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Facultad;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class FacultadDAO extends FactorFactory implements FacultadDaoInterface{
    
    public FacultadDAO() {
        super();
    }
     
    @Override
    public void saveFacultad(Facultad facultad) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(facultad);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Facultad> AllFacultad() {
        EntityManager manager = emf.createEntityManager();        
        List<Facultad> lfacultad = manager.createQuery("FROM Facultad WHERE ESTADO = 'ACTIVO' order by ID_FACULTAD desc").getResultList();
        manager.close();
        return lfacultad;
    }

    @Override
    public void deleteFacultadById(int id) {
        Facultad facultad = findFacultadById(id);
        if (facultad != null) {
            facultad.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(facultad);
            manager.getTransaction().commit();
            manager.close();
        }       
    }

    @Override
    public void updateFacultad(Facultad facultad) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(facultad);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Facultad findFacultadById(int id) {
        EntityManager manager = emf.createEntityManager();        
        Facultad facultad = manager.find(Facultad.class, id);
        manager.close();
        return facultad;
    }

    @Override
    public Facultad findFacultadByDescription(String description) {
         EntityManager manager = emf.createEntityManager();    
         Facultad facultad = (Facultad) manager.createQuery("FROM Facultad WHERE ESTADO = 'ACTIVO' and DESCRIPCION = :des")
                 .setParameter("des", description)
                 .getSingleResult();
         manager.close();
         return facultad;
    }
}
