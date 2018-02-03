package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EscuelaDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Escuela;
import com.vinculacion.app.model.Facultad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author jorge
 */
public class EscuelaDAO extends FactorFactory implements EscuelaDaoInterface{

    public EscuelaDAO() {
        super();
    }

    @Override
    public void saveEscuela(Escuela escuela) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(escuela);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Escuela> AllEscuelas() {
        EntityManager manager = emf.createEntityManager();        
        List<Escuela> lescuela = (List<Escuela>) manager.createQuery("FROM Escuela Where ESTADO = 'ACTIVO' order by ID_ESCUELA asc").getResultList();
        manager.close();
        return lescuela;
    }

    @Override
    public void deleteEscuelaById(int id) {
        Escuela escuela = findEscuelaById(id);
        escuela.setESTADO("INACTIVO");
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(escuela);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void updateEscuela(Escuela escuela) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(escuela);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Escuela findEscuelaById(int id) {
        EntityManager manager = emf.createEntityManager();
        Escuela escuela = manager.find(Escuela.class, id);
        manager.close();
        return escuela;
    }

    @Override
    public Escuela findEscuelaByName(String name) {
        EntityManager manager = emf.createEntityManager();
        Escuela escuela = (Escuela)  manager.createQuery("From Escuela where ESTADO = 'ACTIVO' and NOMBRE_ESCUELA = :name")
                    .setParameter("name", name).getSingleResult();
        manager.close();
        return escuela;        
    }

    @Override
    public List<Escuela> findEscuelaByFacultad(String description) {
        List<Escuela> escuela = new ArrayList<Escuela>();    
        Facultad facultad = null;
        try{
            EntityManager manager = emf.createEntityManager();                     
            facultad = (Facultad) manager.createQuery("FROM Facultad where DESCRIPCION = :des AND ESTADO = 'ACTIVO'")
                    .setParameter("des", description).getSingleResult(); 
            escuela = (List<Escuela>) manager.createQuery("FROM Escuela where ESTADO = 'ACTIVO' and facultad = :fac")
                .setParameter("fac", facultad)
                .getResultList();             
        }catch (NoResultException nre){
        }
        return escuela;
    }
    
}
