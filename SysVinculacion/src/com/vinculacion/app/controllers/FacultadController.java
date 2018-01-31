package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.FacultadDAO;
import com.vinculacion.app.model.Facultad;
import com.vinculacion.app.views.JFrameFacultad;
import com.vinculacion.app.views.JFrameNuevaFacultad;
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
public class FacultadController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameNuevaFacultad newfacultad = new JFrameNuevaFacultad(mp, false);
    JFrameFacultad facultad = new JFrameFacultad(mp, false);
    FacultadDAO faculdao;
    
    public FacultadController(MenuPrincipal menuP, JFrameNuevaFacultad newfacul, JFrameFacultad facul) {
        this.mp = menuP;
        this.newfacultad = newfacul;
        this.facultad = facul;
        faculdao = new FacultadDAO();
        
        this.facultad.btnNuevaFacultad.addActionListener(this);
        this.facultad.btnBorrar.addActionListener(this);
        this.facultad.btnBuscar.addActionListener(this);
        this.facultad.btnEditar.addActionListener(this);
        this.newfacultad.btnGuardar.addActionListener(this);
        this.newfacultad.btnRegresar.addActionListener(this);
        getAllFacultades();                
    }
    
    public void getAllFacultades(){
        DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
        };
        this.facultad.tableFacultad.setModel(model);
        model.addColumn("Código");
        model.addColumn("Descripción");
        model.addColumn("Siglas");
        TableColumnModel tcm =  this.facultad.tableFacultad.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(5);
        tcm.getColumn(1).setPreferredWidth(250);
        
        Object [] data = new Object[3];                    
            
        if (this.facultad.txtCodigo.getText().isEmpty()) {
            this.facultad.tableFacultad.removeAll();
            List<Facultad> facultades = faculdao.AllFacultad();
            for (Facultad facultad : facultades) {
                data[0] = facultad.getID_FACULTAD();
                data[1] = facultad.getDESCRIPCION();
                data[2] = facultad.getSIGLAS();
                model.addRow(data);
            }
        }else{
            this.facultad.tableFacultad.removeAll();
            Facultad facultad = faculdao.findFacultadById(Integer.parseInt(this.facultad.txtCodigo.getText().toString()));
            if (facultad != null) {
                data[0] = facultad.getID_FACULTAD();
                data[1] = facultad.getDESCRIPCION();
                data[2] = facultad.getSIGLAS();
                model.addRow(data);
            }else{
                JOptionPane.showMessageDialog(this.facultad, "El registro con codigo " + this.facultad.txtCodigo.getText().toString() + " no existe");
            }            
        }        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.facultad.btnNuevaFacultad) {
            this.facultad.dispose();
            this.newfacultad.setVisible(true);            
        }
        if (e.getSource() == this.facultad.btnBorrar) {
            try {
                int id = Integer.parseInt(this.facultad.tableFacultad.getValueAt(this.facultad.tableFacultad.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.facultad, "¿Desea eliminar el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){             
                    faculdao.deleteFacultadById(id);
                    JOptionPane.showMessageDialog(this.facultad,  "Registro eliminado!!");
                    faculdao = new FacultadDAO();                    
                    getAllFacultades();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.facultad, "Error: " +ex.getMessage());
            }
        }
        if (e.getSource() == this.facultad.btnBuscar) {
            getAllFacultades();
        }
        if (e.getSource() == this.newfacultad.btnGuardar) {
            try {
                if (this.newfacultad.txtDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.newfacultad, "La descripción de la facultad es requerida");
                }else if(this.newfacultad.txtSiglas.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.newfacultad, "Las Siglas de la facultad es requerida");
                }else{
                    Facultad f = new Facultad();
                    f.setDESCRIPCION(this.newfacultad.txtDescripcion.getText().toString());
                    f.setSIGLAS(this.newfacultad.txtSiglas.getText().toString());
                    faculdao.saveFacultad(f);
                    JOptionPane.showMessageDialog(this.newfacultad, "Facultad ingresada correctamente!!");
                } 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.newfacultad, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.newfacultad.btnRegresar) {
            this.newfacultad.dispose();
            faculdao = new FacultadDAO();
            getAllFacultades();                    
            this.facultad.setVisible(true);
        }
    }
    
}
