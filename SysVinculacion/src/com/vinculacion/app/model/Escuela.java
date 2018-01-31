package com.vinculacion.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ESCUELA")
public class Escuela implements Serializable{
    
    @Id
    @Column(name="ID_ESCUELA")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private int ID_ESCUELA;
    
    @Column(name="NOMBRE_ESCUELA")
    private String NOMBRE_ESCUELA;
    
    @Column(name="DESCRIPCION")
    private String DESCRIPCION;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_FACULTAD")   
    private Facultad facultad;
    
    @OneToMany(mappedBy = "escuela", cascade = CascadeType.ALL)
    private List<Carreras> carreras = new ArrayList<>();
            
    public Escuela(int ID_ESCUELA, String NOMBRE_ESCUELA, String DESCRIPCION, Facultad facultad) {
        this.ID_ESCUELA = ID_ESCUELA;
        this.NOMBRE_ESCUELA = NOMBRE_ESCUELA;
        this.DESCRIPCION = DESCRIPCION;
        this.facultad = facultad;
    }

    public Escuela() {
    }

    public int getID_ESCUELA() {
        return ID_ESCUELA;
    }

    public void setID_ESCUELA(int ID_ESCUELA) {
        this.ID_ESCUELA = ID_ESCUELA;
    }

    public String getNOMBRE_ESCUELA() {
        return NOMBRE_ESCUELA;
    }

    public void setNOMBRE_ESCUELA(String NOMBRE_ESCUELA) {
        this.NOMBRE_ESCUELA = NOMBRE_ESCUELA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Carreras> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carreras> carreras) {
        this.carreras = carreras;
    }
    
}
