package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EstudiantesDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Estudiantes;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class EstudiantesDAO extends FactorFactory implements EstudiantesDaoInterface{

    public EstudiantesDAO() {
        super();
    }

    @Override
    public void saveEstudiante(Estudiantes estudiante) {
        try {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.persist(estudiante);
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
        }           
    }

    @Override
    public List<Estudiantes> AllEstudiantes() {
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>)manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' order by CODIGO desc")
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public void updateEstudiante(Estudiantes estudiante) {
        try {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(estudiante);
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
        }       
    }

    @Override
    public void deleteEstudiante(String id) {
        Estudiantes estudiante = findEstudianteById(id);
        if (estudiante != null) {
            estudiante.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(estudiante);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Estudiantes findEstudianteByCedula(String cedula) {
        EntityManager manager = emf.createEntityManager();        
        Estudiantes est = (Estudiantes) manager.createQuery("FROM Estudiantes WHERE CEDULA = :cedula")
                .setParameter("cedula", cedula)
                .getSingleResult();
        manager.close();
        return est;
    }

    @Override
    public Estudiantes findEstudianteById(String id) {
        EntityManager manager = emf.createEntityManager();        
        Estudiantes estudiante = manager.find(Estudiantes.class, id);
        manager.close();
        return estudiante;
    }
   
    @Override
    public Estudiantes findEstudianteByNameAndLastName(String name, String lastName) {
        EntityManager manager = emf.createEntityManager();        
        Estudiantes est = (Estudiantes) manager.createQuery("FROM Estudiantes WHERE NOMBRES = :name and APELLIDOS = :lastName")
                .setParameter("name", name)
                .setParameter("lastName", lastName)
                .getSingleResult();
        manager.close();
        return est;
    }
}
