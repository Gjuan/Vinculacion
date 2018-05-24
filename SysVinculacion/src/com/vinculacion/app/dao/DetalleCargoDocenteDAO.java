package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DetalleCargoDocenteDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Cargo;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.DetalleCargoDocente;
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
/**
 *
 * @author jorge
 */
public class DetalleCargoDocenteDAO extends Config implements DetalleCargoDocenteDaoInterface{

    public DetalleCargoDocenteDAO() {
        super();
    }
    
    @Override
    public void saveDetalleCargoDocente(DetalleCargoDocente dcd) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into detalle_cargo_docente (id_cargo, id_docente, estado) values(?,?,?)");
            ps.setInt(1, dcd.getCargos().getID_CARGO());
            ps.setInt(2, dcd.getDocentes().getID_DOCENTE());
            ps.setString(3, dcd.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<DetalleCargoDocente> AllDetalleCargoDocente() {
        List<DetalleCargoDocente> ldcd = new ArrayList<DetalleCargoDocente>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dcd.id_detalle_cargo_docente, d.cedula, d.nombres, d.apellidos, d.telefono, d.correo, dcd.estado as estado_detalle_cargo, g.descripcion as genero_docente, carrera.descripcion as des_carrera, es.nombre_escuela, f.descripcion as des_facultad, td.descripcion as des_tipo_dedicacion, c.descripcion as des_cargo FROM detalle_cargo_docente dcd, cargo c, docente d, genero g, carreras carrera, escuela es, facultad f, tipo_dedicacion td WHERE dcd.estado = 'ACTIVO' and dcd.id_cargo = c.id_cargo and dcd.id_docente = d.id_docente and d.id_genero = g.id_genero and d.id_carrera = carrera.id_carrera and d.id_tipo_dedicacion = td.id_tipo_dedicacion and carrera.id_escuela = es.id_escuela and es.id_facultad = f.id_facultad ORDER BY dcd.id_detalle_cargo_docente desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DetalleCargoDocente dcd = new DetalleCargoDocente();
                Cargo cargo = new Cargo();
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                Escuela escuela = new Escuela();
                Facultad facultad = new Facultad();
                Docente docente = new Docente();
                
                dcd.setID_DETALLE_CARGO_DOCENTE(rs.getInt("id_detalle_cargo_docente"));
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                
                dcd.setESTADO(rs.getString("estado_detalle_cargo"));
                
                genero.setDESCRIPCION(rs.getString("genero_docente"));
                docente.setGenero(genero);
                
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                
                facultad.setDESCRIPCION(rs.getString("des_facultad"));
                escuela.setFacultad(facultad);
                
                carrera.setEscuela(escuela);
                
                docente.setCarrera(carrera);               
                
                td.setDESCRIPCION(rs.getString("des_tipo_dedicacion"));
                docente.setTipoDedicacion(td);
                                
                dcd.setDocentes(docente);
                cargo.setDESCRIPCION(rs.getString("des_cargo"));                
                dcd.setCargos(cargo);
                ldcd.add(dcd);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ldcd;
    }

    @Override
    public void updateDetalleCargoDocente(DetalleCargoDocente dcd) {
        try {
            PreparedStatement ps = con.prepareStatement("update detalle_cargo_docente set id_cargo = ?, id_docente = ?, estado = ? where id_detalle_cargo_docente = ?");
            ps.setInt(1, dcd.getCargos().getID_CARGO());
            ps.setInt(2, dcd.getDocentes().getID_DOCENTE());
            ps.setString(3, dcd.getESTADO());
            ps.setInt(4, dcd.getID_DETALLE_CARGO_DOCENTE());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteDetalleCargoDocente(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from detalle_cargo_docente where id_detalle_cargo_docente = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }      
    }

    @Override
    public DetalleCargoDocente findDetalleCargoDocenteById(int id) {
        DetalleCargoDocente dcd = new DetalleCargoDocente();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dcd.id_detalle_cargo_docente, d.cedula, d.nombres, d.apellidos, d.telefono, d.correo, dcd.estado as estado_detalle_cargo, g.descripcion as genero_docente, carrera.descripcion as des_carrera, es.nombre_escuela, f.descripcion as des_facultad, td.descripcion as des_tipo_dedicacion, c.descripcion as des_cargo FROM detalle_cargo_docente dcd, cargo c, docente d, genero g, carreras carrera, escuela es, facultad f, tipo_dedicacion td WHERE dcd.estado = 'ACTIVO' and dcd.id_cargo = c.id_cargo and dcd.id_docente = d.id_docente and d.id_genero = g.id_genero and d.id_carrera = carrera.id_carrera and d.id_tipo_dedicacion = td.id_tipo_dedicacion and carrera.id_escuela = es.id_escuela and es.id_facultad = f.id_facultad and dcd.id_detalle_cargo_docente = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Cargo cargo = new Cargo();
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                Escuela escuela = new Escuela();
                Facultad facultad = new Facultad();
                Docente docente = new Docente();
                
                dcd.setID_DETALLE_CARGO_DOCENTE(rs.getInt("id_detalle_cargo_docente"));
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                
                dcd.setESTADO(rs.getString("estado_detalle_cargo"));
                
                genero.setDESCRIPCION(rs.getString("genero_docente"));
                docente.setGenero(genero);
                
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                
                facultad.setDESCRIPCION(rs.getString("des_facultad"));
                escuela.setFacultad(facultad);
                
                carrera.setEscuela(escuela);
                
                docente.setCarrera(carrera);               
                
                td.setDESCRIPCION(rs.getString("des_tipo_dedicacion"));
                docente.setTipoDedicacion(td);
                                
                dcd.setDocentes(docente);
                cargo.setDESCRIPCION(rs.getString("des_cargo"));                
                dcd.setCargos(cargo);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return dcd;
    }

    @Override
    public List<DetalleCargoDocente> findDetalleCargoDocenteByCedula(String cedulaDocente) {
        List<DetalleCargoDocente> ldcd = new ArrayList<DetalleCargoDocente>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dcd.id_detalle_cargo_docente, d.cedula, d.nombres, d.apellidos, d.telefono, d.correo, dcd.estado as estado_detalle_cargo, g.descripcion as genero_docente, carrera.descripcion as des_carrera, es.nombre_escuela, f.descripcion as des_facultad, td.descripcion as des_tipo_dedicacion, c.descripcion as des_cargo FROM detalle_cargo_docente dcd, cargo c, docente d, genero g, carreras carrera, escuela es, facultad f, tipo_dedicacion td WHERE dcd.estado = 'ACTIVO' and dcd.id_cargo = c.id_cargo and dcd.id_docente = d.id_docente and d.id_genero = g.id_genero and d.id_carrera = carrera.id_carrera and d.id_tipo_dedicacion = td.id_tipo_dedicacion and carrera.id_escuela = es.id_escuela and es.id_facultad = f.id_facultad and d.cedula = ?;");
            ps.setString(1, cedulaDocente);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DetalleCargoDocente dcd = new DetalleCargoDocente();
                Cargo cargo = new Cargo();
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                Escuela escuela = new Escuela();
                Facultad facultad = new Facultad();
                Docente docente = new Docente();
                
                dcd.setID_DETALLE_CARGO_DOCENTE(rs.getInt("id_detalle_cargo_docente"));
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                
                dcd.setESTADO(rs.getString("estado_detalle_cargo"));
                
                genero.setDESCRIPCION(rs.getString("genero_docente"));
                docente.setGenero(genero);
                
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                
                escuela.setNOMBRE_ESCUELA(rs.getString("nombre_escuela"));
                
                facultad.setDESCRIPCION(rs.getString("des_facultad"));
                escuela.setFacultad(facultad);
                
                carrera.setEscuela(escuela);
                
                docente.setCarrera(carrera);               
                
                td.setDESCRIPCION(rs.getString("des_tipo_dedicacion"));
                docente.setTipoDedicacion(td);
                                
                dcd.setDocentes(docente);
                cargo.setDESCRIPCION(rs.getString("des_cargo"));                
                dcd.setCargos(cargo);
                ldcd.add(dcd);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ldcd;
    }
    
}
