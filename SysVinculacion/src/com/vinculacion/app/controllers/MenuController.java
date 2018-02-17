package com.vinculacion.app.controllers;

import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.JFrameAsignaturas;
import com.vinculacion.app.views.JFrameCargo;
import com.vinculacion.app.views.JFrameCargoDepartamental;
import com.vinculacion.app.views.JFrameCarreras;
import com.vinculacion.app.views.JFrameDepartamento;
import com.vinculacion.app.views.JFrameDocente;
import com.vinculacion.app.views.JFrameEditAsignatura;
import com.vinculacion.app.views.JFrameEditCargo;
import com.vinculacion.app.views.JFrameEditCargoDepartamental;
import com.vinculacion.app.views.JFrameEditCarrera;
import com.vinculacion.app.views.JFrameEditDepartamento;
import com.vinculacion.app.views.JFrameEditDocente;
import com.vinculacion.app.views.JFrameEditEmpleado;
import com.vinculacion.app.views.JFrameEditEmpresa;
import com.vinculacion.app.views.JFrameEditEscuela;
import com.vinculacion.app.views.JFrameEditEstudiantes;
import com.vinculacion.app.views.JFrameEditFacultad;
import com.vinculacion.app.views.JFrameEditGenero;
import com.vinculacion.app.views.JFrameEditHorarioPasantias;
import com.vinculacion.app.views.JFrameEditNivel;
import com.vinculacion.app.views.JFrameEditPasantia;
import com.vinculacion.app.views.JFrameEditPerfil;
import com.vinculacion.app.views.JFrameEditPeriodoAcademico;
import com.vinculacion.app.views.JFrameEditSeccion;
import com.vinculacion.app.views.JFrameEditTipoDedicacion;
import com.vinculacion.app.views.JFrameEditTipoDocumentosPracticas;
import com.vinculacion.app.views.JFrameEditUsuario;
import com.vinculacion.app.views.JFrameEmpleados;
import com.vinculacion.app.views.JFrameEmpresa;
import com.vinculacion.app.views.JFrameEscuela;
import com.vinculacion.app.views.JFrameEstudiantes;
import com.vinculacion.app.views.JFrameFacultad;
import com.vinculacion.app.views.JFrameGenero;
import com.vinculacion.app.views.JFrameHorarioPasantias;
import com.vinculacion.app.views.JFrameNivel;
import com.vinculacion.app.views.JFrameNuevaAsignatura;
import com.vinculacion.app.views.JFrameNuevaCarrera;
import com.vinculacion.app.views.JFrameNuevaEmpresa;
import com.vinculacion.app.views.JFrameNuevaEscuela;
import com.vinculacion.app.views.JFrameNuevaFacultad;
import com.vinculacion.app.views.JFrameNuevaPasantia;
import com.vinculacion.app.views.JFrameNuevaSeccion;
import com.vinculacion.app.views.JFrameNuevoCargo;
import com.vinculacion.app.views.JFrameNuevoCargoDepartamental;
import com.vinculacion.app.views.JFrameNuevoDepartamento;
import com.vinculacion.app.views.JFrameNuevoDocente;
import com.vinculacion.app.views.JFrameNuevoEmpleado;
import com.vinculacion.app.views.JFrameNuevoEstudiante;
import com.vinculacion.app.views.JFrameNuevoGenero;
import com.vinculacion.app.views.JFrameNuevoHorarioPasantias;
import com.vinculacion.app.views.JFrameNuevoNivel;
import com.vinculacion.app.views.JFrameNuevoPerfil;
import com.vinculacion.app.views.JFrameNuevoPeriodoAcademico;
import com.vinculacion.app.views.JFrameNuevoTipoDedicacion;
import com.vinculacion.app.views.JFrameNuevoTipoDocumentoPracticas;
import com.vinculacion.app.views.JFrameNuevoUsuario;
import com.vinculacion.app.views.JFramePasantias;
import com.vinculacion.app.views.JFramePerfil;
import com.vinculacion.app.views.JFramePeriodoAcademico;
import com.vinculacion.app.views.JFrameSeccion;
import com.vinculacion.app.views.JFrameTipoDedicacion;
import com.vinculacion.app.views.JFrameTipoDocumentoPracticas;
import com.vinculacion.app.views.JFrameUsuarios;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    Auth auth = new Auth();            
    JFrameFacultad facultad;
    JFrameNuevaFacultad newFacultad;
    JFrameEditFacultad editfacultad;
    JFrameDocente jfrdocent;
    JFrameEditDocente jfreditdocent;
    JFrameNuevoDocente jfrNuevoDocent;
    JFrameAsignaturas jfrasig;
    JFrameEditAsignatura jfreditasig;
    JFrameNuevaAsignatura jfrnewasig;
    JFrameCargo jfrcargo;
    JFrameEditCargo jfreditcargo;
    JFrameNuevoCargo jfrnuevocargo;
    JFrameSeccion jfrseccion;
    JFrameEditSeccion jfreditseccion;
    JFrameNuevaSeccion jfrnuevaseccion;
    JFrameNivel jfrnivel;
    JFrameNuevoNivel jfrnuevonivel;
    JFrameEditNivel jfreditnivel;
    JFramePerfil jfrperfil;
    JFrameNuevoPerfil jfrnuevoperfil;
    JFrameEditPerfil jfreditperfil;
    JFrameTipoDocumentoPracticas jfrtipodocpract;
    JFrameEditTipoDocumentosPracticas jfredittipodocpract;
    JFrameNuevoTipoDocumentoPracticas jfrnuewtipodocpract;
    JFrameUsuarios jfruser;
    JFrameNuevoUsuario jfrnewuser;
    JFrameEditUsuario jfredituser;
    JFrameEditGenero jfreditgenero;
    JFrameNuevoGenero jfrnewgenero;
    JFrameGenero jfrgenero;
    JFrameHorarioPasantias jfrhorario;
    JFrameEditHorarioPasantias jfredithorario;
    JFrameNuevoHorarioPasantias jfrnuevohorario;
    JFrameTipoDedicacion jfrtipodedicacion;
    JFrameEditTipoDedicacion jfredittipodedicacion;
    JFrameNuevoTipoDedicacion jfrnuevotipodedicacion;
    JFramePeriodoAcademico jfrperiodo;
    JFrameEditPeriodoAcademico jfreditperiodo;
    JFrameNuevoPeriodoAcademico jfrnuevoperiodo;
    JFrameCarreras jfrcarrera;
    JFrameEditCarrera jfreditcarrera;
    JFrameNuevaCarrera jfrnuevacarrera;
    JFrameEscuela jfrescuela;
    JFrameEditEscuela jfreditescuela;
    JFrameNuevaEscuela jfrnuevaescuela;
    JFrameEmpresa jfrempresa;
    JFrameNuevaEmpresa jfrnuevaempresa;
    JFrameEditEmpresa jfreditempresa;
    JFrameEditDepartamento jfreditdepart;
    JFrameNuevoDepartamento jfrnuevodepart;
    JFrameDepartamento jfrdepart;
    JFrameCargoDepartamental jfrcd;
    JFrameEditCargoDepartamental jfreditcd;
    JFrameNuevoCargoDepartamental jfrnuevocd;
    JFrameEditEmpleado jfreditempleado;
    JFrameNuevoEmpleado jfrnuevoempleado;
    JFrameEmpleados jfrempleados;
    JFrameEstudiantes jfrestudiante;
    JFrameNuevoEstudiante jfrnuevoest;
    JFrameEditEstudiantes jfreditest;     
    JFramePasantias jfrpasantia;
    JFrameEditPasantia jfreditpasantia;
    JFrameNuevaPasantia jfrnuevapasantia;
    
    public MenuController(MenuPrincipal menu, Auth a) {
        this.mp = menu;
        this.auth = a;
        this.mp.menuItemCerrar.addActionListener(this);
        this.mp.menuItemSalir.addActionListener(this);
        this.mp.menuItemFacultad.addActionListener(this);
        this.mp.menuItemDocentes.addActionListener(this);
        this.mp.menuItemAsignaturas.addActionListener(this);
        this.mp.menuItemCargosDocentes.addActionListener(this);
        this.mp.menuItemSeccion.addActionListener(this);
        this.mp.menuItemNivel.addActionListener(this);
        this.mp.menuItemPerfil.addActionListener(this);
        this.mp.menuItemTipoDocPracticas.addActionListener(this);
        this.mp.menuItemUsuarios.addActionListener(this);
        this.mp.menuItemGenero.addActionListener(this);
        this.mp.menuItemHorarioPasantias.addActionListener(this);
        this.mp.menuItemTipoDedicacionDocente.addActionListener(this);
        this.mp.menuItemPeriodosAcademicos.addActionListener(this);
        this.mp.menuItemCarreras.addActionListener(this);
        this.mp.menuItemEscuelas.addActionListener(this);
        this.mp.menuItemEmpresa.addActionListener(this);
        this.mp.menuItemDepartamento.addActionListener(this);
        this.mp.menuItemCargoDepartamental.addActionListener(this);
        this.mp.menuItemEmpleados.addActionListener(this);
        this.mp.menuItemPasante.addActionListener(this);
        this.mp.menuItemPasantia.addActionListener(this);
        this.mp.menuItemInforme.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.mp.menuItemCerrar){
            this.mp.hide();
            this.auth.setVisible(true);
        }
        if (e.getSource()== this.mp.menuItemSalir) {
            System.exit(0);
        }
        if (e.getSource() == this.mp.menuItemFacultad) {
            this.facultad = new JFrameFacultad(this.mp, false);
            this.facultad.setVisible(true);
            this.newFacultad = new JFrameNuevaFacultad(mp, false);
            this.editfacultad = new JFrameEditFacultad(mp, false);
            FacultadController fcontrol = new FacultadController(mp, newFacultad, facultad, editfacultad);
        }
        if (e.getSource() == this.mp.menuItemDocentes) {
            this.jfrdocent = new JFrameDocente(mp, false);
            this.jfrNuevoDocent = new JFrameNuevoDocente(mp, false);
            this.jfreditdocent = new JFrameEditDocente(mp, false);
            this.jfrdocent.setVisible(true);
            DocentesController docente = new DocentesController(mp, jfrdocent, jfreditdocent, jfrNuevoDocent);
        }
        if (e.getSource() == this.mp.menuItemAsignaturas) {
            this.jfrasig = new JFrameAsignaturas(mp,false);
            this.jfreditasig = new JFrameEditAsignatura(mp, false);
            this.jfrnewasig = new JFrameNuevaAsignatura(mp, false);
            this.jfrasig.setVisible(true);
            AsignaturasController asigcontrol = new AsignaturasController(mp, jfrasig, jfreditasig, jfrnewasig);
        }
        if (e.getSource() == this.mp.menuItemCargosDocentes) {
            this.jfrcargo = new JFrameCargo(mp, false);
            this.jfrnuevocargo = new JFrameNuevoCargo(mp, false);
            this.jfreditcargo = new JFrameEditCargo(mp, false);
            this.jfrcargo.setVisible(true);
            CargoController cc = new CargoController(mp, jfrcargo, jfrnuevocargo, jfreditcargo);
        }
        if (e.getSource() == this.mp.menuItemSeccion) {
            this.jfrseccion = new JFrameSeccion(mp, false);
            this.jfrnuevaseccion = new JFrameNuevaSeccion(mp, false);
            this.jfreditseccion = new JFrameEditSeccion(mp, false);
            this.jfrseccion.setVisible(true);
            SeccionController sc = new SeccionController(mp, jfrseccion, jfrnuevaseccion, jfreditseccion);
        }
        if (e.getSource() == this.mp.menuItemNivel) {
            this.jfrnivel = new JFrameNivel(mp, false);
            this.jfrnuevonivel = new JFrameNuevoNivel(mp, false);
            this.jfreditnivel = new JFrameEditNivel(mp , false);
            this.jfrnivel.setVisible(true);
            NivelController nc = new NivelController(mp, jfrnivel, jfrnuevonivel, jfreditnivel);
        }
        if (e.getSource() == this.mp.menuItemPerfil) {
            this.jfrperfil = new JFramePerfil(mp, false);
            this.jfrnuevoperfil = new JFrameNuevoPerfil(mp, false);
            this.jfreditperfil = new JFrameEditPerfil(mp, false);
            this.jfrperfil.setVisible(true);
            PerfilController pc = new PerfilController(mp, jfrperfil, jfrnuevoperfil, jfreditperfil);
        }
        if (e.getSource() == this.mp.menuItemTipoDocPracticas) {
            this.jfrtipodocpract = new JFrameTipoDocumentoPracticas(mp, false);
            this.jfredittipodocpract = new JFrameEditTipoDocumentosPracticas(mp, false);
            this.jfrnuewtipodocpract = new JFrameNuevoTipoDocumentoPracticas(mp, false);
            this.jfrtipodocpract.setVisible(true);
            TipoDocumentosPracticasController tdpc = new TipoDocumentosPracticasController(mp, jfrtipodocpract, jfrnuewtipodocpract, jfredittipodocpract);
        }
        if (e.getSource() == this.mp.menuItemUsuarios) {
            this.jfrnewuser = new JFrameNuevoUsuario(mp, false);
            this.jfruser = new JFrameUsuarios(mp, false);
            this.jfredituser = new JFrameEditUsuario(mp, false);
            this.jfruser.setVisible(true);
            UsuariosController usercontrol = new UsuariosController(mp, jfruser, jfrnewuser, jfredituser);
        }
        if (e.getSource() == this.mp.menuItemGenero) {
            this.jfrnewgenero = new JFrameNuevoGenero(mp, false);
            this.jfrgenero = new JFrameGenero(mp, false);
            this.jfreditgenero = new JFrameEditGenero(mp, false);
            this.jfrgenero.setVisible(true);
            GeneroController gc = new GeneroController(mp, jfrgenero, jfrnewgenero, jfreditgenero);
        }
        if (e.getSource() == this.mp.menuItemHorarioPasantias) {
            this.jfrnuevohorario = new JFrameNuevoHorarioPasantias(mp, false);
            this.jfrhorario = new JFrameHorarioPasantias(mp, false);
            this.jfredithorario = new JFrameEditHorarioPasantias(mp, false);
            this.jfrhorario.setVisible(true);
            HorarioPasantiasController hpc = new HorarioPasantiasController(mp, jfrhorario, jfrnuevohorario, jfredithorario);
        }
        if (e.getSource() == this.mp.menuItemTipoDedicacionDocente) {
            this.jfrtipodedicacion = new JFrameTipoDedicacion(mp, false);
            this.jfredittipodedicacion = new JFrameEditTipoDedicacion(mp, false);
            this.jfrnuevotipodedicacion = new JFrameNuevoTipoDedicacion(mp, false);
            this.jfrtipodedicacion.setVisible(true);
            TipoDedicacionController tdc = new TipoDedicacionController(mp, jfrtipodedicacion, jfrnuevotipodedicacion, jfredittipodedicacion);
        }
        if (e.getSource() == this.mp.menuItemPeriodosAcademicos) {
            this.jfrperiodo = new JFramePeriodoAcademico(mp, false);
            this.jfreditperiodo = new JFrameEditPeriodoAcademico(mp, false);
            this.jfrnuevoperiodo = new JFrameNuevoPeriodoAcademico(mp, false);
            this.jfrperiodo.setVisible(true);
            PeriodoAcademicoController pacontrol = new PeriodoAcademicoController(mp, jfrperiodo, jfrnuevoperiodo, jfreditperiodo);
        }
        if (e.getSource() == this.mp.menuItemCarreras) {
            this.jfrnuevacarrera = new JFrameNuevaCarrera(mp, false);
            this.jfrcarrera = new JFrameCarreras(mp, false);
            this.jfreditcarrera = new JFrameEditCarrera(mp, false);
            this.jfrcarrera.setVisible(true);
            CarrerasController carreracontrol = new CarrerasController(mp, jfrcarrera, jfrnuevacarrera, jfreditcarrera);
        }
        if (e.getSource() == this.mp.menuItemEscuelas) {
            this.jfrescuela = new JFrameEscuela(mp, false);
            this.jfrnuevaescuela = new JFrameNuevaEscuela(mp, false);
            this.jfreditescuela = new JFrameEditEscuela(mp, false);
            this.jfrescuela.setVisible(true);
            EscuelaController econtrol = new EscuelaController(mp, jfrescuela, jfrnuevaescuela, jfreditescuela);
        }
        if (e.getSource() == this.mp.menuItemEmpresa) {
            this.jfrempresa = new JFrameEmpresa(mp, false);
            this.jfrnuevaempresa = new JFrameNuevaEmpresa(mp, false);
            this.jfreditempresa = new JFrameEditEmpresa(mp, false);
            this.jfrempresa.setVisible(true);
            EmpresaController emcontrol = new EmpresaController(mp, jfrnuevaempresa, jfrempresa, jfreditempresa);
        }
        if (e.getSource() == this.mp.menuItemDepartamento) {
            this.jfrnuevodepart = new JFrameNuevoDepartamento(mp, false);
            this.jfrdepart = new JFrameDepartamento(mp, false);
            this.jfreditdepart = new JFrameEditDepartamento(mp, false);
            this.jfrdepart.setVisible(true);
            DepartamentoController dcontrol= new DepartamentoController(mp, jfrnuevodepart, jfrdepart, jfreditdepart);
        }
        if (e.getSource() == this.mp.menuItemCargoDepartamental) {
            this.jfrnuevocd = new JFrameNuevoCargoDepartamental(mp, false);
            this.jfrcd = new JFrameCargoDepartamental(mp, false);
            this.jfreditcd = new JFrameEditCargoDepartamental(mp, false);
            this.jfrcd.setVisible(true);
            CargoDepartamentalController cdcontrol = new CargoDepartamentalController(mp, jfrcd, jfrnuevocd, jfreditcd);
        }
        if (e.getSource() == this.mp.menuItemEmpleados) {
            this.jfrempleados = new JFrameEmpleados(mp, false);
            this.jfrnuevoempleado = new JFrameNuevoEmpleado(mp, false);
            this.jfreditempleado = new JFrameEditEmpleado(mp, false);
            this.jfrempleados.setVisible(true);
            EmpleadosController emcontrol = new EmpleadosController(mp, jfreditempleado, jfrnuevoempleado, jfrempleados);
        }
        if (e.getSource() == this.mp.menuItemPasante) {
            this.jfrestudiante = new JFrameEstudiantes(mp, false);
            this.jfrnuevoest = new JFrameNuevoEstudiante(mp, false);
            this.jfreditest = new JFrameEditEstudiantes(mp, false);
            this.jfrestudiante.setVisible(true);
            EstudiantesController estcontrol = new EstudiantesController(mp, jfrestudiante, jfrnuevoest, jfreditest);
        }
        if (e.getSource() == this.mp.menuItemPasantia) {
            this.jfrnuevapasantia = new JFrameNuevaPasantia(mp, false);
            this.jfreditpasantia = new JFrameEditPasantia(mp, false);
            this.jfrpasantia = new JFramePasantias(mp, false);
            this.jfrpasantia.setVisible(true);
            PasantiasController pasantiacontrol = new PasantiasController(mp, jfrpasantia, jfrnuevapasantia, jfreditpasantia);
        }
    } 
    
}
