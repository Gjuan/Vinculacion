package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.DocentesDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.TipoDedicacion;
import java.util.List;

public class DocenteDAO extends FactorFactory implements DocentesDaoInterface{

    public DocenteDAO() {
        super();
    }

    @Override
    public void saveDocente(Docente docente) {
    
    }

    @Override
    public List<Docente> AllDocente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDocente(Docente docente) {
    
    }

    @Override
    public void deleteDocente(int id) {
    
    }

    @Override
    public Docente findDocenteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Docente findDocenteByCedula(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Docente> findDocenteByTipoDedicacion(TipoDedicacion tipoDedicacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Docente> findDocenteByCarrera(Carreras carrera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
