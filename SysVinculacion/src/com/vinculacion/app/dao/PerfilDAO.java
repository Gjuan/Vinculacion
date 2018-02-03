package com.vinculacion.app.dao;

import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Perfil;
import java.util.List;
import com.vinculacion.app.Interface.PerfilDaoInterface;
import javax.persistence.EntityManager;

public class PerfilDAO extends FactorFactory implements PerfilDaoInterface{

    public PerfilDAO() {
        super();
    }   
    
    @Override
    public void savePerfil(Perfil perfil) {
       EntityManager manager = emf.createEntityManager();
       manager.getTransaction().begin();
       manager.persist(perfil);
       manager.getTransaction().commit();
       manager.close();
    }

    @Override
    public List<Perfil> AllPerfil() {
        EntityManager manager = emf.createEntityManager();       
        List<Perfil> lperfil = (List<Perfil>)manager.createQuery("FROM Perfil WHERE ESTADO = 'ACTIVO' order by id_perfil asc").getResultList();
        manager.close();
        return lperfil;
    }

    @Override
    public void deletePerfilById(int id) {
        Perfil perfil = findPerfilById(id);
        if (perfil != null) {
            perfil.setESTADO("INACTIVO");            
            EntityManager manager = emf.createEntityManager();                   
            manager.getTransaction().begin();
            manager.merge(perfil);
            manager.getTransaction().commit();
            manager.close();
        }       
    }

    @Override
    public void updatePerfil(Perfil perfil) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(perfil);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Perfil findPerfilById(int id) {
       EntityManager manager = emf.createEntityManager();        
       Perfil perfil = manager.find(Perfil.class, id);
       manager.close();
       return perfil;
    }      
}
