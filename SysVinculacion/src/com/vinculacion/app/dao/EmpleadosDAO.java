package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EmpleadosDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.CargoDepartamental;
import com.vinculacion.app.model.Departamentos;
import com.vinculacion.app.model.Empleados;
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
public class EmpleadosDAO extends Config implements EmpleadosDaoInterface{

    public EmpleadosDAO() {
        super();
    }
        
    @Override
    public void saveEmpleados(Empleados empleado) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into empleados (id_cargo_empresarial, id_departamento, nombres, apellidos, cedula, correo, telefono, estado) values(?,?,?,?,?,?,?,?)");
            ps.setInt(1, empleado.getCargoDepartamental().getID_CARGO_EMPRESARIAL());
            ps.setInt(2, empleado.getDepartamentos().getID_DEPARTAMENTO());
            ps.setString(3, empleado.getNOMBRES());
            ps.setString(4, empleado.getAPELLIDOS());
            ps.setString(5, empleado.getCEDULA());
            ps.setString(6, empleado.getCORREO());
            ps.setString(7, empleado.getTELEFONO());
            ps.setString(8, empleado.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Empleados> AllEmpleados() {
        List<Empleados> lemp = new ArrayList<Empleados>();
        try {
            PreparedStatement ps = con.prepareStatement("select e.id_empleado, cd.descripcion as cargo_depart, d.nombre_departamento, em.nombre_empresa, e.nombres, e.apellidos, e.cedula, e.correo, e.telefono, e.estado as estado_empleado FROM empleados e, cargo_departamental cd, departamentos d, empresa em where e.id_cargo_empresarial = cd.id_cargo_empresarial and e.id_departamento = d.id_departamento and e.estado = 'ACTIVO' and d.id_empresa = em.id_empresa ORDER BY e.id_empleado desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empleados empleado = new Empleados();
                empleado.setID_EMPLEADO(rs.getInt("id_empleado"));
                
                CargoDepartamental cargoDepartamental = new CargoDepartamental();
                cargoDepartamental.setDESCRIPCION(rs.getString("cargo_depart"));
                empleado.setCargoDepartamental(cargoDepartamental);
                
                Departamentos departamento = new Departamentos();
                Empresa empresa = new Empresa();
                
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                empleado.setDepartamentos(departamento);
                
                empleado.setNOMBRES(rs.getString("nombres"));
                empleado.setAPELLIDOS(rs.getString("apellidos"));
                empleado.setCEDULA(rs.getString("cedula"));
                empleado.setCORREO(rs.getString("correo"));
                empleado.setTELEFONO(rs.getString("telefono"));
                empleado.setESTADO(rs.getString("estado_empleado"));
                lemp.add(empleado);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lemp;
    }

    @Override
    public void deleteEmpleado(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from empleados where id_empleado = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void updateEmpleado(Empleados empleado) {
        try {
            PreparedStatement ps = con.prepareStatement("update empleados set id_cargo_empresarial = ?, id_departamento = ?, nombres = ?, apellidos = ?, cedula = ?, correo = ?, telefono = ?, estado = ? where id_empleado = ?");
            ps.setInt(1, empleado.getCargoDepartamental().getID_CARGO_EMPRESARIAL());
            ps.setInt(2, empleado.getDepartamentos().getID_DEPARTAMENTO());
            ps.setString(3, empleado.getNOMBRES());
            ps.setString(4, empleado.getAPELLIDOS());
            ps.setString(5, empleado.getCEDULA());
            ps.setString(6, empleado.getCORREO());
            ps.setString(7, empleado.getTELEFONO());
            ps.setString(8, empleado.getESTADO());
            ps.setInt(9, empleado.getID_EMPLEADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Empleados findEmpleadoByCedula(String cedula) {
        Empleados empleado = new Empleados();
        try {
            PreparedStatement ps = con.prepareStatement("select e.id_empleado, cd.descripcion as cargo_depart, d.nombre_departamento, em.nombre_empresa, e.nombres, e.apellidos, e.cedula, e.correo, e.telefono, e.estado as estado_empleado FROM empleados e, cargo_departamental cd, departamentos d, empresa em where e.id_cargo_empresarial = cd.id_cargo_empresarial and e.id_departamento = d.id_departamento and e.estado = 'ACTIVO' and d.id_empresa = em.id_empresa and e.cedula = ?;");
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                empleado.setID_EMPLEADO(rs.getInt("id_empleado"));
                
                CargoDepartamental cargoDepartamental = new CargoDepartamental();
                cargoDepartamental.setDESCRIPCION(rs.getString("cargo_depart"));
                empleado.setCargoDepartamental(cargoDepartamental);
                
                Departamentos departamento = new Departamentos();
                Empresa empresa = new Empresa();
                
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                empleado.setDepartamentos(departamento);
                
                empleado.setNOMBRES(rs.getString("nombres"));
                empleado.setAPELLIDOS(rs.getString("apellidos"));
                empleado.setCEDULA(rs.getString("cedula"));
                empleado.setCORREO(rs.getString("correo"));
                empleado.setTELEFONO(rs.getString("telefono"));
                empleado.setESTADO(rs.getString("estado_empleado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return empleado;
    }

    @Override
    public Empleados findEmpleadoById(int id) {
        Empleados empleado = new Empleados();
        try {
            PreparedStatement ps = con.prepareStatement("select e.id_empleado, cd.descripcion as cargo_depart, d.nombre_departamento, em.nombre_empresa, e.nombres, e.apellidos, e.cedula, e.correo, e.telefono, e.estado as estado_empleado FROM empleados e, cargo_departamental cd, departamentos d, empresa em where e.id_cargo_empresarial = cd.id_cargo_empresarial and e.id_departamento = d.id_departamento and e.estado = 'ACTIVO' and d.id_empresa = em.id_empresa and e.id_empleado = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                empleado.setID_EMPLEADO(rs.getInt("id_empleado"));
                
                CargoDepartamental cargoDepartamental = new CargoDepartamental();
                cargoDepartamental.setDESCRIPCION(rs.getString("cargo_depart"));
                empleado.setCargoDepartamental(cargoDepartamental);
                
                Departamentos departamento = new Departamentos();
                Empresa empresa = new Empresa();
                
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                empleado.setDepartamentos(departamento);
                
                empleado.setNOMBRES(rs.getString("nombres"));
                empleado.setAPELLIDOS(rs.getString("apellidos"));
                empleado.setCEDULA(rs.getString("cedula"));
                empleado.setCORREO(rs.getString("correo"));
                empleado.setTELEFONO(rs.getString("telefono"));
                empleado.setESTADO(rs.getString("estado_empleado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return empleado;
    }

    @Override
    public List<Empleados> findEmpleadosByCargos(String descripcionCargo) {
        List<Empleados> lemp = new ArrayList<Empleados>();
        try {
            PreparedStatement ps = con.prepareStatement("select e.id_empleado, cd.descripcion as cargo_depart, d.nombre_departamento, em.nombre_empresa, e.nombres, e.apellidos, e.cedula, e.correo, e.telefono, e.estado as estado_empleado FROM empleados e, cargo_departamental cd, departamentos d, empresa em where e.id_cargo_empresarial = cd.id_cargo_empresarial and e.id_departamento = d.id_departamento and e.estado = 'ACTIVO' and d.id_empresa = em.id_empresa and cd.descripcion = ? ORDER BY e.id_empleado desc;");
            ps.setString(1, descripcionCargo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empleados empleado = new Empleados();
                empleado.setID_EMPLEADO(rs.getInt("id_empleado"));
                
                CargoDepartamental cargoDepartamental = new CargoDepartamental();
                cargoDepartamental.setDESCRIPCION(rs.getString("cargo_depart"));
                empleado.setCargoDepartamental(cargoDepartamental);
                
                Departamentos departamento = new Departamentos();
                Empresa empresa = new Empresa();
                
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                empleado.setDepartamentos(departamento);
                
                empleado.setNOMBRES(rs.getString("nombres"));
                empleado.setAPELLIDOS(rs.getString("apellidos"));
                empleado.setCEDULA(rs.getString("cedula"));
                empleado.setCORREO(rs.getString("correo"));
                empleado.setTELEFONO(rs.getString("telefono"));
                empleado.setESTADO(rs.getString("estado_empleado"));
                lemp.add(empleado);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lemp;
    }

    @Override
    public Empleados findEmpleadosByLastNameAndName(String name, String lastName) {
        Empleados empleado = new Empleados();
        try {
            PreparedStatement ps = con.prepareStatement("select e.id_empleado, cd.descripcion as cargo_depart, d.nombre_departamento, em.nombre_empresa, e.nombres, e.apellidos, e.cedula, e.correo, e.telefono, e.estado as estado_empleado FROM empleados e, cargo_departamental cd, departamentos d, empresa em where e.id_cargo_empresarial = cd.id_cargo_empresarial and e.id_departamento = d.id_departamento and e.estado = 'ACTIVO' and d.id_empresa = em.id_empresa and e.nombres = ? and e.apellidos = ?;");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                empleado.setID_EMPLEADO(rs.getInt("id_empleado"));
                
                CargoDepartamental cargoDepartamental = new CargoDepartamental();
                cargoDepartamental.setDESCRIPCION(rs.getString("cargo_depart"));
                empleado.setCargoDepartamental(cargoDepartamental);
                
                Departamentos departamento = new Departamentos();
                Empresa empresa = new Empresa();
                
                empresa.setNOMBRE_EMPRESA(rs.getString("nombre_empresa"));
                departamento.setEmpresa(empresa);
                departamento.setNOMBRE_DEPARTAMENTO(rs.getString("nombre_departamento"));
                empleado.setDepartamentos(departamento);
                
                empleado.setNOMBRES(rs.getString("nombres"));
                empleado.setAPELLIDOS(rs.getString("apellidos"));
                empleado.setCEDULA(rs.getString("cedula"));
                empleado.setCORREO(rs.getString("correo"));
                empleado.setTELEFONO(rs.getString("telefono"));
                empleado.setESTADO(rs.getString("estado_empleado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return empleado;   
    }
    
}
