package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EmpresaDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Empresa;
import java.util.List;
import javax.persistence.EntityManager;
/**
 *
 * @author jorge
 */
public class EmpresaDAO extends FactorFactory implements EmpresaDaoInterface{

    public EmpresaDAO() {
        super();
    }
        
    @Override
    public void saveEmpresa(Empresa empresa) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(empresa);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Empresa> AllEmpresas() {
        EntityManager manager = emf.createEntityManager();        
        List<Empresa> lempresa = (List<Empresa>)manager.createQuery("FROM Empresa order by ID_EMPRESA desc")
                .getResultList();
        manager.close();
        return lempresa;
    }

    @Override
    public void updateEmpresa(Empresa empresa) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(empresa);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteEmpresa(int id) {
    
    }

    @Override
    public Empresa findEmpresaById(int id) {
        EntityManager manager = emf.createEntityManager();        
        Empresa empresa = manager.find(Empresa.class, id);
        manager.close();
        return empresa;
    }

    @Override
    public Empresa findEmpresaByName(String name) {
        EntityManager manager = emf.createEntityManager();        
        Empresa empresa = (Empresa) manager.createQuery("FROM Empresa where NOMBRE_EMPRESA = :name")
                .setParameter("name", name)
                .getSingleResult();
        manager.close();
        return empresa;    
    }
    
}
