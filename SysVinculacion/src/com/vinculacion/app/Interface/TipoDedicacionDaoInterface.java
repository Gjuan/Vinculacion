package com.vinculacion.app.Interface;

import com.vinculacion.app.model.TipoDedicacion;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface TipoDedicacionDaoInterface {
    
    void saveTipoDedicacion(TipoDedicacion tipoDedicacion);
    
    List<TipoDedicacion> AllTiposDedicacion();
    
    void updateTipoDedicacion(TipoDedicacion tipoDedicacion);
    
    void deleteTipoDedicacion(int id);
    
    TipoDedicacion findTipoDedicacionByDescription (String description);
    
    TipoDedicacion findTipoDedicacionById(int id);
    
}
