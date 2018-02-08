package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.AsignaturaDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Asignaturas;
import java.util.List;
import javax.persistence.EntityManager;
/**
 *
 * @author jorge
 */
public class AsignaturasDAO extends FactorFactory implements AsignaturaDaoInterface{

    public AsignaturasDAO() {
        super();
    }
        
    @Override
    public void saveAsignatura(Asignaturas asignatura) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(asignatura);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Asignaturas> AllAsignaturas() {
        EntityManager manager = emf.createEntityManager();        
        List<Asignaturas> lasig = (List<Asignaturas>)manager.createQuery("FROM Asignaturas WHERE ESTADO = 'ACTIVO' order by ID_ASIGNATURA desc")
                .getResultList();
        manager.close();
        return lasig;
    }

    @Override
    public void updateAsignatura(Asignaturas asignatura) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(asignatura);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteAsignatura(int id) {
        Asignaturas asig = findAsignaturaById(id);
        if (asig != null) {
            asig.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(asig);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Asignaturas findAsignaturaByName(String name) {
        EntityManager manager = emf.createEntityManager();        
        Asignaturas asig = (Asignaturas) manager.createQuery("FROM Asignaturas WHERE ESTADO = 'ACTIVO' and NOMBRE_ASIGNATURA = :name")
                .setParameter("name", name)
                .getSingleResult();
        manager.close();
        return asig;
    }

    @Override
    public Asignaturas findAsignaturaById(int id) {
        EntityManager manager = emf.createEntityManager();        
        Asignaturas asig = manager.find(Asignaturas.class, id);
        manager.close();
        return asig;
    }
 
}
