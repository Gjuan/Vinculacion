package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EstudiantesDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.Empleados;
import com.vinculacion.app.model.Estudiantes;
import com.vinculacion.app.model.Genero;
import com.vinculacion.app.model.HorarioPasantias;
import com.vinculacion.app.model.Nivel;
import com.vinculacion.app.model.PeriodoAcademico;
import com.vinculacion.app.model.Seccion;
import com.vinculacion.app.model.TipoDocumentoPracticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class EstudiantesDAO extends Config implements EstudiantesDaoInterface{

    public EstudiantesDAO() {
        super();
    }

    @Override
    public void saveEstudiante(Estudiantes estudiante) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO estudiantes(id_carrera, id_nivel, id_genero, id_periodo_academico, id_seccion, id_tutor_docente, id_tutor_empresarial, id_usuario, id_tipo_documento_practicas, id_horario_pasantias, cod_matricula, nombres, apellidos, cedula, direccion, telefono, correo, foto, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setInt(1, estudiante.getCarrera().getID_CARRERA());
            ps.setInt(2, estudiante.getNivel().getID_NIVEL());
            ps.setInt(3, estudiante.getGenero().getID_GENERO());
            ps.setInt(4, estudiante.getPeriodoAcademico().getID_PERIODO_ACADEMICO());
            ps.setInt(5, estudiante.getSeccion().getID_SECCION());
            ps.setInt(6, estudiante.getDocente().getID_DOCENTE());
            ps.setInt(7, estudiante.getEmpleado().getID_EMPLEADO());
            ps.setInt(8, estudiante.getUsuario().getId_usuario());
            ps.setInt(9, estudiante.getTipoDocumentoPracticas().getId_tipo_documento_practicas());
            ps.setInt(10, estudiante.getHorarioPasantias().getID_HORARIO_PASANTIAS());
            ps.setString(11, estudiante.getCOD_MATRICULA());
            ps.setString(12, estudiante.getNOMBRES());
            ps.setString(13, estudiante.getAPELLIDOS());
            ps.setString(14, estudiante.getCEDULA());
            ps.setString(15, estudiante.getDIRECCION());
            ps.setString(16, estudiante.getTELEFONO());
            ps.setString(17, estudiante.getCORREO());
            ps.setBytes(18, estudiante.getFOTO());
            ps.setString(19, estudiante.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Estudiantes> AllEstudiantes() {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias ORDER BY est.codigo desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public void updateEstudiante(Estudiantes estudiante) {
        try {
            PreparedStatement ps = con.prepareStatement("update estudiantes set id_carrera = ?, id_nivel = ?, id_genero = ?, id_periodo_academico = ?, id_seccion = ?, id_tutor_docente = ?, id_tutor_empresarial = ?, id_usuario = ?, id_tipo_documento_practicas = ?, id_horario_pasantias = ?, cod_matricula = ?, nombres = ?, apellidos = ?, cedula = ?, direccion = ?, telefono = ?, correo = ?, foto = ?, estado = ? where codigo = ?;");
            ps.setInt(1, estudiante.getCarrera().getID_CARRERA());
            ps.setInt(2, estudiante.getNivel().getID_NIVEL());
            ps.setInt(3, estudiante.getGenero().getID_GENERO());
            ps.setInt(4, estudiante.getPeriodoAcademico().getID_PERIODO_ACADEMICO());
            ps.setInt(5, estudiante.getSeccion().getID_SECCION());
            ps.setInt(6, estudiante.getDocente().getID_DOCENTE());
            ps.setInt(7, estudiante.getEmpleado().getID_EMPLEADO());
            ps.setInt(8, estudiante.getUsuario().getId_usuario());
            ps.setInt(9, estudiante.getTipoDocumentoPracticas().getId_tipo_documento_practicas());
            ps.setInt(10, estudiante.getHorarioPasantias().getID_HORARIO_PASANTIAS());
            ps.setString(11, estudiante.getCOD_MATRICULA());
            ps.setString(12, estudiante.getNOMBRES());
            ps.setString(13, estudiante.getAPELLIDOS());
            ps.setString(14, estudiante.getCEDULA());
            ps.setString(15, estudiante.getDIRECCION());
            ps.setString(16, estudiante.getTELEFONO());
            ps.setString(17, estudiante.getCORREO());
            ps.setBytes(18, estudiante.getFOTO());
            ps.setString(19, estudiante.getESTADO());
            ps.setInt(20, estudiante.getCODIGO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }       
    }

    @Override
    public void deleteEstudiante(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from estudiantes where codigo = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }       
    }

    @Override
    public Estudiantes findEstudianteByCedula(String cedula) {
        Estudiantes estudiante = new Estudiantes();
        try {
            PreparedStatement ps = con.prepareStatement("select est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, est.foto, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.cedula as cedula_docente, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.cedula as cedula_empleado, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est from estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp where est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and est.cedula = ?;");
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                estudiante.setFOTO(rs.getBytes("foto"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setCEDULA(rs.getString("cedula_docente"));
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setCEDULA(rs.getString("cedula_empleado"));
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return estudiante;
    }

    @Override
    public Estudiantes findEstudianteById(int id) {
        Estudiantes estudiante = new Estudiantes();
        try {
            PreparedStatement ps = con.prepareStatement("select est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est from estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp where est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and est.codigo = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return estudiante;
    }
   
    @Override
    public Estudiantes findEstudianteByNameAndLastName(String name, String lastName) {
        Estudiantes estudiante = new Estudiantes();
        try {
            PreparedStatement ps = con.prepareStatement("select est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est from estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp where est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and est.nombres = ? and est.apellidos = ?;");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return estudiante;
    }

    @Override
    public List<Estudiantes> EstudiantesByTutorDocente(String nameDocente, String lastNameDocente) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and d.nombres = ? and d.apellidos = ? order by est.codigo desc;");
            ps.setString(1, nameDocente);
            ps.setString(2, lastNameDocente);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTutorDocente(String nameDocente, String lastNameDocente, String fechaInicioPeriodo) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and d.nombres = ? and d.apellidos = ? and pa.fecha_inicio_periodo = ? order by est.codigo desc;");
            ps.setString(1, nameDocente);
            ps.setString(2, lastNameDocente);
            ps.setString(3, fechaInicioPeriodo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTutorEmpresarial(String nameTutorEmp, String lastNameTutorEmp) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and em.nombres = ? and em.apellidos = ? order by est.codigo desc;");
            ps.setString(1, nameTutorEmp);
            ps.setString(2, lastNameTutorEmp);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTutorEmpresarial(String nameTutorEmp, String lastNameTutorEmp, String fechaInicioPeriodo) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and em.nombres = ? and em.apellidos = ? and pa.fecha_inicio_periodo = ? order by est.codigo desc;");
            ps.setString(1, nameTutorEmp);
            ps.setString(2, lastNameTutorEmp);
            ps.setString(3, fechaInicioPeriodo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByCarrera(String descripcionCarrera) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and carrera.descripcion = ? order by est.codigo desc;");
            ps.setString(1, descripcionCarrera);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByCarrera(String descripcionCarrera, String fechaInicioPeriodo) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and carrera.descripcion = ? and pa.fecha_inicio_periodo = ? order by est.codigo desc;");
            ps.setString(1, descripcionCarrera);
            ps.setString(2, fechaInicioPeriodo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTipoDocPracticas(String descripcionTCP) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and tdp.descripcion = ? order by est.codigo desc;");
            ps.setString(1, descripcionTCP);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTipoDocPracticas(String descripcionTCP, String fechaInicioPeriodo) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and tdp.descripcion = ? and pa.fecha_inicio_periodo = ? order by est.codigo desc;");
            ps.setString(1, descripcionTCP);
            ps.setString(2, fechaInicioPeriodo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByPeriodoAcadémico(String fechaInicioPeriodo) {
        List<Estudiantes> lest = new ArrayList<Estudiantes>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT est.codigo as cod_est, est.cedula as cedula_est, est.cod_matricula, est.nombres as nombre_est, est.apellidos as apellido_est, est.direccion as direccion_est, est.telefono as telefono_est, est.correo as correo_est, carrera.descripcion as des_carrera, n.semestre, g.descripcion as des_genero, pa.fecha_inicio_periodo, s.descripcion as des_seccion, d.nombres as nombre_docente, d.apellidos as apellido_docente, em.nombres as nombre_empleado, em.apellidos as apellido_empleado, tdp.descripcion as des_tipo_documento, hp.descripcion_horario, est.estado as estado_est FROM estudiantes est, carreras carrera, nivel n, genero g, periodo_academico pa, seccion s, docente d, empleados em, usuarios u, tipo_documento_practicas tdp, horario_pasantias hp WHERE est.estado = 'ACTIVO' and est.id_carrera = carrera.id_carrera and est.id_nivel = n.id_nivel and est.id_genero = g.id_genero and est.id_periodo_academico = pa.id_periodo_academico and est.id_seccion = s.id_seccion and est.id_tutor_docente = d.id_docente and est.id_tutor_empresarial = em.id_empleado and est.id_usuario = u.id_usuario and est.id_tipo_documento_practicas = tdp.id_tipo_documento_practicas and hp.id_horario_pasantias = est.id_horario_pasantias and pa.fecha_inicio_periodo = ? order by est.codigo desc;");
            ps.setString(1, fechaInicioPeriodo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCODIGO(rs.getInt("cod_est"));
                estudiante.setCEDULA(rs.getString("cedula_est"));
                estudiante.setCOD_MATRICULA(rs.getString("cod_matricula"));
                estudiante.setNOMBRES(rs.getString("nombre_est"));
                estudiante.setAPELLIDOS(rs.getString("apellido_est"));
                estudiante.setDIRECCION(rs.getString("direccion_est"));
                estudiante.setTELEFONO(rs.getString("telefono_est"));
                estudiante.setCORREO(rs.getString("correo_est"));
                
                Carreras carrera = new Carreras();
                carrera.setDESCRIPCION(rs.getString("des_carrera"));
                estudiante.setCarrera(carrera);
                
                Nivel nivel = new Nivel();
                nivel.setSEMESTRE(rs.getString("semestre"));
                estudiante.setNivel(nivel);
                
                Genero genero = new Genero();
                genero.setDESCRIPCION(rs.getString("des_genero"));
                estudiante.setGenero(genero);
                
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setFECHA_INICIO_PERIODO(rs.getString("fecha_inicio_periodo"));
                estudiante.setPeriodoAcademico(pa);
                
                Seccion seccion = new Seccion();
                seccion.setDESCRIPCION(rs.getString("des_seccion"));
                estudiante.setSeccion(seccion);
                
                Docente docente = new Docente();
                docente.setNOMBRES(rs.getString("nombre_docente"));
                docente.setAPELLIDOS(rs.getString("apellido_docente"));
                estudiante.setDocente(docente);
                
                Empleados empleado = new Empleados();
                empleado.setNOMBRES(rs.getString("nombre_empleado"));
                empleado.setAPELLIDOS(rs.getString("apellido_empleado"));
                estudiante.setEmpleado(empleado);
                
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setDescripcion(rs.getString("des_tipo_documento"));
                estudiante.setTipoDocumentoPracticas(tdp);
                
                HorarioPasantias hp = new HorarioPasantias();
                hp.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                estudiante.setHorarioPasantias(hp);
                
                estudiante.setESTADO(rs.getString("estado_est"));
                lest.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lest;
    }
}
