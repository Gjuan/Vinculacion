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
@Table(name="PERFIL")
public class Perfil implements Serializable {
    
    @Id
    @Column(name="id_perfil")
    @GeneratedValue(strategy=GenerationType.IDENTITY)	    
    private int id_perfil;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="ESTADO")
    private String ESTADO;
      
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<Usuarios> usuarios = new ArrayList<>();

    public Perfil() {
    }

    public Perfil(int id_perfil, String descripcion, String ESTADO) {
        this.id_perfil = id_perfil;
        this.descripcion = descripcion;
        this.ESTADO = ESTADO;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    
}
