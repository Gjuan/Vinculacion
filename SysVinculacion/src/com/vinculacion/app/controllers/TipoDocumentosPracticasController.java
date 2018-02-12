package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.TipoDocumentoPracticasDAO;
import com.vinculacion.app.model.TipoDocumentoPracticas;
import com.vinculacion.app.views.JFrameEditTipoDocumentosPracticas;
import com.vinculacion.app.views.JFrameNuevoTipoDocumentoPracticas;
import com.vinculacion.app.views.JFrameTipoDocumentoPracticas;
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
public class TipoDocumentosPracticasController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameTipoDocumentoPracticas jfrtipodoc = new JFrameTipoDocumentoPracticas(mp, false);
    JFrameNuevoTipoDocumentoPracticas jfrnuevotipo = new JFrameNuevoTipoDocumentoPracticas(mp, false);
    JFrameEditTipoDocumentosPracticas jfredit = new JFrameEditTipoDocumentosPracticas(mp, false);
    TipoDocumentoPracticasDAO tdpdao;
    
    public TipoDocumentosPracticasController(MenuPrincipal menup, JFrameTipoDocumentoPracticas tipo, JFrameNuevoTipoDocumentoPracticas nuevo, JFrameEditTipoDocumentosPracticas edit) {
        this.mp = menup;
        this.jfrtipodoc = tipo;
        this.jfrnuevotipo = nuevo;
        this.jfredit = edit;
        
        this.jfrtipodoc.btnBuscar.addActionListener(this);
        this.jfrtipodoc.btnEditar.addActionListener(this);
        this.jfrtipodoc.btnNuevo.addActionListener(this);
        
        this.jfrnuevotipo.btnGuardar.addActionListener(this);
        this.jfrnuevotipo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        
        tdpdao = new TipoDocumentoPracticasDAO();
        getAllTipoDocPracticas();
    }   
    
    public void getAllTipoDocPracticas(){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrtipodoc.tableTipoDocPracticas.setModel(model);
            model.addColumn("Código");
            model.addColumn("Descripción");
            TableColumnModel tcm =  this.jfrtipodoc.tableTipoDocPracticas.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[2];                    

            if (this.jfrtipodoc.txtCodigo.getText().isEmpty()) {
                this.jfrtipodoc.tableTipoDocPracticas.removeAll();
                List<TipoDocumentoPracticas> ltdp = tdpdao.AllTiposDocumentosPracticas();
                for (TipoDocumentoPracticas tdp : ltdp) {
                    data[0] = tdp.getId_tipo_documento_practicas();
                    data[1] = tdp.getDescripcion();                  
                    model.addRow(data);
                }
            }else{
                this.jfrtipodoc.tableTipoDocPracticas.removeAll();
                TipoDocumentoPracticas tdp = tdpdao.findTipoDocumentoPracticasById(Integer.parseInt(this.jfrtipodoc.txtCodigo.getText().toString()));
                if (tdp != null) {
                    data[0] = tdp.getId_tipo_documento_practicas();
                    data[1] = tdp.getDescripcion();       
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrtipodoc, "El registro con codigo " + this.jfrtipodoc.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrtipodoc, "Error: " + ex.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrtipodoc.btnBuscar) {
            getAllTipoDocPracticas();
        }
        if (e.getSource() == this.jfrtipodoc.btnEditar) {
            try {
                if (!this.jfrtipodoc.tableTipoDocPracticas.getValueAt(this.jfrtipodoc.tableTipoDocPracticas.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.txtCodigo.setText(this.jfrtipodoc.tableTipoDocPracticas.getValueAt(this.jfrtipodoc.tableTipoDocPracticas.getSelectedRow(), 0).toString());
                    this.jfredit.txtDescripcion.setText(jfrtipodoc.tableTipoDocPracticas.getValueAt(jfrtipodoc.tableTipoDocPracticas.getSelectedRow(), 1).toString());              
                }else{
                    JOptionPane.showMessageDialog(this.jfrtipodoc,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrtipodoc,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrtipodoc.btnNuevo) {
            this.jfrtipodoc.dispose();
            this.jfrnuevotipo.setVisible(true);
        }
        /*NUEVO TIPO DE DOCUMENTOS DE PRÁCTICAS*/
        if (e.getSource() == this.jfrnuevotipo.btnGuardar) {
             try {
                if (this.jfrnuevotipo.txtDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevotipo, "La descripción es requerida");                 
                }else{
                    TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                    tdp.setDescripcion((this.jfrnuevotipo.txtDescripcion.getText().toString()).toUpperCase());
                    tdpdao.saveTipoDocumentoPracticas(tdp);
                    JOptionPane.showMessageDialog(this.jfrnuevotipo, "Tipo de documento de prácticas registrado correctamente !!");
                    this.jfrnuevotipo.txtDescripcion.setText("");
                    this.jfrnuevotipo.txtDescripcion.requestFocus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevotipo, "Error: " + ex.getMessage());                 
            }
        }
        if (e.getSource() == this.jfrnuevotipo.btnRegresar) {
            this.jfrtipodoc.setVisible(true);            
            getAllTipoDocPracticas();
            this.jfrnuevotipo.dispose();
        }
        /*EDITAR TIPO DE DOCUMENTOS DE PRÁCTICAS*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            try {
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setId_tipo_documento_practicas(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                tdp.setDescripcion((this.jfredit.txtDescripcion.getText().toString()).toUpperCase());
                tdpdao.updateTipoDocumentoPracticas(tdp);
                JOptionPane.showMessageDialog(this.jfredit,"Se ha actualizado este tipo de documento de prácticas!");
                getAllTipoDocPracticas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
    } 
}
