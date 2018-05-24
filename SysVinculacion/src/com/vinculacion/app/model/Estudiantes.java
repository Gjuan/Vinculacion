package com.vinculacion.app.model;

public class Estudiantes {
    
    private int CODIGO;
    
    private Carreras carrera;
    
    private Nivel nivel;
    
    private Genero genero;
    
    private PeriodoAcademico periodoAcademico;
    
    private Seccion seccion;
    
    private Docente docente;
    
    private Empleados empleado;
    
    private Usuarios usuario;
    
    private TipoDocumentoPracticas tipoDocumentoPracticas;
    
    private HorarioPasantias horarioPasantias;
    
    private String COD_MATRICULA;
    
    private String NOMBRES;
    
    private String APELLIDOS;
	
    private String CEDULA;
    
    private String DIRECCION;
    
    private String TELEFONO;
    
    private String CORREO;
    
    private byte[] FOTO;
    
    private String ESTADO;
    
    public Estudiantes(int CODIGO, Carreras carrera, Nivel nivel, Genero genero, PeriodoAcademico periodoAcademico, Seccion seccion, Docente docente, Empleados empleado, Usuarios usuario, TipoDocumentoPracticas tipoDocumentoPracticas, HorarioPasantias horarioPasantias, String COD_MATRICULA, String NOMBRES, String APELLIDOS, String CEDULA, String DIRECCION, String TELEFONO, String CORREO, byte[] FOTO, String Estado) {
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
        this.ESTADO = Estado;
    }

    public Estudiantes() {
    }
   
    public int getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(int CODIGO) {
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

    public byte[] getFOTO() {
        return FOTO;
    }

    public void setFOTO(byte[] FOTO) {
        this.FOTO = FOTO;
    }


    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
   
}
