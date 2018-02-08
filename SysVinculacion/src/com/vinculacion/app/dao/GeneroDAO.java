package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.GeneroDaoInterface;
import com.vinculacion.app.Persistence.FactorFactory;
import com.vinculacion.app.model.Genero;
import java.util.List;
/**
 *
 * @author jorge
 */
public class GeneroDAO extends FactorFactory implements GeneroDaoInterface{

    public GeneroDAO() {
        super();
    }

    @Override
    public void saveGenero(Genero genero) {
    
    }

    @Override
    public List<Genero> AllGeneros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateGenero(Genero genero) {
    
    }

    @Override
    public void deleteGenero(int id) {
    
    }

    @Override
    public Genero findGeneroById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Genero findGeneroByDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
