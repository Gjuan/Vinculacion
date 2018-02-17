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
        EntityManager manager = emf.createEntityManager();        
        List<Pasantias> lpasantia = (List<Pasantias>)manager.createQuery("FROM Pasantias WHERE ESTADO = 'ACTIVO' order by ID_PASANTIAS desc")
                .getResultList();
        manager.close();
        return lpasantia;
    }

    @Override
    public Pasantias findPasantiaById(int id) {
        EntityManager manager = emf.createEntityManager();        
        Pasantias pasantia = manager.find(Pasantias.class, id);
        manager.close();
        return pasantia;
    }

    @Override
    public List<Pasantias> findPasantiaByFechaInicio(String fecha_inicio) {
        EntityManager manager = emf.createEntityManager();        
        List<Pasantias> lpasantia = (List<Pasantias>)manager.createQuery("FROM Pasantias WHERE FECHA_INICIO = :fecha_init order by ID_PASANTIAS desc")
                .setParameter("fecha_init", fecha_inicio)
                .getResultList();
        manager.close();
        return lpasantia;
    }

    @Override
    public List<Pasantias> findPasantiaByFechaFin(String fecha_fin) {
        EntityManager manager = emf.createEntityManager();        
        List<Pasantias> lpasantia = (List<Pasantias>)manager.createQuery("FROM Pasantias WHERE FECHA_CULMINACION = :fecha_fin order by ID_PASANTIAS desc")
                .setParameter("fecha_fin", fecha_fin)
                .getResultList();
        manager.close();
        return lpasantia;
    }

    @Override
    public void updatePasantia(Pasantias pasantia) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(pasantia);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deletePasantia(int id) {
        Pasantias pasantia = findPasantiaById(id);
        if (pasantia != null) {
            pasantia.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(pasantia);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Pasantias findPasantiaByTitulo(String titulo) {
        EntityManager manager = emf.createEntityManager();        
        Pasantias lpasantia = (Pasantias)manager.createQuery("FROM Pasantias WHERE TITULO_PROYECTO = :titulo")
                .setParameter("titulo", titulo)
                .getSingleResult();
        manager.close();
        return lpasantia;
    }
    
}
