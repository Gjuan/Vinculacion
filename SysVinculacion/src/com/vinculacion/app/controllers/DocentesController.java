package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.AsignaturasDAO;
import com.vinculacion.app.dao.CargoDAO;
import com.vinculacion.app.dao.CarrerasDAO;
import com.vinculacion.app.dao.DetalleCargoDocenteDAO;
import com.vinculacion.app.dao.DetalleDocenteAsignaturaDAO;
import com.vinculacion.app.dao.DocenteDAO;
import com.vinculacion.app.dao.EscuelaDAO;
import com.vinculacion.app.dao.FacultadDAO;
import com.vinculacion.app.dao.GeneroDAO;
import com.vinculacion.app.dao.TipoDedicacionDAO;
import com.vinculacion.app.model.DetalleCargoDocente;
import com.vinculacion.app.model.DetalleDocenteAsignatura;
import com.vinculacion.app.model.Docente;
import com.vinculacion.app.model.Cargo;
import com.vinculacion.app.model.Asignaturas;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Escuela;
import com.vinculacion.app.model.Facultad;
import com.vinculacion.app.model.Genero;
import com.vinculacion.app.model.TipoDedicacion;
import com.vinculacion.app.views.JFrameDocente;
import com.vinculacion.app.views.JFrameEditDocente;
import com.vinculacion.app.views.JFrameNuevoDocente;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class DocentesController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameDocente jfrdocente = new JFrameDocente(mp, false);
    JFrameEditDocente editdocent = new JFrameEditDocente(mp, false);
    JFrameNuevoDocente nuevodocent = new JFrameNuevoDocente(mp, false);
    
    DocenteDAO ddao;
    AsignaturasDAO adao;
    CargoDAO cdao;
    DetalleCargoDocenteDAO dcddao;
    DetalleDocenteAsignaturaDAO ddadao;
    CarrerasDAO carreradao;
    GeneroDAO generodao;
    EscuelaDAO escueladao;
    FacultadDAO facultaddao;
    TipoDedicacionDAO tddao;
        
    public DocentesController(MenuPrincipal menup, JFrameDocente docent, JFrameEditDocente edit, JFrameNuevoDocente nuevo) {
        this.jfrdocente = docent;
        this.mp = menup;
        this.editdocent = edit;
        this.nuevodocent = nuevo;
        
        this.jfrdocente.btnNuevo.addActionListener(this);
        this.jfrdocente.btnBuscar.addActionListener(this);
        this.jfrdocente.btnImprimir.addActionListener(this);
        this.jfrdocente.btnEditar.addActionListener(this);
        this.jfrdocente.btnEliminar.addActionListener(this);
        this.jfrdocente.comboMostrarDocentes.addActionListener(this);
        
        this.nuevodocent.btnGuardar.addActionListener(this);
        this.nuevodocent.btnLimpiar.addActionListener(this);
        this.nuevodocent.btnRegresar.addActionListener(this);
        this.nuevodocent.btnAsignarCargo.addActionListener(this);
        this.nuevodocent.btnAsignarAsignatura.addActionListener(this);
        
        this.editdocent.btnGuardar.addActionListener(this);
        this.editdocent.btnRegresar.addActionListener(this);
        
        this.nuevodocent.comboFacultad.addActionListener(this);
        this.nuevodocent.comboEscuelas.addActionListener(this);
                
        ddao = new DocenteDAO();
        this.adao = new AsignaturasDAO();
        this.cdao = new CargoDAO();
        this.dcddao = new DetalleCargoDocenteDAO();
        this.ddadao = new DetalleDocenteAsignaturaDAO();
        carreradao = new CarrerasDAO();
        generodao = new GeneroDAO();
        escueladao = new EscuelaDAO();
        facultaddao = new FacultadDAO();
        tddao = new TipoDedicacionDAO();
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrdocente.btnNuevo) {
            this.nuevodocent.setVisible(true);
            this.nuevodocent.comboGenero.removeAllItems();
            List<Genero> lgenero = generodao.AllGeneros();        
            for (Genero genero : lgenero) {
                this.nuevodocent.comboGenero.addItem(genero.getDESCRIPCION());
            }
            this.nuevodocent.comboTipoDedicacion.removeAllItems();
            List<TipoDedicacion> ltd = tddao.AllTiposDedicacion();       
            for (TipoDedicacion td : ltd) {
                this.nuevodocent.comboTipoDedicacion.addItem(td.getDESCRIPCION());                        
            }
            this.nuevodocent.comboFacultad.removeAllItems(); 
            List<Facultad> lf = facultaddao.AllFacultad();        
            for (Facultad f : lf) {
                this.nuevodocent.comboFacultad.addItem(f.getDESCRIPCION());                                                
            }    
            this.nuevodocent.comboEscuelas.removeAllItems();
            List<Escuela> le = escueladao.AllEscuelas();
            for (Escuela escuela : le) {
                this.nuevodocent.comboEscuelas.addItem(escuela.getNOMBRE_ESCUELA());
            }
            this.nuevodocent.comboCarreras.removeAllItems();
            List<Carreras> lc = carreradao.AllCarreras();
            for (Carreras carrera : lc) {
                this.nuevodocent.comboCarreras.addItem(carrera.getDESCRIPCION());
            }
            this.nuevodocent.comboAsignaturas.removeAllItems();
            List<Asignaturas> lasignatura = adao.AllAsignaturas();        
            for (Asignaturas asignatura : lasignatura) {
                this.nuevodocent.comboAsignaturas.addItem(asignatura.getNOMBRE_ASIGNATURA());
            }
            this.nuevodocent.comboCargos.removeAllItems();
            List<Cargo> lcargos = cdao.AllCargos();
            for (Cargo cargo : lcargos) {
                this.nuevodocent.comboCargos.addItem(cargo.getDESCRIPCION());
            }
            this.nuevodocent.comboDocente.removeAllItems();
            
            List<Docente> ldocente = ddao.AllDocente();
            for (Docente docente : ldocente) {
                this.nuevodocent.comboDocente.addItem(docente.getNOMBRES()+ "-"+ docente.getAPELLIDOS());
            }
            this.jfrdocente.dispose();
        }
        if (e.getSource() == this.jfrdocente.btnBuscar) {
            if (this.jfrdocente.comboMostrarDocentes.getSelectedIndex() == 1) {
                getAllDocentesByCargos();
            }else if(this.jfrdocente.comboMostrarDocentes.getSelectedIndex() == 2){
                getAllDocentesByAsignaturas();
            }else{
                JOptionPane.showMessageDialog(this.jfrdocente, "Seleccione docentes por cargos o por asignaturas para buscar");
            }
        }
        if (e.getSource() == this.jfrdocente.btnImprimir) {
            
        }
        if (e.getSource() == this.jfrdocente.btnEditar) {
           try {
                if (!this.jfrdocente.tableDocentes.getValueAt(this.jfrdocente.tableDocentes.getSelectedRow(), 1).toString().isEmpty()) {
                    this.editdocent.setVisible(true);
                    this.editdocent.comboGenero.removeAllItems();
                    List<Genero> lgenero = generodao.AllGeneros();        
                    for (Genero genero : lgenero) {
                        this.editdocent.comboGenero.addItem(genero.getDESCRIPCION());
                    }
                    this.editdocent.comboTipoDedicacion.removeAllItems();
                    List<TipoDedicacion> ltd = tddao.AllTiposDedicacion();       
                    for (TipoDedicacion td : ltd) {
                        this.editdocent.comboTipoDedicacion.addItem(td.getDESCRIPCION());                        
                    }
                    this.editdocent.comboFacultad.removeAllItems();  
                    List<Facultad> lf = facultaddao.AllFacultad();                                                                                     
                    for (Facultad f : lf) {
                        this.editdocent.comboFacultad.addItem(f.getDESCRIPCION());                                                
                    }
                    this.editdocent.comboEscuelas.removeAllItems();
                    List<Escuela> le = escueladao.AllEscuelas();
                    for (Escuela escuela : le) {
                        this.editdocent.comboEscuelas.addItem(escuela.getNOMBRE_ESCUELA());
                    }
                    this.editdocent.comboCarreras.removeAllItems();
                    List<Carreras> lc = carreradao.AllCarreras();
                    for (Carreras c : lc) {
                        this.editdocent.comboCarreras.addItem(c.getDESCRIPCION());
                    }
                    Docente docente = ddao.findDocenteByCedula(this.jfrdocente.tableDocentes.getValueAt(this.jfrdocente.tableDocentes.getSelectedRow(), 1).toString());
                    this.editdocent.txtCodigo.setText(""+docente.getID_DOCENTE());
                    this.editdocent.txtCedula.setText(docente.getCEDULA());
                    this.editdocent.txtNombres.setText(docente.getNOMBRES());
                    this.editdocent.txtApellidos.setText(docente.getAPELLIDOS());
                    this.editdocent.txtTelefono.setText(docente.getTELEFONO());
                    this.editdocent.txtCorreo.setText(docente.getCORREO());
                    this.editdocent.comboGenero.setSelectedItem(docente.getGenero().getDESCRIPCION());
                    this.editdocent.comboFacultad.setSelectedItem(docente.getCarrera().getEscuela().getFacultad().getDESCRIPCION());
                    this.editdocent.comboEscuelas.setSelectedItem(docente.getCarrera().getEscuela().getNOMBRE_ESCUELA());
                    this.editdocent.comboCarreras.setSelectedItem(docente.getCarrera().getDESCRIPCION());
                    this.editdocent.comboTipoDedicacion.setSelectedItem(docente.getTipoDedicacion().getDESCRIPCION());
                    this.editdocent.comboEstado.setSelectedItem(docente.getESTADO());
                }else{
                    JOptionPane.showMessageDialog(this.jfrdocente,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrdocente,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrdocente.btnEliminar) {
            try {
                int id = Integer.parseInt(this.jfrdocente.tableDocentes.getValueAt(this.jfrdocente.tableDocentes.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrdocente, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){                    
                    if (this.jfrdocente.comboMostrarDocentes.getSelectedIndex() == 1) {
                            this.dcddao.deleteDetalleCargoDocente(id);
                            getAllDocentesByCargos();                           
                            JOptionPane.showMessageDialog(this.jfrdocente,  "Se ha eliminado esta asignación de cargo del docente!!");
                    }else if (this.jfrdocente.comboMostrarDocentes.getSelectedIndex() == 2){
                            this.ddadao.deleteDetalleDocenteAsignatura(id);
                            getAllDocentesByAsignaturas();                                                    
                            JOptionPane.showMessageDialog(this.jfrdocente,  "Se ha eliminado esta asignación de asignatura del docente!!");
                    }                    
                }                               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrdocente, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrdocente.comboMostrarDocentes) {
            try {
                if (this.jfrdocente.comboMostrarDocentes.getSelectedIndex() == 1) {
                    /*DOCENTES POR CARGOS*/
                    getAllDocentesByCargos();
                }else if(this.jfrdocente.comboMostrarDocentes.getSelectedIndex() == 2){
                    /*DOCENTES POR ASIGNATURAS*/
                    getAllDocentesByAsignaturas();
                }else{
                    JOptionPane.showMessageDialog(this.jfrdocente, "Seleccione docentes por cargos o por asignaturas");
                } 
            } catch (Exception xe) {
                 JOptionPane.showMessageDialog(this.jfrdocente, "Error: " + xe.getMessage());
            }         
        }
        /*Nuevo docente*/
        if (e.getSource() == this.nuevodocent.btnLimpiar) {
            this.nuevodocent.txtCedula.setText("");
            this.nuevodocent.txtApellidos.setText("");
            this.nuevodocent.txtNombres.setText("");
            this.nuevodocent.txtCorreo.setText("");
            this.nuevodocent.txtTelefono.setText("");            
            this.nuevodocent.txtCedula.requestFocus();
        }
        
        if (e.getSource() == this.nuevodocent.btnGuardar) {
            try {
                if (this.nuevodocent.txtCedula.getText().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(nuevodocent, "La cédula es requerida");
                }else if (this.nuevodocent.txtNombres.getText().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(nuevodocent, "El nombre es requerido");
                }else if(this.nuevodocent.txtApellidos.getText().toString().isEmpty()){
                    JOptionPane.showMessageDialog(nuevodocent, "El apellido es requerido");
                }else{
                    try {
                        Docente d = ddao.findDocenteByCedula(this.nuevodocent.txtCedula.getText().toString());
                        JOptionPane.showMessageDialog(nuevodocent, "El docente con cédula " + d.getCEDULA() + " ya existe en el sistema");
                    } catch (Exception err) {                    
                        Docente docente = new Docente();
                        docente.setCEDULA(this.nuevodocent.txtCedula.getText().toString());
                        docente.setNOMBRES(this.nuevodocent.txtNombres.getText().toString().toUpperCase());
                        docente.setAPELLIDOS(this.nuevodocent.txtApellidos.getText().toString().toUpperCase());
                        docente.setCORREO(this.nuevodocent.txtCorreo.getText().toString().toLowerCase());
                        docente.setTELEFONO(this.nuevodocent.txtTelefono.getText().toString());
                        Genero genero = generodao.findGeneroByDescription(this.nuevodocent.comboGenero.getSelectedItem().toString());
                        docente.setGenero(genero);
                        TipoDedicacion td = tddao.findTipoDedicacionByDescription(this.nuevodocent.comboTipoDedicacion.getSelectedItem().toString());
                        docente.setTipoDedicacion(td);
                        Carreras carrera = carreradao.findCarreraByDescription(this.nuevodocent.comboCarreras.getSelectedItem().toString());
                        docente.setCarrera(carrera);
                        docente.setESTADO("ACTIVO");
                        ddao.saveDocente(docente);
                        JOptionPane.showMessageDialog(nuevodocent, "Docente registrado correctamente !!");
                        this.nuevodocent.comboDocente.removeAllItems();
                        List<Docente> ldocente = ddao.AllDocente();
                        for (Docente doc : ldocente) {
                            this.nuevodocent.comboDocente.addItem(doc.getNOMBRES()+ "-"+ doc.getAPELLIDOS()); 
                        }                                        
                    }   
                }                              
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(nuevodocent, "Error: "+ex.getMessage());
            }
        }
        if (e.getSource() == this.nuevodocent.btnRegresar) {
            this.nuevodocent.dispose();
            this.jfrdocente.setVisible(true);
        }
        if (e.getSource() == this.nuevodocent.btnAsignarAsignatura) {
            try {    
                DetalleDocenteAsignatura dda = new DetalleDocenteAsignatura();

                String [] datos = this.nuevodocent.comboDocente.getSelectedItem().toString().split("-");
                Docente docente = ddao.findDocenteByLastNameAndName(datos[0], datos[1]);                        

                Asignaturas asignatura = adao.findAsignaturaByName(this.nuevodocent.comboAsignaturas.getSelectedItem().toString());

                dda.setDocentes(docente);
                dda.setAsignaturas(asignatura);
                dda.setESTADO("ACTIVO");
                ddadao.saveDetalleDocenteAsignatura(dda);
                JOptionPane.showMessageDialog(nuevodocent, "Asignatura asignada al docente " + docente.getNOMBRES() + " " + docente.getAPELLIDOS());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(nuevodocent, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.nuevodocent.btnAsignarCargo) {
            try {    
                DetalleCargoDocente dcd = new DetalleCargoDocente();

                String [] datos = this.nuevodocent.comboDocente.getSelectedItem().toString().split("-");
                Docente docente = ddao.findDocenteByLastNameAndName(datos[0], datos[1]);                        

                Cargo cargo = cdao.findCargoByDescription(this.nuevodocent.comboCargos.getSelectedItem().toString());
                
                dcd.setCargos(cargo);
                dcd.setDocentes(docente);
                dcd.setESTADO("ACTIVO");
                dcddao.saveDetalleCargoDocente(dcd);
                JOptionPane.showMessageDialog(nuevodocent, "Cargo asignado al docente " + docente.getNOMBRES() + " " + docente.getAPELLIDOS());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(nuevodocent, "Error: " + ex.getMessage());
            }
        }
        /*Editar docente*/
        if (e.getSource() == this.editdocent.btnGuardar) {
            try {
                Docente docente = new Docente();
                docente.setID_DOCENTE(Integer.parseInt(this.editdocent.txtCodigo.getText().toString()));
                docente.setCEDULA(this.editdocent.txtCedula.getText().toString());
                docente.setNOMBRES(this.editdocent.txtNombres.getText().toString().toUpperCase());
                docente.setAPELLIDOS(this.editdocent.txtApellidos.getText().toString().toUpperCase());
                docente.setTELEFONO(this.editdocent.txtTelefono.getText().toString());
                docente.setCORREO(this.editdocent.txtCorreo.getText().toString().toLowerCase());
                
                Genero genero = generodao.findGeneroByDescription(this.editdocent.comboGenero.getSelectedItem().toString());
                docente.setGenero(genero);
                
                TipoDedicacion td = tddao.findTipoDedicacionByDescription(this.editdocent.comboTipoDedicacion.getSelectedItem().toString());
                docente.setTipoDedicacion(td);
                
                Carreras carrera = carreradao.findCarreraByDescription(this.editdocent.comboCarreras.getSelectedItem().toString());
                docente.setCarrera(carrera);
                
                docente.setESTADO(this.editdocent.comboEstado.getSelectedItem().toString());
                ddao.updateDocente(docente);
                
                JOptionPane.showMessageDialog(this.editdocent,"Se ha actualizado este usuario");
                
                if (this.jfrdocente.comboMostrarDocentes.getSelectedIndex() == 1) {
                    getAllDocentesByCargos();
                }else if(this.jfrdocente.comboMostrarDocentes.getSelectedIndex() == 2){
                    getAllDocentesByAsignaturas();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.editdocent,"Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.editdocent.btnRegresar) {
            this.editdocent.dispose();
            this.jfrdocente.setVisible(true);
        }
    }   
     
    public void getAllDocentesByCargos() {
        try {        
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }            
            };
            this.jfrdocente.tableDocentes.setModel(model);
            model.addColumn("Código");
            model.addColumn("Cédula");
            model.addColumn("Nombres");
            model.addColumn("Apellidos");
            model.addColumn("Teléfono");
            model.addColumn("Correo");
            model.addColumn("Estado del detalle");
            model.addColumn("Género");
            model.addColumn("Carrera");
            model.addColumn("Escuela");
            model.addColumn("Facultad");
            model.addColumn("Tipo de dedicación");
            model.addColumn("Cargo");

            TableColumnModel tcm =  this.jfrdocente.tableDocentes.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(3);
            tcm.getColumn(2).setPreferredWidth(80);
            tcm.getColumn(3).setPreferredWidth(80);

            Object data[] = new Object[13];
            List<DetalleCargoDocente> ldcd = null;
            if (this.jfrdocente.txtCedula.getText().toString().isEmpty()) {
                this.jfrdocente.tableDocentes.removeAll();
                ldcd = dcddao.AllDetalleCargoDocente();
            }else{       
                this.jfrdocente.tableDocentes.removeAll();
                ldcd = dcddao.findDetalleCargoDocenteByCedula(this.jfrdocente.txtCedula.getText().toString());          
            }
                for (DetalleCargoDocente detalleCargoDocente : ldcd) {
                    data[0] = detalleCargoDocente.getID_DETALLE_CARGO_DOCENTE();
                    data[1] = detalleCargoDocente.getDocentes().getCEDULA();
                    data[2] = detalleCargoDocente.getDocentes().getNOMBRES();
                    data[3] = detalleCargoDocente.getDocentes().getAPELLIDOS();
                    data[4] = detalleCargoDocente.getDocentes().getTELEFONO();
                    data[5] = detalleCargoDocente.getDocentes().getCORREO();
                    data[6] = detalleCargoDocente.getESTADO();
                    data[7] = detalleCargoDocente.getDocentes().getGenero().getDESCRIPCION();
                    data[8] = detalleCargoDocente.getDocentes().getCarrera().getDESCRIPCION();
                    data[9] = detalleCargoDocente.getDocentes().getCarrera().getEscuela().getNOMBRE_ESCUELA();
                    data[10] = detalleCargoDocente.getDocentes().getCarrera().getEscuela().getFacultad().getDESCRIPCION();
                    data[11] = detalleCargoDocente.getDocentes().getTipoDedicacion().getDESCRIPCION();
                    data[12] = detalleCargoDocente.getCargos().getDESCRIPCION();                              
                    model.addRow(data);
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.jfrdocente, "Su búsqueda no ha sido atendida");
        }
    }

    public void getAllDocentesByAsignaturas() {
        try {       
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }            
            };
            this.jfrdocente.tableDocentes.setModel(model);
            model.addColumn("Código");
            model.addColumn("Cédula");
            model.addColumn("Nombres");
            model.addColumn("Apellidos");
            model.addColumn("Teléfono");
            model.addColumn("Correo");
            model.addColumn("Estado del detalle");
            model.addColumn("Género");
            model.addColumn("Carrera");
            model.addColumn("Escuela");
            model.addColumn("Facultad");
            model.addColumn("Tipo de dedicación");
            model.addColumn("Asignatura");

            TableColumnModel tcm =  this.jfrdocente.tableDocentes.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(3);
            tcm.getColumn(2).setPreferredWidth(80);
            tcm.getColumn(3).setPreferredWidth(80);

            Object data[] = new Object[13];
            List<DetalleDocenteAsignatura> ldda = null;
            if (this.jfrdocente.txtCedula.getText().toString().isEmpty()) {
                this.jfrdocente.tableDocentes.removeAll();
                ldda = this.ddadao.AllDetalleDocenteAsignatura();
            }else{       
                this.jfrdocente.tableDocentes.removeAll();
                ldda = this.ddadao.findDetalleDocenteAsignaturaByCedula(this.jfrdocente.txtCedula.getText().toString());          
            }
                for (DetalleDocenteAsignatura dda : ldda) {
                    data[0] = dda.getID_DETALLE_DOCENTE_ASIG();
                    data[1] = dda.getDocentes().getCEDULA();
                    data[2] = dda.getDocentes().getNOMBRES();
                    data[3] = dda.getDocentes().getAPELLIDOS();
                    data[4] = dda.getDocentes().getTELEFONO();
                    data[5] = dda.getDocentes().getCORREO();
                    data[6] = dda.getESTADO();
                    data[7] = dda.getDocentes().getGenero().getDESCRIPCION();
                    data[8] = dda.getDocentes().getCarrera().getDESCRIPCION();
                    data[9] = dda.getDocentes().getCarrera().getEscuela().getNOMBRE_ESCUELA();
                    data[10] = dda.getDocentes().getCarrera().getEscuela().getFacultad().getDESCRIPCION();
                    data[11] = dda.getDocentes().getTipoDedicacion().getDESCRIPCION();
                    data[12] = dda.getAsignaturas().getNOMBRE_ASIGNATURA();                              
                    model.addRow(data);
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.jfrdocente, "Su búsqueda no ha sido atendida");
        }
    }
}
