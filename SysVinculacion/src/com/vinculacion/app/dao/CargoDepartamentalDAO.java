package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.CargoDepartamentalDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.CargoDepartamental;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class CargoDepartamentalDAO extends Config implements CargoDepartamentalDaoInterface{

    public CargoDepartamentalDAO() {
        super();
    }
        
    @Override
    public void saveCargoDepartamental(CargoDepartamental cargo) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into cargo_departamental (descripcion, estado) values(?,?)");
            ps.setString(1, cargo.getDESCRIPCION());
            ps.setString(2, cargo.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<CargoDepartamental> AllCargosDepartamental() {
        List<CargoDepartamental> lcargos = new ArrayList<CargoDepartamental>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargo_departamental WHERE ESTADO = 'ACTIVO' order by ID_CARGO_EMPRESARIAL desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CargoDepartamental cargos = new CargoDepartamental();
                cargos.setID_CARGO_EMPRESARIAL(rs.getInt("id_cargo_empresarial"));
                cargos.setDESCRIPCION(rs.getString("descripcion"));
                cargos.setESTADO(rs.getString("estado"));
                lcargos.add(cargos);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lcargos;
    }

    @Override
    public void updateCargoDepartamental(CargoDepartamental cargo) {
        try {
            PreparedStatement ps = con.prepareStatement("update cargo_departamental set descripcion = ?, estado = ? where id_cargo_empresarial = ?");
            ps.setString(1, cargo.getDESCRIPCION());
            ps.setString(2, cargo.getESTADO());
            ps.setInt(3, cargo.getID_CARGO_EMPRESARIAL());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteCargoDepartamental(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from cargo_departamental where id_cargo_empresarial = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }       
    }

    @Override
    public CargoDepartamental findCargoDepartamentalByDescription(String description) {
        CargoDepartamental cargos = new CargoDepartamental();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargo_departamental where DESCRIPCION = ? and ESTADO = 'ACTIVO'");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){                
                cargos.setID_CARGO_EMPRESARIAL(rs.getInt("id_cargo_empresarial"));
                cargos.setDESCRIPCION(rs.getString("descripcion"));
                cargos.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return cargos;
    }

    @Override
    public CargoDepartamental findCargoDepartamentalById(int id) {
        CargoDepartamental cargos = new CargoDepartamental();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargo_departamental where id_cargo_empresarial = ? and ESTADO = 'ACTIVO'");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){           
                cargos.setID_CARGO_EMPRESARIAL(rs.getInt("id_cargo_empresarial"));
                cargos.setDESCRIPCION(rs.getString("descripcion"));
                cargos.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return cargos;
    }   
}
