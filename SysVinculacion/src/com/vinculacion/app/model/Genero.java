package com.vinculacion.app.model;
public class Genero{
    private int ID_GENERO;
    
    private String DESCRIPCION;
    
    private String ESTADO;
      
    public Genero(int ID_GENERO, String DESCRIPCION, String ESTADO) {
        this.ID_GENERO = ID_GENERO;
        this.DESCRIPCION = DESCRIPCION;
        this.ESTADO = ESTADO;
    }

    public Genero() {
    }

    public int getID_GENERO() {
        return ID_GENERO;
    }

    public void setID_GENERO(int ID_GENERO) {
        this.ID_GENERO = ID_GENERO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
