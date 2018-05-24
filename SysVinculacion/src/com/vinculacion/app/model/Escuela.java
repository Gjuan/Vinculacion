package com.vinculacion.app.model;

public class Escuela {
    
    private int ID_ESCUELA;
    
    private String NOMBRE_ESCUELA;
    
    private String DESCRIPCION;
    
    private Facultad facultad;
    
    private String ESTADO;
      
    public Escuela(int ID_ESCUELA, String NOMBRE_ESCUELA, String DESCRIPCION, Facultad facultad, String ESTADO) {
        this.ID_ESCUELA = ID_ESCUELA;
        this.NOMBRE_ESCUELA = NOMBRE_ESCUELA;
        this.DESCRIPCION = DESCRIPCION;
        this.facultad = facultad;
        this.ESTADO = ESTADO;
    }
            
    public Escuela() {
    }

    public int getID_ESCUELA() {
        return ID_ESCUELA;
    }

    public void setID_ESCUELA(int ID_ESCUELA) {
        this.ID_ESCUELA = ID_ESCUELA;
    }

    public String getNOMBRE_ESCUELA() {
        return NOMBRE_ESCUELA;
    }

    public void setNOMBRE_ESCUELA(String NOMBRE_ESCUELA) {
        this.NOMBRE_ESCUELA = NOMBRE_ESCUELA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
       
}
