package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.SeccionDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Seccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class SeccionDAO extends Config implements SeccionDaoInterface{

    public SeccionDAO() {
        super();
    }
        
    @Override
    public void saveSeccion(Seccion seccion) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into seccion (descripcion) values(?)");
            ps.setString(1, seccion.getDESCRIPCION());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Seccion> AllSecciones() {
        List<Seccion> lseccion = new ArrayList<Seccion>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from seccion order by id_seccion desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Seccion seccion = new Seccion();
                seccion.setID_SECCION(rs.getInt("id_seccion"));
                seccion.setDESCRIPCION(rs.getString("descripcion"));
                lseccion.add(seccion);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lseccion;
    }

    @Override
    public void deleteSeccion(int id) { 
    }

    @Override
    public void updateSeccion(Seccion seccion) {
        try {
            PreparedStatement ps = con.prepareStatement("update seccion set descripcion = ? where id_seccion = ?");
            ps.setString(1, seccion.getDESCRIPCION());
            ps.setInt(2, seccion.getID_SECCION());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Seccion findSeccionById(int id) {
        Seccion seccion = new Seccion();
        try {
            PreparedStatement ps = con.prepareStatement("select * from seccion where id_seccion = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                seccion.setID_SECCION(rs.getInt("id_seccion"));
                seccion.setDESCRIPCION(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return seccion;
    }

    @Override
    public Seccion findSeccionByDescription(String description) {
        Seccion seccion = new Seccion();
        try {
            PreparedStatement ps = con.prepareStatement("select * from seccion where descripcion = ?");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                seccion.setID_SECCION(rs.getInt("id_seccion"));
                seccion.setDESCRIPCION(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return seccion;
    }
    
}
