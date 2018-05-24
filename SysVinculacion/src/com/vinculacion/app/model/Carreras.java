package com.vinculacion.app.model;

public class Carreras{
    
    private int ID_CARRERA;
    
    private String DESCRIPCION;
    
    private Escuela escuela; 
    
    private String ESTADO;
      
    public Carreras(int ID_CARRERA, String DESCRIPCION, Escuela escuela, String ESTADO) {
        this.ID_CARRERA = ID_CARRERA;
        this.DESCRIPCION = DESCRIPCION;
        this.escuela = escuela;
        this.ESTADO = ESTADO;
    }

    public Carreras() {
    }

    public int getID_CARRERA() {
        return ID_CARRERA;
    }

    public void setID_CARRERA(int ID_CARRERA) {
        this.ID_CARRERA = ID_CARRERA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
 
}
