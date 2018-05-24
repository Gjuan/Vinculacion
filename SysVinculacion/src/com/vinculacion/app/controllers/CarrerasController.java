package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.CarrerasDAO;
import com.vinculacion.app.dao.EscuelaDAO;
import com.vinculacion.app.model.Carreras;
import com.vinculacion.app.model.Escuela;
import com.vinculacion.app.views.JFrameCarreras;
import com.vinculacion.app.views.JFrameEditCarrera;
import com.vinculacion.app.views.JFrameNuevaCarrera;
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
public class CarrerasController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameCarreras jfrcarrera = new JFrameCarreras(mp, false);
    JFrameNuevaCarrera jfrnuevacarrera = new JFrameNuevaCarrera(mp, false);
    JFrameEditCarrera jfreditcarrera = new JFrameEditCarrera(mp, false);
    CarrerasDAO cdao;
    EscuelaDAO edao;
    List<Escuela> lescuela;
     
    public CarrerasController(MenuPrincipal menup, JFrameCarreras carrera, JFrameNuevaCarrera nueva, JFrameEditCarrera edit) {
        this.mp = menup;
        this.jfrcarrera = carrera;
        this.jfrnuevacarrera = nueva;
        this.jfreditcarrera = edit;
        
        this.jfrcarrera.btnBuscar.addActionListener(this);
        this.jfrcarrera.btnDarBaja.addActionListener(this);
        this.jfrcarrera.btnNueva.addActionListener(this);
        this.jfrcarrera.btnEditar.addActionListener(this);
        
        this.jfrnuevacarrera.btnGuardar.addActionListener(this);
        this.jfrnuevacarrera.btnRegresar.addActionListener(this);
        
        this.jfreditcarrera.btnGuardar.addActionListener(this);
        this.jfreditcarrera.btnRegresar.addActionListener(this);
        
        this.jfrcarrera.comboEscuelas.addActionListener(this);
        
        cdao = new CarrerasDAO();
        edao = new EscuelaDAO();
        getAllCarreras();
        
        lescuela = edao.AllEscuelas();
        
        this.jfrcarrera.comboEscuelas.addItem("SELECCIONE");        
        for (Escuela escuela : lescuela) {
            this.jfrcarrera.comboEscuelas.addItem(escuela.getNOMBRE_ESCUELA());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrcarrera.btnBuscar) {
            getAllCarreras();
        }
        if (e.getSource() == this.jfrcarrera.comboEscuelas) {
            getAllCarreraByEscuela();
        }
        if (e.getSource() == this.jfrcarrera.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrcarrera.tableCarreras.getValueAt(this.jfrcarrera.tableCarreras.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrcarrera, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    cdao.deleteCarreraById(id);
                    getAllCarreras();
                    JOptionPane.showMessageDialog(this.jfrcarrera,  "Se ha dado de baja al registro!!");                    
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrcarrera, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrcarrera.btnEditar) {
            try {
                if (!this.jfrcarrera.tableCarreras.getValueAt(this.jfrcarrera.tableCarreras.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfreditcarrera.setVisible(true);
                    this.jfreditcarrera.comboEscuela.removeAllItems();
                    for (Escuela escuela : lescuela) {
                        this.jfreditcarrera.comboEscuela.addItem(escuela.getNOMBRE_ESCUELA());
                    }
                    this.jfreditcarrera.txtCodigo.setText(this.jfrcarrera.tableCarreras.getValueAt(this.jfrcarrera.tableCarreras.getSelectedRow(), 0).toString());
                    this.jfreditcarrera.txtDescripcion.setText(this.jfrcarrera.tableCarreras.getValueAt(this.jfrcarrera.tableCarreras.getSelectedRow(), 1).toString());                    
                    this.jfreditcarrera.comboEscuela.setSelectedItem(this.jfrcarrera.tableCarreras.getValueAt(this.jfrcarrera.tableCarreras.getSelectedRow(), 2).toString());
                    this.jfreditcarrera.comboEstado.setSelectedItem(this.jfrcarrera.tableCarreras.getValueAt(this.jfrcarrera.tableCarreras.getSelectedRow(), 3).toString());
                }else{
                    JOptionPane.showMessageDialog(this.jfrcarrera,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrcarrera,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrcarrera.btnNueva) {
            this.jfrnuevacarrera.setVisible(true);
            this.jfrnuevacarrera.comboEscuela.removeAllItems();
            for (Escuela escuela : lescuela) {
                this.jfrnuevacarrera.comboEscuela.addItem(escuela.getNOMBRE_ESCUELA());
            }
            this.jfrcarrera.dispose();
        }
        /*NUEVA CARRERA*/
        if (e.getSource() == this.jfrnuevacarrera.btnGuardar) {
            try {
                if (this.jfrnuevacarrera.txtDescripcion.getText().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevacarrera, "La descripción de la carrera es requerida !!");
                }else{
                    Carreras carrera = new Carreras();
                    carrera.setDESCRIPCION((this.jfrnuevacarrera.txtDescripcion.getText().toString()).toUpperCase());
                    Escuela escuela = edao.findEscuelaByName(this.jfrnuevacarrera.comboEscuela.getSelectedItem().toString());
                    carrera.setEscuela(escuela);
                    carrera.setESTADO("ACTIVO");
                    cdao.saveCarreras(carrera);
                    JOptionPane.showMessageDialog(this.jfrnuevacarrera, "Carrera registrada!!");
                    this.jfrnuevacarrera.txtDescripcion.setText("");
                    this.jfrnuevacarrera.comboEscuela.setSelectedIndex(0);
                    this.jfrnuevacarrera.txtDescripcion.requestFocus();
                }
            } catch (Exception xe) {
                JOptionPane.showMessageDialog(this.jfrnuevacarrera, "Error: " + xe.getMessage());
            }
        }
        if (e.getSource() == this.jfrnuevacarrera.btnRegresar) {
            this.jfrcarrera.setVisible(true);
            getAllCarreras();
            this.jfrnuevacarrera.dispose();
        }
        /*EDITAR CARRERAS*/
        if (e.getSource() == this.jfreditcarrera.btnGuardar) {
            try {
                Carreras carrera = new Carreras();                    
                carrera.setID_CARRERA(Integer.parseInt(this.jfreditcarrera.txtCodigo.getText().toString()));
                carrera.setDESCRIPCION((this.jfreditcarrera.txtDescripcion.getText().toString()).toUpperCase());
                Escuela escuela = edao.findEscuelaByName(this.jfreditcarrera.comboEscuela.getSelectedItem().toString());
                carrera.setEscuela(escuela);
                carrera.setESTADO(this.jfreditcarrera.comboEstado.getSelectedItem().toString());
                cdao.updateCarrera(carrera);
                JOptionPane.showMessageDialog(this.jfreditcarrera,"Se ha actualizado esta carrera");
                getAllCarreras();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfreditcarrera, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfreditcarrera.btnRegresar) {
            this.jfreditcarrera.dispose();
        }
    }    
    
    public void setDefaultTable(DefaultTableModel dtm){
        dtm.addColumn("Código");
        dtm.addColumn("Descripción de carrera");
        dtm.addColumn("Escuela");
        dtm.addColumn("Estado");
        TableColumnModel tcm =  this.jfrcarrera.tableCarreras.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(3);
        tcm.getColumn(1).setPreferredWidth(250);
    }
    
    public void getAllCarreras() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrcarrera.tableCarreras.setModel(model);
            setDefaultTable(model);            
            Object [] data = new Object[4];                    

            if (this.jfrcarrera.txtCodigo.getText().isEmpty()) {
                this.jfrcarrera.tableCarreras.removeAll();
                List<Carreras> lcarrera = cdao.AllCarreras();
                for (Carreras carrera : lcarrera) {
                    data[0] = carrera.getID_CARRERA();
                    data[1] = carrera.getDESCRIPCION();
                    data[2] = carrera.getEscuela().getNOMBRE_ESCUELA();
                    data[3] = carrera.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrcarrera.tableCarreras.removeAll();
                Carreras carrera = cdao.findCarreraById(Integer.parseInt(this.jfrcarrera.txtCodigo.getText().toString()));
                if (carrera != null) {
                    data[0] = carrera.getID_CARRERA();
                    data[1] = carrera.getDESCRIPCION();
                    data[2] = carrera.getEscuela().getNOMBRE_ESCUELA();
                    data[3] = carrera.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrcarrera, "El registro con codigo " + this.jfrcarrera.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrcarrera, "Error: " + ex.getMessage());
        }
    }

    public void getAllCarreraByEscuela() {
        try {
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrcarrera.tableCarreras.setModel(model);
            setDefaultTable(model);
            
            this.jfrcarrera.tableCarreras.removeAll();
            Object [] data = new Object[4];    
            List<Carreras> lcarrera = cdao.findCarrerasByEscuela(this.jfrcarrera.comboEscuelas.getSelectedItem().toString());
            for (Carreras carrera : lcarrera) {
                data[0] = carrera.getID_CARRERA();
                data[1] = carrera.getDESCRIPCION();
                data[2] = carrera.getEscuela().getNOMBRE_ESCUELA();
                data[3] = carrera.getESTADO();
                model.addRow(data);
            }
        } catch (Exception e) {
            getAllCarreras();
        }
    }
    
}
