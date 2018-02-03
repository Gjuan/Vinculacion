package com.vinculacion.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EMPLEADOS")
public class Empleados implements Serializable{
    
    @Id
    @Column(name="ID_EMPLEADO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID_EMPLEADO;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_CARGO_EMPRESARIAL")
    private CargoDepartamental cargoDepartamental;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_DEPARTAMENTO")
    private Departamentos departamentos;
    
    @Column(name="NOMBRES")
    private String NOMBRES;
    
    @Column(name="APELLIDOS")
    private String APELLIDOS;
   
    @Column(name="CEDULA")
    private String CEDULA;
    
    @Column(name="CORREO")
    private String CORREO;

    @Column(name="TELEFONO")
    private String TELEFONO;
    
    @Column(name="ESTADO")
    private String ESTADO;
      
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();

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
    
    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
