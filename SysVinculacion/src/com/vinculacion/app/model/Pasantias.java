package com.vinculacion.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name="PASANTIAS")
public class Pasantias implements Serializable{
    
    @Id
    @Column(name="ID_PASANTIAS")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_PASANTIAS;
    
    @Column(name="TIEMPO_COMPLETO")
    private int TIEMPO_COMPLETO;
    
    @Column(name="MEDIO_TIEMPO")
    private int MEDIO_TIEMPO;
    
    @Column(name="TOTAL_HORAS")
    private int TOTAL_HORAS;
    
    @Column(name="FECHA_INICIO")
    private Date FECHA_INICIO;
    
    @Column(name="FECHA_CULMINACION")
    private Date FECHA_CULMINACION;
    
    @OneToMany(mappedBy = "pasantias", cascade = CascadeType.ALL)
    private List<InformePasantias> informePasantias = new ArrayList<>();
    
    public Pasantias() {
    }

    public Pasantias(int ID_PASANTIAS, int TIEMPO_COMPLETO, int MEDIO_TIEMPO, int TOTAL_HORAS, Date FECHA_INICIO, Date FECHA_CULMINACION) {
        this.ID_PASANTIAS = ID_PASANTIAS;
        this.TIEMPO_COMPLETO = TIEMPO_COMPLETO;
        this.MEDIO_TIEMPO = MEDIO_TIEMPO;
        this.TOTAL_HORAS = TOTAL_HORAS;
        this.FECHA_INICIO = FECHA_INICIO;
        this.FECHA_CULMINACION = FECHA_CULMINACION;
    }

    public int getID_PASANTIAS() {
        return ID_PASANTIAS;
    }

    public void setID_PASANTIAS(int ID_PASANTIAS) {
        this.ID_PASANTIAS = ID_PASANTIAS;
    }

    public int getTIEMPO_COMPLETO() {
        return TIEMPO_COMPLETO;
    }

    public void setTIEMPO_COMPLETO(int TIEMPO_COMPLETO) {
        this.TIEMPO_COMPLETO = TIEMPO_COMPLETO;
    }

    public int getMEDIO_TIEMPO() {
        return MEDIO_TIEMPO;
    }

    public void setMEDIO_TIEMPO(int MEDIO_TIEMPO) {
        this.MEDIO_TIEMPO = MEDIO_TIEMPO;
    }

    public int getTOTAL_HORAS() {
        return TOTAL_HORAS;
    }

    public void setTOTAL_HORAS(int TOTAL_HORAS) {
        this.TOTAL_HORAS = TOTAL_HORAS;
    }

    public Date getFECHA_INICIO() {
        return FECHA_INICIO;
    }

    public void setFECHA_INICIO(Date FECHA_INICIO) {
        this.FECHA_INICIO = FECHA_INICIO;
    }

    public Date getFECHA_CULMINACION() {
        return FECHA_CULMINACION;
    }

    public void setFECHA_CULMINACION(Date FECHA_CULMINACION) {
        this.FECHA_CULMINACION = FECHA_CULMINACION;
    }

    public List<InformePasantias> getInformePasantias() {
        return informePasantias;
    }

    public void setInformePasantias(List<InformePasantias> informePasantias) {
        this.informePasantias = informePasantias;
    }
    
    
}
