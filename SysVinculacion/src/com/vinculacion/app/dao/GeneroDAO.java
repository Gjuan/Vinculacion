package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.GeneroDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Genero;
import java.util.List;
import javax.persistence.EntityManager;
/**
 *
 * @author jorge
 */
public class GeneroDAO extends FactorFactory implements GeneroDaoInterface{

    public GeneroDAO() {
        super();
    }

    @Override
    public void saveGenero(Genero genero) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(genero);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Genero> AllGeneros() {
        EntityManager manager = emf.createEntityManager();        
        List<Genero> lgenero = (List<Genero>)manager.createQuery("FROM Genero WHERE ESTADO = 'ACTIVO' order by ID_GENERO desc")
                .getResultList();
        manager.close();
        return lgenero;
    }

    @Override
    public void updateGenero(Genero genero) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(genero);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteGenero(int id) {
        Genero genero = findGeneroById(id);
        if (genero != null) {
            genero.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(genero);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Genero findGeneroById(int id) {
        EntityManager manager = emf.createEntityManager();        
        Genero genero = manager.find(Genero.class, id);
        manager.close();
        return genero;
    }

    @Override
    public Genero findGeneroByDescription(String description) {
        EntityManager manager = emf.createEntityManager();        
        Genero genero = (Genero) manager.createQuery("FROM Genero WHERE ESTADO = 'ACTIVO' and DESCRIPCION = :des")
                .setParameter("des", description)
                .getSingleResult();
        manager.close();
        return genero;  
    }
    
}
