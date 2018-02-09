package com.vinculacion.app.Interface;

import com.vinculacion.app.model.PeriodoAcademico;
import java.util.List;

/**
 *
 * @author jorge
 */
public interface PeriodosAcademicosDaoInterface {
    
    void savePeriodosAcademicos(PeriodoAcademico periodo);
    
    List<PeriodoAcademico> AllPeriodosAcademicos();
    
    void updatePeriodoAcademico(PeriodoAcademico periodo);
    
    void deletePeriodoAcademico (int id); 
    
    List<PeriodoAcademico> findPeriodoAcademicoByName (String name);
    
    PeriodoAcademico findPeriodoAcademicoByStartDate (String fecha_inicio);
    
    PeriodoAcademico findPeriodoAcademicoById(int id);
    
}
