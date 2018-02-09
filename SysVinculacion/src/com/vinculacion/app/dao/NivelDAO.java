package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.NivelDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Nivel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class NivelDAO extends FactorFactory implements NivelDaoInterface{

    public NivelDAO() {
        super();
    }    
    
    @Override
    public void saveNivel(Nivel nivel) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(nivel);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Nivel> AllNiveles() {
        EntityManager manager = emf.createEntityManager();        
        List<Nivel> lnivel = (List<Nivel>)manager.createQuery("FROM Nivel WHERE ESTADO = 'ACTIVO' order by ID_NIVEL desc")
                .getResultList();
        manager.close();
        return lnivel;
    }

    @Override
    public void updateNivel(Nivel nivel) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(nivel);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteNivel(int id) {
        Nivel nivel = findNivelById(id);
        if (nivel != null) {
            nivel.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(nivel);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Nivel findNivelById(int id) {
        EntityManager manager = emf.createEntityManager();        
        Nivel nivel = manager.find(Nivel.class, id);
        manager.close();
        return nivel;
    }

    @Override
    public Nivel findNivelBySemestre(String semestre) {
        EntityManager manager = emf.createEntityManager();        
        Nivel nivel = (Nivel) manager.createQuery("FROM Nivel WHERE ESTADO = 'ACTIVO' and SEMESTRE = :semest")
                .setParameter("semest", semestre)
                .getSingleResult();
        manager.close();
        return nivel;
    }
    
}
