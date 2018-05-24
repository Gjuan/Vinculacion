package com.vinculacion.app.model;

public class InformePasantias{
    
    private int ID_PASANTIAS_ESTUDIANTES;
	
    private Pasantias pasantias;
    
    private Estudiantes estudiantes;
    
    private String FECHA_ENTREGA_INFORME;
    
    private String ESTADO;
      
    public InformePasantias() {
    }

    public InformePasantias(int ID_PASANTIAS_ESTUDIANTES, Pasantias pasantias, Estudiantes estudiantes, String FECHA_ENTREGA_INFORME, String ESTADO) {
        this.ID_PASANTIAS_ESTUDIANTES = ID_PASANTIAS_ESTUDIANTES;
        this.pasantias = pasantias;
        this.estudiantes = estudiantes;
        this.FECHA_ENTREGA_INFORME = FECHA_ENTREGA_INFORME;
        this.ESTADO = ESTADO;
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
    
    public String getFECHA_ENTREGA_INFORME() {
        return FECHA_ENTREGA_INFORME;
    }

    public void setFECHA_ENTREGA_INFORME(String FECHA_ENTREGA_INFORME) {
        this.FECHA_ENTREGA_INFORME = FECHA_ENTREGA_INFORME;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
}
