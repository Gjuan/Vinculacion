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
@Table(name="NIVEL")
public class Nivel implements Serializable{
    
    @Id
    @Column(name="ID_NIVEL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_NIVEL;
    
    @Column(name="SEMESTRE")
    private String SEMESTRE;
    
    @Column(name="ESTADO")
    private String ESTADO;
      
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "nivel", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();
    
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
    
    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
