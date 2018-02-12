package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.CargoDepartamentalDAO;
import com.vinculacion.app.model.CargoDepartamental;
import com.vinculacion.app.views.JFrameCargoDepartamental;
import com.vinculacion.app.views.JFrameEditCargoDepartamental;
import com.vinculacion.app.views.JFrameNuevoCargoDepartamental;
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
public class CargoDepartamentalController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameCargoDepartamental jfrcd = new JFrameCargoDepartamental(mp, false);
    JFrameNuevoCargoDepartamental jfrnuevocd = new JFrameNuevoCargoDepartamental(mp, false);
    JFrameEditCargoDepartamental jfreditcd = new JFrameEditCargoDepartamental(mp, false);
    CargoDepartamentalDAO cddao;
    
    public CargoDepartamentalController(MenuPrincipal menu, JFrameCargoDepartamental cd, JFrameNuevoCargoDepartamental nuevo, JFrameEditCargoDepartamental edit) {
        this.mp = menu;
        this.jfrcd = cd;
        this.jfreditcd = edit;
        this.jfrnuevocd = nuevo;
        
        this.jfrcd.btnNuevo.addActionListener(this);
        this.jfrcd.btnBuscar.addActionListener(this);
        this.jfrcd.btnDarBaja.addActionListener(this);
        this.jfrcd.btnEditar.addActionListener(this);
        
        this.jfrnuevocd.btnGuardar.addActionListener(this);
        this.jfrnuevocd.btnRegresar.addActionListener(this);
        
        this.jfreditcd.btnGuardar.addActionListener(this);
        this.jfreditcd.btnRegresar.addActionListener(this);
        
        cddao = new CargoDepartamentalDAO();
        getAllCargosDepartamentales();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrcd.btnBuscar) {
            getAllCargosDepartamentales();
        }
        if (e.getSource() == this.jfrcd.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrcd.tableCargosDepartamentos.getValueAt(this.jfrcd.tableCargosDepartamentos.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrcd, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    cddao.deleteCargoDepartamental(id);
                    JOptionPane.showMessageDialog(this.jfrcd,  "Se ha dado de baja al registro!!");
                    getAllCargosDepartamentales();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrcd, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrcd.btnEditar) {
            try {
                if (!this.jfrcd.tableCargosDepartamentos.getValueAt(this.jfrcd.tableCargosDepartamentos.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfreditcd.setVisible(true);
                    this.jfreditcd.txtCodigo.setText(this.jfrcd.tableCargosDepartamentos.getValueAt(this.jfrcd.tableCargosDepartamentos.getSelectedRow(), 0).toString());
                    this.jfreditcd.txtCargoDepartamental.setText(this.jfrcd.tableCargosDepartamentos.getValueAt(this.jfrcd.tableCargosDepartamentos.getSelectedRow(), 1).toString());
                    this.jfreditcd.comboEstado.setSelectedItem(this.jfrcd.tableCargosDepartamentos.getValueAt(this.jfrcd.tableCargosDepartamentos.getSelectedRow(), 2).toString());
                }else{
                    JOptionPane.showMessageDialog(mp,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(mp,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrcd.btnNuevo) {
            this.jfrnuevocd.setVisible(true);
            this.jfrcd.dispose();
        }
        /*Nuevo cargo de departamento*/
        if (e.getSource() == this.jfrnuevocd.btnGuardar) {
            try {
                if (this.jfrnuevocd.txtCargoDepartamental.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevocd, "La descripción de este cargo es requerida");
                }else{
                    CargoDepartamental cd = new CargoDepartamental();
                    cd.setDESCRIPCION(this.jfrnuevocd.txtCargoDepartamental.getText().toString().toUpperCase());
                    cd.setESTADO("ACTIVO");
                    cddao.saveCargoDepartamental(cd);
                    JOptionPane.showMessageDialog(this.jfrnuevocd, "El cargo ha sido ingresado correctamente!!");
                    this.jfrnuevocd.txtCargoDepartamental.setText("");
                } 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevocd, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrnuevocd.btnRegresar) {
            this.jfrnuevocd.dispose();
            this.jfrcd.setVisible(true);
            getAllCargosDepartamentales();
        }
        /*Editar cargo de departamento*/
        if (e.getSource() == this.jfreditcd.btnGuardar) {
            try {
                CargoDepartamental cd = new CargoDepartamental();
                cd.setID_CARGO_EMPRESARIAL(Integer.parseInt(this.jfreditcd.txtCodigo.getText().toString()));
                cd.setDESCRIPCION(this.jfreditcd.txtCargoDepartamental.getText().toString().toUpperCase());
                cd.setESTADO(this.jfreditcd.comboEstado.getSelectedItem().toString());
                cddao.updateCargoDepartamental(cd);
                JOptionPane.showMessageDialog(this.jfreditcd, "Cargo departamental actualizado");
                getAllCargosDepartamentales();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfreditcd, "Error: " + ex.getMessage());
            }        
        }
        if (e.getSource() == this.jfreditcd.btnRegresar) {
            this.jfreditcd.dispose();
        }
    } 

    public void getAllCargosDepartamentales() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrcd.tableCargosDepartamentos.setModel(model);
            model.addColumn("Código");
            model.addColumn("Descripción");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.jfrcd.tableCargosDepartamentos.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(250);

            Object [] data = new Object[3];                    

            if (this.jfrcd.txtCodigo.getText().isEmpty()) {
                this.jfrcd.tableCargosDepartamentos.removeAll();
                List<CargoDepartamental> lcd = cddao.AllCargosDepartamental();
                for (CargoDepartamental cd : lcd) {
                    data[0] = cd.getID_CARGO_EMPRESARIAL();
                    data[1] = cd.getDESCRIPCION();
                    data[2] = cd.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrcd.tableCargosDepartamentos.removeAll();
                CargoDepartamental cd = cddao.findCargoDepartamentalById(Integer.parseInt(this.jfrcd.txtCodigo.getText().toString()));
                if (cd != null) {
                    data[0] = cd.getID_CARGO_EMPRESARIAL();
                    data[1] = cd.getDESCRIPCION();
                    data[2] = cd.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrcd, "El registro con codigo " + this.jfrcd.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrcd, "Error: " + ex.getMessage());
        }
    }
}
