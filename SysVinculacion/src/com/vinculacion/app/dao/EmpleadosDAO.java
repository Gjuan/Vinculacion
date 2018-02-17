package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EmpleadosDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.CargoDepartamental;
import com.vinculacion.app.model.Departamentos;
import com.vinculacion.app.model.Empleados;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class EmpleadosDAO extends FactorFactory implements EmpleadosDaoInterface{

    public EmpleadosDAO() {
        super();
    }
        
    @Override
    public void saveEmpleados(Empleados empleado) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(empleado);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Empleados> AllEmpleados() {
        EntityManager manager = emf.createEntityManager();        
        List<Empleados> lemp = (List<Empleados>) manager.createQuery("FROM Empleados WHERE ESTADO = 'ACTIVO' order by ID_EMPLEADO desc")
                .getResultList();
        manager.close();
        return lemp;
    }

    @Override
    public void deleteEmpleado(int id) {
        Empleados emp = findEmpleadoById(id);
        if (emp != null) {
            emp.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(emp);
            manager.getTransaction().commit();
            manager.close();
        }        
    }

    @Override
    public void updateEmpleado(Empleados empleado) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(empleado);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Empleados findEmpleadoByCedula(String cedula) {
        EntityManager manager = emf.createEntityManager();
        Empleados emp = (Empleados)  manager.createQuery("From Empleados where CEDULA = :ced")
                .setParameter("ced", cedula)
                .getSingleResult();
        manager.close();
        return emp;
    }

    @Override
    public Empleados findEmpleadoById(int id) {
        EntityManager manager = emf.createEntityManager();
        Empleados empleado = manager.find(Empleados.class, id);
        manager.close();
        return empleado;
    }

    @Override
    public List<Empleados> findEmpleadosByCargos(String descripcionCargo) {
        CargoDepartamentalDAO cdao = new CargoDepartamentalDAO();
        CargoDepartamental cargos = cdao.findCargoDepartamentalByDescription(descripcionCargo);
        EntityManager manager = emf.createEntityManager();
        List<Empleados> lemp = manager.createQuery("FROM Empleados WHERE ESTADO = 'ACTIVO' AND cargoDepartamental = :cargos order by ID_EMPLEADO desc")
                .setParameter("cargos", cargos)
                .getResultList();
        manager.close();
        return lemp;
    }

    @Override
    public Empleados findEmpleadosByLastNameAndName(String name, String lastName) {
        EntityManager manager = emf.createEntityManager();        
        Empleados empl = (Empleados) manager.createQuery("FROM Empleados WHERE ESTADO = 'ACTIVO' and APELLIDOS = :lastname and NOMBRES = :name")
                .setParameter("name", name)
                .setParameter("lastname", lastName)
                .getSingleResult();
        manager.close();
        return empl;   
    }
    
}
