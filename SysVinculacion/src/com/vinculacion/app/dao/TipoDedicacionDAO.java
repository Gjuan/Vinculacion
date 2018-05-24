package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.TipoDedicacionDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.TipoDedicacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class TipoDedicacionDAO extends Config implements TipoDedicacionDaoInterface{

    public TipoDedicacionDAO() {
        super();
    }    
    
    @Override
    public void saveTipoDedicacion(TipoDedicacion tipoDedicacion) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into tipo_dedicacion (descripcion, estado) values(?,?)");
            ps.setString(1, tipoDedicacion.getDESCRIPCION());
            ps.setString(2, tipoDedicacion.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<TipoDedicacion> AllTiposDedicacion() {
        List<TipoDedicacion> ltipodedicacion = new ArrayList<TipoDedicacion>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipo_dedicacion WHERE ESTADO = 'ACTIVO' order by ID_TIPO_DEDICACION desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoDedicacion td = new TipoDedicacion();
                td.setID_TIPO_DEDICACION(rs.getInt("id_tipo_dedicacion"));
                td.setDESCRIPCION(rs.getString("descripcion"));
                td.setESTADO(rs.getString("estado"));
                ltipodedicacion.add(td);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ltipodedicacion;
    }

    @Override
    public void updateTipoDedicacion(TipoDedicacion tipoDedicacion) {
        try {
            PreparedStatement ps = con.prepareStatement("update tipo_dedicacion set descripcion = ?, estado = ? where id_tipo_dedicacion = ?");
            ps.setString(1, tipoDedicacion.getDESCRIPCION());
            ps.setString(2, tipoDedicacion.getESTADO());
            ps.setInt(3, tipoDedicacion.getID_TIPO_DEDICACION());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteTipoDedicacion(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from tipo_dedicacion where id_tipo_dedicacion = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public TipoDedicacion findTipoDedicacionByDescription(String description) {
        TipoDedicacion tipodedicacion = new TipoDedicacion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipo_dedicacion WHERE ESTADO = 'ACTIVO' and DESCRIPCION = ?");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tipodedicacion.setID_TIPO_DEDICACION(rs.getInt("id_tipo_dedicacion"));
                tipodedicacion.setDESCRIPCION(rs.getString("descripcion"));
                tipodedicacion.setESTADO(rs.getString("estado"));
            }
        }catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tipodedicacion;
    }

    @Override
    public TipoDedicacion findTipoDedicacionById(int id) {
        TipoDedicacion tipodedicacion = new TipoDedicacion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipo_dedicacion WHERE ESTADO = 'ACTIVO' and id_tipo_dedicacion = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tipodedicacion.setID_TIPO_DEDICACION(rs.getInt("id_tipo_dedicacion"));
                tipodedicacion.setDESCRIPCION(rs.getString("descripcion"));
                tipodedicacion.setESTADO(rs.getString("estado"));
            }
        }catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tipodedicacion;
    }
    
}
