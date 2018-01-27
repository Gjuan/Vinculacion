package com.vinculacion.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public Nivel() {
    }
    
    public Nivel(int ID_NIVEL, String SEMESTRE) {
        this.ID_NIVEL = ID_NIVEL;
        this.SEMESTRE = SEMESTRE;
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
    
}
