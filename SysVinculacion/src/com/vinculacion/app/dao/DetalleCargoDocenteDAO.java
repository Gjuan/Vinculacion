package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DetalleCargoDocenteDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.DetalleCargoDocente;
import com.vinculacion.app.model.Docente;
import java.util.List;

/**
 *
 * @author jorge
 */
public class DetalleCargoDocenteDAO extends FactorFactory implements DetalleCargoDocenteDaoInterface{

    public DetalleCargoDocenteDAO() {
        super();
    }
    
    @Override
    public void saveDetalleCargoDocente(DetalleCargoDocente dcd) {
    
    }

    @Override
    public List<DetalleCargoDocente> AllDetalleCargoDocente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDetalleCargoDocente(DetalleCargoDocente dcd) {
    
    }

    @Override
    public void deleteDetalleCargoDocente(int id) {
    
    }

    @Override
    public DetalleCargoDocente findDetalleCargoDocenteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleCargoDocente> findDetalleCargoDocenteByCedula(Docente docente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
