package com.vinculacion.app.model;

public class Facultad{
    
    private int ID_FACULTAD;
    
    private String DESCRIPCION;
    
    private String SIGLAS;
    
    private String ESTADO;
      
    public Facultad() {
    }

    public Facultad(int ID_FACULTAD, String DESCRIPCION, String SIGLAS, String ESTADO) {
        this.ID_FACULTAD = ID_FACULTAD;
        this.DESCRIPCION = DESCRIPCION;
        this.SIGLAS = SIGLAS;
        this.ESTADO = ESTADO;
    }
    
    public int getID_FACULTAD() {
        return ID_FACULTAD;
    }

    public void setID_FACULTAD(int ID_FACULTAD) {
        this.ID_FACULTAD = ID_FACULTAD;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getSIGLAS() {
        return SIGLAS;
    }

    public void setSIGLAS(String SIGLAS) {
        this.SIGLAS = SIGLAS;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
   
}
