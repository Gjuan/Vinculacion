package com.vinculacion.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DETALLE_DOCENTE_ASIGNATURA")
public class DetalleDocenteAsignatura implements Serializable{
    
    @Id
    @Column(name="ID_DETALLE_DOCENTE_ASIG")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_DETALLE_DOCENTE_ASIG;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_DOCENTE")
    private Docente docentes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_ASIGNATURA")
    private Asignaturas asignaturas;

    public DetalleDocenteAsignatura(int ID_DETALLE_DOCENTE_ASIG, Docente docentes, Asignaturas asignaturas) {
        this.ID_DETALLE_DOCENTE_ASIG = ID_DETALLE_DOCENTE_ASIG;
        this.docentes = docentes;
        this.asignaturas = asignaturas;
    }

    public DetalleDocenteAsignatura() {
    }

    public int getID_DETALLE_DOCENTE_ASIG() {
        return ID_DETALLE_DOCENTE_ASIG;
    }

    public void setID_DETALLE_DOCENTE_ASIG(int ID_DETALLE_DOCENTE_ASIG) {
        this.ID_DETALLE_DOCENTE_ASIG = ID_DETALLE_DOCENTE_ASIG;
    }

    public Docente getDocentes() {
        return docentes;
    }

    public void setDocentes(Docente docentes) {
        this.docentes = docentes;
    }

    public Asignaturas getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Asignaturas asignaturas) {
        this.asignaturas = asignaturas;
    }
    
}
