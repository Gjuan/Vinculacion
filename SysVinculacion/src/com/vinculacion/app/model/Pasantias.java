package com.vinculacion.app.model;

public class Pasantias{
    
    private int ID_PASANTIAS;
    
    private String TITULO_PROYECTO;
    
    private int TIEMPO_COMPLETO;
    
    private int MEDIO_TIEMPO;
    
    private int TOTAL_HORAS;
    
    private String FECHA_INICIO;
    
    private String FECHA_CULMINACION;
    
    private String ESTADO;
      
    public Pasantias() {
    }

    public Pasantias(int ID_PASANTIAS, String TITULO_PROYECTO, int TIEMPO_COMPLETO, int MEDIO_TIEMPO, int TOTAL_HORAS, String FECHA_INICIO, String FECHA_CULMINACION, String ESTADO) {
        this.ID_PASANTIAS = ID_PASANTIAS;
        this.TITULO_PROYECTO = TITULO_PROYECTO;
        this.TIEMPO_COMPLETO = TIEMPO_COMPLETO;
        this.MEDIO_TIEMPO = MEDIO_TIEMPO;
        this.TOTAL_HORAS = TOTAL_HORAS;
        this.FECHA_INICIO = FECHA_INICIO;
        this.FECHA_CULMINACION = FECHA_CULMINACION;
        this.ESTADO = ESTADO;
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

    public String getFECHA_INICIO() {
        return FECHA_INICIO;
    }

    public void setFECHA_INICIO(String FECHA_INICIO) {
        this.FECHA_INICIO = FECHA_INICIO;
    }

    public String getFECHA_CULMINACION() {
        return FECHA_CULMINACION;
    }

    public void setFECHA_CULMINACION(String FECHA_CULMINACION) {
        this.FECHA_CULMINACION = FECHA_CULMINACION;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
   
    public String getTITULO_PROYECTO() {
        return TITULO_PROYECTO;
    }

    public void setTITULO_PROYECTO(String TITULO_PROYECTO) {
        this.TITULO_PROYECTO = TITULO_PROYECTO;
    }
}
