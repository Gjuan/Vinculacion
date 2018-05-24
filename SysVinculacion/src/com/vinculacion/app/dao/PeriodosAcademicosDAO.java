package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.PeriodosAcademicosDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.PeriodoAcademico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class PeriodosAcademicosDAO extends Config implements PeriodosAcademicosDaoInterface{

    public PeriodosAcademicosDAO() {
        super();
    }
    
    @Override
    public void savePeriodosAcademicos(PeriodoAcademico periodo) {
       try {
            PreparedStatement ps = con.prepareStatement("insert into periodo_academico (anio_academico, nombre_periodo, fecha_inicio_periodo, fecha_fin_periodo, estado) values(?,?,?,?,?)");
            ps.setInt(1, periodo.getANIO_ACADEMICO());
            ps.setString(2, periodo.getNOMBRE_PERIODO());
            ps.setString(3, periodo.getFECHA_INICIO_PERIODO());
            ps.setString(4, periodo.getFECHA_FIN_PERIODO());
            ps.setString(5, periodo.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } 
    }

    @Override
    public List<PeriodoAcademico> AllPeriodosAcademicos() {
        List<PeriodoAcademico> lperiodo = new ArrayList<PeriodoAcademico>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM periodo_academico WHERE ESTADO = 'ACTIVO' order by ID_PERIODO_ACADEMICO desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setID_PERIODO_ACADEMICO(rs.getInt("id_periodo_academico"));
                pa.setANIO_ACADEMICO(rs.getInt("anio_academico"));
                pa.setNOMBRE_PERIODO(rs.getString("nombre_periodo"));
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                pa.setFECHA_FIN_PERIODO(rs.getString("fecha_fin_periodo"));
                pa.setESTADO(rs.getString("estado"));
                lperiodo.add(pa);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lperiodo;
    }

    @Override
    public void updatePeriodoAcademico(PeriodoAcademico periodo) {
        try {
            PreparedStatement ps = con.prepareStatement("update periodo_academico set anio_academico = ?, nombre_periodo = ?, fecha_inicio_periodo = ?, fecha_fin_periodo = ?, estado = ? where id_periodo_academico = ?");
            ps.setInt(1, periodo.getANIO_ACADEMICO());
            ps.setString(2, periodo.getNOMBRE_PERIODO());
            ps.setString(3, periodo.getFECHA_INICIO_PERIODO());
            ps.setString(4, periodo.getFECHA_FIN_PERIODO());
            ps.setString(5, periodo.getESTADO());
            ps.setInt(6, periodo.getID_PERIODO_ACADEMICO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deletePeriodoAcademico(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from periodo_academico where id_periodo_academico = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }        
    }

    @Override
    public List<PeriodoAcademico> findPeriodoAcademicoByName(String name) {
        List<PeriodoAcademico> lperiodo = new ArrayList<PeriodoAcademico>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM periodo_academico WHERE NOMBRE_PERIODO = ? AND ESTADO = 'ACTIVO' order by ID_PERIODO_ACADEMICO desc;");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setID_PERIODO_ACADEMICO(rs.getInt("id_periodo_academico"));
                pa.setANIO_ACADEMICO(rs.getInt("anio_academico"));
                pa.setNOMBRE_PERIODO(rs.getString("nombre_periodo"));
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                pa.setFECHA_FIN_PERIODO(rs.getString("fecha_fin_periodo"));
                pa.setESTADO(rs.getString("estado"));
                lperiodo.add(pa);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lperiodo;
    }

    @Override
    public PeriodoAcademico findPeriodoAcademicoByStartDate(String fecha_inicio) {
        PeriodoAcademico periodo = new PeriodoAcademico();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM periodo_academico WHERE ESTADO = 'ACTIVO' and FECHA_INICIO_PERIODO = ?");
            ps.setString(1, fecha_inicio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                periodo.setID_PERIODO_ACADEMICO(rs.getInt("id_periodo_academico"));
                periodo.setANIO_ACADEMICO(rs.getInt("anio_academico"));
                periodo.setNOMBRE_PERIODO(rs.getString("nombre_periodo"));
                periodo.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                periodo.setFECHA_FIN_PERIODO(rs.getString("fecha_fin_periodo"));
                periodo.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return periodo;
    }

    @Override
    public PeriodoAcademico findPeriodoAcademicoById(int id) {
        PeriodoAcademico periodo = new PeriodoAcademico();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM periodo_academico WHERE ESTADO = 'ACTIVO' and id_periodo_academico = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                periodo.setID_PERIODO_ACADEMICO(rs.getInt("id_periodo_academico"));
                periodo.setANIO_ACADEMICO(rs.getInt("anio_academico"));
                periodo.setNOMBRE_PERIODO(rs.getString("nombre_periodo"));
                periodo.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                periodo.setFECHA_FIN_PERIODO(rs.getString("fecha_fin_periodo"));
                periodo.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return periodo;
    }
    
}
