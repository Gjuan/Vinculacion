package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.PerfilDAO;
import com.vinculacion.app.model.Perfil;
import com.vinculacion.app.views.JFrameEditPerfil;
import com.vinculacion.app.views.JFrameNuevoPerfil;
import com.vinculacion.app.views.JFramePerfil;
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
public class PerfilController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFramePerfil jfrperfil = new JFramePerfil(mp, false);
    JFrameNuevoPerfil jfrnuevo = new JFrameNuevoPerfil(mp, false);
    JFrameEditPerfil jfredit = new JFrameEditPerfil(mp, false);
    
    PerfilDAO perfildao;
        
    public PerfilController(MenuPrincipal menup, JFramePerfil perfil, JFrameNuevoPerfil nuevo, JFrameEditPerfil edit) {
        this.mp = menup;
        this.jfrperfil = perfil;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrperfil.btnBuscar.addActionListener(this);
        this.jfrperfil.btnDarBaja.addActionListener(this);
        this.jfrperfil.btnEditar.addActionListener(this);
        this.jfrperfil.btnNuevo.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        
        perfildao = new PerfilDAO();
        getAllPerfiles();
    }    
    
    public void getAllPerfiles(){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrperfil.tablePerfil.setModel(model);
            model.addColumn("Código");
            model.addColumn("Descripción");
            model.addColumn("Estado");
            TableColumnModel tcm =  this.jfrperfil.tablePerfil.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[3];                    

            if (this.jfrperfil.txtCodigo.getText().isEmpty()) {
                this.jfrperfil.tablePerfil.removeAll();
                List<Perfil> lperfil = perfildao.AllPerfil();
                for (Perfil perfil : lperfil) {
                    data[0] = perfil.getId_perfil();
                    data[1] = perfil.getDescripcion();
                    data[2] = perfil.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrperfil.tablePerfil.removeAll();
                Perfil perfil = perfildao.findPerfilById(Integer.parseInt(this.jfrperfil.txtCodigo.getText().toString()));
                if (perfil != null) {
                    data[0] = perfil.getId_perfil();
                    data[1] = perfil.getDescripcion();
                    data[2] = perfil.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrperfil, "El registro con codigo " + this.jfrperfil.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrperfil, "Error: " + ex.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrperfil.btnBuscar) {
            getAllPerfiles();
        }
        if (e.getSource() == this.jfrperfil.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrperfil.tablePerfil.getValueAt(this.jfrperfil.tablePerfil.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrperfil, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    perfildao.deletePerfilById(id);
                    JOptionPane.showMessageDialog(this.jfrperfil,  "Se ha dado de baja al registro!!");
                    getAllPerfiles();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrperfil, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrperfil.btnEditar) {
            try {
                if (!this.jfrperfil.tablePerfil.getValueAt(this.jfrperfil.tablePerfil.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.txtCodigo.setText(this.jfrperfil.tablePerfil.getValueAt(this.jfrperfil.tablePerfil.getSelectedRow(), 0).toString());
                    this.jfredit.txtDescripcion.setText(this.jfrperfil.tablePerfil.getValueAt(this.jfrperfil.tablePerfil.getSelectedRow(), 1).toString());
                    this.jfredit.comboEstado.setSelectedItem(this.jfrperfil.tablePerfil.getValueAt(this.jfrperfil.tablePerfil.getSelectedRow(), 2).toString());
                }else{
                    JOptionPane.showMessageDialog(this.jfrperfil,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrperfil,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrperfil.btnNuevo) {
            this.jfrnuevo.setVisible(true);
            this.jfrperfil.dispose();
        }
        /*NUEVO PERFIL*/
        if (e.getSource() == this.jfrnuevo.btnGuardar) {
            try {
                if (this.jfrnuevo.txtDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevo, "La descripción del perfil es requerida");                 
                }else{
                    Perfil perfil = new Perfil();
                    perfil.setDescripcion((this.jfrnuevo.txtDescripcion.getText().toString()).toUpperCase());
                    perfil.setESTADO(("ACTIVO").toUpperCase());
                    perfildao.savePerfil(perfil);
                    JOptionPane.showMessageDialog(this.jfrnuevo, "Perfil registrado correctamente !!");
                    this.jfrnuevo.txtDescripcion.setText("");
                    this.jfrnuevo.txtDescripcion.requestFocus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevo, "Error: " + ex.getMessage());                 
            }
        }
        if (e.getSource() == this.jfrnuevo.btnRegresar) {
            this.jfrnuevo.dispose();
            this.jfrperfil.setVisible(true);
            getAllPerfiles();
        }
        /*EDITA PERFIL*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            try {
                Perfil perfil = new Perfil();
                perfil.setId_perfil(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                perfil.setDescripcion((this.jfredit.txtDescripcion.getText().toString()).toUpperCase());
                perfil.setESTADO(this.jfredit.comboEstado.getSelectedItem().toString());
                perfildao.updatePerfil(perfil);
                JOptionPane.showMessageDialog(this.jfredit,"Se ha actualizado este perfil");
                getAllPerfiles();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
    }
}
