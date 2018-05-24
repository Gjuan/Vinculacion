package com.vinculacion.app.model;

public class Nivel{
    
    private int ID_NIVEL;
    
    private String SEMESTRE;
    
    private String ESTADO;
      
    public Nivel() {
    }

    public Nivel(int ID_NIVEL, String SEMESTRE, String ESTADO) {
        this.ID_NIVEL = ID_NIVEL;
        this.SEMESTRE = SEMESTRE;
        this.ESTADO = ESTADO;
    }

    public int getID_NIVEL() {
        return ID_NIVEL;
    }

    public void setID_NIVEL(int ID_NIVEL) {
        this.ID_NIVEL = ID_NIVEL;
    }

    public String getSEMESTRE() {
        return SEMESTRE;
    }

    public void setSEMESTRE(String SEMESTRE) {
        this.SEMESTRE = SEMESTRE;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
