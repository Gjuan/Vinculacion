package com.vinculacion.app.Interface;

import com.vinculacion.app.model.Genero;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface GeneroDaoInterface {
    
    void saveGenero(Genero genero);
    
    List<Genero> AllGeneros();
    
    void updateGenero (Genero genero);
    
    void deleteGenero(int id);
    
    Genero findGeneroById(int id);
    
    Genero findGeneroByDescription(String description);
    
}
