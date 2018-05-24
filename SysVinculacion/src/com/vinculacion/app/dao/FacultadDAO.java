package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.FacultadDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Facultad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class FacultadDAO extends Config implements FacultadDaoInterface{
    
    public FacultadDAO() {
        super();
    }
     
    @Override
    public void saveFacultad(Facultad facultad) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into facultad (descripcion, siglas, estado) values(?,?,?)");
            ps.setString(1, facultad.getDESCRIPCION());
            ps.setString(2, facultad.getSIGLAS());
            ps.setString(3, facultad.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Facultad> AllFacultad() {
        List<Facultad> lfacultad = new ArrayList<Facultad>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM facultad WHERE ESTADO = 'ACTIVO' ORDER BY ID_FACULTAD desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Facultad facultad = new Facultad();
                facultad.setID_FACULTAD(rs.getInt("id_facultad"));
                facultad.setDESCRIPCION(rs.getString("descripcion"));
                facultad.setSIGLAS(rs.getString("siglas"));
                facultad.setESTADO(rs.getString("estado"));
                lfacultad.add(facultad);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lfacultad;
    }

    @Override
    public void deleteFacultadById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from facultad where id_facultad = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }       
    }

    @Override
    public void updateFacultad(Facultad facultad) {
        try {
            PreparedStatement ps = con.prepareStatement("update facultad set descripcion = ?, siglas = ?, estado = ? where id_facultad = ?");
            ps.setString(1, facultad.getDESCRIPCION());
            ps.setString(2, facultad.getSIGLAS());
            ps.setString(3, facultad.getESTADO());
            ps.setInt(4, facultad.getID_FACULTAD());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Facultad findFacultadById(int id) {
        Facultad facultad = new Facultad();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM facultad WHERE id_facultad = ? and ESTADO = 'ACTIVO';");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                facultad.setID_FACULTAD(rs.getInt("id_facultad"));
                facultad.setDESCRIPCION(rs.getString("descripcion"));
                facultad.setSIGLAS(rs.getString("siglas"));
                facultad.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return facultad;
    }

    @Override
    public Facultad findFacultadByDescription(String description) {
        Facultad facultad = new Facultad();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM facultad WHERE descripcion = ? and ESTADO = 'ACTIVO';");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                facultad.setID_FACULTAD(rs.getInt("id_facultad"));
                facultad.setDESCRIPCION(rs.getString("descripcion"));
                facultad.setSIGLAS(rs.getString("siglas"));
                facultad.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return facultad;
    }
}
