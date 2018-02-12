package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.EscuelaDAO;
import com.vinculacion.app.dao.FacultadDAO;
import com.vinculacion.app.model.Escuela;
import com.vinculacion.app.model.Facultad;
import com.vinculacion.app.views.JFrameEditEscuela;
import com.vinculacion.app.views.JFrameEscuela;
import com.vinculacion.app.views.JFrameNuevaEscuela;
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
public class EscuelaController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameEscuela jfrescuela = new JFrameEscuela(mp, false);
    JFrameNuevaEscuela jfrnueva = new JFrameNuevaEscuela(mp, false);
    JFrameEditEscuela jfredit = new JFrameEditEscuela(mp, false);
    
    EscuelaDAO edao;
    FacultadDAO fdao;
    List<Facultad> lf;
    
    public EscuelaController(MenuPrincipal menup, JFrameEscuela escuela, JFrameNuevaEscuela nueva, JFrameEditEscuela edit)  {
        this.mp = menup;
        this.jfrescuela = escuela;
        this.jfrnueva = nueva;
        this.jfredit = edit;
        
        this.jfrescuela.btnBuscar.addActionListener(this);
        this.jfrescuela.btnDarBaja.addActionListener(this);
        this.jfrescuela.btnEditar.addActionListener(this);
        this.jfrescuela.btnNueva.addActionListener(this);
        
        this.jfrnueva.btnGuardar.addActionListener(this);
        this.jfrnueva.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        
        this.jfrescuela.comboFacultad.addActionListener(this);
        
        fdao = new FacultadDAO();
        edao = new EscuelaDAO();
        
        lf = fdao.AllFacultad();
        this.jfrescuela.comboFacultad.addItem("SELECCIONE");        
        for (Facultad facultad : lf) {
            this.jfrescuela.comboFacultad.addItem(facultad.getDESCRIPCION());
        }
        getAllEscuelas();
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrescuela.btnBuscar) {
            getAllEscuelas();
        }
        if (e.getSource() == this.jfrescuela.comboFacultad) {
            getAllEscuelasByFacultad();
        }
        if (e.getSource() == this.jfrescuela.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrescuela.tableEscuelas.getValueAt(this.jfrescuela.tableEscuelas.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrescuela, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    edao.deleteEscuelaById(id);
                    getAllEscuelas();
                    JOptionPane.showMessageDialog(this.jfrescuela,  "Se ha dado de baja al registro!!");                    
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrescuela, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrescuela.btnEditar) {
             try {
                if (!this.jfrescuela.tableEscuelas.getValueAt(this.jfrescuela.tableEscuelas.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.comboFacultad.removeAllItems();
                    for (Facultad facultad : lf) {
                        this.jfredit.comboFacultad.addItem(facultad.getDESCRIPCION());
                    }
                    this.jfredit.txtCodigo.setText(this.jfrescuela.tableEscuelas.getValueAt(this.jfrescuela.tableEscuelas.getSelectedRow(), 0).toString());
                    this.jfredit.txtNombreEscuela.setText(this.jfrescuela.tableEscuelas.getValueAt(this.jfrescuela.tableEscuelas.getSelectedRow(), 1).toString());                                    
                    this.jfredit.txtDescripcion.setText(this.jfrescuela.tableEscuelas.getValueAt(this.jfrescuela.tableEscuelas.getSelectedRow(), 2).toString());    
                    this.jfredit.comboFacultad.setSelectedItem(this.jfrescuela.tableEscuelas.getValueAt(this.jfrescuela.tableEscuelas.getSelectedRow(), 3).toString());
                    this.jfredit.comboEstado.setSelectedItem(this.jfrescuela.tableEscuelas.getValueAt(this.jfrescuela.tableEscuelas.getSelectedRow(), 4).toString());
                }else{
                    JOptionPane.showMessageDialog(this.jfrescuela,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrescuela,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrescuela.btnNueva) {
            this.jfrnueva.setVisible(true);
            this.jfrnueva.comboFacultad.removeAllItems();
            for (Facultad facultad : lf) {
                this.jfrnueva.comboFacultad.addItem(facultad.getDESCRIPCION());
            }
            this.jfrescuela.dispose();
        }
        /*Nueva escuela*/
        if (e.getSource() == this.jfrnueva.btnGuardar) {
            try {
                if (this.jfrnueva.txtDescripcion.getText().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnueva, "La descripción de la escuela es requeridad !!");
                }else if(this.jfrnueva.txtNombreEscuela.getText().toString().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnueva, "El nombre de la escuela es requeridad !!");                
                }else{
                    Escuela escuela = new Escuela();
                    escuela.setNOMBRE_ESCUELA((this.jfrnueva.txtNombreEscuela.getText().toString()).toUpperCase());                    
                    escuela.setDESCRIPCION((this.jfrnueva.txtDescripcion.getText().toString()).toUpperCase());
                    Facultad facultad = fdao.findFacultadByDescription(this.jfrnueva.comboFacultad.getSelectedItem().toString());
                    escuela.setFacultad(facultad);
                    escuela.setESTADO("ACTIVO");
                    edao.saveEscuela(escuela);
                    JOptionPane.showMessageDialog(this.jfrnueva, "Escuela registrada!!");
                    this.jfrnueva.txtDescripcion.setText("");
                    this.jfrnueva.comboFacultad.setSelectedIndex(0);
                    this.jfrnueva.txtNombreEscuela.setText("");
                    this.jfrnueva.txtNombreEscuela.requestFocus();
                }
            } catch (Exception xe) {
                JOptionPane.showMessageDialog(this.jfrnueva, "Error: " + xe.getMessage());
            }
        }
        if (e.getSource() == this.jfrnueva.btnRegresar) {
            this.jfrnueva.dispose();
            this.jfrescuela.setVisible(true);
            getAllEscuelas();
        }
        /*Editar escuelas*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            try {
                Escuela escuela = new Escuela();                   
                escuela.setID_ESCUELA(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                escuela.setNOMBRE_ESCUELA((this.jfredit.txtNombreEscuela.getText().toString()).toUpperCase());
                escuela.setDESCRIPCION((this.jfredit.txtDescripcion.getText().toString()).toUpperCase());
                Facultad facultad = fdao.findFacultadByDescription(this.jfredit.comboFacultad.getSelectedItem().toString());
                escuela.setFacultad(facultad);
                escuela.setESTADO(this.jfredit.comboEstado.getSelectedItem().toString());
                edao.updateEscuela(escuela);
                JOptionPane.showMessageDialog(this.jfredit,"Se ha actualizado esta escuela");
                getAllEscuelas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
    }
    
    public void setDefaultTable(DefaultTableModel dtm){
        dtm.addColumn("Código");
        dtm.addColumn("Nombre de la escuela");
        dtm.addColumn("Descripción");
        dtm.addColumn("Facultad");
        dtm.addColumn("Estado");
        TableColumnModel tcm =  this.jfrescuela.tableEscuelas.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(3);
        tcm.getColumn(1).setPreferredWidth(150);
        tcm.getColumn(2).setPreferredWidth(250);        
    }
    
    public void getAllEscuelas() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrescuela.tableEscuelas.setModel(model);
            setDefaultTable(model);            
            Object [] data = new Object[5];                    

            if (this.jfrescuela.txtCodigo.getText().isEmpty()) {
                this.jfrescuela.tableEscuelas.removeAll();
                List<Escuela> lescuela = edao.AllEscuelas();
                for (Escuela escuela : lescuela) {
                    data[0] = escuela.getID_ESCUELA();
                    data[1] = escuela.getNOMBRE_ESCUELA();
                    data[2] = escuela.getDESCRIPCION();
                    data[3] = escuela.getFacultad().getDESCRIPCION();
                    data[4] = escuela.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrescuela.tableEscuelas.removeAll();
                Escuela escuela = edao.findEscuelaById(Integer.parseInt(this.jfrescuela.txtCodigo.getText().toString()));
                if (escuela != null) {
                    data[0] = escuela.getID_ESCUELA();
                    data[1] = escuela.getNOMBRE_ESCUELA();
                    data[2] = escuela.getDESCRIPCION();
                    data[3] = escuela.getFacultad().getDESCRIPCION();
                    data[4] = escuela.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrescuela, "El registro con codigo " + this.jfrescuela.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrescuela, "Error: " + ex.getMessage());
        }
    }
    
    public void getAllEscuelasByFacultad(){
        try {
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrescuela.tableEscuelas.setModel(model);
            setDefaultTable(model);
            
            this.jfrescuela.tableEscuelas.removeAll();
            Object [] data = new Object[5];    
            List<Escuela> lescuela = edao.findEscuelaByFacultad(this.jfrescuela.comboFacultad.getSelectedItem().toString());
            for (Escuela escuela : lescuela) {
                data[0] = escuela.getID_ESCUELA();
                data[1] = escuela.getNOMBRE_ESCUELA();
                data[2] = escuela.getDESCRIPCION();
                data[3] = escuela.getFacultad().getDESCRIPCION();
                data[4] = escuela.getESTADO();                    
                model.addRow(data);
            }
        } catch (Exception ex) {
            getAllEscuelas();
        }
    }
    
}
