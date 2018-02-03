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
@Table(name="CARGO_DEPARTAMENTAL")
public class CargoDepartamental implements Serializable{
    
    @Id
    @Column(name="ID_CARGO_EMPRESARIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private int ID_CARGO_EMPRESARIAL;
    
    @Column(name="DESCRIPCION")
    private String DESCRIPCION;
    
    @Column(name="ESTADO")
    private String ESTADO;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cargoDepartamental", cascade = CascadeType.ALL)
    private List<Empleados> empleados = new ArrayList<>();
    
    public CargoDepartamental() {
    }

    public CargoDepartamental(int ID_CARGO_EMPRESARIAL, String DESCRIPCION, String ESTADO) {
        this.ID_CARGO_EMPRESARIAL = ID_CARGO_EMPRESARIAL;
        this.DESCRIPCION = DESCRIPCION;
        this.ESTADO = ESTADO;
    }

    public int getID_CARGO_EMPRESARIAL() {
        return ID_CARGO_EMPRESARIAL;
    }

    public void setID_CARGO_EMPRESARIAL(int ID_CARGO_EMPRESARIAL) {
        this.ID_CARGO_EMPRESARIAL = ID_CARGO_EMPRESARIAL;
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
    
    public List<Empleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleados> empleados) {
        this.empleados = empleados;
    }
    
}
