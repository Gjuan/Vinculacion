package com.vinculacion.app.model;

public class Departamentos {
    
    private int ID_DEPARTAMENTO;
    
    private Empresa empresa;
    
    private String NOMBRE_DEPARTAMENTO;
    
    private String ESTADO;
          
    public Departamentos() {
    }

    public Departamentos(int ID_DEPARTAMENTO, Empresa empresa, String NOMBRE_DEPARTAMENTO, String ESTADO) {
        this.ID_DEPARTAMENTO = ID_DEPARTAMENTO;
        this.empresa = empresa;
        this.NOMBRE_DEPARTAMENTO = NOMBRE_DEPARTAMENTO;
        this.ESTADO = ESTADO;
    }
    
    public int getID_DEPARTAMENTO() {
        return ID_DEPARTAMENTO;
    }

    public void setID_DEPARTAMENTO(int ID_DEPARTAMENTO) {
        this.ID_DEPARTAMENTO = ID_DEPARTAMENTO;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNOMBRE_DEPARTAMENTO() {
        return NOMBRE_DEPARTAMENTO;
    }

    public void setNOMBRE_DEPARTAMENTO(String NOMBRE_DEPARTAMENTO) {
        this.NOMBRE_DEPARTAMENTO = NOMBRE_DEPARTAMENTO;
    }
    
    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
