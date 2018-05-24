package com.vinculacion.app.model;

public class Asignaturas{
    
    private int ID_ASIGNATURA;
    
    private String NOMBRE_ASIGNATURA;
    
    private String ESTADO;
    
    public Asignaturas(int ID_ASIGNATURA, String NOMBRE_ASIGNATURA, String ESTADO) {
        this.ID_ASIGNATURA = ID_ASIGNATURA;
        this.NOMBRE_ASIGNATURA = NOMBRE_ASIGNATURA;
        this.ESTADO = ESTADO;
    }

    public Asignaturas() {
    }

    public int getID_ASIGNATURA() {
        return ID_ASIGNATURA;
    }

    public void setID_ASIGNATURA(int ID_ASIGNATURA) {
        this.ID_ASIGNATURA = ID_ASIGNATURA;
    }

    public String getNOMBRE_ASIGNATURA() {
        return NOMBRE_ASIGNATURA;
    }

    public void setNOMBRE_ASIGNATURA(String NOMBRE_ASIGNATURA) {
        this.NOMBRE_ASIGNATURA = NOMBRE_ASIGNATURA;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
