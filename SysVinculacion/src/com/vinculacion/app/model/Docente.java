package com.vinculacion.app.model;

public class Docente{
    
    private int ID_DOCENTE;
    
    private Genero genero;
    
    private Carreras carrera;
    
    private TipoDedicacion tipoDedicacion;
    
    private String CEDULA;
    
    private String NOMBRES;
    
    private String APELLIDOS;
    
    private String TELEFONO;
    
    private String CORREO;
    
    private String ESTADO;
      
    public Docente(int ID_DOCENTE, Genero genero, Carreras carrera, TipoDedicacion tipoDedicacion, String CEDULA, String NOMBRES, String APELLIDOS, String TELEFONO, String CORREO, String ESTADO) {
        this.ID_DOCENTE = ID_DOCENTE;
        this.genero = genero;
        this.carrera = carrera;
        this.tipoDedicacion = tipoDedicacion;
        this.CEDULA = CEDULA;
        this.NOMBRES = NOMBRES;
        this.APELLIDOS = APELLIDOS;
        this.TELEFONO = TELEFONO;
        this.CORREO = CORREO;
        this.ESTADO = ESTADO;
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

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
       
}
