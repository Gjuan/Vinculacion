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
@Table(name="ASIGNATURAS")
public class Asignaturas implements Serializable{
    
    @Id
    @Column(name="ID_ASIGNATURA")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_ASIGNATURA;
    
    @Column(name="NOMBRE_ASIGNATURA")
    private String NOMBRE_ASIGNATURA;
    
    @OneToMany(mappedBy = "asignaturas", cascade = CascadeType.ALL)
    private List<DetalleDocenteAsignatura> detallesDocenteAsignatura = new ArrayList<>();

    public Asignaturas(int ID_ASIGNATURA, String NOMBRE_ASIGNATURA) {
        this.ID_ASIGNATURA = ID_ASIGNATURA;
        this.NOMBRE_ASIGNATURA = NOMBRE_ASIGNATURA;
    }

    public Asignaturas() {
    }

    public int getID_ASIGNATURA() {
        return ID_ASIGNATURA;
    }

    public void setID_ASIGNATURA(int ID_ASIGNATURA) {
        this.ID_ASIGNATURA = ID_ASIGNATURA;
    }

    public String getNOMBRE_ASIGNATURA() {
        return NOMBRE_ASIGNATURA;
    }

    public void setNOMBRE_ASIGNATURA(String NOMBRE_ASIGNATURA) {
        this.NOMBRE_ASIGNATURA = NOMBRE_ASIGNATURA;
    }

    public List<DetalleDocenteAsignatura> getDetallesDocenteAsignatura() {
        return detallesDocenteAsignatura;
    }

    public void setDetallesDocenteAsignatura(List<DetalleDocenteAsignatura> detallesDocenteAsignatura) {
        this.detallesDocenteAsignatura = detallesDocenteAsignatura;
    }
    
}
