package com.vinculacion.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="INFORME_PASANTIAS")
public class InformePasantias implements Serializable{
    
    @Id
    @Column(name="ID_PASANTIAS_ESTUDIANTES")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_PASANTIAS_ESTUDIANTES;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_PASANTIAS")
    private Pasantias pasantias;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CODIGO_ESTUDIANTE")
    private Estudiantes estudiantes;
    
    @Column(name="TITULO_PROYECTO")
    private String TITULO_PROYECTO;
    
    @Column(name="FECHA_ENTREGA_INFORME")
    private Date FECHA_ENTREGA_INFORME;

    public InformePasantias() {
    }

    public InformePasantias(int ID_PASANTIAS_ESTUDIANTES, Pasantias pasantias, Estudiantes estudiantes, String TITULO_PROYECTO, Date FECHA_ENTREGA_INFORME) {
        this.ID_PASANTIAS_ESTUDIANTES = ID_PASANTIAS_ESTUDIANTES;
        this.pasantias = pasantias;
        this.estudiantes = estudiantes;
        this.TITULO_PROYECTO = TITULO_PROYECTO;
        this.FECHA_ENTREGA_INFORME = FECHA_ENTREGA_INFORME;
    }

    public int getID_PASANTIAS_ESTUDIANTES() {
        return ID_PASANTIAS_ESTUDIANTES;
    }

    public void setID_PASANTIAS_ESTUDIANTES(int ID_PASANTIAS_ESTUDIANTES) {
        this.ID_PASANTIAS_ESTUDIANTES = ID_PASANTIAS_ESTUDIANTES;
    }

    public Pasantias getPasantias() {
        return pasantias;
    }

    public void setPasantias(Pasantias pasantias) {
        this.pasantias = pasantias;
    }

    public Estudiantes getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiantes estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getTITULO_PROYECTO() {
        return TITULO_PROYECTO;
    }

    public void setTITULO_PROYECTO(String TITULO_PROYECTO) {
        this.TITULO_PROYECTO = TITULO_PROYECTO;
    }

    public Date getFECHA_ENTREGA_INFORME() {
        return FECHA_ENTREGA_INFORME;
    }

    public void setFECHA_ENTREGA_INFORME(Date FECHA_ENTREGA_INFORME) {
        this.FECHA_ENTREGA_INFORME = FECHA_ENTREGA_INFORME;
    }
    
    
}
