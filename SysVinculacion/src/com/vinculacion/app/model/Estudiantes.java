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
@Table(name="ESTUDIANTES")
public class Estudiantes implements Serializable{
    
    @Id
    @Column(name="CODIGO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String CODIGO;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_CARRERA")
    private Carreras carrera;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_NIVEL")
    private Nivel nivel;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_GENERO")
    private Genero genero;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_PERIODO_ACADEMICO")
    private PeriodoAcademico periodoAcademico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_SECCION")
    private Seccion seccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_TUTOR_DOCENTE")
    private Docente docente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_TUTOR_EMPRESARIAL")
    private Empleados empleado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_USUARIO")
    private Usuarios usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_TIPO_DOCUMENTO_PRACTICAS")
    private TipoDocumentoPracticas tipoDocumentoPracticas;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_HORARIO_PASANTIAS")
    private HorarioPasantias horarioPasantias;
    
    @Column(name="COD_MATRICULA")
    private String COD_MATRICULA;
    
    @Column(name="NOMBRES")
    private String NOMBRES;
    
    @Column(name="APELLIDOS")
    private String APELLIDOS;
	
    @Column(name="CEDULA")
    private String CEDULA;
    
    @Column(name="DIRECCION")
    private String DIRECCION;
    
    @Column(name="TELEFONO")
    private String TELEFONO;
    
    @Column(name="CORREO")
    private String CORREO;
    
    @Column(name="FOTO")
    private String FOTO;
    
    @OneToMany(mappedBy = "estudiantes", cascade = CascadeType.ALL)
    private List<InformePasantias> informePasantias = new ArrayList<>();
    
    public Estudiantes(String CODIGO, Carreras carrera, Nivel nivel, Genero genero, PeriodoAcademico periodoAcademico, Seccion seccion, Docente docente, Empleados empleado, Usuarios usuario, TipoDocumentoPracticas tipoDocumentoPracticas, HorarioPasantias horarioPasantias, String COD_MATRICULA, String NOMBRES, String APELLIDOS, String CEDULA, String DIRECCION, String TELEFONO, String CORREO, String FOTO) {
        this.CODIGO = CODIGO;
        this.carrera = carrera;
        this.nivel = nivel;
        this.genero = genero;
        this.periodoAcademico = periodoAcademico;
        this.seccion = seccion;
        this.docente = docente;
        this.empleado = empleado;
        this.usuario = usuario;
        this.tipoDocumentoPracticas = tipoDocumentoPracticas;
        this.horarioPasantias = horarioPasantias;
        this.COD_MATRICULA = COD_MATRICULA;
        this.NOMBRES = NOMBRES;
        this.APELLIDOS = APELLIDOS;
        this.CEDULA = CEDULA;
        this.DIRECCION = DIRECCION;
        this.TELEFONO = TELEFONO;
        this.CORREO = CORREO;
        this.FOTO = FOTO;
    }

    public Estudiantes() {
    }
   
    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public Carreras getCarrera() {
        return carrera;
    }

    public void setCarrera(Carreras carrera) {
        this.carrera = carrera;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public PeriodoAcademico getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public TipoDocumentoPracticas getTipoDocumentoPracticas() {
        return tipoDocumentoPracticas;
    }

    public void setTipoDocumentoPracticas(TipoDocumentoPracticas tipoDocumentoPracticas) {
        this.tipoDocumentoPracticas = tipoDocumentoPracticas;
    }

    public HorarioPasantias getHorarioPasantias() {
        return horarioPasantias;
    }

    public void setHorarioPasantias(HorarioPasantias horarioPasantias) {
        this.horarioPasantias = horarioPasantias;
    }

    public String getCOD_MATRICULA() {
        return COD_MATRICULA;
    }

    public void setCOD_MATRICULA(String COD_MATRICULA) {
        this.COD_MATRICULA = COD_MATRICULA;
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

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
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

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }

    public List<InformePasantias> getInformePasantias() {
        return informePasantias;
    }

    public void setInformePasantias(List<InformePasantias> informePasantias) {
        this.informePasantias = informePasantias;
    }
    
}
