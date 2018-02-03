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
@Table(name="EMPRESA")
public class Empresa implements Serializable{
    
    @Id
    @Column(name="ID_EMPRESA")
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private int ID_EMPRESA;
    
    @Column(name="NOMBRE_EMPRESA")
    private String NOMBRE_EMPRESA;
    
    @Column(name="TELEFONO")
    private String TELEFONO;
    
    @Column(name="DIRECCION")
    private String DIRECCION;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "empresa", cascade = CascadeType.ALL)    
    private List<Departamentos> departamentos = new ArrayList<>();
            
    public Empresa(int ID_EMPRESA, String NOMBRE_EMPRESA, String TELEFONO, String DIRECCION) {
        this.ID_EMPRESA = ID_EMPRESA;
        this.NOMBRE_EMPRESA = NOMBRE_EMPRESA;
        this.TELEFONO = TELEFONO;
        this.DIRECCION = DIRECCION;
    }

    public Empresa() {
    }

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }

    public String getNOMBRE_EMPRESA() {
        return NOMBRE_EMPRESA;
    }

    public void setNOMBRE_EMPRESA(String NOMBRE_EMPRESA) {
        this.NOMBRE_EMPRESA = NOMBRE_EMPRESA;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public List<Departamentos> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamentos> departamentos) {
        this.departamentos = departamentos;
    }
    
}
