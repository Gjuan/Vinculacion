package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.NivelDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Nivel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class NivelDAO extends Config implements NivelDaoInterface{

    public NivelDAO() {
        super();
    }    
    
    @Override
    public void saveNivel(Nivel nivel) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into nivel (semestre, estado) values(?,?)");
            ps.setString(1, nivel.getSEMESTRE());
            ps.setString(2, nivel.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Nivel> AllNiveles() {
        List<Nivel> lnivel = new ArrayList<Nivel>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM nivel WHERE ESTADO = 'ACTIVO' order by ID_NIVEL desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Nivel nivel = new Nivel();
                nivel.setID_NIVEL(rs.getInt("id_nivel"));
                nivel.setSEMESTRE(rs.getString("semestre"));
                nivel.setESTADO(rs.getString("estado"));
                lnivel.add(nivel);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lnivel;
    }

    @Override
    public void updateNivel(Nivel nivel) {
        try {
            PreparedStatement ps = con.prepareStatement("update nivel set semestre = ?, estado = ? where id_nivel = ?");
            ps.setString(1, nivel.getSEMESTRE());
            ps.setString(2, nivel.getESTADO());
            ps.setInt(3, nivel.getID_NIVEL());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteNivel(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from nivel where id_nivel = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Nivel findNivelById(int id) {
        Nivel nivel = new Nivel();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM nivel WHERE ESTADO = 'ACTIVO' and ID_NIVEL = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nivel.setID_NIVEL(rs.getInt("id_nivel"));
                nivel.setSEMESTRE(rs.getString("semestre"));
                nivel.setESTADO(rs.getString("estado"));
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return nivel;
    }

    @Override
    public Nivel findNivelBySemestre(String semestre) {
        Nivel nivel = new Nivel();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Nivel WHERE ESTADO = 'ACTIVO' and SEMESTRE = ?");
            ps.setString(1, semestre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nivel.setID_NIVEL(rs.getInt("id_nivel"));
                nivel.setSEMESTRE(rs.getString("semestre"));
                nivel.setESTADO(rs.getString("estado"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return nivel;
    }
    
}
