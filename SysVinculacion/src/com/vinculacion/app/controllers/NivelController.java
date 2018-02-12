package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.NivelDAO;
import com.vinculacion.app.model.Nivel;
import com.vinculacion.app.views.JFrameEditNivel;
import com.vinculacion.app.views.JFrameNivel;
import com.vinculacion.app.views.JFrameNuevoNivel;
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
public class NivelController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameNivel jfrnivel = new JFrameNivel(mp, false);
    JFrameNuevoNivel jfrnuevo = new JFrameNuevoNivel(mp, false);
    JFrameEditNivel jfredit = new JFrameEditNivel(mp, false);
    NivelDAO niveldao;
    
    public NivelController(MenuPrincipal menup, JFrameNivel nivel, JFrameNuevoNivel nuevo, JFrameEditNivel edit) {
        this.mp = menup;
        this.jfrnivel = nivel;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrnivel.btnBuscar.addActionListener(this);
        this.jfrnivel.btnDarBaja.addActionListener(this);
        this.jfrnivel.btnEditar.addActionListener(this);
        this.jfrnivel.btnNuevo.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        
        niveldao = new NivelDAO();
        getAllNiveles();
    }
    
    public void getAllNiveles(){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrnivel.tableNivel.setModel(model);
            model.addColumn("Código");
            model.addColumn("Semestre");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.jfrnivel.tableNivel.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[3];                    

            if (this.jfrnivel.txtCodigo.getText().isEmpty()) {
                this.jfrnivel.tableNivel.removeAll();
                List<Nivel> lnivel = niveldao.AllNiveles();
                for (Nivel nivel : lnivel) {
                    data[0] = nivel.getID_NIVEL();
                    data[1] = nivel.getSEMESTRE();
                    data[2] = nivel.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrnivel.tableNivel.removeAll();
                Nivel nivel = niveldao.findNivelById(Integer.parseInt(this.jfrnivel.txtCodigo.getText().toString()));
                if (nivel != null) {
                    data[0] = nivel.getID_NIVEL();
                    data[1] = nivel.getSEMESTRE();
                    data[2] = nivel.getESTADO();                    
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrnivel, "El registro con codigo " + this.jfrnivel.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrnivel, "Error: " + ex.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrnivel.btnBuscar) {
            getAllNiveles();
        }
        if (e.getSource() == this.jfrnivel.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrnivel.tableNivel.getValueAt(this.jfrnivel.tableNivel.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrnivel, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    niveldao.deleteNivel(id);
                    JOptionPane.showMessageDialog(this.jfrnivel,  "Se ha dado de baja al registro!!");
                    getAllNiveles();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnivel, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrnivel.btnEditar) {
            try {
                if (!this.jfrnivel.tableNivel.getValueAt(this.jfrnivel.tableNivel.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.txtCodigo.setText(this.jfrnivel.tableNivel.getValueAt(this.jfrnivel.tableNivel.getSelectedRow(), 0).toString());
                    this.jfredit.txtSemestre.setText(this.jfrnivel.tableNivel.getValueAt(this.jfrnivel.tableNivel.getSelectedRow(), 1).toString());
                    this.jfredit.comboEstado.setSelectedItem(this.jfrnivel.tableNivel.getValueAt(this.jfrnivel.tableNivel.getSelectedRow(), 2).toString());
                }else{
                    JOptionPane.showMessageDialog(this.jfrnivel,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrnivel,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrnivel.btnNuevo) {
            this.jfrnivel.dispose();
            this.jfrnuevo.setVisible(true);
        }
        /*Nuevo nivel*/
        if (e.getSource() == this.jfrnuevo.btnGuardar) {
            try {
                if (this.jfrnuevo.txtSemestre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevo, "El semestre es requerido");                 
                }else{
                    Nivel nivel = new Nivel();
                    nivel.setSEMESTRE(this.jfrnuevo.txtSemestre.getText().toString());
                    nivel.setESTADO("ACTIVO");
                    niveldao.saveNivel(nivel);
                    JOptionPane.showMessageDialog(this.jfrnivel, "Nivel registrado correctamente !!");
                    this.jfrnuevo.txtSemestre.setText("");               
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevo, "Error: " + ex.getMessage());                 
            }
        }
        if (e.getSource() == this.jfrnuevo.btnRegresar) {
            this.jfrnuevo.dispose();
            this.jfrnivel.setVisible(true);
            getAllNiveles();
        }
        /*Editar nivel*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            try {
                Nivel nivel = new Nivel();
                nivel.setID_NIVEL(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                nivel.setSEMESTRE(this.jfredit.txtSemestre.getText().toString());
                nivel.setESTADO(this.jfredit.comboEstado.getSelectedItem().toString());
                niveldao.updateNivel(nivel);
                JOptionPane.showMessageDialog(this.jfredit,"Se ha actualizado este nivel");
                getAllNiveles();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
    }  
}
