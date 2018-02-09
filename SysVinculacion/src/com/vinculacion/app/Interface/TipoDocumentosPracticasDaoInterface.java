package com.vinculacion.app.Interface;

import com.vinculacion.app.model.TipoDocumentoPracticas;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface TipoDocumentosPracticasDaoInterface {
    
    void saveTipoDocumentoPracticas (TipoDocumentoPracticas tdp);
    
    List<TipoDocumentoPracticas> AllTiposDocumentosPracticas ();
    
    void updateTipoDocumentoPracticas (TipoDocumentoPracticas tdp);
    
    TipoDocumentoPracticas findTipoDocumentoPracticasByDescription (String description);
    
    TipoDocumentoPracticas findTipoDocumentoPracticasById(int id);
    
}
