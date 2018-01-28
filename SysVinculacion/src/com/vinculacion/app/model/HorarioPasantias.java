package com.vinculacion.app.model;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name="HORARIO_PASANTIAS")
public class HorarioPasantias implements Serializable{
   
    @Id
    @Column(name="ID_HORARIO_PASANTIAS")
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private int ID_HORARIO_PASANTIAS;
    
    @Column(name="DESCRIPCION_HORARIO")
    private String DESCRIPCION_HORARIO; 
    
    @OneToMany(mappedBy = "horarioPasantias", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();
    
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

    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
