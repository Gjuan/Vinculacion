package com.vinculacion.app.model;

public class Perfil{
    
    private int id_perfil;
    
    private String descripcion;
    
    private String ESTADO;
      
    public Perfil() {
    }

    public Perfil(int id_perfil, String descripcion, String ESTADO) {
        this.id_perfil = id_perfil;
        this.descripcion = descripcion;
        this.ESTADO = ESTADO;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
