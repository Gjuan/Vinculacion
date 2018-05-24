package com.vinculacion.app.model;

public class Seccion {
    
    private int ID_SECCION;
    
    private String DESCRIPCION;
    
    public Seccion() {
    }

    public Seccion(int ID_SECCION, String DESCRIPCION) {
        this.ID_SECCION = ID_SECCION;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getID_SECCION() {
        return ID_SECCION;
    }

    public void setID_SECCION(int ID_SECCION) {
        this.ID_SECCION = ID_SECCION;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

}
