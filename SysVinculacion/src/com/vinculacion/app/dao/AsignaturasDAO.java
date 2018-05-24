package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.AsignaturaDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Asignaturas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jorge
 */
public class AsignaturasDAO extends Config implements AsignaturaDaoInterface{

    public AsignaturasDAO() {
        super();
    }
        
    @Override
    public void saveAsignatura(Asignaturas asignatura) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into asignaturas(nombre_asignatura, estado) values(?,?)");
            ps.setString(1, asignatura.getNOMBRE_ASIGNATURA());
            ps.setString(2, asignatura.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Asignaturas> AllAsignaturas() {
        List<Asignaturas> lasig = new ArrayList<Asignaturas>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM asignaturas WHERE ESTADO = 'ACTIVO' order by ID_ASIGNATURA desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Asignaturas asignatura = new Asignaturas();
                asignatura.setID_ASIGNATURA(rs.getInt("id_asignatura"));
                asignatura.setNOMBRE_ASIGNATURA(rs.getString("nombre_asignatura"));
                asignatura.setESTADO(rs.getString("estado"));
                lasig.add(asignatura);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lasig;
    }

    @Override
    public void updateAsignatura(Asignaturas asignatura) {
        try {
            PreparedStatement ps = con.prepareStatement("update asignaturas set nombre_asignatura = ?, estado = ? where id_asignatura = ?");
            ps.setString(1, asignatura.getNOMBRE_ASIGNATURA());
            ps.setString(2, asignatura.getESTADO());
            ps.setInt(3, asignatura.getID_ASIGNATURA());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAsignatura(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from asignaturas where id_asignatura = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Asignaturas findAsignaturaByName(String name) {
        Asignaturas asignatura = new Asignaturas();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM asignaturas WHERE ESTADO = 'ACTIVO' and NOMBRE_ASIGNATURA = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                asignatura.setID_ASIGNATURA(rs.getInt("id_asignatura"));
                asignatura.setNOMBRE_ASIGNATURA(rs.getString("nombre_asignatura"));
                asignatura.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return asignatura;
    }

    @Override
    public Asignaturas findAsignaturaById(int id) {
        Asignaturas asignatura = new Asignaturas();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM asignaturas WHERE ESTADO = 'ACTIVO' and id_asignatura = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                asignatura.setID_ASIGNATURA(rs.getInt("id_asignatura"));
                asignatura.setNOMBRE_ASIGNATURA(rs.getString("nombre_asignatura"));
                asignatura.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return asignatura;
    }
 
}
