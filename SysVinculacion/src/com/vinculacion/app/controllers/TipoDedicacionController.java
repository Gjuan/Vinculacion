package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.TipoDedicacionDAO;
import com.vinculacion.app.model.TipoDedicacion;
import com.vinculacion.app.views.JFrameEditTipoDedicacion;
import com.vinculacion.app.views.JFrameNuevoTipoDedicacion;
import com.vinculacion.app.views.JFrameTipoDedicacion;
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
public class TipoDedicacionController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameTipoDedicacion frmtipodedica = new JFrameTipoDedicacion(mp, false);
    JFrameNuevoTipoDedicacion newtipo = new JFrameNuevoTipoDedicacion(mp, false);
    JFrameEditTipoDedicacion edittipo = new JFrameEditTipoDedicacion(mp, false);
    
    TipoDedicacionDAO tddao;
    
    public TipoDedicacionController(MenuPrincipal menup, JFrameTipoDedicacion frmtd, JFrameNuevoTipoDedicacion newtipo, JFrameEditTipoDedicacion tipode) {
        this.mp = menup;
        this.frmtipodedica = frmtd;
        this.newtipo = newtipo;
        this.edittipo = tipode;
        
        this.frmtipodedica.btnBuscar.addActionListener(this);
        this.frmtipodedica.btnDarBaja.addActionListener(this);
        this.frmtipodedica.btnEditar.addActionListener(this);
        this.frmtipodedica.btnNuevo.addActionListener(this);
        this.newtipo.btnGuardar.addActionListener(this);
        this.newtipo.btnRegresar.addActionListener(this);
        this.edittipo.btnGuardar.addActionListener(this);
        this.edittipo.btnRegresar.addActionListener(this);
        
        tddao = new TipoDedicacionDAO();
        getAllTipoDedicacion(); 
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmtipodedica.btnBuscar) {
            getAllTipoDedicacion();
        }
        if (e.getSource() == this.frmtipodedica.btnDarBaja) {
             try {
                int id = Integer.parseInt(this.frmtipodedica.tableTipoDedicacion.getValueAt(this.frmtipodedica.tableTipoDedicacion.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.frmtipodedica, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    tddao.deleteTipoDedicacion(id);
                    JOptionPane.showMessageDialog(this.frmtipodedica,  "Se ha dado de baja al registro!!");
                    getAllTipoDedicacion();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.frmtipodedica, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.frmtipodedica.btnEditar) {
            try {
                if (!this.frmtipodedica.tableTipoDedicacion.getValueAt(this.frmtipodedica.tableTipoDedicacion.getSelectedRow(), 0).toString().isEmpty()) {
                    this.edittipo.setVisible(true);
                    this.edittipo.txtCodigo.setText(this.frmtipodedica.tableTipoDedicacion.getValueAt(this.frmtipodedica.tableTipoDedicacion.getSelectedRow(), 0).toString());
                    this.edittipo.txtDescripcion.setText(this.frmtipodedica.tableTipoDedicacion.getValueAt(this.frmtipodedica.tableTipoDedicacion.getSelectedRow(), 1).toString());
                    this.edittipo.comboEstado.setSelectedItem(this.frmtipodedica.tableTipoDedicacion.getValueAt(this.frmtipodedica.tableTipoDedicacion.getSelectedRow(), 2).toString());
                }else{
                    JOptionPane.showMessageDialog(this.frmtipodedica,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.frmtipodedica,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.frmtipodedica.btnNuevo) {
            this.newtipo.setVisible(true);
            this.frmtipodedica.dispose();
        }
        /*NUEVO TIPO DE DEDICACIÓN*/
        if (e.getSource() == this.newtipo.btnGuardar) {
            try {
                if (this.newtipo.txtDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.newtipo, "La descripción es requerida");
                }else{
                    TipoDedicacion td = new TipoDedicacion();
                    td.setDESCRIPCION((this.newtipo.txtDescripcion.getText().toString()).toUpperCase());
                    td.setESTADO("ACTIVO");
                    tddao.saveTipoDedicacion(td);
                    JOptionPane.showMessageDialog(this.newtipo, "Tipo de dedicación registrada!!");
                    this.newtipo.txtDescripcion.setText("");
                    this.newtipo.txtDescripcion.requestFocus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.newtipo, "Error: " + ex.getMessage());       
            }
        }
        if (e.getSource() == this.newtipo.btnRegresar) {
            this.frmtipodedica.setVisible(true);
            getAllTipoDedicacion();
            this.newtipo.dispose();
        }
        /*EDITAR TIPO DE DEDICACIÓN*/
        if (e.getSource() == this.edittipo.btnGuardar) {
             try {
                 TipoDedicacion td = new TipoDedicacion();
                td.setID_TIPO_DEDICACION(Integer.parseInt(this.edittipo.txtCodigo.getText().toString()));
                td.setDESCRIPCION((this.edittipo.txtDescripcion.getText().toString()).toUpperCase());
                td.setESTADO(this.edittipo.comboEstado.getSelectedItem().toString());
                tddao.updateTipoDedicacion(td);
                JOptionPane.showMessageDialog(this.edittipo,"Se ha actualizado este tipo de dedicación");
                getAllTipoDedicacion();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.edittipo, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.edittipo.btnRegresar) {
            this.edittipo.dispose();
        }
    }

    public void getAllTipoDedicacion() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.frmtipodedica.tableTipoDedicacion.setModel(model);
            model.addColumn("Código");
            model.addColumn("Descripción");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.frmtipodedica.tableTipoDedicacion.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[3];                    

            if (this.frmtipodedica.txtCodigo.getText().isEmpty()) {
                this.frmtipodedica.tableTipoDedicacion.removeAll();
                List<TipoDedicacion> ltd = tddao.AllTiposDedicacion();
                for (TipoDedicacion td : ltd) {
                    data[0] = td.getID_TIPO_DEDICACION();
                    data[1] = td.getDESCRIPCION();
                    data[2] = td.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.frmtipodedica.tableTipoDedicacion.removeAll();
                TipoDedicacion td = tddao.findTipoDedicacionById(Integer.parseInt(this.frmtipodedica.txtCodigo.getText().toString()));
                if (td != null) {
                    data[0] = td.getID_TIPO_DEDICACION();
                    data[1] = td.getDESCRIPCION();
                    data[2] = td.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.frmtipodedica, "El registro con codigo " + this.frmtipodedica.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.frmtipodedica, "Error: " + ex.getMessage());
        }
    }
    
}
