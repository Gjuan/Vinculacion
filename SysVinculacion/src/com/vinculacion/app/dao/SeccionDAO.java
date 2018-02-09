package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.SeccionDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Seccion;
import java.util.List;
import javax.persistence.EntityManager;

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
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(seccion);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Seccion> AllSecciones() {
        EntityManager manager = emf.createEntityManager();        
        List<Seccion> lseccion = (List<Seccion>)manager.createQuery("FROM Seccion WHERE order by ID_SECCION desc")
                .getResultList();
        manager.close();
        return lseccion;
    }

    @Override
    public void deleteSeccion(int id) { 
    }

    @Override
    public void updateSeccion(Seccion seccion) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(seccion);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Seccion findSeccionById(int id) {
        EntityManager manager = emf.createEntityManager();
        Seccion seccion = manager.find(Seccion.class, id);
        manager.close();
        return seccion;
    }

    @Override
    public Seccion findSeccionByDescription(String description) {
        EntityManager manager = emf.createEntityManager();
        Seccion seccion = (Seccion) manager.createQuery("From Seccion where DESCRIPCION = :des")
                .setParameter("des", description).getSingleResult();
        manager.close();
        return seccion;
    }
    
}
