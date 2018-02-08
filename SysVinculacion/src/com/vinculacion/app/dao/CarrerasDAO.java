package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.CarrerasDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Carreras;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jorge
 */
public class CarrerasDAO extends FactorFactory implements CarrerasDaoInterface{

    public CarrerasDAO() {
        super();
    }

    @Override
    public void saveCarreras(Carreras carrera) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(carrera);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Carreras> AllCarreras() {
        EntityManager manager = emf.createEntityManager();        
        List<Carreras> lcarreras = (List<Carreras>)manager.createQuery("FROM Carreras WHERE ESTADO = 'ACTIVO' order by ID_CARRERA desc").getResultList();
        manager.close();
        return lcarreras;
    }

    @Override
    public void deleteCarreraById(int id) {
        Carreras carrera = findCarreraById(id);
        if (carrera != null) {
            carrera.setESTADO("INACTIVO");
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(carrera);
            manager.getTransaction().commit();
            manager.close();
        }        
    }

    @Override
    public void updateCarrera(Carreras carrera) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(carrera);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Carreras findCarreraById(int id) {
        EntityManager manager = emf.createEntityManager();
        Carreras carrera = manager.find(Carreras.class, id);
        manager.close();
        return carrera;
    }

    @Override
    public Carreras findCarreraByDescription(String descripcion) {
        EntityManager manager = emf.createEntityManager();
        Carreras carrera = (Carreras)  manager.createQuery("From Carreras where DESCRIPCION = :des and ESTADO = 'ACTIVO'")
                .setParameter("des", descripcion).getSingleResult();
        manager.close();
        return carrera;
    }
   /* public static void main(String[] args) {
        CarrerasDAO c = new CarrerasDAO();
        List<Carreras> le = c.AllCarreras();
        for (Carreras carrera : le) {
            if (carrera.getEscuela().getESTADO().equals("ACTIVO") && carrera.getEscuela().getFacultad().getESTADO().equals("ACTIVO")) {
                System.out.println("-------------------------------");
                System.out.println("ID: " + carrera.getID_CARRERA());
                System.out.println("Carrera: "+carrera.getDESCRIPCION());
            }           
        }
    }*/
}
