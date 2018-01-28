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
@Table(name="DOCENTE")
public class Docente implements Serializable{
    
    @Id
    @Column(name="ID_DOCENTE")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_DOCENTE;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_GENERO")  
    private Genero genero;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_CARRERA")
    private Carreras carrera;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_TIPO_DEDICACION")
    private TipoDedicacion tipoDedicacion;
    
    @Column(name="CEDULA")
    private String CEDULA;
    
    @Column(name="NOMBRES")
    private String NOMBRES;
    
    @Column(name="APELLIDOS")    
    private String APELLIDOS;
    
    @Column(name="TELEFONO")    
    private String TELEFONO;
    
    @Column(name="CORREO")    
    private String CORREO;
    
    @OneToMany(mappedBy = "docentes", cascade = CascadeType.ALL)
    private List<DetalleDocenteAsignatura> detallesDocenteAsignatura = new ArrayList<>();
    
    @OneToMany(mappedBy = "docentes", cascade = CascadeType.ALL)
    private List<DetalleCargoDocente> detalleCargoDocente = new ArrayList<>();
    
    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();
    
    public Docente(int ID_DOCENTE, Genero genero, Carreras carrera, TipoDedicacion tipoDedicacion, String CEDULA, String NOMBRES, String APELLIDOS, String TELEFONO, String CORREO) {
        this.ID_DOCENTE = ID_DOCENTE;
        this.genero = genero;
        this.carrera = carrera;
        this.tipoDedicacion = tipoDedicacion;
        this.CEDULA = CEDULA;
        this.NOMBRES = NOMBRES;
        this.APELLIDOS = APELLIDOS;
        this.TELEFONO = TELEFONO;
        this.CORREO = CORREO;
    }

    public Docente() {
    }

    public int getID_DOCENTE() {
        return ID_DOCENTE;
    }

    public void setID_DOCENTE(int ID_DOCENTE) {
        this.ID_DOCENTE = ID_DOCENTE;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Carreras getCarrera() {
        return carrera;
    }

    public void setCarrera(Carreras carrera) {
        this.carrera = carrera;
    }

    public TipoDedicacion getTipoDedicacion() {
        return tipoDedicacion;
    }

    public void setTipoDedicacion(TipoDedicacion tipoDedicacion) {
        this.tipoDedicacion = tipoDedicacion;
    }

    public String getCEDULA() {
        return CEDULA;
    }

    public void setCEDULA(String CEDULA) {
        this.CEDULA = CEDULA;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public List<DetalleDocenteAsignatura> getDetallesDocenteAsignatura() {
        return detallesDocenteAsignatura;
    }

    public void setDetallesDocenteAsignatura(List<DetalleDocenteAsignatura> detallesDocenteAsignatura) {
        this.detallesDocenteAsignatura = detallesDocenteAsignatura;
    }

    public List<DetalleCargoDocente> getDetalleCargoDocente() {
        return detalleCargoDocente;
    }

    public void setDetalleCargoDocente(List<DetalleCargoDocente> detalleCargoDocente) {
        this.detalleCargoDocente = detalleCargoDocente;
    }

    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
