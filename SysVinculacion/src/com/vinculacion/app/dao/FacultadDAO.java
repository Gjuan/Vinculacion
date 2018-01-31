package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.FacultadDaoInterface;
import com.vinculacion.app.factory.FactorFactory;
import com.vinculacion.app.model.Escuela;
import com.vinculacion.app.model.Facultad;
import java.util.Iterator;
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
        Facultad facultad = findFacultadById(id);
        if (facultad != null) {
            Iterator<Escuela> i = facultad.getEscuelas().iterator();
            while(i.hasNext()){
                Escuela escuela = i.next();
                i.remove();
                sesion.getSession().delete(escuela);
            }
            facultad.getEscuelas().clear();
            sesion.getSession().delete(facultad);
            sesion.getSession().getTransaction().commit();
        }
    }

    @Override
    public void updateFacultad(Facultad facultad) {
        sesion.getSession().update(facultad);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public Facultad findFacultadById(int id) {
        return (Facultad)sesion.getSession().get(Facultad.class, id);
    }
}
