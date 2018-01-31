package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.FacultadDaoInterface;
import com.vinculacion.app.factory.FactorFactory;
import com.vinculacion.app.model.Facultad;
import java.util.List;

/**
 *
 * @author jorge
 */
public class FacultadDAO implements FacultadDaoInterface{
    
    private FactorFactory sesion;

    public FacultadDAO() {
        sesion = new FactorFactory();
    }
     
    @Override
    public void saveFacultad(Facultad facultad) {
        sesion.getSession().persist(facultad);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public List<Facultad> AllFacultad() {
        return sesion.getSession().createQuery("FROM Facultad").list();
    }

    @Override
    public void deleteFacultadById(int id) {
        sesion.getSession().delete(id);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public void updateFacultad(Facultad facultad) {
        sesion.getSession().update(facultad);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public Facultad findFacultadById(int id) {
        return sesion.getSession().load(Facultad.class, id);
    }
}
