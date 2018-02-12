package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.CargoDAO;
import com.vinculacion.app.model.Cargo;
import com.vinculacion.app.views.JFrameCargo;
import com.vinculacion.app.views.JFrameEditCargo;
import com.vinculacion.app.views.JFrameNuevoCargo;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CargoController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameCargo jfrcargo = new JFrameCargo(mp, false);
    JFrameNuevoCargo jfrnuevocargo = new JFrameNuevoCargo(mp, false);
    JFrameEditCargo editcargo = new JFrameEditCargo(mp, false);
    CargoDAO cargodao;
    
    public CargoController(MenuPrincipal menup, JFrameCargo cargo, JFrameNuevoCargo newcargo, JFrameEditCargo edcargo) {
        this.mp = menup;
        this.jfrcargo = cargo;
        this.jfrnuevocargo = newcargo;
        this.editcargo = edcargo;
        cargodao = new CargoDAO();
        
        this.jfrcargo.btnBuscar.addActionListener(this);
        this.jfrcargo.btnDarBaja.addActionListener(this);
        this.jfrcargo.btnEditar.addActionListener(this);
        this.jfrcargo.btnNuevo.addActionListener(this);
        
        this.jfrnuevocargo.btnGuardar.addActionListener(this);
        this.jfrnuevocargo.btnRegresar.addActionListener(this);
        
        this.editcargo.btnGuardar.addActionListener(this);
        this.editcargo.btnRegresar.addActionListener(this);
        getAllCargos();
    }
    
    public void getAllCargos(){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrcargo.tableCargos.setModel(model);
            model.addColumn("Código");
            model.addColumn("Descripción del cargo");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.jfrcargo.tableCargos.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[3];                    

            if (this.jfrcargo.txtCodigo.getText().isEmpty()) {
                this.jfrcargo.tableCargos.removeAll();
                List<Cargo> lcargo = cargodao.AllCargos();
                for (Cargo cargo : lcargo) {
                    data[0] = cargo.getID_CARGO();
                    data[1] = cargo.getDESCRIPCION();
                    data[2] = cargo.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrcargo.tableCargos.removeAll();                
                Cargo cargo = cargodao.findCargoById(Integer.parseInt(this.jfrcargo.txtCodigo.getText().toString()));
                if (cargo != null) {
                    data[0] = cargo.getID_CARGO();
                    data[1] = cargo.getDESCRIPCION();
                    data[2] = cargo.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrcargo, "El registro con codigo " + this.jfrcargo.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrcargo, "Error: " + ex.getMessage());
        }    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrcargo.btnBuscar) {
            getAllCargos();
        }
        if (e.getSource() == this.jfrcargo.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrcargo.tableCargos.getValueAt(this.jfrcargo.tableCargos.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrcargo, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    cargodao.deleteCargo(id);
                    JOptionPane.showMessageDialog(this.jfrcargo,  "Se ha dado de baja al registro!!");
                    getAllCargos();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrcargo, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrcargo.btnEditar) {
            try {
                if (!this.jfrcargo.tableCargos.getValueAt(this.jfrcargo.tableCargos.getSelectedRow(), 0).toString().isEmpty()) {
                    this.editcargo.setVisible(true);
                    this.editcargo.txtCodigo.setText(this.jfrcargo.tableCargos.getValueAt(this.jfrcargo.tableCargos.getSelectedRow(), 0).toString());
                    this.editcargo.txtDescripcion.setText(this.jfrcargo.tableCargos.getValueAt(this.jfrcargo.tableCargos.getSelectedRow(), 1).toString());
                    Cargo cargo = cargodao.findCargoById(Integer.parseInt(this.jfrcargo.tableCargos.getValueAt(this.jfrcargo.tableCargos.getSelectedRow(), 0).toString()));
                    this.editcargo.comboEstado.setSelectedItem(cargo.getESTADO());
                }else{
                    JOptionPane.showMessageDialog(this.jfrcargo,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrcargo,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrcargo.btnNuevo) {
            this.jfrnuevocargo.setVisible(true);
            this.jfrcargo.dispose();
        }
        if (e.getSource() == this.jfrnuevocargo.btnGuardar) {
            try {
                if (this.jfrnuevocargo.txtDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevocargo, "La descripción del cargo es requerida");                 
                }else{
                    Cargo cargo = new Cargo();
                    cargo.setDESCRIPCION((this.jfrnuevocargo.txtDescripcion.getText().toString()).toUpperCase());
                    cargo.setESTADO("ACTIVO");
                    cargodao.saveCargo(cargo);
                    JOptionPane.showMessageDialog(this.jfrnuevocargo, "Cargo registrado correctamente !!");
                    this.jfrnuevocargo.txtDescripcion.setText("");
                    this.jfrnuevocargo.txtDescripcion.requestFocus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevocargo, "Error: " + ex.getMessage());                 
            }
        }
        if (e.getSource() == this.jfrnuevocargo.btnRegresar) {
            this.jfrnuevocargo.dispose();
            this.jfrcargo.setVisible(true);
            getAllCargos();
        }
        if (e.getSource() == this.editcargo.btnGuardar) {
            try {
                Cargo cargo = new Cargo();
                cargo.setID_CARGO(Integer.parseInt(this.editcargo.txtCodigo.getText().toString()));
                cargo.setDESCRIPCION((this.editcargo.txtDescripcion.getText().toString()).toUpperCase());
                cargo.setESTADO(this.editcargo.comboEstado.getSelectedItem().toString());
                cargodao.updateCargo(cargo);
                JOptionPane.showMessageDialog(this.editcargo,"Se ha actualizado este cargo");
                getAllCargos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.editcargo, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.editcargo.btnRegresar) {
            this.editcargo.dispose();
            getAllCargos();
        }
    }   
}
