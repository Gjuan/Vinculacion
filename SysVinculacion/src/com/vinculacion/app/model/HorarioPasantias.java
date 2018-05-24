package com.vinculacion.app.model;

public class HorarioPasantias{
   
    private int ID_HORARIO_PASANTIAS;
    
    private String DESCRIPCION_HORARIO; 
        
    public HorarioPasantias(int ID_HORARIO_PASANTIAS, String DESCRIPCION_HORARIO) {
        this.ID_HORARIO_PASANTIAS = ID_HORARIO_PASANTIAS;
        this.DESCRIPCION_HORARIO = DESCRIPCION_HORARIO;
    }

    public HorarioPasantias() {
    }

    public int getID_HORARIO_PASANTIAS() {
        return ID_HORARIO_PASANTIAS;
    }

    public void setID_HORARIO_PASANTIAS(int ID_HORARIO_PASANTIAS) {
        this.ID_HORARIO_PASANTIAS = ID_HORARIO_PASANTIAS;
    }

    public String getDESCRIPCION_HORARIO() {
        return DESCRIPCION_HORARIO;
    }

    public void setDESCRIPCION_HORARIO(String DESCRIPCION_HORARIO) {
        this.DESCRIPCION_HORARIO = DESCRIPCION_HORARIO;
    }

}
