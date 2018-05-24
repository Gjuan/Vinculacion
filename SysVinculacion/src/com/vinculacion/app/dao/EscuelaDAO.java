package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EscuelaDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Escuela;
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
public class EscuelaDAO extends Config implements EscuelaDaoInterface{

    public EscuelaDAO() {
        super();
    }

    @Override
    public void saveEscuela(Escuela escuela) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into escuela (nombre_escuela, descripcion, id_facultad, estado) values(?,?,?,?)");
            ps.setString(1, escuela.getNOMBRE_ESCUELA());
            ps.setString(2, escuela.getDESCRIPCION());
            ps.setInt(3, escuela.getFacultad().getID_FACULTAD());
            ps.setString(4, escuela.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    @Override
    public List<Escuela> AllEscuelas() {
        List<Escuela> lescuela = new ArrayList<Escuela>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT e.id_escuela, e.nombre_escuela, e.descripcion as des_escuela, f.descripcion as des_facultad, e.estado as estado_escuela FROM facultad f, escuela e WHERE e.ESTADO = 'ACTIVO' and e.id_facultad = f.id_facultad order by e.ID_ESCUELA desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Escuela escuela = new Escuela();
                escuela.setID_ESCUELA(rs.getInt("id_escuela"));
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                escuela.setDESCRIPCION(rs.getString("des_escuela"));
                
                Facultad facultad = new Facultad();
                facultad.setDESCRIPCION(rs.getString("des_facultad"));
                escuela.setFacultad(facultad);
                
                escuela.setESTADO(rs.getString("estado_escuela"));
                lescuela.add(escuela);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lescuela;
    }

    @Override
    public void deleteEscuelaById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from escuela where id_escuela = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void updateEscuela(Escuela escuela) {
        try {
            PreparedStatement ps = con.prepareStatement("update escuela set nombre_escuela = ?, descripcion = ?, id_facultad = ?, estado = ? where id_escuela = ?");
            ps.setString(1, escuela.getNOMBRE_ESCUELA());
            ps.setString(2, escuela.getDESCRIPCION());
            ps.setInt(3, escuela.getFacultad().getID_FACULTAD());
            ps.setString(4, escuela.getESTADO());
            ps.setInt(5, escuela.getID_ESCUELA());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Escuela findEscuelaById(int id) {
        Escuela escuela = new Escuela();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT e.id_escuela, e.nombre_escuela, e.descripcion as des_escuela, f.descripcion as des_facultad, e.estado as estado_escuela FROM facultad f, escuela e WHERE e.ESTADO = 'ACTIVO' and e.id_facultad = f.id_facultad and e.id_escuela = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                escuela.setID_ESCUELA(rs.getInt("id_escuela"));
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                escuela.setDESCRIPCION(rs.getString("des_escuela"));
                
                Facultad facultad = new Facultad();
                facultad.setDESCRIPCION(rs.getString("des_facultad"));
                escuela.setFacultad(facultad);
                
                escuela.setESTADO(rs.getString("estado_escuela"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return escuela;
    }

    @Override
    public Escuela findEscuelaByName(String name) {
        Escuela escuela = new Escuela();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT e.id_escuela, e.nombre_escuela, e.descripcion as des_escuela, f.descripcion as des_facultad, e.estado as estado_escuela FROM facultad f, escuela e WHERE e.ESTADO = 'ACTIVO' and e.id_facultad = f.id_facultad and e.nombre_escuela = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                escuela.setID_ESCUELA(rs.getInt("id_escuela"));
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                escuela.setDESCRIPCION(rs.getString("des_escuela"));
                
                Facultad facultad = new Facultad();
                facultad.setDESCRIPCION(rs.getString("des_facultad"));
                escuela.setFacultad(facultad);
                
                escuela.setESTADO(rs.getString("estado_escuela"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return escuela;
    }

    @Override
    public List<Escuela> findEscuelaByFacultad(String description) {
        List<Escuela> lescuela = new ArrayList<Escuela>();    
        try {
            PreparedStatement ps = con.prepareStatement("SELECT e.id_escuela, e.nombre_escuela, e.descripcion as des_escuela, f.descripcion as des_facultad, e.estado as estado_escuela FROM facultad f, escuela e WHERE e.ESTADO = 'ACTIVO' and e.id_facultad = f.id_facultad and f.descripcion = ?;");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Escuela escuela = new Escuela();
                escuela.setID_ESCUELA(rs.getInt("id_escuela"));
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                escuela.setDESCRIPCION(rs.getString("des_escuela"));
                
                Facultad facultad = new Facultad();
                facultad.setDESCRIPCION(rs.getString("des_facultad"));
                escuela.setFacultad(facultad);
                
                escuela.setESTADO(rs.getString("estado_escuela"));
                lescuela.add(escuela);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lescuela;
    }
    /*public static void main(String[] args) {
        EscuelaDAO e = new EscuelaDAO();
        List<Escuela> le = e.AllEscuelas();
        for (Escuela escuela : le) {
            if (escuela.getFacultad().getESTADO().equals("ACTIVO")) {
                System.out.println("-------------------------------");
                System.out.println("ID: " + escuela.getID_ESCUELA());
                System.out.println("NOMBRE: " + escuela.getNOMBRE_ESCUELA());
                System.out.println("DESCRIPCION: " + escuela.getDESCRIPCION());
                System.out.println("FACULTAD: " + escuela.getFacultad().getDESCRIPCION());
            }           
        }
    }*/
}
