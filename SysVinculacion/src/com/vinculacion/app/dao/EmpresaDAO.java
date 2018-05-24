package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EmpresaDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jorge
 */
public class EmpresaDAO extends Config implements EmpresaDaoInterface{

    public EmpresaDAO() {
        super();
    }
        
    @Override
    public void saveEmpresa(Empresa empresa) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into empresa (nombre_empresa, telefono, direccion) values(?,?,?)");
            ps.setString(1, empresa.getNOMBRE_EMPRESA());
            ps.setString(2, empresa.getTELEFONO());
            ps.setString(3, empresa.getDIRECCION());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Empresa> AllEmpresas() {
        List<Empresa> lempresa = new ArrayList<Empresa>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM empresa order by ID_EMPRESA desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empresa empresa = new Empresa();
                empresa.setID_EMPRESA(rs.getInt("id_empresa"));
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                empresa.setTELEFONO(rs.getString("telefono"));
                empresa.setDIRECCION(rs.getString("direccion"));
                lempresa.add(empresa);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lempresa;
    }

    @Override
    public void updateEmpresa(Empresa empresa) {
        try {
            PreparedStatement ps = con.prepareStatement("update empresa set nombre_empresa = ?, telefono = ?, direccion = ? where id_empresa = ?");
            ps.setString(1, empresa.getNOMBRE_EMPRESA());
            ps.setString(2, empresa.getTELEFONO());
            ps.setString(3, empresa.getDIRECCION());
            ps.setInt(4, empresa.getID_EMPRESA());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmpresa(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from empresa where id_empresa = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Empresa findEmpresaById(int id) {
        Empresa empresa = new Empresa();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM empresa where id_empresa = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                empresa.setID_EMPRESA(rs.getInt("id_empresa"));
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                empresa.setTELEFONO(rs.getString("telefono"));
                empresa.setDIRECCION(rs.getString("direccion"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return empresa;
    }

    @Override
    public Empresa findEmpresaByName(String name) {
        Empresa empresa = new Empresa();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM empresa where nombre_empresa = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                empresa.setID_EMPRESA(rs.getInt("id_empresa"));
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                empresa.setTELEFONO(rs.getString("telefono"));
                empresa.setDIRECCION(rs.getString("direccion"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return empresa;    
    }
    
}
