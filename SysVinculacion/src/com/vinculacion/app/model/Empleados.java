package com.vinculacion.app.model;

public class Empleados{
    
    private int ID_EMPLEADO;
    
    private CargoDepartamental cargoDepartamental;
    
    private Departamentos departamentos;
    
    private String NOMBRES;
    
    private String APELLIDOS;
   
    private String CEDULA;
    
    private String CORREO;

    private String TELEFONO;
    
    private String ESTADO;
      
    public Empleados(int ID_EMPLEADO, CargoDepartamental cargoDepartamental, Departamentos departamentos, String NOMBRES, String APELLIDOS, String CEDULA, String CORREO, String TELEFONO, String ESTADO) {
        this.ID_EMPLEADO = ID_EMPLEADO;
        this.cargoDepartamental = cargoDepartamental;
        this.departamentos = departamentos;
        this.NOMBRES = NOMBRES;
        this.APELLIDOS = APELLIDOS;
        this.CEDULA = CEDULA;
        this.CORREO = CORREO;
        this.TELEFONO = TELEFONO;
        this.ESTADO = ESTADO;
    }
    
    public Empleados() {
    }

    public int getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(int ID_EMPLEADO) {
        this.ID_EMPLEADO = ID_EMPLEADO;
    }

    public CargoDepartamental getCargoDepartamental() {
        return cargoDepartamental;
    }

    public void setCargoDepartamental(CargoDepartamental cargoDepartamental) {
        this.cargoDepartamental = cargoDepartamental;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
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

    public String getCEDULA() {
        return CEDULA;
    }

    public void setCEDULA(String CEDULA) {
        this.CEDULA = CEDULA;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
      
}
