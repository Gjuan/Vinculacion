package com.vinculacion.app.model;

public class DetalleCargoDocente{
    
    private int ID_DETALLE_CARGO_DOCENTE;
    
    private Cargo cargos;
    
    private Docente docentes;
    
    private String ESTADO;

    public DetalleCargoDocente(int ID_DETALLE_CARGO_DOCENTE, Cargo cargos, Docente docentes, String ESTADO) {
        this.ID_DETALLE_CARGO_DOCENTE = ID_DETALLE_CARGO_DOCENTE;
        this.cargos = cargos;
        this.docentes = docentes;
        this.ESTADO = ESTADO;
    }
      
    public DetalleCargoDocente() {
    }

    public int getID_DETALLE_CARGO_DOCENTE() {
        return ID_DETALLE_CARGO_DOCENTE;
    }

    public void setID_DETALLE_CARGO_DOCENTE(int ID_DETALLE_CARGO_DOCENTE) {
        this.ID_DETALLE_CARGO_DOCENTE = ID_DETALLE_CARGO_DOCENTE;
    }

    public Cargo getCargos() {
        return cargos;
    }

    public void setCargos(Cargo cargos) {
        this.cargos = cargos;
    }

    public Docente getDocentes() {
        return docentes;
    }

    public void setDocentes(Docente docentes) {
        this.docentes = docentes;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
