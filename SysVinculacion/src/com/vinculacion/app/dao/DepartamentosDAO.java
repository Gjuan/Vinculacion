package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DepartamentosDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Departamentos;
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
public class DepartamentosDAO extends Config implements DepartamentosDaoInterface{

    public DepartamentosDAO() {
        super();
    }    
    
    @Override
    public void saveDepartamentos(Departamentos departamento) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into departamentos (id_empresa, nombre_departamento, estado) values(?,?,?)");
            ps.setInt(1, departamento.getEmpresa().getID_EMPRESA());
            ps.setString(2, departamento.getNOMBRE_DEPARTAMENTO());
            ps.setString(3, departamento.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Departamentos> AllDepartamentos() {
        List<Departamentos> ldep = new ArrayList<Departamentos>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT d.id_departamento, e.nombre_empresa, d.nombre_departamento, d.estado as estado_departamento FROM departamentos d, empresa e WHERE d.ESTADO = 'ACTIVO' and d.id_empresa = e.id_empresa order by d.ID_DEPARTAMENTO desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Departamentos departamento = new Departamentos();
                departamento.setID_DEPARTAMENTO(rs.getInt("id_departamento"));
                
                Empresa empresa = new Empresa();
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                departamento.setESTADO(rs.getString("estado_departamento"));
                ldep.add(departamento);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ldep;
    }

    @Override
    public void updateDepartamentos(Departamentos departamento) {
        try {
            PreparedStatement ps = con.prepareStatement("update departamentos set id_empresa = ?, nombre_departamento = ?, estado = ? where id_departamento = ?");
            ps.setInt(1, departamento.getEmpresa().getID_EMPRESA());
            ps.setString(2, departamento.getNOMBRE_DEPARTAMENTO());
            ps.setString(3, departamento.getESTADO());
            ps.setInt(4, departamento.getID_DEPARTAMENTO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteDepartamentos(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from departamentos where id_departamento = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    @Override
    public Departamentos findDepartamentoByName(String name, String nameEmpresa) {
        Departamentos departamento = new Departamentos();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT d.id_departamento, e.nombre_empresa, d.nombre_departamento, d.estado as estado_departamento FROM departamentos d, empresa e WHERE d.ESTADO = 'ACTIVO' and d.id_empresa = e.id_empresa and d.nombre_departamento = ? and e.nombre_empresa = ?;");
            ps.setString(1, name);
            ps.setString(2, nameEmpresa);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                departamento.setID_DEPARTAMENTO(rs.getInt("id_departamento"));
                
                Empresa empresa = new Empresa();
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                departamento.setESTADO(rs.getString("estado_departamento"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return departamento;
    }

    @Override
    public Departamentos findDepartamentoById(int id) {
        Departamentos departamento = new Departamentos();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT d.id_departamento, e.nombre_empresa, d.nombre_departamento, d.estado as estado_departamento FROM departamentos d, empresa e WHERE d.ESTADO = 'ACTIVO' and d.id_empresa = e.id_empresa and d.id_departamento = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                departamento.setID_DEPARTAMENTO(rs.getInt("id_departamento"));
                
                Empresa empresa = new Empresa();
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                departamento.setESTADO(rs.getString("estado_departamento"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return departamento;
    }

    @Override
    public List<Departamentos> AllDepartamentosByEmpresa(String name) {
        List<Departamentos> ldep = new ArrayList<Departamentos>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT d.id_departamento, e.nombre_empresa, d.nombre_departamento, d.estado as estado_departamento FROM departamentos d, empresa e WHERE d.ESTADO = 'ACTIVO' and d.id_empresa = e.id_empresa and e.nombre_empresa = ? order by d.ID_DEPARTAMENTO desc;");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Departamentos departamento = new Departamentos();
                departamento.setID_DEPARTAMENTO(rs.getInt("id_departamento"));
                
                Empresa empresa = new Empresa();
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                departamento.setESTADO(rs.getString("estado_departamento"));
                ldep.add(departamento);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ldep;
    }
    
}
