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
        
        this.nuevo.btnGuardar.addActionListener(this);
        this.nuevo.btnRegresar.addActionListener(this);
        this.nuevo.btnSeleccionarFoto.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        this.jfredit.btnSeleccionarFoto.addActionListener(this);
        
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
        
        getAllEstudiantes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrestudiante.btnBuscar) {
            getAllEstudiantes();
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
            
            /*FALTA AGREGAR LOS DATOS A LOS DEMÁS COMBO*/
            
            this.jfrestudiante.dispose();
        }
        /*Nuevo estudiante*/
        if (e.getSource() == this.nuevo.btnGuardar) {
            try {
                if (this.nuevo.txtCedula.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.nuevo, "La cédula es requerida");
                }else if(this.nuevo.txtCodigoMatricula.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.nuevo, "El código de matricula es requerido");
                }else if(this.nuevo.txtNombre.getText().toString().isEmpty()){
                    JOptionPane.showMessageDialog(this.nuevo, "El nombre es requerido");                
                }else if(this.nuevo.txtApellidos.getText().toString().isEmpty()){
                    JOptionPane.showMessageDialog(this.nuevo, "El apellido es requerido");                                
                }else{
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
                    
                    est.setFOTO(this.file.getSelectedFile().getName());
                   
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
                    this.nuevo.txtTelefono.setText("");
                    this.nuevo.txtCedula.requestFocus();
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
                    archivo = file.getSelectedFile();
                    bImg = Visor(archivo);
                    ImageIcon img = new ImageIcon(bImg);
                    Icon icono = new ImageIcon(img.getImage().getScaledInstance(this.nuevo.lbFotoPasante.getWidth(), this.nuevo.lbFotoPasante.getHeight(), Image.SCALE_DEFAULT));
                    this.nuevo.lbFotoPasante.setIcon(icono);
                    this.nuevo.repaint();            
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(jfrestudiante,"Error: " + ex.getMessage());
            }             
        }
        /*Editar estudiante*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            if(returnVal2 == JFileChooser.APPROVE_OPTION) {                  
                
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
        if (e.getSource() == this.jfredit.btnSeleccionarFoto) {
            file2 = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
            file2.setFileFilter(filter);
            returnVal2 = file2.showOpenDialog(this.jfredit);                
            if(returnVal2 == JFileChooser.APPROVE_OPTION) {                  
                archivo2 = file2.getSelectedFile();
                bImg2 = Visor(archivo2);
                ImageIcon img = new ImageIcon(bImg2);
                Icon icono = new ImageIcon(img.getImage().getScaledInstance(this.jfredit.lbFotoPasante.getWidth(), this.jfredit.lbFotoPasante.getHeight(), Image.SCALE_DEFAULT));
                this.jfredit.lbFotoPasante.setIcon(icono);
                this.jfredit.repaint();                
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
            model.addColumn("Foto");
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

            if (this.jfrestudiante.txtCedula.getText().isEmpty()) {
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
                    data[8] = est.getFOTO();
                    data[9] = est.getCarrera().getDESCRIPCION();
                    data[10] = est.getNivel().getSEMESTRE();
                    data[11] = est.getGenero().getDESCRIPCION();
                    data[12] = est.getPeriodoAcademico().getFECHA_INICIO_PERIODO();
                    data[13] = est.getSeccion().getDESCRIPCION();
                    data[14] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[15] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[16] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[17] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[18] = est.getESTADO();
                    model.addRow(data);
                }
            }else{
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
                    data[8] = est.getFOTO();
                    data[9] = est.getCarrera().getDESCRIPCION();
                    data[10] = est.getNivel().getSEMESTRE();
                    data[11] = est.getGenero();
                    data[12] = est.getPeriodoAcademico();
                    data[13] = est.getSeccion();
                    data[14] = est.getDocente().getAPELLIDOS() + "-" + est.getDocente().getNOMBRES();
                    data[15] = est.getEmpleado().getAPELLIDOS() + "-" + est.getEmpleado().getNOMBRES();
                    data[16] = est.getTipoDocumentoPracticas().getDescripcion();
                    data[17] = est.getHorarioPasantias().getDESCRIPCION_HORARIO();
                    data[18] = est.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrestudiante, "El estudiante con cédula " + this.jfrestudiante.txtCedula.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrestudiante, "Error: " + ex.getMessage());
        }
    }   
    
    public byte[] Visor(File f) {
        byte[] bImg = new byte[1024*100];
        try {
            entrada = new FileInputStream(f);
            entrada.read(bImg);
        } catch (Exception e) {}
        return bImg;
    }
}
