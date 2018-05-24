package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DetalleDocenteAsignaturaDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Asignaturas;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.DetalleDocenteAsignatura;
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
public class DetalleDocenteAsignaturaDAO extends Config implements DetalleDocenteAsignaturaDaoInterface{

    public DetalleDocenteAsignaturaDAO() {
        super();
    }
        
    @Override
    public void saveDetalleDocenteAsignatura(DetalleDocenteAsignatura dda) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into detalle_docente_asignatura (id_docente, id_asignatura, estado) values(?,?,?)");
            ps.setInt(1, dda.getDocentes().getID_DOCENTE());
            ps.setInt(2, dda.getAsignaturas().getID_ASIGNATURA());
            ps.setString(3, dda.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<DetalleDocenteAsignatura> AllDetalleDocenteAsignatura() {
        List<DetalleDocenteAsignatura> ldda = new ArrayList<DetalleDocenteAsignatura>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dda.id_detalle_docente_asig, d.cedula, d.nombres, d.apellidos, d.telefono, d.correo, dda.estado as estado_detalle_asig, g.descripcion as genero_docente, carrera.descripcion as des_carrera, es.nombre_escuela, f.descripcion as des_facultad, td.descripcion as des_tipo_dedicacion, a.nombre_asignatura from detalle_docente_asignatura dda, asignaturas a, docente d, genero g, carreras carrera, escuela es, facultad f, tipo_dedicacion td where dda.estado = 'ACTIVO' and dda.id_asignatura = a.id_asignatura and dda.id_docente = d.id_docente and d.id_genero = g.id_genero and d.id_carrera = carrera.id_carrera and d.id_tipo_dedicacion = td.id_tipo_dedicacion and carrera.id_escuela = es.id_escuela and es.id_facultad = f.id_facultad order by dda.id_detalle_docente_asig desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DetalleDocenteAsignatura dda = new DetalleDocenteAsignatura();
                Asignaturas asig = new Asignaturas();
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                Escuela escuela = new Escuela();
                Facultad facultad = new Facultad();
                Docente docente = new Docente();
                
                dda.setID_DETALLE_DOCENTE_ASIG(rs.getInt("id_detalle_docente_asig"));
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                
                dda.setESTADO(rs.getString("estado_detalle_asig"));
                
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
                                
                dda.setDocentes(docente);
                asig.setNOMBRE_ASIGNATURA(rs.getString("nombre_asignatura"));
                dda.setAsignaturas(asig);
                ldda.add(dda);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ldda;
    }

    @Override
    public void updateDetalleDocenteAsignatura(DetalleDocenteAsignatura dda) {
        try {
            PreparedStatement ps = con.prepareStatement("update detalle_docente_asignatura set id_docente = ?, id_asignatura = ?, estado = ? where id_detalle_docente_asig = ?");
            ps.setInt(1, dda.getDocentes().getID_DOCENTE());
            ps.setInt(2, dda.getAsignaturas().getID_ASIGNATURA());
            ps.setString(3, dda.getESTADO());
            ps.setInt(4, dda.getID_DETALLE_DOCENTE_ASIG());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteDetalleDocenteAsignatura(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from detalle_docente_asignatura where id_detalle_docente_asig = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }      
    }

    @Override
    public List<DetalleDocenteAsignatura> findDetalleDocenteAsignaturaByCedula(String cedulaDocente) {
        List<DetalleDocenteAsignatura> ldda = new ArrayList<DetalleDocenteAsignatura>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dda.id_detalle_docente_asig, d.cedula, d.nombres, d.apellidos, d.telefono, d.correo, dda.estado as estado_detalle_asig, g.descripcion as genero_docente, carrera.descripcion as des_carrera, es.nombre_escuela, f.descripcion as des_facultad, td.descripcion as des_tipo_dedicacion, a.nombre_asignatura from detalle_docente_asignatura dda, asignaturas a, docente d, genero g, carreras carrera, escuela es, facultad f, tipo_dedicacion td where dda.estado = 'ACTIVO' and dda.id_asignatura = a.id_asignatura and dda.id_docente = d.id_docente and d.id_genero = g.id_genero and d.id_carrera = carrera.id_carrera and d.id_tipo_dedicacion = td.id_tipo_dedicacion and carrera.id_escuela = es.id_escuela and es.id_facultad = f.id_facultad and d.cedula = ? order by dda.id_detalle_docente_asig desc;");
            ps.setString(1, cedulaDocente);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DetalleDocenteAsignatura dda = new DetalleDocenteAsignatura();
                Asignaturas asig = new Asignaturas();
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                Escuela escuela = new Escuela();
                Facultad facultad = new Facultad();
                Docente docente = new Docente();
                
                dda.setID_DETALLE_DOCENTE_ASIG(rs.getInt("id_detalle_docente_asig"));
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                
                dda.setESTADO(rs.getString("estado_detalle_asig"));
                
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
                                
                dda.setDocentes(docente);
                asig.setNOMBRE_ASIGNATURA(rs.getString("nombre_asignatura"));
                dda.setAsignaturas(asig);
                ldda.add(dda);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ldda;
    }

    @Override
    public DetalleDocenteAsignatura findDetalleDocenteAsignaturaById(int id) {
        DetalleDocenteAsignatura dda = new DetalleDocenteAsignatura();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dda.id_detalle_docente_asig, d.cedula, d.nombres, d.apellidos, d.telefono, d.correo, dda.estado as estado_detalle_asig, g.descripcion as genero_docente, carrera.descripcion as des_carrera, es.nombre_escuela, f.descripcion as des_facultad, td.descripcion as des_tipo_dedicacion, a.nombre_asignatura from detalle_docente_asignatura dda, asignaturas a, docente d, genero g, carreras carrera, escuela es, facultad f, tipo_dedicacion td where dda.estado = 'ACTIVO' and dda.id_asignatura = a.id_asignatura and dda.id_docente = d.id_docente and d.id_genero = g.id_genero and d.id_carrera = carrera.id_carrera and d.id_tipo_dedicacion = td.id_tipo_dedicacion and carrera.id_escuela = es.id_escuela and es.id_facultad = f.id_facultad and dda.id_detalle_docente_asig = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Asignaturas asig = new Asignaturas();
                Genero genero = new Genero();
                TipoDedicacion td = new TipoDedicacion();
                Carreras carrera = new Carreras();
                Escuela escuela = new Escuela();
                Facultad facultad = new Facultad();
                Docente docente = new Docente();
                
                dda.setID_DETALLE_DOCENTE_ASIG(rs.getInt("id_detalle_docente_asig"));
                
                docente.setCEDULA(rs.getString("cedula"));
                docente.setNOMBRES(rs.getString("nombres"));
                docente.setAPELLIDOS(rs.getString("apellidos"));
                docente.setTELEFONO(rs.getString("telefono"));
                docente.setCORREO(rs.getString("correo"));
                
                dda.setESTADO(rs.getString("estado_detalle_asig"));
                
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
                                
                dda.setDocentes(docente);
                asig.setNOMBRE_ASIGNATURA(rs.getString("nombre_asignatura"));
                dda.setAsignaturas(asig);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return dda;
    }
    
}
