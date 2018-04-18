package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EstudiantesDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.Empleados;
import com.vinculacion.app.model.Estudiantes;
import com.vinculacion.app.model.PeriodoAcademico;
import com.vinculacion.app.model.TipoDocumentoPracticas;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class EstudiantesDAO extends FactorFactory implements EstudiantesDaoInterface{

    public EstudiantesDAO() {
        super();
    }

    @Override
    public void saveEstudiante(Estudiantes estudiante) {
        try {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.persist(estudiante);
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
        }           
    }

    @Override
    public List<Estudiantes> AllEstudiantes() {
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>)manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' order by CODIGO desc")
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public void updateEstudiante(Estudiantes estudiante) {
        try {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(estudiante);
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
        }       
    }

    @Override
    public void deleteEstudiante(String id) {
        Estudiantes estudiante = findEstudianteById(id);
        if (estudiante != null) {
            estudiante.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(estudiante);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Estudiantes findEstudianteByCedula(String cedula) {
        EntityManager manager = emf.createEntityManager();        
        Estudiantes est = (Estudiantes) manager.createQuery("FROM Estudiantes WHERE CEDULA = :cedula")
                .setParameter("cedula", cedula)
                .getSingleResult();
        manager.close();
        return est;
    }

    @Override
    public Estudiantes findEstudianteById(String id) {
        EntityManager manager = emf.createEntityManager();        
        Estudiantes estudiante = manager.find(Estudiantes.class, id);
        manager.close();
        return estudiante;
    }
   
    @Override
    public Estudiantes findEstudianteByNameAndLastName(String name, String lastName) {
        EntityManager manager = emf.createEntityManager();        
        Estudiantes est = (Estudiantes) manager.createQuery("FROM Estudiantes WHERE NOMBRES = :name and APELLIDOS = :lastName")
                .setParameter("name", name)
                .setParameter("lastName", lastName)
                .getSingleResult();
        manager.close();
        return est;
    }

    @Override
    public List<Estudiantes> EstudiantesByTutorDocente(String nameDocente, String lastNameDocente) {
        DocenteDAO ddao = new DocenteDAO();
        Docente docente = ddao.findDocenteByLastNameAndName(nameDocente, lastNameDocente);        
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and docente = :docente order by CODIGO desc")
                .setParameter("docente", docente)
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTutorDocente(String nameDocente, String lastNameDocente, String fechaInicioPeriodo) {
        DocenteDAO ddao = new DocenteDAO();
        Docente docente = ddao.findDocenteByLastNameAndName(nameDocente, lastNameDocente);        
        
        PeriodosAcademicosDAO padao = new PeriodosAcademicosDAO();
        PeriodoAcademico periodoAcademico = padao.findPeriodoAcademicoByStartDate(fechaInicioPeriodo);
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and docente = :docente and periodoAcademico = :periodoAcademico order by CODIGO desc")
                .setParameter("docente", docente)
                .setParameter("periodoAcademico", periodoAcademico)
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTutorEmpresarial(String nameTutorEmp, String lastNameTutorEmp) {
        EmpleadosDAO edao = new EmpleadosDAO();
        Empleados emp = edao.findEmpleadosByLastNameAndName(nameTutorEmp, lastNameTutorEmp);
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and empleado = :empleado order by CODIGO desc")
                .setParameter("empleado", emp)
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTutorEmpresarial(String nameTutorEmp, String lastNameTutorEmp, String fechaInicioPeriodo) {
        EmpleadosDAO edao = new EmpleadosDAO();
        Empleados emp = edao.findEmpleadosByLastNameAndName(nameTutorEmp, lastNameTutorEmp);
        
        PeriodosAcademicosDAO padao = new PeriodosAcademicosDAO();
        PeriodoAcademico periodoAcademico = padao.findPeriodoAcademicoByStartDate(fechaInicioPeriodo);
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and empleado = :empleado and periodoAcademico = :periodoAcademico order by CODIGO desc")
                .setParameter("empleado", emp)
                .setParameter("periodoAcademico", periodoAcademico)
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByCarrera(String descripcionCarrera) {
        CarrerasDAO cdao = new CarrerasDAO();
        Carreras carrera = cdao.findCarreraByDescription(descripcionCarrera);
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and carrera = :carrera order by CODIGO desc")
                .setParameter("carrera", carrera)
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByCarrera(String descripcionCarrera, String fechaInicioPeriodo) {
        CarrerasDAO cdao = new CarrerasDAO();
        Carreras carrera = cdao.findCarreraByDescription(descripcionCarrera);
        
        PeriodosAcademicosDAO padao = new PeriodosAcademicosDAO();
        PeriodoAcademico periodoAcademico = padao.findPeriodoAcademicoByStartDate(fechaInicioPeriodo);
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and carrera = :carrera and periodoAcademico = :periodoAcademico order by CODIGO desc")
                .setParameter("carrera", carrera)
                .setParameter("periodoAcademico", periodoAcademico)
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTipoDocPracticas(String descripcionTCP) {
        TipoDocumentoPracticasDAO tdpdao = new TipoDocumentoPracticasDAO();
        TipoDocumentoPracticas tipoDocumentoPracticas = tdpdao.findTipoDocumentoPracticasByDescription(descripcionTCP);
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and tipoDocumentoPracticas = :tipoDocumentoPracticas order by CODIGO desc")
                .setParameter("tipoDocumentoPracticas", tipoDocumentoPracticas)
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByTipoDocPracticas(String descripcionTCP, String fechaInicioPeriodo) {
        TipoDocumentoPracticasDAO tdpdao = new TipoDocumentoPracticasDAO();
        TipoDocumentoPracticas tipoDocumentoPracticas = tdpdao.findTipoDocumentoPracticasByDescription(descripcionTCP);
        
        PeriodosAcademicosDAO padao = new PeriodosAcademicosDAO();
        PeriodoAcademico periodoAcademico = padao.findPeriodoAcademicoByStartDate(fechaInicioPeriodo);
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and tipoDocumentoPracticas = :tipoDocumentoPracticas and periodoAcademico = :periodoAcademico order by CODIGO desc")
                .setParameter("tipoDocumentoPracticas", tipoDocumentoPracticas)
                .setParameter("periodoAcademico",periodoAcademico)
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public List<Estudiantes> EstudiantesByPeriodoAcadémico(String fechaInicioPeriodo) {
        PeriodosAcademicosDAO padao = new PeriodosAcademicosDAO();
        PeriodoAcademico periodo = padao.findPeriodoAcademicoByStartDate(fechaInicioPeriodo);
        
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>) manager.createQuery("FROM Estudiantes WHERE ESTADO = 'ACTIVO' and periodoAcademico = :periodoAcademico order by CODIGO desc")
                .setParameter("periodoAcademico", periodo)
                .getResultList();
        manager.close();
        return lest;
    }
}
