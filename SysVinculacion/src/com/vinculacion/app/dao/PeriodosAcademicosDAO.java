package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.PeriodosAcademicosDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.PeriodoAcademico;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class PeriodosAcademicosDAO extends FactorFactory implements PeriodosAcademicosDaoInterface{

    public PeriodosAcademicosDAO() {
        super();
    }
    
    @Override
    public void savePeriodosAcademicos(PeriodoAcademico periodo) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(periodo);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<PeriodoAcademico> AllPeriodosAcademicos() {
        EntityManager manager = emf.createEntityManager();        
        List<PeriodoAcademico> lperiodo = (List<PeriodoAcademico>)manager.createQuery("FROM PeriodoAcademico WHERE ESTADO = 'ACTIVO' order by ID_PERIODO_ACADEMICO desc")
                .getResultList();
        manager.close();
        return lperiodo;
    }

    @Override
    public void updatePeriodoAcademico(PeriodoAcademico periodo) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(periodo);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deletePeriodoAcademico(int id) {
        PeriodoAcademico periodo = findPeriodoAcademicoById(id);
        if (periodo != null) {
            periodo.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(periodo);
            manager.getTransaction().commit();
            manager.close();
        }        
    }

    @Override
    public List<PeriodoAcademico> findPeriodoAcademicoByName(String name) {
        EntityManager manager = emf.createEntityManager();        
        List<PeriodoAcademico> lperiodo = manager.createQuery("FROM PeriodoAcademico WHERE NOMBRE_PERIODO = :name AND ESTADO = 'ACTIVO' order by ID_PERIODO_ACADEMICO desc")
                .setParameter("name", name)
                .getResultList();
        manager.close();
        return lperiodo;
    }

    @Override
    public PeriodoAcademico findPeriodoAcademicoByStartDate(String fecha_inicio) {
        EntityManager manager = emf.createEntityManager();
        PeriodoAcademico periodo = (PeriodoAcademico) manager.createQuery("FROM PeriodoAcademico WHERE ESTADO = 'ACTIVO' and FECHA_INICIO_PERIODO = :finit")
                .setParameter("finit", fecha_inicio)
                .getSingleResult();
        manager.close();
        return periodo;
    }

    @Override
    public PeriodoAcademico findPeriodoAcademicoById(int id) {
        EntityManager manager = emf.createEntityManager();
        PeriodoAcademico periodo = manager.find(PeriodoAcademico.class, id);
        manager.close();
        return periodo;
    }
    
}
