package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DetalleDocenteAsignaturaDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.DetalleDocenteAsignatura;
import com.vinculacion.app.model.Docente;
import java.util.List;

/**
 *
 * @author jorge
 */
public class DetalleDocenteAsignaturaDAO extends FactorFactory implements DetalleDocenteAsignaturaDaoInterface{

    public DetalleDocenteAsignaturaDAO() {
        super();
    }
        
    @Override
    public void saveDetalleDocenteAsignatura(DetalleDocenteAsignatura dda) {
    
    }

    @Override
    public List<DetalleDocenteAsignatura> AllDetalleDocenteAsignatura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDetalleDocenteAsignatura(DetalleDocenteAsignatura dda) {
    
    }

    @Override
    public void deleteDetalleDocenteAsignatura(int id) {
    
    }

    @Override
    public List<DetalleDocenteAsignatura> findDetalleDocenteAsignaturaByCedula(Docente docente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
