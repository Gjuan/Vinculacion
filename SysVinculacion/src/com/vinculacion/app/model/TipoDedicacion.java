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
@Table(name="TIPO_DEDICACION")
public class TipoDedicacion implements Serializable{
    
    @Id
    @Column(name="ID_TIPO_DEDICACION")
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private int ID_TIPO_DEDICACION;
    
    @Column(name="DESCRIPCION")
    private String DESCRIPCION;
    
    @OneToMany(mappedBy = "tipoDedicacion", cascade = CascadeType.ALL)
    private List<Docente> docentes = new ArrayList<>();
    
    public TipoDedicacion(int ID_TIPO_DEDICACION, String DESCRIPCION) {
        this.ID_TIPO_DEDICACION = ID_TIPO_DEDICACION;
        this.DESCRIPCION = DESCRIPCION;
    }

    public TipoDedicacion() {
    }

    public int getID_TIPO_DEDICACION() {
        return ID_TIPO_DEDICACION;
    }

    public void setID_TIPO_DEDICACION(int ID_TIPO_DEDICACION) {
        this.ID_TIPO_DEDICACION = ID_TIPO_DEDICACION;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }
    
}
