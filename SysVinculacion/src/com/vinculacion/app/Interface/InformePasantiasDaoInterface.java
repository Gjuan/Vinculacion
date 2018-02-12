package com.vinculacion.app.Interface;

import com.vinculacion.app.model.InformePasantias;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface InformePasantiasDaoInterface {
    
    void saveInformePasantias (InformePasantias informe);
    
    List<InformePasantias> AllInformes();
    
    InformePasantias findInformePasantiaById(int id);
    
    void updateInformPasantia (InformePasantias informe);
    
    void deleteInformePasantia (int id);
    
    List<InformePasantias> findInformeByCedulaEstudiante (String cedulaEstudiante);
    
    List<InformePasantias> findInformesByFechaEntrega (String fecha_entrega);
    
}
