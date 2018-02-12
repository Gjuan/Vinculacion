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
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(estudiante);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Estudiantes> AllEstudiantes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEstudiante(Estudiantes estudiante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEstudiante(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estudiantes findEstudianteByCedula(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
