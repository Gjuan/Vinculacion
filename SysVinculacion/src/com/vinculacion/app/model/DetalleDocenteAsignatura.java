package com.vinculacion.app.model;

public class DetalleDocenteAsignatura{
    
    private int ID_DETALLE_DOCENTE_ASIG;
    
    private Docente docentes;
    
    private Asignaturas asignaturas;
    
    private String ESTADO;

    public DetalleDocenteAsignatura(int ID_DETALLE_DOCENTE_ASIG, Docente docentes, Asignaturas asignaturas, String ESTADO) {
        this.ID_DETALLE_DOCENTE_ASIG = ID_DETALLE_DOCENTE_ASIG;
        this.docentes = docentes;
        this.asignaturas = asignaturas;
        this.ESTADO = ESTADO;
    }
      
    public DetalleDocenteAsignatura() {
    }

    public int getID_DETALLE_DOCENTE_ASIG() {
        return ID_DETALLE_DOCENTE_ASIG;
    }

    public void setID_DETALLE_DOCENTE_ASIG(int ID_DETALLE_DOCENTE_ASIG) {
        this.ID_DETALLE_DOCENTE_ASIG = ID_DETALLE_DOCENTE_ASIG;
    }

    public Docente getDocentes() {
        return docentes;
    }

    public void setDocentes(Docente docentes) {
        this.docentes = docentes;
    }

    public Asignaturas getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Asignaturas asignaturas) {
        this.asignaturas = asignaturas;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
