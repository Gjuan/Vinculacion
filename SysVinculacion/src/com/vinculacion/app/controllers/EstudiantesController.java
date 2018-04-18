package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.CarrerasDAO;
import com.vinculacion.app.dao.DocenteDAO;
import com.vinculacion.app.dao.EmpleadosDAO;
import com.vinculacion.app.dao.EstudiantesDAO;
import com.vinculacion.app.dao.GeneroDAO;
import com.vinculacion.app.dao.HorariosPasantiasDAO;
import com.vinculacion.app.dao.NivelDAO;
import com.vinculacion.app.dao.PeriodosAcademicosDAO;
import com.vinculacion.app.dao.SeccionDAO;
import com.vinculacion.app.dao.TipoDocumentoPracticasDAO;
import com.vinculacion.app.dao.UsuariosDAO;
import com.vinculacion.app.informes.InformesFinales;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.Empleados;
import com.vinculacion.app.model.Estudiantes;
import com.vinculacion.app.model.Genero;
import com.vinculacion.app.model.HorarioPasantias;
import com.vinculacion.app.model.Nivel;
import com.vinculacion.app.model.PeriodoAcademico;
import com.vinculacion.app.model.Seccion;
import com.vinculacion.app.model.TipoDocumentoPracticas;
import com.vinculacion.app.model.Usuarios;
import com.vinculacion.app.views.JFrameEditEstudiantes;
import com.vinculacion.app.views.JFrameEstudiantes;
import com.vinculacion.app.views.JFrameNuevoEstudiante;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class EstudiantesController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameEstudiantes jfrestudiante = new JFrameEstudiantes(mp, false);
    JFrameNuevoEstudiante nuevo = new JFrameNuevoEstudiante(mp, false);
    JFrameEditEstudiantes jfredit = new JFrameEditEstudiantes(mp, false);
    EstudiantesDAO estdao;
    CarrerasDAO cdao;
    DocenteDAO ddao;
    EmpleadosDAO emdao;       
    NivelDAO ndao;
    GeneroDAO gdao;
    SeccionDAO sdao;
    PeriodosAcademicosDAO padao;
    TipoDocumentoPracticasDAO tdpdao;
    HorariosPasantiasDAO hpdao;
    UsuariosDAO udao;
    
    JFileChooser file, file2;
    int returnVal, returnVal2;
    FileInputStream entrada;
    File archivo, archivo2;
    byte[] bImg;
    byte[] bImg2;
           
    public EstudiantesController(MenuPrincipal menu, JFrameEstudiantes est, JFrameNuevoEstudiante newest, JFrameEditEstudiantes edit) {
        this.mp = menu;
        this.jfrestudiante = est;
        this.nuevo = newest;
        this.jfredit = edit;
        
        this.jfrestudiante.btnBuscar.addActionListener(this);
        this.jfrestudiante.btnDarBaja.addActionListener(this);
        this.jfrestudiante.btnEditar.addActionListener(this);
        this.jfrestudiante.btnNuevo.addActionListener(this);
        this.jfrestudiante.btnImprimir.addActionListener(this);
        
        this.nuevo.btnGuardar.addActionListener(this);
        this.nuevo.btnRegresar.addActionListener(this);
        this.nuevo.btnSeleccionarFoto.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        this.jfredit.btnSeleccionarFoto.addActionListener(this);
        
        this.nuevo.txtCedula.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(Character.isLetter(e.getKeyChar())) {          
                    e.consume();               
                    JOptionPane.showMessageDialog(nuevo, "La cédula debe ser númerica!!");   
                    nuevo.txtCedula.setText("");
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        estdao = new EstudiantesDAO();
        cdao = new CarrerasDAO();
        ddao = new DocenteDAO();
        emdao = new EmpleadosDAO();
        ndao = new NivelDAO();
        gdao = new GeneroDAO();
        sdao = new SeccionDAO();
        padao = new PeriodosAcademicosDAO();
        tdpdao = new TipoDocumentoPracticasDAO();
        hpdao = new HorariosPasantiasDAO();
        udao = new UsuariosDAO();
        
        this.jfrestudiante.comboDocente.removeAllItems();
        List<Docente> ldocente = ddao.AllDocente();
        this.jfrestudiante.comboDocente.addItem("Todos");
        for (Docente docente : ldocente) {
            this.jfrestudiante.comboDocente.addItem(docente.getAPELLIDOS() + "-" + docente.getNOMBRES());
        }
        
        this.jfrestudiante.comboTutorEmpresarial.removeAllItems();
        List<Empleados> lemp = emdao.AllEmpleados();
        this.jfrestudiante.comboTutorEmpresarial.addItem("Todos");
        for (Empleados empleado : lemp) {
            this.jfrestudiante.comboTutorEmpresarial.addItem(empleado.getAPELLIDOS() + "-" + empleado.getNOMBRES() + "-EMPRESA: " + empleado.getDepartamentos().getEmpresa().getNOMBRE_EMPRESA());
        }
        
        this.jfrestudiante.comboCarrera.removeAllItems();
        List<Carreras> lcarrera = cdao.AllCarreras();
        this.jfrestudiante.comboCarrera.addItem("Todas");
        for (Carreras carrera : lcarrera) {
            this.jfrestudiante.comboCarrera.addItem(carrera.getDESCRIPCION());
        }
        
        this.jfrestudiante.comboTipoDocPracticas.removeAllItems();
        List<TipoDocumentoPracticas> ltdp = tdpdao.AllTiposDocumentosPracticas();
        this.jfrestudiante.comboTipoDocPracticas.addItem("Todos");
        for (TipoDocumentoPracticas tdp : ltdp) {
            this.jfrestudiante.comboTipoDocPracticas.addItem(tdp.getDescripcion());
        }
        
        this.jfrestudiante.periodoAcademico.removeAllItems();
        List<PeriodoAcademico> lperiodo = padao.AllPeriodosAcademicos();
        this.jfrestudiante.periodoAcademico.addItem("Todos");
        for (PeriodoAcademico periodoAcademico : lperiodo) {
            this.jfrestudiante.periodoAcademico.addItem(periodoAcademico.getFECHA_INICIO_PERIODO());           
        }
        
        getAllEstudiantes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrestudiante.btnBuscar) {
            getAllEstudiantes();
        }
        if (e.getSource() == this.jfrestudiante.btnImprimir) {
            if (this.jfrestudiante.txtCedula.getText().isEmpty() && this.jfrestudiante.comboCarrera.getSelectedIndex() == 0 && this.jfrestudiante.comboDocente.getSelectedIndex() == 0 && this.jfrestudiante.comboTipoDocPracticas.getSelectedIndex() == 0 && this.jfrestudiante.comboTutorEmpresarial.getSelectedIndex() == 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                try {
                    InformesFinales informe = new InformesFinales();
                    informe.InformeEstudiantes();
                    informe.cerrar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");
                }
            }else if(this.jfrestudiante.comboCarrera.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                try {
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByCarrera(this.jfrestudiante.comboCarrera.getSelectedItem().toString());
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.comboCarrera.setSelectedIndex(0);
                
            }else if(this.jfrestudiante.comboCarrera.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() != 0){
                try {
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByCarreraAndPeriodo(this.jfrestudiante.comboCarrera.getSelectedItem().toString(), this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.comboCarrera.setSelectedIndex(0);
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
                
            }else if(this.jfrestudiante.comboDocente.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                try {
                    String [] namesDocente = this.jfrestudiante.comboDocente.getSelectedItem().toString().split("-");
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByDocentes(namesDocente[1], namesDocente[0]);
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.comboDocente.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboDocente.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() != 0){
                try {
                    String [] namesDocente = this.jfrestudiante.comboDocente.getSelectedItem().toString().split("-");
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByDocentesAndPeriodo(namesDocente[1], namesDocente[0], this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.comboDocente.setSelectedIndex(0);
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboTipoDocPracticas.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                try {
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByDocumento(this.jfrestudiante.comboTipoDocPracticas.getSelectedItem().toString());
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.comboTipoDocPracticas.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboTipoDocPracticas.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() != 0){
                try {
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByDocumentoAndPeriodo(this.jfrestudiante.comboTipoDocPracticas.getSelectedItem().toString(), this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.comboTipoDocPracticas.setSelectedIndex(0);
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboTutorEmpresarial.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                try {
                    String [] namesEmpleado = this.jfrestudiante.comboTutorEmpresarial.getSelectedItem().toString().split("-");
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByEmpleado(namesEmpleado[1], namesEmpleado[0]);
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.comboTutorEmpresarial.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboTutorEmpresarial.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() != 0){
                try {
                    String [] namesEmpleado = this.jfrestudiante.comboTutorEmpresarial.getSelectedItem().toString().split("-");                        
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByEmpleadoAndPeriodo(namesEmpleado[1], namesEmpleado[0], this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.comboTutorEmpresarial.setSelectedIndex(0);
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }else {
                try {
                    InformesFinales informe = new InformesFinales();
                    informe.InformePasantesByPeriodo(this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                    informe.cerrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this.jfrestudiante, "Informe vacío");                
                }
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }        
        }
        if (e.getSource() == this.jfrestudiante.btnDarBaja) {
            try {
                String id = this.jfrestudiante.tableEstudiantes.getValueAt(this.jfrestudiante.tableEstudiantes.getSelectedRow(), 0).toString();
                int i = JOptionPane.showConfirmDialog(this.jfrestudiante, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    estdao.deleteEstudiante(id);
                    JOptionPane.showMessageDialog(this.jfrestudiante,  "Se ha dado de baja a este estudiante!!");
                    getAllEstudiantes();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrestudiante,"Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrestudiante.btnEditar) {
            try {
                if (!this.jfrestudiante.tableEstudiantes.getValueAt(this.jfrestudiante.tableEstudiantes.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.comboCarrera.removeAllItems();
                    List<Carreras> lcarrera = cdao.AllCarreras();
                    for (Carreras carreras : lcarrera) {
                        this.jfredit.comboCarrera.addItem(carreras.getDESCRIPCION());
                    }

                    this.jfredit.comboGenero.removeAllItems();
                    List<Genero> lgen = gdao.AllGeneros();
                    for (Genero genero : lgen) {
                        this.jfredit.comboGenero.addItem(genero.getDESCRIPCION());
                    }

                    this.jfredit.comboHorario.removeAllItems();
                    List<HorarioPasantias> lh = hpdao.AllHorariosPasantias();
                    for (HorarioPasantias horarioPasantias : lh) {
                        this.jfredit.comboHorario.addItem(horarioPasantias.getDESCRIPCION_HORARIO());
                    }

                    this.jfredit.comboNivel.removeAllItems();
                    List<Nivel> lnivel = ndao.AllNiveles();
                    for (Nivel nivel : lnivel) {
                        this.jfredit.comboNivel.addItem(nivel.getSEMESTRE());
                    }

                    this.jfredit.comboPeriodoAcademico.removeAllItems();
                    List<PeriodoAcademico> lpea = padao.AllPeriodosAcademicos();
                    for (PeriodoAcademico periodoAcademico : lpea) {
                        this.jfredit.comboPeriodoAcademico.addItem(periodoAcademico.getFECHA_INICIO_PERIODO());
                    }

                    this.jfredit.comboSeccion.removeAllItems();
                    List<Seccion> lseccion = sdao.AllSecciones();
                    for (Seccion seccion : lseccion) {
                        this.jfredit.comboSeccion.addItem(seccion.getDESCRIPCION());
                    }
                    this.jfredit.comboTipoDocumento.removeAllItems();
                    List<TipoDocumentoPracticas> ltdp = tdpdao.AllTiposDocumentosPracticas();
                    for (TipoDocumentoPracticas tipoDocumentoPracticas : ltdp) {
                        this.jfredit.comboTipoDocumento.addItem(tipoDocumentoPracticas.getDescripcion());
                    }

                    this.jfredit.comboTutorDocente.removeAllItems();
                    List<Docente> ldocente = ddao.AllDocente();
                    for (Docente docente : ldocente) {
                        this.jfredit.comboTutorDocente.addItem(docente.getNOMBRES() + "-"+ docente.getAPELLIDOS());
                    }

                    this.jfredit.comboTutorEmpresarial.removeAllItems();
                    List<Empleados> lemp = emdao.AllEmpleados();
                    for (Empleados empleados : lemp) {
                        this.jfredit.comboTutorEmpresarial.addItem(empleados.getNOMBRES() + "-" + empleados.getAPELLIDOS());
                    }
                    
                    this.jfredit.txtCodigo.setText(this.jfrestudiante.tableEstudiantes.getValueAt(this.jfrestudiante.tableEstudiantes.getSelectedRow(), 0).toString());
                    this.jfredit.txtCedula.setText(this.jfrestudiante.tableEstudiantes.getValueAt(this.jfrestudiante.tableEstudiantes.getSelectedRow(), 1).toString());                    
                    Estudiantes est = estdao.findEstudianteByCedula(this.jfrestudiante.tableEstudiantes.getValueAt(this.jfrestudiante.tableEstudiantes.getSelectedRow(), 1).toString());
                    this.jfredit.txtApellidos.setText(est.getAPELLIDOS());
                    this.jfredit.txtNombre.setText(est.getNOMBRES());
                    this.jfredit.txtCodigoMatricula.setText(est.getCOD_MATRICULA());
                    this.jfredit.txtCorreo.setText(est.getCORREO());
                    this.jfredit.txtDireccion.setText(est.getDIRECCION());
                    this.jfredit.txtTelefono.setText(est.getTELEFONO());
                    this.jfredit.comboCarrera.setSelectedItem(est.getCarrera().getDESCRIPCION());
                    this.jfredit.comboGenero.setSelectedItem(est.getGenero().getDESCRIPCION());
                    this.jfredit.comboHorario.setSelectedItem(est.getHorarioPasantias().getDESCRIPCION_HORARIO());
                    this.jfredit.comboNivel.setSelectedItem(est.getNivel().getSEMESTRE());
                    this.jfredit.comboPeriodoAcademico.setSelectedItem(est.getPeriodoAcademico().getFECHA_INICIO_PERIODO());
                    this.jfredit.comboSeccion.setSelectedItem(est.getSeccion().getDESCRIPCION());
                    this.jfredit.comboTipoDocumento.setSelectedItem(est.getTipoDocumentoPracticas().getDescripcion());                    
                    this.jfredit.comboTutorDocente.setSelectedItem(est.getDocente().getNOMBRES() + "-" + est.getDocente().getAPELLIDOS());
                    this.jfredit.comboTutorEmpresarial.setSelectedItem(est.getEmpleado().getNOMBRES() + "-" + est.getEmpleado().getAPELLIDOS());
                    this.jfredit.comboEstado.setSelectedItem(est.getESTADO());
                    
                    ImageIcon img = new ImageIcon(est.getFOTO());
                    Icon icono = new ImageIcon(img.getImage().getScaledInstance(this.jfredit.lbFotoPasante.getWidth(), this.jfredit.lbFotoPasante.getHeight(), Image.SCALE_DEFAULT));
                    this.jfredit.lbFotoPasante.setIcon(icono);
                    this.jfredit.repaint();
                }else{
                    JOptionPane.showMessageDialog(mp,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(mp,"Seleccione un registro y luego de click en editar");
            }
        }        
        if (e.getSource() == this.jfrestudiante.btnNuevo) {
            this.nuevo.setVisible(true);
            
            this.nuevo.comboCarrera.removeAllItems();
            List<Carreras> lcarrera = cdao.AllCarreras();
            for (Carreras carreras : lcarrera) {
                this.nuevo.comboCarrera.addItem(carreras.getDESCRIPCION());
            }
            
            this.nuevo.comboGenero.removeAllItems();
            List<Genero> lgen = gdao.AllGeneros();
            for (Genero genero : lgen) {
                this.nuevo.comboGenero.addItem(genero.getDESCRIPCION());
            }
            
            this.nuevo.comboHorario.removeAllItems();
            List<HorarioPasantias> lh = hpdao.AllHorariosPasantias();
            for (HorarioPasantias horarioPasantias : lh) {
                this.nuevo.comboHorario.addItem(horarioPasantias.getDESCRIPCION_HORARIO());
            }
            
            this.nuevo.comboNivel.removeAllItems();
            List<Nivel> lnivel = ndao.AllNiveles();
            for (Nivel nivel : lnivel) {
                this.nuevo.comboNivel.addItem(nivel.getSEMESTRE());
            }
            
            this.nuevo.comboPeriodoAcademico.removeAllItems();
            List<PeriodoAcademico> lpea = padao.AllPeriodosAcademicos();
            for (PeriodoAcademico periodoAcademico : lpea) {
                this.nuevo.comboPeriodoAcademico.addItem(periodoAcademico.getFECHA_INICIO_PERIODO());
            }
            
            this.nuevo.comboSeccion.removeAllItems();
            List<Seccion> lseccion = sdao.AllSecciones();
            for (Seccion seccion : lseccion) {
                this.nuevo.comboSeccion.addItem(seccion.getDESCRIPCION());
            }
            this.nuevo.comboTipoDocumento.removeAllItems();
            List<TipoDocumentoPracticas> ltdp = tdpdao.AllTiposDocumentosPracticas();
            for (TipoDocumentoPracticas tipoDocumentoPracticas : ltdp) {
                this.nuevo.comboTipoDocumento.addItem(tipoDocumentoPracticas.getDescripcion());
            }
            
            this.nuevo.comboTutorDocente.removeAllItems();
            List<Docente> ldocente = ddao.AllDocente();
            for (Docente docente : ldocente) {
                this.nuevo.comboTutorDocente.addItem(docente.getNOMBRES() + "-"+ docente.getAPELLIDOS());
            }
            
            this.nuevo.comboTutorEmpresarial.removeAllItems();
            List<Empleados> lemp = emdao.AllEmpleados();
            for (Empleados empleados : lemp) {
                this.nuevo.comboTutorEmpresarial.addItem(empleados.getNOMBRES() + "-" + empleados.getAPELLIDOS());
            }
            
            this.jfrestudiante.dispose();
        }
        /*Nuevo estudiante*/
        if (e.getSource() == this.nuevo.btnGuardar) {
            try {
                if (this.nuevo.txtCedula.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.nuevo, "La cédula es requerida");
                }else if(this.nuevo.txtCedula.getText().length() != 10){
                    JOptionPane.showMessageDialog(this.nuevo, "La cédula debe tener 10 dígitos");
                }else if(this.nuevo.txtCodigoMatricula.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.nuevo, "El código de matricula es requerido");
                }else if(this.nuevo.txtNombre.getText().toString().isEmpty()){
                    JOptionPane.showMessageDialog(this.nuevo, "El nombre es requerido");                
                }else if(this.nuevo.txtApellidos.getText().toString().isEmpty()){
                    JOptionPane.showMessageDialog(this.nuevo, "El apellido es requerido");                                
                }else{
                    try {
                        Estudiantes estu = estdao.findEstudianteByCedula(this.nuevo.txtCedula.getText().toString());
                        JOptionPane.showMessageDialog(this.nuevo, "Este estudiante con cédula " + estu.getCEDULA()+ " ya existe en el sistema");
                    } catch (Exception err) {
                        Estudiantes est = new Estudiantes();
                        est.setAPELLIDOS(this.nuevo.txtApellidos.getText().toString().toUpperCase());
                        est.setNOMBRES(this.nuevo.txtNombre.getText().toString().toUpperCase());
                        est.setCEDULA(this.nuevo.txtCedula.getText().toString());
                        est.setCOD_MATRICULA(this.nuevo.txtCodigoMatricula.getText().toString());
                        est.setCORREO(this.nuevo.txtCorreo.getText().toString().toLowerCase());

                        Carreras carrera = cdao.findCarreraByDescription(this.nuevo.comboCarrera.getSelectedItem().toString());
                        est.setCarrera(carrera);

                        Nivel nivel = ndao.findNivelBySemestre(this.nuevo.comboNivel.getSelectedItem().toString());
                        est.setNivel(nivel);

                        Genero genero = gdao.findGeneroByDescription(this.nuevo.comboGenero.getSelectedItem().toString());
                        est.setGenero(genero);

                        Seccion seccion = sdao.findSeccionByDescription(this.nuevo.comboSeccion.getSelectedItem().toString());
                        est.setSeccion(seccion);

                        est.setDIRECCION(this.nuevo.txtDireccion.getText().toString());

                        String [] profesor = this.nuevo.comboTutorDocente.getSelectedItem().toString().split("-");
                        Docente docente = ddao.findDocenteByLastNameAndName(profesor[0], profesor[1]);
                        est.setDocente(docente);

                        String [] tutorEmpresarial = this.nuevo.comboTutorEmpresarial.getSelectedItem().toString().split("-");                    
                        Empleados empleado = emdao.findEmpleadosByLastNameAndName(tutorEmpresarial[0], tutorEmpresarial[1]);
                        est.setEmpleado(empleado);

                        PeriodoAcademico periodo = padao.findPeriodoAcademicoByStartDate(this.nuevo.comboPeriodoAcademico.getSelectedItem().toString());                    
                        est.setPeriodoAcademico(periodo);

                        est.setFOTO(bImg);

                        TipoDocumentoPracticas tdp = tdpdao.findTipoDocumentoPracticasByDescription(this.nuevo.comboTipoDocumento.getSelectedItem().toString());
                        est.setTipoDocumentoPracticas(tdp);

                        HorarioPasantias hp = hpdao.findHorarioPasantiaByDescription(this.nuevo.comboHorario.getSelectedItem().toString());
                        est.setHorarioPasantias(hp);

                        est.setTELEFONO(this.nuevo.txtTelefono.getText().toString());

                        int iduser = AuthController.id;
                        Usuarios usuario = udao.findUsuarioById(iduser);
                        est.setUsuario(usuario);

                        est.setESTADO("ACTIVO");
                        estdao.saveEstudiante(est);
                        JOptionPane.showMessageDialog(this.nuevo, "Pasante ingresado correctamente!!");

                        this.nuevo.txtApellidos.setText("");
                        this.nuevo.txtNombre.setText("");
                        this.nuevo.txtCodigoMatricula.setText("");
                        this.nuevo.txtCorreo.setText("");
                        this.nuevo.txtDireccion.setText("");
                        this.nuevo.txtCedula.setText("");
                        this.nuevo.txtTelefono.setText("");
                        this.nuevo.comboCarrera.setSelectedIndex(0);
                        this.nuevo.comboGenero.setSelectedIndex(0);
                        this.nuevo.comboHorario.setSelectedIndex(0);
                        this.nuevo.comboNivel.setSelectedIndex(0);
                        this.nuevo.comboPeriodoAcademico.setSelectedIndex(0);
                        this.nuevo.comboSeccion.setSelectedIndex(0);
                        this.nuevo.comboTipoDocumento.setSelectedIndex(0);
                        this.nuevo.comboTutorDocente.setSelectedIndex(0);
                        this.nuevo.comboTutorEmpresarial.setSelectedIndex(0);
                        this.nuevo.lbFotoPasante.setIcon(null);
                        this.nuevo.repaint();
                        this.nuevo.txtCedula.requestFocus();
                    }   
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.nuevo, "Error: " + ex.getMessage());
            }            
        }
        if (e.getSource() == this.nuevo.btnRegresar) {
            this.nuevo.dispose();
            this.jfrestudiante.setVisible(true);
            getAllEstudiantes();
        }
        if (e.getSource() == this.nuevo.btnSeleccionarFoto) {
            try {
                file = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
                file.setFileFilter(filter);
                returnVal = file.showOpenDialog(this.nuevo);                
                if(returnVal == JFileChooser.APPROVE_OPTION) {                  
                    archivo = new File(file.getSelectedFile().getAbsolutePath());
                    bImg = Visor(archivo);
                    ImageIcon img = new ImageIcon(bImg);
                    Icon icono = new ImageIcon(img.getImage().getScaledInstance(this.nuevo.lbFotoPasante.getWidth(), this.nuevo.lbFotoPasante.getHeight(), Image.SCALE_DEFAULT));
                    this.nuevo.lbFotoPasante.setIcon(icono);
                    this.nuevo.repaint();            
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.nuevo,"Error: " + ex.getMessage());
            }             
        }
        /*Editar estudiante*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            try {
                Estudiantes est = new Estudiantes();
                est.setCODIGO(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                est.setCEDULA(this.jfredit.txtCedula.getText().toString());
                est.setNOMBRES(this.jfredit.txtNombre.getText().toString().toUpperCase());
                est.setAPELLIDOS(this.jfredit.txtApellidos.getText().toString().toUpperCase());
                est.setCOD_MATRICULA(this.jfredit.txtCodigoMatricula.getText().toString());
                est.setDIRECCION(this.jfredit.txtDireccion.getText().toString().toUpperCase());
                est.setTELEFONO(this.jfredit.txtTelefono.getText().toString());
                est.setCORREO(this.jfredit.txtCorreo.getText().toString().toLowerCase());
                Carreras carrera = cdao.findCarreraByDescription(this.jfredit.comboCarrera.getSelectedItem().toString());
                est.setCarrera(carrera);                
                Nivel niv = ndao.findNivelBySemestre(this.jfredit.comboNivel.getSelectedItem().toString());
                est.setNivel(niv);
                Genero genero = gdao.findGeneroByDescription(this.jfredit.comboGenero.getSelectedItem().toString());
                est.setGenero(genero);
                Seccion seccion = sdao.findSeccionByDescription(this.jfredit.comboSeccion.getSelectedItem().toString());
                est.setSeccion(seccion);
                PeriodoAcademico periodo = padao.findPeriodoAcademicoByStartDate(this.jfredit.comboPeriodoAcademico.getSelectedItem().toString());
                est.setPeriodoAcademico(periodo);
                String profesor[] = this.jfredit.comboTutorDocente.getSelectedItem().toString().split("-");
                Docente docente = ddao.findDocenteByLastNameAndName(profesor[0], profesor[1]);
                est.setDocente(docente);
                String emplead [] = this.jfredit.comboTutorEmpresarial.getSelectedItem().toString().split("-");
                Empleados empleado = emdao.findEmpleadosByLastNameAndName(emplead[0], emplead[1]);
                est.setEmpleado(empleado);
                TipoDocumentoPracticas tdp = tdpdao.findTipoDocumentoPracticasByDescription(this.jfredit.comboTipoDocumento.getSelectedItem().toString());
                est.setTipoDocumentoPracticas(tdp);
                HorarioPasantias horario =  hpdao.findHorarioPasantiaByDescription(this.jfredit.comboHorario.getSelectedItem().toString());
                est.setHorarioPasantias(horario);
                Usuarios usuario = udao.findUsuarioById(AuthController.id);
                est.setUsuario(usuario);        
                est.setESTADO(this.jfredit.comboEstado.getSelectedItem().toString());               
                
                if (returnVal2 == JFileChooser.APPROVE_OPTION) {
                    est.setFOTO(bImg2);               
                }else{
                    Estudiantes estudiante = estdao.findEstudianteById(this.jfredit.txtCodigo.getText().toString());                                   
                    est.setFOTO(estudiante.getFOTO());
                }            
                estdao.updateEstudiante(est);
                JOptionPane.showMessageDialog(this.jfredit, "Estudiante actualizado");
                getAllEstudiantes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
        if (e.getSource() == this.jfredit.btnSeleccionarFoto) {
            try {
                file2 = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
                file2.setFileFilter(filter);
                returnVal2 = file2.showOpenDialog(this.jfredit);                
                if(returnVal2 == JFileChooser.APPROVE_OPTION) {                  
                    archivo2 = new File(file2.getSelectedFile().getAbsolutePath());
                    bImg2 = Visor(archivo2);
                    ImageIcon img = new ImageIcon(bImg2);
                    Icon icono = new ImageIcon(img.getImage().getScaledInstance(this.jfredit.lbFotoPasante.getWidth(), this.jfredit.lbFotoPasante.getHeight(), Image.SCALE_DEFAULT));
                    this.jfredit.lbFotoPasante.setIcon(icono);
                    this.jfredit.repaint();            
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit,"Error: " + ex.getMessage());
            }
        }
    }    

    public void getAllEstudiantes() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrestudiante.tableEstudiantes.setModel(model);
            model.addColumn("Código");
            model.addColumn("Cédula");
            model.addColumn("Código de matricula");
            model.addColumn("Nombres");
            model.addColumn("Apellidos");
            model.addColumn("Dirección");
            model.addColumn("Teléfono");
            model.addColumn("Correo");
            model.addColumn("Carrera");
            model.addColumn("Nivel");
            model.addColumn("Género");
            model.addColumn("Periodo académico");
            model.addColumn("Sección");
            model.addColumn("Tutor docente");
            model.addColumn("Tutor de la empresa");
            model.addColumn("Tipo de documento de prácticas");
            model.addColumn("Horario de pasantías");
            model.addColumn("Estado");
            
            Object [] data = new Object[19];                    

            if (this.jfrestudiante.txtCedula.getText().isEmpty() && this.jfrestudiante.comboCarrera.getSelectedIndex() == 0 && this.jfrestudiante.comboDocente.getSelectedIndex() == 0 && this.jfrestudiante.comboTipoDocPracticas.getSelectedIndex() == 0 && this.jfrestudiante.comboTutorEmpresarial.getSelectedIndex() == 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0) {
                this.jfrestudiante.tableEstudiantes.removeAll();
                List<Estudiantes> lest = estdao.AllEstudiantes();
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
            }else if(!this.jfrestudiante.txtCedula.getText().isEmpty()){
                this.jfrestudiante.tableEstudiantes.removeAll();               
                Estudiantes est = estdao.findEstudianteByCedula(this.jfrestudiante.txtCedula.getText().toString());
                if (est != null) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();                    
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero();
                    data[11] = est.getPeriodoAcademico();
                    data[12] = est.getSeccion();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrestudiante, "El estudiante con cédula " + this.jfrestudiante.txtCedula.getText().toString() + " no existe");
                }
                this.jfrestudiante.txtCedula.setText("");
            }else if(this.jfrestudiante.comboCarrera.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                this.jfrestudiante.tableEstudiantes.removeAll();
                List<Estudiantes> lest = estdao.EstudiantesByCarrera(this.jfrestudiante.comboCarrera.getSelectedItem().toString());
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.comboCarrera.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboCarrera.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() != 0){
                this.jfrestudiante.tableEstudiantes.removeAll();
            
                List<Estudiantes> lest = estdao.EstudiantesByCarrera(this.jfrestudiante.comboCarrera.getSelectedItem().toString(), this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.comboCarrera.setSelectedIndex(0);
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboDocente.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                this.jfrestudiante.tableEstudiantes.removeAll();
                String [] namesDocente = this.jfrestudiante.comboDocente.getSelectedItem().toString().split("-");
                List<Estudiantes> lest = estdao.EstudiantesByTutorDocente(namesDocente[1], namesDocente[0]);
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.comboDocente.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboDocente.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() != 0){
            this.jfrestudiante.tableEstudiantes.removeAll();
                String [] namesDocente = this.jfrestudiante.comboDocente.getSelectedItem().toString().split("-");           
                List<Estudiantes> lest = estdao.EstudiantesByTutorDocente(namesDocente[1], namesDocente[0], this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.comboDocente.setSelectedIndex(0);
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboTipoDocPracticas.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                this.jfrestudiante.tableEstudiantes.removeAll();
                List<Estudiantes> lest = estdao.EstudiantesByTipoDocPracticas(this.jfrestudiante.comboTipoDocPracticas.getSelectedItem().toString());
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.comboTipoDocPracticas.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboTipoDocPracticas.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() != 0){
                this.jfrestudiante.tableEstudiantes.removeAll();      
                List<Estudiantes> lest = estdao.EstudiantesByTipoDocPracticas(this.jfrestudiante.comboTipoDocPracticas.getSelectedItem().toString(), this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.comboTipoDocPracticas.setSelectedIndex(0);
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboTutorEmpresarial.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() == 0){
                this.jfrestudiante.tableEstudiantes.removeAll();
                String [] namesEmpleado = this.jfrestudiante.comboTutorEmpresarial.getSelectedItem().toString().split("-");
                List<Estudiantes> lest = estdao.EstudiantesByTutorEmpresarial(namesEmpleado[1], namesEmpleado[0]);
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.comboTutorEmpresarial.setSelectedIndex(0);
            }else if(this.jfrestudiante.comboTutorEmpresarial.getSelectedIndex() != 0 && this.jfrestudiante.periodoAcademico.getSelectedIndex() != 0){
                this.jfrestudiante.tableEstudiantes.removeAll();
                String [] namesEmpleado = this.jfrestudiante.comboTutorEmpresarial.getSelectedItem().toString().split("-");
                List<Estudiantes> lest = estdao.EstudiantesByTutorEmpresarial(namesEmpleado[1], namesEmpleado[0], this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.comboTutorEmpresarial.setSelectedIndex(0);
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }else{
                this.jfrestudiante.tableEstudiantes.removeAll();
                
                List<Estudiantes> lest = estdao.EstudiantesByPeriodoAcadémico(this.jfrestudiante.periodoAcademico.getSelectedItem().toString());
                for (Estudiantes est : lest) {
                    data[0] = est.getCODIGO();
                    data[1] = est.getCEDULA();
                    data[2] = est.getCOD_MATRICULA();
                    data[3] = est.getNOMBRES();
                    data[4] = est.getAPELLIDOS();
                    data[5] = est.getDIRECCION();
                    data[6] = est.getTELEFONO();
                    data[7] = est.getCORREO();
                    data[8] = est.getCarrera().getDESCRIPCION();
                    data[9] = est.getNivel().getSEMESTRE();
                    data[10] = est.getGenero().getDESCRIPCION();
                    data[11] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[12] = est.getSeccion().getDESCRIPCION();
                    data[13] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[14] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[15] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[16] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[17] = est.getESTADO();
                    model.addRow(data);
                }
                this.jfrestudiante.periodoAcademico.setSelectedIndex(0);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrestudiante, "Error: " + ex.getMessage());
        }
    }   
    
    public byte[] Visor(File f) {
        byte[] bImg = new byte[(int) f.length()];
        try {
            entrada = new FileInputStream(f);
            entrada.read(bImg);
        } catch (Exception e) {}
        return bImg;
    }
}
