package com.vinculacion.app.dao;

import com.vinculacion.app.factory.FactorFactory;
import com.vinculacion.app.model.Usuarios;
import java.util.List;
import com.vinculacion.app.Interface.UsuariosDaoInterface;

public class UsuariosDAO implements UsuariosDaoInterface{
    
    private FactorFactory sesion;

    public UsuariosDAO() {
        sesion = new FactorFactory();
    }    
    
    @Override
    public void saveUsuarios(Usuarios usuario) {
        sesion.getSession().persist(usuario);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public List<Usuarios> AllUsuarios() {
        return sesion.getSession().createQuery("FROM Usuarios").list();
    }

    @Override
    public void deleteUsuarioById(int id) {
        sesion.getSession().delete(id);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public void updateUsuario(Usuarios usuario) {
        sesion.getSession().update(usuario);
        sesion.getSession().getTransaction().commit();
    }

    @Override
    public Usuarios findUsuarioById(int id) {
        return sesion.getSession().load(Usuarios.class, id);
    }

    @Override
    public Usuarios findUsuarioByUserAndPass(String nom_user, String pass) {
        return (Usuarios) sesion.getSession().createQuery("FROM Usuarios where nom_usuario = :nom_user and contrasena = :pass")
                .setParameter("nom_user", nom_user)
                .setParameter("pass", pass)
                .uniqueResult();
    }
}
