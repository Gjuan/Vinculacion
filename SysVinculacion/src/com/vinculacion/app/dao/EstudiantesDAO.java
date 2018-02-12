package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.EstudiantesDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.Empleados;
import com.vinculacion.app.model.Estudiantes;
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
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(estudiante);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Estudiantes> AllEstudiantes() {
        EntityManager manager = emf.createEntityManager();        
        List<Estudiantes> lest = (List<Estudiantes>)manager.createQuery("FROM Estudiantes order by CODIGO desc")
                .getResultList();
        manager.close();
        return lest;
    }

    @Override
    public void updateEstudiante(Estudiantes estudiante) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(estudiante);
        manager.getTransaction().commit();
        manager.close();
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
    
   /* public static void main(String[] args) {
        EstudiantesDAO edao = new EstudiantesDAO();
        CarrerasDAO cdao = new CarrerasDAO();
        DocenteDAO ddao = new DocenteDAO();
        EmpleadosDAO empldao = new EmpleadosDAO();
        
        Estudiantes e = new Estudiantes();
        e.setAPELLIDOS("VELEZ");
        e.setNOMBRES("JOEL");
        e.setCEDULA("1208912788");
        e.setCOD_MATRICULA("MAT-00043");
        e.setCORREO("jvlz@outlook.es");
        
        Carreras carrera = cdao.findCarreraByDescription("INGENIERÍA EN SISTEMAS");
        e.setCarrera(carrera);
        
        e.setDIRECCION("Babahoyo");
        
        Docente docente = ddao.findDocenteByLastNameAndName("NARCISA", "CRESPO TORRES");
        e.setDocente(docente);
        
        e.setESTADO("ACTIVO");
        
        Empleados empleado = empldao.findEmpleadosByLastNameAndName("HARRY", "SALTOS");
        e.setEmpleado(empleado);
        
        e.setFOTO("joelvlz.jpg");

    }*/
}
