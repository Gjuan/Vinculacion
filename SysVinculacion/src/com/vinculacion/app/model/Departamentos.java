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
@Table(name="DEPARTAMENTOS")
public class Departamentos implements Serializable{
    
    @Id
    @Column(name="ID_DEPARTAMENTO")
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private int ID_DEPARTAMENTO;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_EMPRESA")    
    private Empresa empresa;
    
    @Column(name="NOMBRE_DEPARTAMENTO")
    private String NOMBRE_DEPARTAMENTO;
    
    @Column(name="ESTADO")
    private String ESTADO;
      
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "departamentos", cascade = CascadeType.ALL)
    private List<Empleados> empleados = new ArrayList<>();
    
    public Departamentos() {
    }

    public Departamentos(int ID_DEPARTAMENTO, Empresa empresa, String NOMBRE_DEPARTAMENTO, String ESTADO) {
        this.ID_DEPARTAMENTO = ID_DEPARTAMENTO;
        this.empresa = empresa;
        this.NOMBRE_DEPARTAMENTO = NOMBRE_DEPARTAMENTO;
        this.ESTADO = ESTADO;
    }
    
    public int getID_DEPARTAMENTO() {
        return ID_DEPARTAMENTO;
    }

    public void setID_DEPARTAMENTO(int ID_DEPARTAMENTO) {
        this.ID_DEPARTAMENTO = ID_DEPARTAMENTO;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNOMBRE_DEPARTAMENTO() {
        return NOMBRE_DEPARTAMENTO;
    }

    public void setNOMBRE_DEPARTAMENTO(String NOMBRE_DEPARTAMENTO) {
        this.NOMBRE_DEPARTAMENTO = NOMBRE_DEPARTAMENTO;
    }

    public List<Empleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleados> empleados) {
        this.empleados = empleados;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
