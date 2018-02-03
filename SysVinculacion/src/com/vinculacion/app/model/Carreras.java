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
@Table(name="CARRERAS")
public class Carreras implements Serializable{
    
    @Id
    @Column(name="ID_CARRERA")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private int ID_CARRERA;
    
    @Column(name="DESCRIPCION")
    private String DESCRIPCION;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_ESCUELA")   
    private Escuela escuela; 
    
    @Column(name="ESTADO")
    private String ESTADO;
      
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<Docente> docentes = new ArrayList<>();
       
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();

    public Carreras(int ID_CARRERA, String DESCRIPCION, Escuela escuela, String ESTADO) {
        this.ID_CARRERA = ID_CARRERA;
        this.DESCRIPCION = DESCRIPCION;
        this.escuela = escuela;
        this.ESTADO = ESTADO;
    }

    public Carreras() {
    }

    public int getID_CARRERA() {
        return ID_CARRERA;
    }

    public void setID_CARRERA(int ID_CARRERA) {
        this.ID_CARRERA = ID_CARRERA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
