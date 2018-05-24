package com.vinculacion.app.model;

public class Cargo{
    
    private int ID_CARGO;
    
    private String DESCRIPCION;
    
    private String ESTADO;
        
    public Cargo(int ID_CARGO, String DESCRIPCION, String ESTADO) {
        this.ID_CARGO = ID_CARGO;
        this.DESCRIPCION = DESCRIPCION;
        this.ESTADO = ESTADO;
    }

    public Cargo() {
    }

    public int getID_CARGO() {
        return ID_CARGO;
    }

    public void setID_CARGO(int ID_CARGO) {
        this.ID_CARGO = ID_CARGO;
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
