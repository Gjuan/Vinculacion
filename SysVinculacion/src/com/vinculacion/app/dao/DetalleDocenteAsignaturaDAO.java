package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DetalleDocenteAsignaturaDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.DetalleDocenteAsignatura;
import com.vinculacion.app.model.Docente;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class DetalleDocenteAsignaturaDAO extends FactorFactory implements DetalleDocenteAsignaturaDaoInterface{

    public DetalleDocenteAsignaturaDAO() {
        super();
    }
        
    @Override
    public void saveDetalleDocenteAsignatura(DetalleDocenteAsignatura dda) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(dda);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<DetalleDocenteAsignatura> AllDetalleDocenteAsignatura() {
        EntityManager manager = emf.createEntityManager();        
        List<DetalleDocenteAsignatura> ldda = (List<DetalleDocenteAsignatura>)manager.createQuery("FROM DetalleDocenteAsignatura WHERE ESTADO = 'ACTIVO' order by ID_DETALLE_DOCENTE_ASIG desc")
                .getResultList();
        manager.close();
        return ldda;
    }

    @Override
    public void updateDetalleDocenteAsignatura(DetalleDocenteAsignatura dda) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(dda);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteDetalleDocenteAsignatura(int id) {
        DetalleDocenteAsignatura dda = findDetalleDocenteAsignaturaById(id);
        if (dda != null) {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.createQuery("DELETE FROM DetalleDocenteAsignatura where ID_DETALLE_DOCENTE_ASIG = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            manager.getTransaction().commit();
            manager.close();
        }      
    }

    @Override
    public List<DetalleDocenteAsignatura> findDetalleDocenteAsignaturaByCedula(String cedulaDocente) {
        DocenteDAO docentedao = new DocenteDAO();
        Docente docente = docentedao.findDocenteByCedula(cedulaDocente);
        EntityManager manager = emf.createEntityManager();
        List<DetalleDocenteAsignatura> ldda = manager.createQuery("FROM DetalleDocenteAsignatura WHERE ESTADO = 'ACTIVO' AND docentes = :d")
                .setParameter("d", docente)
                .getResultList();
        manager.close();
        return ldda;
    }

    @Override
    public DetalleDocenteAsignatura findDetalleDocenteAsignaturaById(int id) {
        EntityManager manager = emf.createEntityManager();
        DetalleDocenteAsignatura dda = manager.find(DetalleDocenteAsignatura.class, id);
        manager.close();
        return dda;
    }
    
}
