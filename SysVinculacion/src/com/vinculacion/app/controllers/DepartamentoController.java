package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.DepartamentosDAO;
import com.vinculacion.app.dao.EmpresaDAO;
import com.vinculacion.app.model.Departamentos;
import com.vinculacion.app.model.Empresa;
import com.vinculacion.app.views.JFrameDepartamento;
import com.vinculacion.app.views.JFrameEditDepartamento;
import com.vinculacion.app.views.JFrameNuevoDepartamento;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jorge
 */
public class DepartamentoController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameNuevoDepartamento jfrnuevodepart = new JFrameNuevoDepartamento(mp, false);
    JFrameDepartamento jfrdepart = new JFrameDepartamento(mp, false);
    JFrameEditDepartamento jfreditdepart = new JFrameEditDepartamento(mp, false);
    DepartamentosDAO ddao;
    EmpresaDAO edao;
    
    public DepartamentoController(MenuPrincipal menu, JFrameNuevoDepartamento nuevo, JFrameDepartamento depart, JFrameEditDepartamento edit) {
        this.mp = menu;
        this.jfrnuevodepart = nuevo;
        this.jfreditdepart = edit;
        this.jfrdepart = depart;
        
        this.jfrdepart.btnBuscar.addActionListener(this);
        this.jfrdepart.btnDarBaja.addActionListener(this);
        this.jfrdepart.btnEditar.addActionListener(this);
        this.jfrdepart.btnNuevo.addActionListener(this);
        
        this.jfrnuevodepart.btnGuardar.addActionListener(this);
        this.jfrnuevodepart.btnRegresar.addActionListener(this);
        
        this.jfreditdepart.btnGuardar.addActionListener(this);
        this.jfreditdepart.btnRegresar.addActionListener(this);
        
        ddao = new DepartamentosDAO();
        edao = new EmpresaDAO();
        getAllDepartamentos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrdepart.btnBuscar) {
            getAllDepartamentos();
        }
        if (e.getSource() == this.jfrdepart.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrdepart.tableDepartamentos.getValueAt(this.jfrdepart.tableDepartamentos.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrdepart, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    ddao.deleteDepartamentos(id);
                    JOptionPane.showMessageDialog(this.jfrdepart,  "Se ha dado de baja al registro!!");
                    getAllDepartamentos();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrdepart, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrdepart.btnEditar) {
            try {
                if (this.jfrdepart.tableDepartamentos.getValueAt(this.jfrdepart.tableDepartamentos.getSelectedRow(), 0).toString().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrdepart, "Debe seleccionar un registro y luego dar click en editar");
                }else{
                    this.jfreditdepart.setVisible(true);
                    
                    this.jfreditdepart.comboEmpresa.removeAllItems();
                    List<Empresa> lempresa = edao.AllEmpresas();                    
                    for (Empresa empresa : lempresa) {
                        this.jfreditdepart.comboEmpresa.addItem(empresa.getNOMBRE_EMPRESA());
                    }                   
                    
                    this.jfreditdepart.txtCodigo.setText(this.jfrdepart.tableDepartamentos.getValueAt(this.jfrdepart.tableDepartamentos.getSelectedRow(), 0).toString());
                    this.jfreditdepart.txtNombreDepartamento.setText(this.jfrdepart.tableDepartamentos.getValueAt(this.jfrdepart.tableDepartamentos.getSelectedRow(), 2).toString());
                    this.jfreditdepart.comboEmpresa.setSelectedItem(this.jfrdepart.tableDepartamentos.getValueAt(this.jfrdepart.tableDepartamentos.getSelectedRow(), 1).toString());
                    this.jfreditdepart.comboEstado.setSelectedItem(this.jfrdepart.tableDepartamentos.getValueAt(this.jfrdepart.tableDepartamentos.getSelectedRow(), 3).toString());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrdepart, "Debe seleccionar un registro y luego dar click en editar");
            }
        }
        if (e.getSource() == this.jfrdepart.btnNuevo) {
            this.jfrnuevodepart.comboEmpresa.removeAllItems();
            List<Empresa> lempresa = edao.AllEmpresas();                    
            for (Empresa empresa : lempresa) {
                this.jfrnuevodepart.comboEmpresa.addItem(empresa.getNOMBRE_EMPRESA());
            }
            this.jfrnuevodepart.setVisible(true);
            this.jfrdepart.dispose();
        }
        /*Nuevo departamento*/
        if (e.getSource() == this.jfrnuevodepart.btnGuardar) {
            try {
                if (this.jfrnuevodepart.txtNombreDepartamento.getText().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevodepart, "El nombre del departamento es requerido");
                }else{
                    Departamentos departamento = new Departamentos();
                    Empresa empresa = edao.findEmpresaByName(this.jfrnuevodepart.comboEmpresa.getSelectedItem().toString());
                    departamento.setEmpresa(empresa);
                    departamento.setNOMBRE_DEPARTAMENTO(this.jfrnuevodepart.txtNombreDepartamento.getText().toString().toUpperCase());                    
                    departamento.setESTADO("ACTIVO");
                    ddao.saveDepartamentos(departamento);
                    JOptionPane.showMessageDialog(this.jfrnuevodepart, "Departamento registrado !!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevodepart, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrnuevodepart.btnRegresar) {
            this.jfrnuevodepart.dispose();            
            this.jfrdepart.setVisible(true);
            getAllDepartamentos();
        }
        /*Editar departamentos*/
        if (e.getSource() == this.jfreditdepart.btnGuardar) {
            try {
                Departamentos departamento = new Departamentos();
                departamento.setID_DEPARTAMENTO(Integer.parseInt(this.jfreditdepart.txtCodigo.getText().toString()));
                Empresa empresa = edao.findEmpresaByName(this.jfreditdepart.comboEmpresa.getSelectedItem().toString());
                departamento.setEmpresa(empresa);
                departamento.setNOMBRE_DEPARTAMENTO(this.jfreditdepart.txtNombreDepartamento.getText().toString().toUpperCase());
                departamento.setESTADO(this.jfreditdepart.comboEstado.getSelectedItem().toString());
                ddao.updateDepartamentos(departamento);
                JOptionPane.showMessageDialog(jfreditdepart, "Se ha actualizado este registro!!");
                getAllDepartamentos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(jfreditdepart, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.jfreditdepart.btnRegresar) {
            this.jfreditdepart.dispose();
        }
    }

    public void getAllDepartamentos() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrdepart.tableDepartamentos.setModel(model);
            model.addColumn("Código");
            model.addColumn("Empresa");
            model.addColumn("Nombre del departamento");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.jfrdepart.tableDepartamentos.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(250);
            tcm.getColumn(2).setPreferredWidth(250);
            Object [] data = new Object[4];                    

            if (this.jfrdepart.txtCodigo.getText().isEmpty()) {
                this.jfrdepart.tableDepartamentos.removeAll();
                List<Departamentos> ldepart = ddao.AllDepartamentos();
                for (Departamentos departamento : ldepart) {
                    data[0] = departamento.getID_DEPARTAMENTO();
                    data[1] = departamento.getEmpresa().getNOMBRE_EMPRESA();
                    data[2] = departamento.getNOMBRE_DEPARTAMENTO();
                    data[3] = departamento.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrdepart.tableDepartamentos.removeAll();
                Departamentos departamento = ddao.findDepartamentoById(Integer.parseInt(this.jfrdepart.txtCodigo.getText().toString()));
                if (departamento != null) {
                    data[0] = departamento.getID_DEPARTAMENTO();
                    data[1] = departamento.getEmpresa().getNOMBRE_EMPRESA();
                    data[2] = departamento.getNOMBRE_DEPARTAMENTO();
                    data[3] = departamento.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrdepart, "El registro con codigo " + this.jfrdepart.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrdepart, "Error: " + ex.getMessage());
        }
    }   
}
