package com.vinculacion.app.dao;

import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Usuarios;
import java.util.List;
import com.vinculacion.app.Interface.UsuariosDaoInterface;
import javax.persistence.EntityManager;

public class UsuariosDAO extends FactorFactory implements UsuariosDaoInterface{

    public UsuariosDAO() {
        super();
    }    
    
    @Override
    public void saveUsuarios(Usuarios usuario) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Usuarios> AllUsuarios() {
        EntityManager manager = emf.createEntityManager();
        List<Usuarios> lusuarios = (List<Usuarios>) manager.createQuery("From Usuarios WHERE estado = 'ACTIVO' order by id_usuario asc").getResultList();
        manager.close();
        return lusuarios;
    }

    @Override
    public void deleteUsuarioById(int id) {
        Usuarios usuario = findUsuarioById(id);
        if (usuario != null) {
            usuario.setEstado("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(usuario);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public void updateUsuario(Usuarios usuario) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(usuario);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Usuarios findUsuarioById(int id) {
        EntityManager manager = emf.createEntityManager();
        Usuarios usuario = manager.find(Usuarios.class, id);
        manager.close();
        return usuario;
    }

    @Override
    public Usuarios findUsuarioByUserAndPass(String nom_user, String pass) {
       EntityManager manager = emf.createEntityManager();
       Usuarios usuario = (Usuarios) manager.createQuery("FROM Usuarios WHERE nom_usuario = :nom_user and contrasena = :pass and estado = 'ACTIVO'")
               .setParameter("nom_user", nom_user)
               .setParameter("pass", pass).getSingleResult();    
       manager.close();
       return usuario;
    }
}
