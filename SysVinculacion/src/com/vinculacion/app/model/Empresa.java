package com.vinculacion.app.model;

public class Empresa{
    
    private int ID_EMPRESA;
    
    private String NOMBRE_EMPRESA;
    
    private String TELEFONO;
    
    private String DIRECCION;
                
    public Empresa(int ID_EMPRESA, String NOMBRE_EMPRESA, String TELEFONO, String DIRECCION) {
        this.ID_EMPRESA = ID_EMPRESA;
        this.NOMBRE_EMPRESA = NOMBRE_EMPRESA;
        this.TELEFONO = TELEFONO;
        this.DIRECCION = DIRECCION;
    }

    public Empresa() {
    }

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }

    public String getNOMBRE_EMPRESA() {
        return NOMBRE_EMPRESA;
    }

    public void setNOMBRE_EMPRESA(String NOMBRE_EMPRESA) {
        this.NOMBRE_EMPRESA = NOMBRE_EMPRESA;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }
}
