package com.vinculacion.app.model;

public class TipoDedicacion{
    
    private int ID_TIPO_DEDICACION;
    
    private String DESCRIPCION;
     
    private String ESTADO;
      
    public TipoDedicacion(int ID_TIPO_DEDICACION, String DESCRIPCION, String ESTADO) {
        this.ID_TIPO_DEDICACION = ID_TIPO_DEDICACION;
        this.DESCRIPCION = DESCRIPCION;
        this.ESTADO = ESTADO;
    }

    public TipoDedicacion() {
    }

    public int getID_TIPO_DEDICACION() {
        return ID_TIPO_DEDICACION;
    }

    public void setID_TIPO_DEDICACION(int ID_TIPO_DEDICACION) {
        this.ID_TIPO_DEDICACION = ID_TIPO_DEDICACION;
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
