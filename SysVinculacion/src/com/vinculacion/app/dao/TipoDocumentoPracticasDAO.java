package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.TipoDocumentosPracticasDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.TipoDocumentoPracticas;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class TipoDocumentoPracticasDAO extends FactorFactory implements TipoDocumentosPracticasDaoInterface{

    public TipoDocumentoPracticasDAO() {
        super();
    }
       
    @Override
    public void saveTipoDocumentoPracticas(TipoDocumentoPracticas tdp) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(tdp);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<TipoDocumentoPracticas> AllTiposDocumentosPracticas() {
        EntityManager manager = emf.createEntityManager();        
        List<TipoDocumentoPracticas> ltdp = (List<TipoDocumentoPracticas>)manager.createQuery("FROM TipoDocumentoPracticas WHERE order by id_tipo_documento_practicas desc")
                .getResultList();
        manager.close();
        return ltdp;
    }

    @Override
    public void updateTipoDocumentoPracticas(TipoDocumentoPracticas tdp) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(tdp);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public TipoDocumentoPracticas findTipoDocumentoPracticasByDescription(String description) {
        EntityManager manager = emf.createEntityManager();
        TipoDocumentoPracticas tdp = (TipoDocumentoPracticas)  manager.createQuery("From TipoDocumentoPracticas where descripcion = :des")
                .setParameter("des", description)
                .getSingleResult();
        manager.close();
        return tdp;
    }

    @Override
    public TipoDocumentoPracticas findTipoDocumentoPracticasById(int id) {
        EntityManager manager = emf.createEntityManager();
        TipoDocumentoPracticas tdp = manager.find(TipoDocumentoPracticas.class, id);
        manager.close();
        return tdp;
    }
    
}
