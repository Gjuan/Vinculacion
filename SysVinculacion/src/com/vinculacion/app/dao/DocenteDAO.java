package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DocentesDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.TipoDedicacion;
import java.util.List;
import javax.persistence.EntityManager;

public class DocenteDAO extends FactorFactory implements DocentesDaoInterface{

    public DocenteDAO() {
        super();
    }

    @Override
    public void saveDocente(Docente docente) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(docente);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Docente> AllDocente() {
        EntityManager manager = emf.createEntityManager();        
        List<Docente> lcargo = (List<Docente>)manager.createQuery("FROM Docente WHERE ESTADO = 'ACTIVO' order by ID_DOCENTE desc")
                .getResultList();
        manager.close();
        return lcargo;
    }

    @Override
    public void updateDocente(Docente docente) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(docente);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteDocente(int id) {
        Docente docente = findDocenteById(id);
        if (docente != null) {
            docente.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(docente);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public Docente findDocenteById(int id) {
        EntityManager manager = emf.createEntityManager();        
        Docente docente = manager.find(Docente.class, id);
        manager.close();
        return docente;
    }

    @Override
    public Docente findDocenteByCedula(String cedula) {
        EntityManager manager = emf.createEntityManager();        
        Docente docente = (Docente) manager.createQuery("FROM Docente WHERE ESTADO = 'ACTIVO' and CEDULA = :ced")
                .setParameter("ced", cedula)
                .getSingleResult();
        manager.close();
        return docente;    
    }

    @Override
    public List<Docente> findDocenteByTipoDedicacion(String descripcionTipoDedicacion) {
        EntityManager manager = emf.createEntityManager();
        TipoDedicacionDAO tipoddao = new TipoDedicacionDAO();
        TipoDedicacion td = tipoddao.findTipoDedicacionByDescription(descripcionTipoDedicacion);
        List<Docente> ldocente = manager.createQuery("FROM Docente WHERE ESTADO = 'ACTIVO' and tipoDedicacion = :td order by ID_DOCENTE desc")
                .setParameter("td", td)
                .getResultList();
        manager.close();
        return ldocente;
    }

    @Override
    public List<Docente> findDocenteByCarrera(String descripcionCarrera) {
        EntityManager manager = emf.createEntityManager();
        CarrerasDAO carreradao = new CarrerasDAO();
        Carreras c = carreradao.findCarreraByDescription(descripcionCarrera);
        List<Docente> ldocente = manager.createQuery("FROM Docente WHERE ESTADO = 'ACTIVO' and carrera = :c order by ID_DOCENTE desc")
                .setParameter("c", c)
                .getResultList();
        manager.close();
        return ldocente;
    }  

    @Override
    public Docente findDocenteByLastNameAndName(String name, String lastName) {
        EntityManager manager = emf.createEntityManager();        
        Docente docente = (Docente) manager.createQuery("FROM Docente WHERE ESTADO = 'ACTIVO' and APELLIDOS = :lastname and NOMBRES = :name")
                .setParameter("name", name)
                .setParameter("lastname", lastName)
                .getSingleResult();
        manager.close();
        return docente;   
    }
}
