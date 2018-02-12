package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DetalleCargoDocenteDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.DetalleCargoDocente;
import com.vinculacion.app.model.Docente;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class DetalleCargoDocenteDAO extends FactorFactory implements DetalleCargoDocenteDaoInterface{

    public DetalleCargoDocenteDAO() {
        super();
    }
    
    @Override
    public void saveDetalleCargoDocente(DetalleCargoDocente dcd) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(dcd);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<DetalleCargoDocente> AllDetalleCargoDocente() {
        EntityManager manager = emf.createEntityManager();        
        List<DetalleCargoDocente> ldcd = (List<DetalleCargoDocente>)manager.createQuery("FROM DetalleCargoDocente WHERE ESTADO = 'ACTIVO' order by ID_DETALLE_CARGO_DOCENTE desc")
                .getResultList();
        manager.close();
        return ldcd;
    }

    @Override
    public void updateDetalleCargoDocente(DetalleCargoDocente dcd) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(dcd);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteDetalleCargoDocente(int id) {
        DetalleCargoDocente dcd = findDetalleCargoDocenteById(id);
        if (dcd != null) {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.createQuery("DELETE FROM DetalleCargoDocente WHERE ID_DETALLE_CARGO_DOCENTE = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            manager.getTransaction().commit();
            manager.close();
        }      
    }

    @Override
    public DetalleCargoDocente findDetalleCargoDocenteById(int id) {
        EntityManager manager = emf.createEntityManager();
        DetalleCargoDocente dcd = manager.find(DetalleCargoDocente.class, id);
        manager.close();
        return dcd;
    }

    @Override
    public List<DetalleCargoDocente> findDetalleCargoDocenteByCedula(String cedulaDocente) {
        DocenteDAO docdao = new DocenteDAO();
        Docente doc = docdao.findDocenteByCedula(cedulaDocente);
        EntityManager manager = emf.createEntityManager();       
        List<DetalleCargoDocente> ldcd = manager.createQuery("FROM DetalleCargoDocente WHERE ESTADO = 'ACTIVO' and docentes = :docente")
                .setParameter("docente", doc)
                .getResultList();
        manager.close();
        return ldcd;
    }
    
}
