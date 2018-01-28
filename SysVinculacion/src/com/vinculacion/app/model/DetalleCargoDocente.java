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
@Table(name="DETALLE_CARGO_DOCENTE")
public class DetalleCargoDocente implements Serializable{
    
    @Id
    @Column(name="ID_DETALLE_CARGO_DOCENTE")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_DETALLE_CARGO_DOCENTE;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_CARGO")
    private Cargo cargos;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_DOCENTE")
    private Docente docentes;

    public DetalleCargoDocente(int ID_DETALLE_CARGO_DOCENTE, Cargo cargos, Docente docentes) {
        this.ID_DETALLE_CARGO_DOCENTE = ID_DETALLE_CARGO_DOCENTE;
        this.cargos = cargos;
        this.docentes = docentes;
    }

    public DetalleCargoDocente() {
    }

    public int getID_DETALLE_CARGO_DOCENTE() {
        return ID_DETALLE_CARGO_DOCENTE;
    }

    public void setID_DETALLE_CARGO_DOCENTE(int ID_DETALLE_CARGO_DOCENTE) {
        this.ID_DETALLE_CARGO_DOCENTE = ID_DETALLE_CARGO_DOCENTE;
    }

    public Cargo getCargos() {
        return cargos;
    }

    public void setCargos(Cargo cargos) {
        this.cargos = cargos;
    }

    public Docente getDocentes() {
        return docentes;
    }

    public void setDocentes(Docente docentes) {
        this.docentes = docentes;
    }
    
}
