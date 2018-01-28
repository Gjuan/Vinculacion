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
@Table(name="GENERO")
public class Genero implements Serializable{
    
    @Id
    @Column(name="ID_GENERO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_GENERO;
    
    @Column(name="DESCRIPCION")
    private String DESCRIPCION;
    
    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
    private List<Docente> docentes = new ArrayList<>();
    
    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();
    
    public Genero(int ID_GENERO, String DESCRIPCION) {
        this.ID_GENERO = ID_GENERO;
        this.DESCRIPCION = DESCRIPCION;
    }

    public Genero() {
    }

    public int getID_GENERO() {
        return ID_GENERO;
    }

    public void setID_GENERO(int ID_GENERO) {
        this.ID_GENERO = ID_GENERO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
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
