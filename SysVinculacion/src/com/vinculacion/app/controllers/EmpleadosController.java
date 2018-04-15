package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.CargoDepartamentalDAO;
import com.vinculacion.app.dao.DepartamentosDAO;
import com.vinculacion.app.dao.EmpleadosDAO;
import com.vinculacion.app.model.CargoDepartamental;
import com.vinculacion.app.model.Departamentos;
import com.vinculacion.app.model.Empleados;
import com.vinculacion.app.views.JFrameEditEmpleado;
import com.vinculacion.app.views.JFrameEmpleados;
import com.vinculacion.app.views.JFrameNuevoEmpleado;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jorge
 */
public class EmpleadosController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameEditEmpleado jfredit = new JFrameEditEmpleado(mp, false);
    JFrameNuevoEmpleado jfrnuevo = new JFrameNuevoEmpleado(mp, false);
    JFrameEmpleados jfrempleados = new JFrameEmpleados(mp, false);
    EmpleadosDAO edao;
    CargoDepartamentalDAO cddao;
    DepartamentosDAO ddao;
    
    public EmpleadosController(MenuPrincipal menu, JFrameEditEmpleado edit, JFrameNuevoEmpleado nuevo, JFrameEmpleados emplea) {
        this.mp = menu;
        this.jfredit = edit;
        this.jfrnuevo = nuevo;
        this.jfrempleados = emplea;
        
        this.jfrempleados.btnBuscar.addActionListener(this);
        this.jfrempleados.btnDarBaja.addActionListener(this);
        this.jfrempleados.btnEditar.addActionListener(this);
        this.jfrempleados.btnNuevo.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        
        this.jfrnuevo.txtCedula.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(Character.isLetter(e.getKeyChar())) {          
                    e.consume();               
                    JOptionPane.showMessageDialog(jfrnuevo, "La cédula debe ser númerica!!");   
                    jfrnuevo.txtCedula.setText("");
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });       
        
        edao = new EmpleadosDAO();
        cddao = new CargoDepartamentalDAO();
        ddao = new DepartamentosDAO();
        
        getAllEmpleados();
    }
       
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrempleados.btnBuscar) {
            getAllEmpleados();
        }
        if (e.getSource() == this.jfrempleados.btnDarBaja) {
             try {
                int id = Integer.parseInt(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrempleados, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    edao.deleteEmpleado(id);
                    JOptionPane.showMessageDialog(this.jfrempleados,  "Se ha dado de baja al registro!!");
                    getAllEmpleados();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrempleados, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrempleados.btnEditar) {
             try {
                if (!this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.comboCargo.removeAllItems();
                    List<CargoDepartamental> lcargo = cddao.AllCargosDepartamental();
                    for (CargoDepartamental cargoDepartamental : lcargo) {
                        this.jfredit.comboCargo.addItem(cargoDepartamental.getDESCRIPCION());
                    }
                    this.jfredit.comboDepartamento.removeAllItems();
                    List<Departamentos> ld = ddao.AllDepartamentos();
                    for (Departamentos departamentos : ld) {
                        this.jfredit.comboDepartamento.addItem(departamentos.getEmpresa().getNOMBRE_EMPRESA()+"-"+departamentos.getNOMBRE_DEPARTAMENTO());
                    }
                    this.jfredit.txtCodigo.setText(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 0).toString());
                    this.jfredit.txtCedula.setText(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 1).toString());
                    this.jfredit.txtNombres.setText(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 2).toString());
                    this.jfredit.txtApellidos.setText(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 3).toString());
                    this.jfredit.txtCorreo.setText(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 4).toString());
                    this.jfredit.comboCargo.setSelectedItem(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 5).toString());
                    this.jfredit.comboDepartamento.setSelectedItem(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 6).toString()+"-"+this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 7).toString());                  
                    this.jfredit.txtTelefono.setText(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(), 8).toString());
                    this.jfredit.comboEstado.setSelectedItem(this.jfrempleados.tableEmpleados.getValueAt(this.jfrempleados.tableEmpleados.getSelectedRow(),9).toString());                                      
                }else{
                    JOptionPane.showMessageDialog(mp,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(mp,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrempleados.btnNuevo) {
            this.jfrnuevo.setVisible(true);
            this.jfrnuevo.comboCargo.removeAllItems();
            List<CargoDepartamental> lcargo = cddao.AllCargosDepartamental();
            for (CargoDepartamental cargoDepartamental : lcargo) {
                this.jfrnuevo.comboCargo.addItem(cargoDepartamental.getDESCRIPCION());
            }
            this.jfrnuevo.comboDepartamento.removeAllItems();
            List<Departamentos> ld = ddao.AllDepartamentos();
            for (Departamentos departamentos : ld) {
                this.jfrnuevo.comboDepartamento.addItem(departamentos.getEmpresa().getNOMBRE_EMPRESA()+"-"+departamentos.getNOMBRE_DEPARTAMENTO());
            }
            this.jfrempleados.dispose();
        }
        /*Nuevo empleado*/
        if (e.getSource() == this.jfrnuevo.btnGuardar) {
             try {
                if (this.jfrnuevo.txtCedula.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevo, "La cédula es requerida");
                }else if (this.jfrnuevo.txtCedula.getText().length() != 10){
                    JOptionPane.showMessageDialog(this.jfrnuevo, "La cédula debe tener 10 dígitos");               
                }else if (this.jfrnuevo.txtApellidos.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnuevo, "El apellido es requerido");
                }else if(this.jfrnuevo.txtNombres.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnuevo, "El nombre es requerido");                
                }else if(this.jfrnuevo.txtCorreo.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnuevo, "El correo es requerido");                
                }else{
                    try {
                        Empleados emplead = edao.findEmpleadoByCedula(this.jfrnuevo.txtCedula.getText().toString());
                        JOptionPane.showMessageDialog(this.jfrnuevo, "El empleado con cédula "+emplead.getCEDULA()+" ya existe");
                    } catch (Exception err) {
                        Empleados empl = new Empleados();
                        empl.setCEDULA(this.jfrnuevo.txtCedula.getText().toString());
                        empl.setNOMBRES(this.jfrnuevo.txtNombres.getText().toString().toUpperCase());
                        empl.setAPELLIDOS(this.jfrnuevo.txtApellidos.getText().toString().toUpperCase());
                        empl.setCORREO(this.jfrnuevo.txtCorreo.getText().toString().toLowerCase());
                        empl.setTELEFONO(this.jfrnuevo.txtTelefono.getText().toString());
                        CargoDepartamental cd = cddao.findCargoDepartamentalByDescription(this.jfrnuevo.comboCargo.getSelectedItem().toString());
                        String depart[] = this.jfrnuevo.comboDepartamento.getSelectedItem().toString().split("-");
                        Departamentos d = ddao.findDepartamentoByName(depart[1], depart[0]);
                        empl.setCargoDepartamental(cd);
                        empl.setDepartamentos(d);
                        empl.setESTADO("ACTIVO");
                        edao.saveEmpleados(empl);
                        JOptionPane.showMessageDialog(this.jfrnuevo, "El empleado ha sido ingresado correctamente!!");
                        this.jfrnuevo.txtCedula.setText("");
                        this.jfrnuevo.txtCedula.requestFocus();
                        this.jfrnuevo.txtApellidos.setText("");
                        this.jfrnuevo.txtNombres.setText("");
                        this.jfrnuevo.txtCorreo.setText("");
                        this.jfrnuevo.txtTelefono.setText("");
                    }
                } 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevo, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrnuevo.btnRegresar) {
            this.jfrnuevo.dispose();
            this.jfrempleados.setVisible(true);
            getAllEmpleados();
        }
        /*Editar empleado*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            try {
                Empleados empl = new Empleados();
                empl.setID_EMPLEADO(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                empl.setCEDULA(this.jfredit.txtCedula.getText().toString());
                empl.setNOMBRES(this.jfredit.txtNombres.getText().toString().toUpperCase());
                empl.setAPELLIDOS(this.jfredit.txtApellidos.getText().toString().toUpperCase());
                empl.setCORREO(this.jfredit.txtCorreo.getText().toString().toLowerCase());
                empl.setTELEFONO(this.jfredit.txtTelefono.getText().toString());
                CargoDepartamental cd = cddao.findCargoDepartamentalByDescription(this.jfredit.comboCargo.getSelectedItem().toString());
                String depart[] = this.jfredit.comboDepartamento.getSelectedItem().toString().split("-");
                Departamentos d = ddao.findDepartamentoByName(depart[1] , depart[0]);
                empl.setCargoDepartamental(cd);
                empl.setDepartamentos(d);                    
                empl.setESTADO(this.jfredit.comboEstado.getSelectedItem().toString());
                edao.updateEmpleado(empl);
                JOptionPane.showMessageDialog(this.jfredit, "Empleado actualizado");
                getAllEmpleados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());
            }        
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
    }

    public void getAllEmpleados() {
         try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrempleados.tableEmpleados.setModel(model);
            model.addColumn("Código");
            model.addColumn("Cédula");
            model.addColumn("Nombres");
            model.addColumn("Apellidos");
            model.addColumn("Correo");
            model.addColumn("Cargo");
            model.addColumn("Empresa");
            model.addColumn("Departamento");
            model.addColumn("Teléfono");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.jfrempleados.tableEmpleados.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(80);
            tcm.getColumn(1).setPreferredWidth(90);
            tcm.getColumn(2).setPreferredWidth(90);
            Object [] data = new Object[10];                    

            if (this.jfrempleados.txtCedula.getText().isEmpty()) {
                this.jfrempleados.tableEmpleados.removeAll();
                List<Empleados> lem = edao.AllEmpleados();
                for (Empleados empleado : lem) {
                    data[0] = empleado.getID_EMPLEADO();
                    data[1] = empleado.getCEDULA();
                    data[2] = empleado.getNOMBRES();
                    data[3] = empleado.getAPELLIDOS();
                    data[4] = empleado.getCORREO();
                    data[5] = empleado.getCargoDepartamental().getDESCRIPCION();
                    data[6] = empleado.getDepartamentos().getEmpresa().getNOMBRE_EMPRESA();
                    data[7] = empleado.getDepartamentos().getNOMBRE_DEPARTAMENTO();
                    data[8] = empleado.getTELEFONO();
                    data[9] = empleado.getESTADO();
                    model.addRow(data);
                }
            }else{
                    this.jfrempleados.tableEmpleados.removeAll();
                    Empleados empleado = edao.findEmpleadoByCedula(this.jfrempleados.txtCedula.getText().toString());
                    data[0] = empleado.getID_EMPLEADO();
                    data[1] = empleado.getCEDULA();
                    data[2] = empleado.getNOMBRES();
                    data[3] = empleado.getAPELLIDOS();
                    data[4] = empleado.getCORREO();
                    data[5] = empleado.getCargoDepartamental().getDESCRIPCION();
                    data[6] = empleado.getDepartamentos().getEmpresa().getNOMBRE_EMPRESA();
                    data[7] = empleado.getDepartamentos().getNOMBRE_DEPARTAMENTO();
                    data[8] = empleado.getTELEFONO();
                    data[9] = empleado.getESTADO();
                    model.addRow(data);                   
            }                            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrempleados, "Ha ocurrido un error interno mientras se intentaba realizar su búsqueda");
        }
    }
}
