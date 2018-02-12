package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.GeneroDAO;
import com.vinculacion.app.model.Genero;
import com.vinculacion.app.views.JFrameEditGenero;
import com.vinculacion.app.views.JFrameGenero;
import com.vinculacion.app.views.JFrameNuevoGenero;
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
public class GeneroController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameGenero jfrgenero = new JFrameGenero(mp, false);
    JFrameNuevoGenero jfrnuevo = new JFrameNuevoGenero(mp, false);
    JFrameEditGenero jfredit = new JFrameEditGenero(mp, false);
    GeneroDAO gdao;
    
    public GeneroController(MenuPrincipal menup, JFrameGenero genero, JFrameNuevoGenero nuevo, JFrameEditGenero edit) {
        this.mp = menup;
        this.jfrgenero = genero;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrgenero.btnBuscar.addActionListener(this);
        this.jfrgenero.btnDarBaja.addActionListener(this);
        this.jfrgenero.btnEditar.addActionListener(this);
        this.jfrgenero.btnNuevo.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        gdao = new GeneroDAO();
        getAllGeneros();
    }
    
    public void getAllGeneros(){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrgenero.tableGenero.setModel(model);
            model.addColumn("Código");
            model.addColumn("Descripción del género");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.jfrgenero.tableGenero.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[3];                    

            if (this.jfrgenero.txtCodigo.getText().isEmpty()) {
                this.jfrgenero.tableGenero.removeAll();
                List<Genero> lgen = gdao.AllGeneros();
                for (Genero genero : lgen) {
                    data[0] = genero.getID_GENERO();
                    data[1] = genero.getDESCRIPCION();
                    data[2] = genero.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrgenero.tableGenero.removeAll();                
                Genero genero = gdao.findGeneroById(Integer.parseInt(this.jfrgenero.txtCodigo.getText().toString()));
                if (genero != null) {
                    data[0] = genero.getID_GENERO();
                    data[1] = genero.getDESCRIPCION();
                    data[2] = genero.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrgenero, "El registro con codigo " + this.jfrgenero.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrgenero, "Error: " + ex.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrgenero.btnBuscar) {
            getAllGeneros();
        }
        if (e.getSource() == this.jfrgenero.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrgenero.tableGenero.getValueAt(this.jfrgenero.tableGenero.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrgenero, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    gdao.deleteGenero(id);
                    JOptionPane.showMessageDialog(this.jfrgenero,  "Se ha dado de baja al registro!!");
                    getAllGeneros();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrgenero, "Debe seleccionar un registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrgenero.btnEditar) {
            try {
                if (!this.jfrgenero.tableGenero.getValueAt(this.jfrgenero.tableGenero.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.txtCodigo.setText(this.jfrgenero.tableGenero.getValueAt(this.jfrgenero.tableGenero.getSelectedRow(), 0).toString());
                    this.jfredit.txtDescripcion.setText(this.jfrgenero.tableGenero.getValueAt(this.jfrgenero.tableGenero.getSelectedRow(), 1).toString());                    
                    this.jfredit.comboEstado.setSelectedItem(this.jfrgenero.tableGenero.getValueAt(this.jfrgenero.tableGenero.getSelectedRow(), 2).toString());
                }else{
                    JOptionPane.showMessageDialog(this.jfrgenero,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrgenero,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrgenero.btnNuevo) {
            this.jfrnuevo.setVisible(true);
            this.jfrgenero.dispose();
        }
        /*NUEVO GENERO*/
        if (e.getSource() == this.jfrnuevo.btnGuardar) {
            try {
                if (this.jfrnuevo.txtDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevo, "La descripción del género es requerida");
                }else{
                    Genero genero = new Genero();
                    genero.setDESCRIPCION((this.jfrnuevo.txtDescripcion.getText().toString()).toUpperCase());
                    genero.setESTADO("ACTIVO");
                    gdao.saveGenero(genero);
                    JOptionPane.showMessageDialog(this.jfrnuevo, "Género registrado!!");
                    this.jfrnuevo.txtDescripcion.setText("");
                    this.jfrnuevo.txtDescripcion.requestFocus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevo, "Error: " + ex.getMessage());       
            }
        }
        if (e.getSource() == this.jfrnuevo.btnRegresar) {
            this.jfrnuevo.dispose();
            this.jfrgenero.setVisible(true);
            getAllGeneros();
        }
        /*EDITAR GÉNERO*/
        if (e.getSource() == this.jfredit.btnGuardar) {
             try {
                Genero genero = new Genero();
                genero.setID_GENERO(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                genero.setDESCRIPCION((this.jfredit.txtDescripcion.getText().toString()).toUpperCase());
                genero.setESTADO(this.jfredit.comboEstado.getSelectedItem().toString());
                gdao.updateGenero(genero);
                JOptionPane.showMessageDialog(this.jfredit,"Se ha actualizado este género ");
                getAllGeneros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
    }    
}
