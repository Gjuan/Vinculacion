package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.TipoDedicacionDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.TipoDedicacion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class TipoDedicacionDAO extends FactorFactory implements TipoDedicacionDaoInterface{

    public TipoDedicacionDAO() {
        super();
    }    
    
    @Override
    public void saveTipoDedicacion(TipoDedicacion tipoDedicacion) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(tipoDedicacion);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<TipoDedicacion> AllTiposDedicacion() {
        EntityManager manager = emf.createEntityManager();        
        List<TipoDedicacion> ltipodedicacion = (List<TipoDedicacion>)manager.createQuery("FROM TipoDedicacion WHERE ESTADO = 'ACTIVO' order by ID_TIPO_DEDICACION desc")
                .getResultList();
        manager.close();
        return ltipodedicacion;
    }

    @Override
    public void updateTipoDedicacion(TipoDedicacion tipoDedicacion) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(tipoDedicacion);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteTipoDedicacion(int id) {
        TipoDedicacion tipodedicacion = findTipoDedicacionById(id);
        if (tipodedicacion != null) {
            tipodedicacion.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(tipodedicacion);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public TipoDedicacion findTipoDedicacionByDescription(String description) {
        EntityManager manager = emf.createEntityManager();            
        TipoDedicacion tipodedicacion = (TipoDedicacion) manager.createQuery("FROM TipoDedicacion WHERE ESTADO = 'ACTIVO' and DESCRIPCION = :des")
                .setParameter("des", description)
                .getSingleResult();
        manager.close();
        return tipodedicacion;
    }

    @Override
    public TipoDedicacion findTipoDedicacionById(int id) {
        EntityManager manager = emf.createEntityManager();        
        TipoDedicacion tipodedicacion = manager.find(TipoDedicacion.class, id);
        manager.close();
        return tipodedicacion;
    }
    
}
