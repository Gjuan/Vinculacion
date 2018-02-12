package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.InformePasantiasDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Estudiantes;
import com.vinculacion.app.model.InformePasantias;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class InformePasantiasDAO extends FactorFactory implements InformePasantiasDaoInterface{

    public InformePasantiasDAO() {
        super();
    }

    @Override
    public void saveInformePasantias(InformePasantias informe) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(informe);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<InformePasantias> AllInformes() {
        EntityManager manager = emf.createEntityManager();        
        List<InformePasantias> linforme = (List<InformePasantias>) manager.createQuery("FROM InformePasantias where ESTADO = 'ACTIVO' order by ID_PASANTIAS_ESTUDIANTES desc")
                .getResultList();
        manager.close();
        return linforme;
    }

    @Override
    public void updateInformPasantia(InformePasantias informe) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(informe);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void deleteInformePasantia(int id) {
        InformePasantias informe = findInformePasantiaById(id);
        if (informe != null) {
            informe.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(informe);
            manager.getTransaction().commit();
            manager.close();
        }
    }

    @Override
    public List<InformePasantias> findInformeByCedulaEstudiante(String cedulaEstudiante) {
        EstudiantesDAO estdao = new EstudiantesDAO();
        Estudiantes est = estdao.findEstudianteByCedula(cedulaEstudiante);
        
        EntityManager manager = emf.createEntityManager();        
        List<InformePasantias> linforme = (List<InformePasantias>) manager.createQuery("FROM InformePasantias WHERE estudiantes = :est AND ESTADO = 'ACTIVO' order by ID_PASANTIAS_ESTUDIANTES desc")
                .setParameter("est", est)
                .getResultList();
        manager.close();
        return linforme;
    }

    @Override
    public List<InformePasantias> findInformesByFechaEntrega(String fecha_entrega) {
        EntityManager manager = emf.createEntityManager();        
        List<InformePasantias> linforme = (List<InformePasantias>) manager.createQuery("FROM InformePasantias WHERE FECHA_ENTREGA_INFORME = :fentr AND ESTADO = 'ACTIVO' order by ID_PASANTIAS_ESTUDIANTES desc")
                .setParameter("fentr", fecha_entrega)
                .getResultList();
        manager.close();
        return linforme;
    }

    @Override
    public InformePasantias findInformePasantiaById(int id) {
        EntityManager manager = emf.createEntityManager();        
        InformePasantias informe = manager.find(InformePasantias.class, id);
        manager.close();
        return informe;
    }
        
}
