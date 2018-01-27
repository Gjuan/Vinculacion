package com.vinculacion.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Date FECHA_INICIO_PERIODO;

    @Column(name="FECHA_FIN_PERIODO")
    private Date FECHA_FIN_PERIODO;

    public PeriodoAcademico() {
    }

    public PeriodoAcademico(int ID_PERIODO_ACADEMICO, int ANIO_ACADEMICO, String NOMBRE_PERIODO, Date FECHA_INICIO_PERIODO, Date FECHA_FIN_PERIODO) {
        this.ID_PERIODO_ACADEMICO = ID_PERIODO_ACADEMICO;
        this.ANIO_ACADEMICO = ANIO_ACADEMICO;
        this.NOMBRE_PERIODO = NOMBRE_PERIODO;
        this.FECHA_INICIO_PERIODO = FECHA_INICIO_PERIODO;
        this.FECHA_FIN_PERIODO = FECHA_FIN_PERIODO;
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

    public Date getFECHA_INICIO_PERIODO() {
        return FECHA_INICIO_PERIODO;
    }

    public void setFECHA_INICIO_PERIODO(Date FECHA_INICIO_PERIODO) {
        this.FECHA_INICIO_PERIODO = FECHA_INICIO_PERIODO;
    }

    public Date getFECHA_FIN_PERIODO() {
        return FECHA_FIN_PERIODO;
    }

    public void setFECHA_FIN_PERIODO(Date FECHA_FIN_PERIODO) {
        this.FECHA_FIN_PERIODO = FECHA_FIN_PERIODO;
    }
    
}
