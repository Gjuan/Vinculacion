package com.vinculacion.app.Interface;

import com.vinculacion.app.model.DetalleCargoDocente;
import com.vinculacion.app.model.Docente;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface DetalleCargoDocenteDaoInterface {
    
    void saveDetalleCargoDocente (DetalleCargoDocente dcd);
    
    List<DetalleCargoDocente> AllDetalleCargoDocente ();
    
    void updateDetalleCargoDocente (DetalleCargoDocente dcd);
    
    void deleteDetalleCargoDocente (int id);
    
    DetalleCargoDocente findDetalleCargoDocenteById(int id);
    
    List<DetalleCargoDocente> findDetalleCargoDocenteByCedula (String cedulaDocente);
    
}
