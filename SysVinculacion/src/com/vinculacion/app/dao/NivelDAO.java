package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.NivelDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Nivel;
import java.util.List;

/**
 *
 * @author jorge
 */
public class NivelDAO extends FactorFactory implements NivelDaoInterface{

    public NivelDAO() {
        super();
    }    
    
    @Override
    public void saveNivel(Nivel nivel) {
    
    }

    @Override
    public List<Nivel> AllNiveles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateNivel(Nivel nivel) {
    
    }

    @Override
    public void deleteNivel(int id) {
    
    }

    @Override
    public Nivel findNivelById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Nivel findNivelBySemestre(String semestre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
