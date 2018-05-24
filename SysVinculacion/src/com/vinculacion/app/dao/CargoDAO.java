package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.CargoDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class CargoDAO extends Config implements CargoDaoInterface{

    public CargoDAO() {
        super();
    }
        
    @Override
    public void saveCargo(Cargo cargo) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into cargo (descripcion, estado) values(?,?)");
            ps.setString(1, cargo.getDESCRIPCION());
            ps.setString(2, cargo.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Cargo> AllCargos() {
        List<Cargo> lcargo = new ArrayList<Cargo>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargo WHERE ESTADO = 'ACTIVO' order by ID_CARGO desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cargo cargo = new Cargo();
                cargo.setID_CARGO(rs.getInt("id_cargo"));
                cargo.setDESCRIPCION(rs.getString("descripcion"));
                cargo.setESTADO(rs.getString("estado"));
                lcargo.add(cargo);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lcargo;
    }

    @Override
    public void updateCargo(Cargo cargo) {
        try {
            PreparedStatement ps = con.prepareStatement("update cargo set descripcion = ?, estado = ? where id_cargo = ?");
            ps.setString(1, cargo.getDESCRIPCION());
            ps.setString(2, cargo.getESTADO());
            ps.setInt(3, cargo.getID_CARGO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteCargo(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from cargo where id_cargo = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Cargo findCargoByDescription(String description) { 
        Cargo cargo = new Cargo(); 
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargo WHERE DESCRIPCION = ? and ESTADO = 'ACTIVO'");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cargo.setID_CARGO(rs.getInt("id_cargo"));
                cargo.setDESCRIPCION(rs.getString("descripcion"));
                cargo.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return cargo;    
    }

    @Override
    public Cargo findCargoById(int id) {
        Cargo cargo = new Cargo(); 
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargo WHERE id_cargo = ? and ESTADO = 'ACTIVO'");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cargo.setID_CARGO(rs.getInt("id_cargo"));
                cargo.setDESCRIPCION(rs.getString("descripcion"));
                cargo.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return cargo;
    }
}
