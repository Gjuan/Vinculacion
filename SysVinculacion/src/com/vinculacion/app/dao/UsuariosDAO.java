package com.vinculacion.app.dao;

import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Usuarios;
import java.util.List;
import com.vinculacion.app.Interface.UsuariosDaoInterface;
import com.vinculacion.app.model.Perfil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuariosDAO extends Config implements UsuariosDaoInterface{

    public UsuariosDAO() {
        super();
    }    
    
    @Override
    public void saveUsuarios(Usuarios usuario) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into usuarios (nombres, apellidos, correo, nom_usuario, contrasena, id_perfil, estado) values(?,?,?,?,?,?,?)");
            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getNom_usuario());
            ps.setString(5, usuario.getContrasena());
            ps.setInt(6, usuario.getPerfil().getId_perfil());
            ps.setString(7, usuario.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Usuarios> AllUsuarios() {
        List<Usuarios> lusuarios = new ArrayList<Usuarios>();
        try {
            PreparedStatement ps = con.prepareStatement("select u.id_usuario, u.nombres, u.apellidos, u.correo, u.nom_usuario, u.contrasena, p.descripcion, u.estado as estado_user from perfil p, usuarios u where p.id_perfil = u.id_perfil order by u.id_usuario desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuarios user = new Usuarios();
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombres(rs.getString("nombres"));
                user.setApellidos(rs.getString("apellidos"));
                user.setCorreo(rs.getString("correo"));
                user.setNom_usuario(rs.getString("nom_usuario"));
                user.setContrasena(rs.getString("contrasena"));
                Perfil perfil = new Perfil();
                perfil.setDescripcion(rs.getString("descripcion"));
                user.setPerfil(perfil);
                user.setEstado(rs.getString("estado_user"));
                lusuarios.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lusuarios;
    }

    @Override
    public void deleteUsuarioById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from usuarios where id_usuario = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void updateUsuario(Usuarios usuario) {
        try {
            PreparedStatement ps = con.prepareStatement("update usuarios set nombres = ?, apellidos = ?, correo = ?, nom_usuario = ?, contrasena = ?, id_perfil = ?, estado = ? where id_usuario = ?");
            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getNom_usuario());
            ps.setString(5, usuario.getContrasena());
            ps.setInt(6, usuario.getPerfil().getId_perfil());
            ps.setString(7, usuario.getEstado());
            ps.setInt(8, usuario.getId_usuario());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Usuarios findUsuarioById(int id) {
        Usuarios user = new Usuarios();
        try {
            PreparedStatement ps = con.prepareStatement("select u.id_usuario, u.nombres, u.apellidos, u.correo, u.nom_usuario, u.contrasena, p.descripcion, u.estado as estado_user from perfil p, usuarios u where p.id_perfil = u.id_perfil and u.id_perfil = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombres(rs.getString("nombres"));
                user.setApellidos(rs.getString("apellidos"));
                user.setCorreo(rs.getString("correo"));
                user.setNom_usuario(rs.getString("nom_usuario"));
                user.setContrasena(rs.getString("contrasena"));
                Perfil perfil = new Perfil();
                perfil.setDescripcion(rs.getString("descripcion"));
                user.setPerfil(perfil);
                user.setEstado(rs.getString("estado_user"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return user;
    }

    @Override
    public Usuarios findUsuarioByUserAndPass(String nom_user, String pass) {
        Usuarios user = new Usuarios();
        try {
            PreparedStatement ps = con.prepareStatement("select u.id_usuario, u.nombres, u.apellidos, u.correo, u.nom_usuario, u.contrasena, p.descripcion, u.estado as estado_user from perfil p, usuarios u where p.id_perfil = u.id_perfil and u.nom_usuario = ? and u.contrasena = ?;");
            ps.setString(1, nom_user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombres(rs.getString("nombres"));
                user.setApellidos(rs.getString("apellidos"));
                user.setCorreo(rs.getString("correo"));
                user.setNom_usuario(rs.getString("nom_usuario"));
                user.setContrasena(rs.getString("contrasena"));
                Perfil perfil = new Perfil();
                perfil.setDescripcion(rs.getString("descripcion"));
                user.setPerfil(perfil);
                user.setEstado(rs.getString("estado_user"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
       return user;
    }

    @Override
    public List<Usuarios> findUserByPerfil(String descriptionPerfil) {
        List<Usuarios> lusuarios = new ArrayList<Usuarios>();
        try {
            PreparedStatement ps = con.prepareStatement("select u.id_usuario, u.nombres, u.apellidos, u.correo, u.nom_usuario, u.contrasena, p.descripcion, u.estado as estado_user from perfil p, usuarios u where p.id_perfil = u.id_perfil and p.descripcion = ?;");
            ps.setString(1, descriptionPerfil);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuarios user = new Usuarios();
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombres(rs.getString("nombres"));
                user.setApellidos(rs.getString("apellidos"));
                user.setCorreo(rs.getString("correo"));
                user.setNom_usuario(rs.getString("nom_usuario"));
                user.setContrasena(rs.getString("contrasena"));
                Perfil perfil = new Perfil();
                perfil.setDescripcion(rs.getString("descripcion"));
                user.setPerfil(perfil);
                user.setEstado(rs.getString("estado_user"));
                lusuarios.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lusuarios;
    }
}
