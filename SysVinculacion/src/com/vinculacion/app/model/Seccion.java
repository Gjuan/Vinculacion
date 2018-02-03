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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SECCION")
public class Seccion implements Serializable{
    
    @Id
    @Column(name="ID_SECCION")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_SECCION;
    
    @Column(name="DESCRIPCION")
    private String DESCRIPCION;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seccion", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();
    
    public Seccion() {
    }

    public Seccion(int ID_SECCION, String DESCRIPCION) {
        this.ID_SECCION = ID_SECCION;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getID_SECCION() {
        return ID_SECCION;
    }

    public void setID_SECCION(int ID_SECCION) {
        this.ID_SECCION = ID_SECCION;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
