package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.SeccionDAO;
import com.vinculacion.app.model.Seccion;
import com.vinculacion.app.views.JFrameEditSeccion;
import com.vinculacion.app.views.JFrameNuevaSeccion;
import com.vinculacion.app.views.JFrameSeccion;
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
public class SeccionController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameSeccion jfrseccion = new JFrameSeccion(mp, false);
    JFrameNuevaSeccion newseccion = new JFrameNuevaSeccion(mp, false);
    JFrameEditSeccion editseccion = new JFrameEditSeccion(mp , false);
    SeccionDAO secciondao;
    
    public SeccionController(MenuPrincipal menu, JFrameSeccion fseccion, JFrameNuevaSeccion nueva, JFrameEditSeccion edit) {
        this.mp = menu;
        this.jfrseccion = fseccion;
        this.newseccion = nueva;
        this.editseccion = edit;
        
        this.jfrseccion.btnBuscar.addActionListener(this);
        this.jfrseccion.btnEditar.addActionListener(this);
        this.jfrseccion.btnNuevo.addActionListener(this);
        this.newseccion.btnGuardar.addActionListener(this);
        this.newseccion.btnRegresar.addActionListener(this);
        this.editseccion.btnGuardar.addActionListener(this);
        this.editseccion.btnRegresar.addActionListener(this);
        
        secciondao = new SeccionDAO();
        getAllSeccion();
    }
    
    public void getAllSeccion (){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrseccion.tableSeccion.setModel(model);
            model.addColumn("Código");
            model.addColumn("Descripción");
            TableColumnModel tcm =  this.jfrseccion.tableSeccion.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[2];                    

            if (this.jfrseccion.txtCodigo.getText().isEmpty()) {
                this.jfrseccion.tableSeccion.removeAll();
                List<Seccion> lseccion = secciondao.AllSecciones();
                for (Seccion seccion : lseccion) {
                    data[0] = seccion.getID_SECCION();
                    data[1] = seccion.getDESCRIPCION();
                    model.addRow(data);
                }
            }else{
                this.jfrseccion.tableSeccion.removeAll();               
                Seccion seccion= secciondao.findSeccionById(Integer.parseInt(this.jfrseccion.txtCodigo.getText().toString()));
                if (seccion != null) {
                    data[0] = seccion.getID_SECCION();
                    data[1] = seccion.getDESCRIPCION();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrseccion, "El registro con codigo " + this.jfrseccion.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrseccion, "Error: " + ex.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrseccion.btnBuscar) {
            getAllSeccion();
        }
        if (e.getSource() == this.jfrseccion.btnEditar) {
            try {
                if (!this.jfrseccion.tableSeccion.getValueAt(this.jfrseccion.tableSeccion.getSelectedRow(), 0).toString().isEmpty()) {
                    this.editseccion.setVisible(true);
                    this.editseccion.txtCodigo.setText(this.jfrseccion.tableSeccion.getValueAt(this.jfrseccion.tableSeccion.getSelectedRow(), 0).toString());
                    this.editseccion.txtDescripcion.setText(jfrseccion.tableSeccion.getValueAt(this.jfrseccion.tableSeccion.getSelectedRow(), 1).toString());
                }else{
                    JOptionPane.showMessageDialog(this.jfrseccion,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrseccion,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrseccion.btnNuevo) {
            this.newseccion.setVisible(true);
            this.jfrseccion.dispose();
        }
        if (e.getSource() == this.newseccion.btnGuardar) {
            try {
                if (this.newseccion.txtDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.newseccion, "La sección es requerida");
                }else{
                    Seccion seccion = new Seccion();
                    seccion.setDESCRIPCION(this.newseccion.txtDescripcion.getText().toString());
                    secciondao.saveSeccion(seccion);
                    JOptionPane.showMessageDialog(this.newseccion, "Sección registrada!!");
                    this.newseccion.txtDescripcion.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.newseccion, "Error: " + ex.getMessage());       
            }
        }
        if (e.getSource() == this.newseccion.btnRegresar) {
            this.jfrseccion.setVisible(true);
            this.newseccion.dispose();
            getAllSeccion();
        }
        if (e.getSource() == this.editseccion.btnGuardar) {
            try {
                Seccion seccion = new Seccion();
                seccion.setID_SECCION(Integer.parseInt(this.editseccion.txtCodigo.getText().toString()));
                seccion.setDESCRIPCION(this.editseccion.txtDescripcion.getText().toString());
                secciondao.updateSeccion(seccion);
                JOptionPane.showMessageDialog(this.editseccion,"Se ha actualizado esta sección");
                getAllSeccion();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.newseccion, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.editseccion.btnRegresar) {
            this.editseccion.dispose();
            getAllSeccion();
        }
    }    
}
