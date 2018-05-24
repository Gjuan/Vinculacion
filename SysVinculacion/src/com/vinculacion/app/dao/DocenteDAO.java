package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DocentesDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.Escuela;
import com.vinculacion.app.model.Facultad;
import com.vinculacion.app.model.Genero;
import com.vinculacion.app.model.TipoDedicacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAO extends Config implements DocentesDaoInterface{

    public DocenteDAO() {
        super();
    }

    @Override
    public void saveDocente(Docente docente) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into docente (id_genero, id_carrera, id_tipo_dedicacion, cedula, nombres, apellidos, telefono, correo, estado) values(?,?,?,?,?,?,?,?,?);");
            ps.setInt(1, docente.getGenero().getID_GENERO());
            ps.setInt(2, docente.getCarrera().getID_CARRERA());
            ps.setInt(3, docente.getTipoDedicacion().getID_TIPO_DEDICACION());
            ps.setString(4, docente.getCEDULA());
            ps.setString(5, docente.getNOMBRES());
            ps.setString(6, docente.getAPELLIDOS());
            ps.setString(7, docente.getTELEFONO());
            ps.setString(8, docente.getCORREO());
            ps.setString(9, docente.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Docente> AllDocente() {
        List<Docente> ldocente = new ArrayList<Docente>();
        try {
            PreparedStatement ps = con.prepareStatement("select * from docente order by id_docente desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Docente docente = new Docente();
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                docente.setID_DOCENTE(rs.getInt("id_docente"));
                
                genero.setID_GENERO(rs.getInt("id_genero"));
                docente.setGenero(genero);
                
                carrera.setID_CARRERA(rs.getInt("id_carrera"));
                docente.setCarrera(carrera);
                
                td.setID_TIPO_DEDICACION(rs.getInt("id_tipo_dedicacion"));
                docente.setTipoDedicacion(td);
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                docente.setESTADO(rs.getString("estado"));
                ldocente.add(docente);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ldocente;
    }

    @Override
    public void updateDocente(Docente docente) {
        try {
            PreparedStatement ps = con.prepareStatement("update docente set id_genero = ?, id_carrera = ?, id_tipo_dedicacion = ?, cedula = ?, nombres = ?, apellidos = ?, telefono = ?, correo = ?, estado = ? where id_docente = ?");
            ps.setInt(1, docente.getGenero().getID_GENERO());
            ps.setInt(2, docente.getCarrera().getID_CARRERA());
            ps.setInt(3, docente.getTipoDedicacion().getID_TIPO_DEDICACION());
            ps.setString(4, docente.getCEDULA());
            ps.setString(5, docente.getNOMBRES());
            ps.setString(6, docente.getAPELLIDOS());
            ps.setString(7, docente.getTELEFONO());
            ps.setString(8, docente.getCORREO());
            ps.setString(9, docente.getESTADO());
            ps.setInt(10, docente.getID_DOCENTE());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteDocente(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from docente where id_docente = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Docente findDocenteById(int id) {
        Docente docente = new Docente();
        try {
            PreparedStatement ps = con.prepareStatement("select * from docente where id_docente = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                docente.setID_DOCENTE(rs.getInt("id_docente"));
                
                genero.setID_GENERO(rs.getInt("id_genero"));
                docente.setGenero(genero);
                
                carrera.setID_CARRERA(rs.getInt("id_carrera"));
                docente.setCarrera(carrera);
                
                td.setID_TIPO_DEDICACION(rs.getInt("id_tipo_dedicacion"));
                docente.setTipoDedicacion(td);
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                docente.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return docente;
    }

    @Override
    public Docente findDocenteByCedula(String cedula) {
        Docente docente = new Docente();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT d.id_docente, d.cedula, d.nombres, d.apellidos, d.telefono, d.correo, g.id_genero, g.descripcion as des_genero, f.descripcion as des_facultad, e.nombre_escuela, carrera.id_carrera, carrera.descripcion as des_carrera, td.id_tipo_dedicacion,td.descripcion as des_tipo_dedicacion, d.estado as estado_docente from docente d, carreras carrera, escuela e, facultad f, genero g, tipo_dedicacion td where e.id_facultad = f.id_facultad and carrera.id_escuela = e.id_escuela and d.id_genero = g.id_genero and d.id_carrera = carrera.id_carrera and td.id_tipo_dedicacion = d.id_tipo_dedicacion and cedula = ?");
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                Escuela escuela = new Escuela();
                Facultad facultad = new Facultad();
                
                docente.setID_DOCENTE(rs.getInt("id_docente"));
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                
                genero.setID_GENERO(rs.getInt("id_genero"));
                genero.setDESCRIPCION(rs.getString("des_genero"));
                docente.setGenero(genero);
                
                carrera.setID_CARRERA(rs.getInt("id_carrera"));
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                facultad.setDESCRIPCION(rs.getString("des_facultad"));
                escuela.setFacultad(facultad);
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                carrera.setEscuela(escuela);
                docente.setCarrera(carrera);
                
                td.setID_TIPO_DEDICACION(rs.getInt("id_tipo_dedicacion"));
                td.setDESCRIPCION(rs.getString("des_tipo_dedicacion"));
                docente.setTipoDedicacion(td);
                
                docente.setESTADO(rs.getString("estado_docente"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return docente;
    }

    @Override
    public Docente findDocenteByLastNameAndName(String name, String lastName) {
        Docente docente = new Docente();
        try {
            PreparedStatement ps = con.prepareStatement("select * from docente where nombres = ? and apellidos = ?");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                docente.setID_DOCENTE(rs.getInt("id_docente"));
                
                genero.setID_GENERO(rs.getInt("id_genero"));
                docente.setGenero(genero);
                
                carrera.setID_CARRERA(rs.getInt("id_carrera"));
                docente.setCarrera(carrera);
                
                td.setID_TIPO_DEDICACION(rs.getInt("id_tipo_dedicacion"));
                docente.setTipoDedicacion(td);
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                docente.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return docente;
    }
}
