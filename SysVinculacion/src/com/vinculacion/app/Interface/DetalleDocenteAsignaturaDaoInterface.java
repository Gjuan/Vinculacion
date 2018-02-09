package com.vinculacion.app.Interface;

import com.vinculacion.app.model.DetalleDocenteAsignatura;
import com.vinculacion.app.model.Docente;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface DetalleDocenteAsignaturaDaoInterface {
    
    void saveDetalleDocenteAsignatura(DetalleDocenteAsignatura dda);
    
    List<DetalleDocenteAsignatura> AllDetalleDocenteAsignatura();
    
    void updateDetalleDocenteAsignatura (DetalleDocenteAsignatura dda);
    
    void deleteDetalleDocenteAsignatura(int id);
    
    List<DetalleDocenteAsignatura> findDetalleDocenteAsignaturaByCedula(String cedulaDocente);
    
    DetalleDocenteAsignatura findDetalleDocenteAsignaturaById(int id);
}
