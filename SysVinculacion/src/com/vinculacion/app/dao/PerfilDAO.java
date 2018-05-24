package com.vinculacion.app.dao;

import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Perfil;
import java.util.List;
import com.vinculacion.app.Interface.PerfilDaoInterface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerfilDAO extends Config implements PerfilDaoInterface{

    public PerfilDAO() {
        super();
    }   
    
    @Override
    public void savePerfil(Perfil perfil) {
       try {
            PreparedStatement ps = con.prepareStatement("insert into perfil (descripcion, estado) values(?,?)");
            ps.setString(1, perfil.getDescripcion());
            ps.setString(2, perfil.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Perfil> AllPerfil() {
       List<Perfil> lp = new ArrayList<Perfil>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from perfil order by id_perfil desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Perfil perfil = new Perfil();
                perfil.setId_perfil(rs.getInt("id_perfil"));
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setESTADO(rs.getString("estado"));
                lp.add(perfil);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lp;
    }

    @Override
    public void deletePerfilById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from perfil where id_perfil = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void updatePerfil(Perfil perfil) {
        try {
            PreparedStatement ps = con.prepareStatement("update perfil set descripcion = ?, estado = ? where id_perfil = ?");
            ps.setString(1, perfil.getDescripcion());
            ps.setString(2, perfil.getESTADO());
            ps.setInt(3, perfil.getId_perfil());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Perfil findPerfilById(int id) {
        Perfil perfil = new Perfil();            
        try {
            PreparedStatement ps = con.prepareStatement("select * from perfil where id_perfil = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                perfil.setId_perfil(rs.getInt("id_perfil"));
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return perfil;
    }      

    @Override
    public Perfil findPerfilByDescription(String description) {
        Perfil perfil = new Perfil();            
        try {
            PreparedStatement ps = con.prepareStatement("select * from perfil where descripcion = ?");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                perfil.setId_perfil(rs.getInt("id_perfil"));
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return perfil;
    }
}
