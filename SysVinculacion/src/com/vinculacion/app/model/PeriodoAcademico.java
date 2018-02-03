package com.vinculacion.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name="PERIODO_ACADEMICO")
public class PeriodoAcademico implements Serializable{
    
    @Id
    @Column(name="ID_PERIODO_ACADEMICO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_PERIODO_ACADEMICO;
    
    @Column(name="ANIO_ACADEMICO")
    private int ANIO_ACADEMICO;

    @Column(name="NOMBRE_PERIODO")
    private String NOMBRE_PERIODO;

    @Column(name="FECHA_INICIO_PERIODO")
    private String FECHA_INICIO_PERIODO;

    @Column(name="FECHA_FIN_PERIODO")
    private String FECHA_FIN_PERIODO;
    
    @Column(name="ESTADO")
    private String ESTADO;
      
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "periodoAcademico", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();
    
    public PeriodoAcademico() {
    }

    public PeriodoAcademico(int ID_PERIODO_ACADEMICO, int ANIO_ACADEMICO, String NOMBRE_PERIODO, String FECHA_INICIO_PERIODO, String FECHA_FIN_PERIODO, String ESTADO) {
        this.ID_PERIODO_ACADEMICO = ID_PERIODO_ACADEMICO;
        this.ANIO_ACADEMICO = ANIO_ACADEMICO;
        this.NOMBRE_PERIODO = NOMBRE_PERIODO;
        this.FECHA_INICIO_PERIODO = FECHA_INICIO_PERIODO;
        this.FECHA_FIN_PERIODO = FECHA_FIN_PERIODO;
        this.ESTADO = ESTADO;
    }

    public int getID_PERIODO_ACADEMICO() {
        return ID_PERIODO_ACADEMICO;
    }

    public void setID_PERIODO_ACADEMICO(int ID_PERIODO_ACADEMICO) {
        this.ID_PERIODO_ACADEMICO = ID_PERIODO_ACADEMICO;
    }

    public int getANIO_ACADEMICO() {
        return ANIO_ACADEMICO;
    }

    public void setANIO_ACADEMICO(int ANIO_ACADEMICO) {
        this.ANIO_ACADEMICO = ANIO_ACADEMICO;
    }

    public String getNOMBRE_PERIODO() {
        return NOMBRE_PERIODO;
    }

    public void setNOMBRE_PERIODO(String NOMBRE_PERIODO) {
        this.NOMBRE_PERIODO = NOMBRE_PERIODO;
    }

    public String getFECHA_INICIO_PERIODO() {
        return FECHA_INICIO_PERIODO;
    }

    public void setFECHA_INICIO_PERIODO(String FECHA_INICIO_PERIODO) {
        this.FECHA_INICIO_PERIODO = FECHA_INICIO_PERIODO;
    }

    public String getFECHA_FIN_PERIODO() {
        return FECHA_FIN_PERIODO;
    }

    public void setFECHA_FIN_PERIODO(String FECHA_FIN_PERIODO) {
        this.FECHA_FIN_PERIODO = FECHA_FIN_PERIODO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
