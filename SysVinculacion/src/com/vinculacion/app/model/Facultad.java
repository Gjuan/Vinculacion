package com.vinculacion.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FACULTAD")
public class Facultad implements Serializable{
    
    @Id
    @Column(name="ID_FACULTAD")
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private int ID_FACULTAD;
    
    @Column(name="DESCRIPCION")
    private String DESCRIPCION;
    
    @Column(name="SIGLAS")
    private String SIGLAS;
    
    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL)
    private List<Escuela> escuelas = new ArrayList<>();
    
    public Facultad() {
    }

    public Facultad(int ID_FACULTAD, String DESCRIPCION, String SIGLAS) {
        this.ID_FACULTAD = ID_FACULTAD;
        this.DESCRIPCION = DESCRIPCION;
        this.SIGLAS = SIGLAS;
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

    public List<Escuela> getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(List<Escuela> escuelas) {
        this.escuelas = escuelas;
    }
    
}
