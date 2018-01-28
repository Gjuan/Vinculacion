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
@Table(name="TIPO_DOCUMENTO_PRACTICAS")
public class TipoDocumentoPracticas implements Serializable{
    
    @Id
    @Column(name="id_tipo_documento_practicas")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_tipo_documento_practicas;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "tipoDocumentoPracticas", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();
    
    public TipoDocumentoPracticas(int id_tipo_documento_practicas, String descripcion) {
        this.id_tipo_documento_practicas = id_tipo_documento_practicas;
        this.descripcion = descripcion;
    }

    public TipoDocumentoPracticas() {
    }

    public int getId_tipo_documento_practicas() {
        return id_tipo_documento_practicas;
    }

    public void setId_tipo_documento_practicas(int id_tipo_documento_practicas) {
        this.id_tipo_documento_practicas = id_tipo_documento_practicas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
