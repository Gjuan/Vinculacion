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
@Table(name="CARGO")
public class Cargo implements Serializable{
    
    @Id
    @Column(name="ID_CARGO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_CARGO;
    
    @Column(name="DESCRIPCION")
    private String DESCRIPCION;
    
    @Column(name="ESTADO")
    private String ESTADO;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cargos", cascade = CascadeType.ALL)
    private List<DetalleCargoDocente> detalleCargoDocente = new ArrayList<>();
    
    public Cargo(int ID_CARGO, String DESCRIPCION, String ESTADO) {
        this.ID_CARGO = ID_CARGO;
        this.DESCRIPCION = DESCRIPCION;
        this.ESTADO = ESTADO;
    }

    public Cargo() {
    }

    public int getID_CARGO() {
        return ID_CARGO;
    }

    public void setID_CARGO(int ID_CARGO) {
        this.ID_CARGO = ID_CARGO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public List<DetalleCargoDocente> getDetalleCargoDocente() {
        return detalleCargoDocente;
    }

    public void setDetalleCargoDocente(List<DetalleCargoDocente> detalleCargoDocente) {
        this.detalleCargoDocente = detalleCargoDocente;
    }
    
}
