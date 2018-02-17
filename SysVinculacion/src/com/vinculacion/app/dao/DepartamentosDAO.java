package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DepartamentosDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Departamentos;
import com.vinculacion.app.model.Empresa;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class DepartamentosDAO extends FactorFactory implements DepartamentosDaoInterface{

    public DepartamentosDAO() {
        super();
    }    
    
    @Override
    public void saveDepartamentos(Departamentos departamento) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(departamento);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Departamentos> AllDepartamentos() {
        EntityManager manager = emf.createEntityManager();        
        List<Departamentos> lcarreras = (List<Departamentos>)manager.createQuery("FROM Departamentos WHERE ESTADO = 'ACTIVO' order by ID_DEPARTAMENTO desc")
                .getResultList();
        manager.close();
        return lcarreras;
    }

    @Override
    public void updateDepartamentos(Departamentos departamento) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(departamento);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteDepartamentos(int id) {
        Departamentos departamento = findDepartamentoById(id);
        if (departamento != null) {
            departamento.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(departamento);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Departamentos findDepartamentoByName(String name, String nameEmpresa) {
        EmpresaDAO edao = new EmpresaDAO();
        Empresa empresa = edao.findEmpresaByName(nameEmpresa);
        
        EntityManager manager = emf.createEntityManager();
        Departamentos departamento = (Departamentos)  manager.createQuery("FROM Departamentos where NOMBRE_DEPARTAMENTO = :name and empresa = :empresa and ESTADO = 'ACTIVO'")
                .setParameter("name", name)
                .setParameter("empresa", empresa)
                .getSingleResult();
        manager.close();
        return departamento;
    }

    @Override
    public Departamentos findDepartamentoById(int id) {
        EntityManager manager = emf.createEntityManager();
        Departamentos departamento = manager.find(Departamentos.class, id);
        manager.close();
        return departamento;
    }

    @Override
    public List<Departamentos> AllDepartamentosByEmpresa(String name) {
        EmpresaDAO edao = new EmpresaDAO();
        Empresa empresa = edao.findEmpresaByName(name);
        EntityManager manager = emf.createEntityManager();
        List<Departamentos> ldepartamento = (List<Departamentos>) manager.createQuery("FROM Departamentos WHERE empresa = :emp AND ESTADO = 'ACTIVO'")
                .setParameter("emp", empresa)
                .getResultList();
        return ldepartamento;
    }
    
}
