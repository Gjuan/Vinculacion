package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.AsignaturasDAO;
import com.vinculacion.app.model.Asignaturas;
import com.vinculacion.app.views.JFrameAsignaturas;
import com.vinculacion.app.views.JFrameEditAsignatura;
import com.vinculacion.app.views.JFrameNuevaAsignatura;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AsignaturasController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameAsignaturas jfrasig = new JFrameAsignaturas(mp, false);
    JFrameEditAsignatura editasig = new JFrameEditAsignatura(mp, false);
    JFrameNuevaAsignatura nuevaasig = new JFrameNuevaAsignatura(mp, false);
    AsignaturasDAO asigdao;
    
    public AsignaturasController(MenuPrincipal menup, JFrameAsignaturas asig, JFrameEditAsignatura edit, JFrameNuevaAsignatura nuevo) {
        this.mp = menup;
        this.jfrasig = asig;
        this.editasig = edit;
        this.nuevaasig = nuevo;
        asigdao = new AsignaturasDAO();
        
        this.jfrasig.btnBuscar.addActionListener(this);
        this.jfrasig.btnDarBaja.addActionListener(this);
        this.jfrasig.btnEditar.addActionListener(this);
        this.jfrasig.btnNuevo.addActionListener(this);
        
        this.editasig.btnGuardar.addActionListener(this);
        this.editasig.btnRegresar.addActionListener(this);
        
        this.nuevaasig.btnGuardar.addActionListener(this);
        this.nuevaasig.btnRegresar.addActionListener(this);
        
        getAllAsignaturas();
    }
    
     public void getAllAsignaturas(){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrasig.tableAsignatura.setModel(model);
            model.addColumn("Código");
            model.addColumn("Nombre de la asignatura");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.jfrasig.tableAsignatura.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[3];                    

            if (this.jfrasig.txtCodigo.getText().isEmpty()) {
                this.jfrasig.tableAsignatura.removeAll();
                List<Asignaturas> lasig = asigdao.AllAsignaturas();
                for (Asignaturas asignatura : lasig) {
                    data[0] = asignatura.getID_ASIGNATURA();
                    data[1] = asignatura.getNOMBRE_ASIGNATURA();
                    data[2] = asignatura.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrasig.tableAsignatura.removeAll();                
                Asignaturas asignatura = asigdao.findAsignaturaById(Integer.parseInt(this.jfrasig.txtCodigo.getText().toString()));
                if (asignatura != null) {
                    data[0] = asignatura.getID_ASIGNATURA();
                    data[1] = asignatura.getNOMBRE_ASIGNATURA();
                    data[2] = asignatura.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrasig, "El registro con codigo " + this.jfrasig.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrasig, "Error: " + ex.getMessage());
        }    
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrasig.btnNuevo) {
            this.nuevaasig.setVisible(true);
            this.jfrasig.dispose();
        }
        if (e.getSource() == this.jfrasig.btnBuscar) {
            getAllAsignaturas();
        }
        if (e.getSource() == this.jfrasig.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrasig.tableAsignatura.getValueAt(this.jfrasig.tableAsignatura.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrasig, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    asigdao.deleteAsignatura(id);
                    JOptionPane.showMessageDialog(this.jfrasig,  "Se ha dado de baja al registro!!");
                    getAllAsignaturas();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrasig, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrasig.btnEditar) {
            try {
                if (!this.jfrasig.tableAsignatura.getValueAt(this.jfrasig.tableAsignatura.getSelectedRow(), 0).toString().isEmpty()) {
                    this.editasig.setVisible(true);
                    this.editasig.txtCodigo.setText(this.jfrasig.tableAsignatura.getValueAt(this.jfrasig.tableAsignatura.getSelectedRow(), 0).toString());
                    this.editasig.txtNombreAsignatura.setText(this.jfrasig.tableAsignatura.getValueAt(this.jfrasig.tableAsignatura.getSelectedRow(), 1).toString());
                    Asignaturas asignatura = asigdao.findAsignaturaById(Integer.parseInt(this.jfrasig.tableAsignatura.getValueAt(this.jfrasig.tableAsignatura.getSelectedRow(), 0).toString()));
                    this.editasig.comboEstado.setSelectedItem(asignatura.getESTADO());
                }else{
                    JOptionPane.showMessageDialog(this.jfrasig,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrasig,"Seleccione un registro y luego de click en editar");
            }
        }       
        if (e.getSource() == this.nuevaasig.btnRegresar) {
            this.nuevaasig.dispose();
            this.jfrasig.setVisible(true);
            getAllAsignaturas();
        }
        if (e.getSource() == this.nuevaasig.btnGuardar) {
            try {
                if (this.nuevaasig.txtNombreAsignatura.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.nuevaasig, "El nombre de la asignatura es requerido");
                }else{
                    Asignaturas asignatura = new Asignaturas();
                    asignatura.setNOMBRE_ASIGNATURA((this.nuevaasig.txtNombreAsignatura.getText().toString()).toUpperCase());
                    asignatura.setESTADO("ACTIVO");
                    asigdao.saveAsignatura(asignatura);
                    JOptionPane.showMessageDialog(this.nuevaasig, "Asignatura registrada!!");
                    this.nuevaasig.txtNombreAsignatura.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.nuevaasig, "Error: " + ex.getMessage());       
            }
        }
        if (e.getSource() == this.editasig.btnGuardar) {
            try {
                Asignaturas asignatura = new Asignaturas();
                asignatura.setID_ASIGNATURA(Integer.parseInt(this.editasig.txtCodigo.getText().toString()));
                asignatura.setNOMBRE_ASIGNATURA((this.editasig.txtNombreAsignatura.getText().toString()).toUpperCase());
                asignatura.setESTADO(this.editasig.comboEstado.getSelectedItem().toString());
                asigdao.updateAsignatura(asignatura);
                JOptionPane.showMessageDialog(this.editasig,"Se ha actualizado esta asignatura");
                getAllAsignaturas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.editasig, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.editasig.btnRegresar) {            
            this.editasig.dispose();
            getAllAsignaturas();
        }
    }   
}
