package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.CarrerasDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Escuela;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class CarrerasDAO extends Config implements CarrerasDaoInterface{

    public CarrerasDAO() {
        super();
    }

    @Override
    public void saveCarreras(Carreras carrera) {
       try {
            PreparedStatement ps = con.prepareStatement("insert into carreras (descripcion, id_escuela, estado) values(?,?,?)");
            ps.setString(1, carrera.getDESCRIPCION());
            ps.setInt(2, carrera.getEscuela().getID_ESCUELA());
            ps.setString(3, carrera.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Carreras> AllCarreras() {
        List<Carreras> lcarreras = new ArrayList<Carreras>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT c.id_carrera, c.descripcion as des_carrera, e.nombre_escuela, c.estado as estado_carrera FROM carreras c, escuela e WHERE c.ESTADO = 'ACTIVO' and c.id_escuela = e.id_escuela order by c.ID_CARRERA desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Carreras carrera = new Carreras();
                carrera.setID_CARRERA(rs.getInt("id_carrera"));
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                
                Escuela escuela = new Escuela();
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                carrera.setEscuela(escuela);
                
                carrera.setESTADO(rs.getString("estado_carrera"));
                lcarreras.add(carrera);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lcarreras;
    }

    @Override
    public void deleteCarreraById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from carreras where id_carrera = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void updateCarrera(Carreras carrera) {
        try {
            PreparedStatement ps = con.prepareStatement("update carreras set descripcion = ?, id_escuela = ?, estado = ? where id_carrera = ?");
            ps.setString(1, carrera.getDESCRIPCION());
            ps.setInt(2, carrera.getEscuela().getID_ESCUELA());
            ps.setString(3, carrera.getESTADO());
            ps.setInt(4, carrera.getID_CARRERA());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Carreras findCarreraById(int id) {
        Carreras carrera = new Carreras();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT c.id_carrera, c.descripcion as des_carrera, e.nombre_escuela, c.estado as estado_carrera FROM carreras c, escuela e WHERE c.ESTADO = 'ACTIVO' and c.id_escuela = e.id_escuela and c.id_carrera = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                carrera.setID_CARRERA(rs.getInt("id_carrera"));
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                
                Escuela escuela = new Escuela();
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                carrera.setEscuela(escuela);
                
                carrera.setESTADO(rs.getString("estado_carrera"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return carrera;
    }

    @Override
    public Carreras findCarreraByDescription(String descripcion) {
        Carreras carrera = new Carreras();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT c.id_carrera, c.descripcion as des_carrera, e.nombre_escuela, c.estado as estado_carrera FROM carreras c, escuela e WHERE c.ESTADO = 'ACTIVO' and c.id_escuela = e.id_escuela and c.descripcion = ?");
            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                carrera.setID_CARRERA(rs.getInt("id_carrera"));
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                
                Escuela escuela = new Escuela();
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                carrera.setEscuela(escuela);
                
                carrera.setESTADO(rs.getString("estado_carrera"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return carrera;
    }

    @Override
    public List<Carreras> findCarrerasByEscuela(String nombreEscuela) {
        List<Carreras> lcarreras = new ArrayList<Carreras>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT c.id_carrera, c.descripcion as des_carrera, e.nombre_escuela, c.estado as estado_carrera FROM carreras c, escuela e WHERE c.ESTADO = 'ACTIVO' and c.id_escuela = e.id_escuela and e.nombre_escuela = ?;");
            ps.setString(1, nombreEscuela);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Carreras carrera = new Carreras();
                carrera.setID_CARRERA(rs.getInt("id_carrera"));
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                
                Escuela escuela = new Escuela();
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                carrera.setEscuela(escuela);
                
                carrera.setESTADO(rs.getString("estado_carrera"));
                lcarreras.add(carrera);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lcarreras;
    }
}
