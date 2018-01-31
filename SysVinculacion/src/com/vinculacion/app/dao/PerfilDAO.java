package com.vinculacion.app.dao;

import com.vinculacion.app.factory.FactorFactory;
import com.vinculacion.app.model.Perfil;
import java.util.List;
import com.vinculacion.app.Interface.PerfilDaoInterface;

public class PerfilDAO implements PerfilDaoInterface{
    
    private FactorFactory sesion;

    public PerfilDAO() {
        sesion = new FactorFactory();
    }    
    
    @Override
    public void savePerfil(Perfil perfil) {
        sesion.getSession().persist(perfil);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public List<Perfil> AllPerfil() {
        return sesion.getSession().createQuery("FROM Perfil").list();
    }

    @Override
    public void deletePerfilById(int id) {
        sesion.getSession().delete(id);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public void updatePerfil(Perfil perfil) {
        sesion.getSession().update(perfil);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public Perfil findPerfilById(int id) {
        return sesion.getSession().load(Perfil.class, id);
    }    
    
}
