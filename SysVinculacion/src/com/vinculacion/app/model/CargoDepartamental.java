package com.vinculacion.app.model;

public class CargoDepartamental{
    
    private int ID_CARGO_EMPRESARIAL;
    
    private String DESCRIPCION;
    
    private String ESTADO;
        
    public CargoDepartamental() {
    }

    public CargoDepartamental(int ID_CARGO_EMPRESARIAL, String DESCRIPCION, String ESTADO) {
        this.ID_CARGO_EMPRESARIAL = ID_CARGO_EMPRESARIAL;
        this.DESCRIPCION = DESCRIPCION;
        this.ESTADO = ESTADO;
    }

    public int getID_CARGO_EMPRESARIAL() {
        return ID_CARGO_EMPRESARIAL;
    }

    public void setID_CARGO_EMPRESARIAL(int ID_CARGO_EMPRESARIAL) {
        this.ID_CARGO_EMPRESARIAL = ID_CARGO_EMPRESARIAL;
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
