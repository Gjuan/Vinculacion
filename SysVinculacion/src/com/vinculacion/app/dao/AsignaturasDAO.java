package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.AsignaturaDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Asignaturas;
import java.util.List;

/**
 *
 * @author jorge
 */
public class AsignaturasDAO extends FactorFactory implements AsignaturaDaoInterface{

    public AsignaturasDAO() {
        super();
    }
        
    @Override
    public void saveAsignatura(Asignaturas asignatura) {

    }

    @Override
    public List<Asignaturas> AllAsignaturas() {
        return null;
    }

    @Override
    public void updateAsignatura(Asignaturas asignatura) {

    }

    @Override
    public void deleteAsignatura(int id) {

    }

    @Override
    public Asignaturas findAsignaturaByName(String name) {
        return null;
    }

    @Override
    public Asignaturas findAsignaturaById(int id) {
        return null;
    }
    
}
