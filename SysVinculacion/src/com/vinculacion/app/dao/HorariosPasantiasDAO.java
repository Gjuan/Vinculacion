package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.HorarioPasantiasDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.HorarioPasantias;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class HorariosPasantiasDAO extends FactorFactory implements HorarioPasantiasDaoInterface {

    public HorariosPasantiasDAO() {
        super();
    }
        
    @Override
    public void saveHorarioPasantias(HorarioPasantias horario) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(horario);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<HorarioPasantias> AllHorariosPasantias() {
        EntityManager manager = emf.createEntityManager();        
        List<HorarioPasantias> lhorarios = (List<HorarioPasantias>)manager.createQuery("FROM HorarioPasantias WHERE order by ID_HORARIO_PASANTIAS desc").getResultList();
        manager.close();
        return lhorarios;
    }

    @Override
    public void deleteHorariosPasantias(int id) {
    }

    @Override
    public void updateHorarioPasantia(HorarioPasantias horario) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(horario);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public HorarioPasantias findHorarioPasantiaById(int id) {
        EntityManager manager = emf.createEntityManager();
        HorarioPasantias horario = manager.find(HorarioPasantias.class, id);
        manager.close();
        return horario;
    }

    @Override
    public HorarioPasantias findHorarioPasantiaByDescription(String description) {
        EntityManager manager = emf.createEntityManager();
        HorarioPasantias horario = (HorarioPasantias)  manager.createQuery("From HorarioPasantias where DESCRIPCION_HORARIO = :des")
                .setParameter("des", description).getSingleResult();
        manager.close();
        return horario;
    }
    
}
