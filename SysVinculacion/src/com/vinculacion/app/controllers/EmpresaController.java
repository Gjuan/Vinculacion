package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.EmpresaDAO;
import com.vinculacion.app.model.Empresa;
import com.vinculacion.app.views.JFrameEditEmpresa;
import com.vinculacion.app.views.JFrameEmpresa;
import com.vinculacion.app.views.JFrameNuevaEmpresa;
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
public class EmpresaController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameNuevaEmpresa jfrnueva = new JFrameNuevaEmpresa(mp, false);
    JFrameEmpresa jfrempresa = new JFrameEmpresa(mp, false);
    JFrameEditEmpresa jfredit = new JFrameEditEmpresa(mp, false);
    EmpresaDAO edao;
    
    public EmpresaController(MenuPrincipal menu, JFrameNuevaEmpresa nueva, JFrameEmpresa empresa, JFrameEditEmpresa edit) {
        this.mp = menu;
        this.jfrnueva = nueva;
        this.jfrempresa = empresa;
        this.jfredit = edit;
        
        this.jfrnueva.btnGuardar.addActionListener(this);
        this.jfrnueva.btnRegresar.addActionListener(this);
        
        this.jfrempresa.btnBuscar.addActionListener(this);
        this.jfrempresa.btnEditar.addActionListener(this);
        this.jfrempresa.btnNueva.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        edao = new EmpresaDAO();
        getAllEmpresas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrempresa.btnBuscar) {
            getAllEmpresas();
        }
        if (e.getSource() == this.jfrempresa.btnEditar) {
            try {
                if (!this.jfrempresa.tableEmpresa.getValueAt(this.jfrempresa.tableEmpresa.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.txtCodigo.setText(this.jfrempresa.tableEmpresa.getValueAt(this.jfrempresa.tableEmpresa.getSelectedRow(), 0).toString());
                    this.jfredit.txtNombreEmpresa.setText(this.jfrempresa.tableEmpresa.getValueAt(this.jfrempresa.tableEmpresa.getSelectedRow(), 1).toString());
                    this.jfredit.txtTelefono.setText(this.jfrempresa.tableEmpresa.getValueAt(this.jfrempresa.tableEmpresa.getSelectedRow(), 2).toString());                    
                    this.jfredit.txtDireccion.setText(this.jfrempresa.tableEmpresa.getValueAt(this.jfrempresa.tableEmpresa.getSelectedRow(), 3).toString());
                }else{
                    JOptionPane.showMessageDialog(mp,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(mp,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrempresa.btnNueva) {
            this.jfrnueva.setVisible(true);
            this.jfrempresa.dispose();
        }
        /*Editar empresa*/
        if (e.getSource() == this.jfredit.btnGuardar) {
             try {
                Empresa empresa = new Empresa();
                empresa.setID_EMPRESA(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                empresa.setNOMBRE_EMPRESA(this.jfredit.txtNombreEmpresa.getText().toString().toUpperCase());
                empresa.setTELEFONO(this.jfredit.txtTelefono.getText().toString());
                empresa.setDIRECCION(this.jfredit.txtDireccion.getText().toString().toUpperCase());
                edao.updateEmpresa(empresa);
                JOptionPane.showMessageDialog(this.jfredit, "Empresa actualizada");
                getAllEmpresas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());
            }    
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();            
        }
        /*Nueva empresa*/
        if (e.getSource() == this.jfrnueva.btnGuardar) {
            try {
                if (this.jfrnueva.txtNombreEmpresa.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnueva, "El nombre de la empresa es requerido");
                }else if(this.jfrnueva.txtDireccion.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnueva, "La direcicón es requerida");
                }else{
                    Empresa empresa = new Empresa();
                    empresa.setNOMBRE_EMPRESA(this.jfrnueva.txtNombreEmpresa.getText().toString().toUpperCase());
                    empresa.setTELEFONO(this.jfrnueva.txtTelefono.getText().toString());
                    empresa.setDIRECCION(this.jfrnueva.txtDireccion.getText().toString().toUpperCase());
                    edao.saveEmpresa(empresa);
                    JOptionPane.showMessageDialog(this.jfrnueva, "Empresa ingresada correctamente!!");
                    this.jfrnueva.txtNombreEmpresa.setText("");
                    this.jfrnueva.txtDireccion.setText("");
                    this.jfrnueva.txtNombreEmpresa.requestFocus();
                    this.jfrnueva.txtTelefono.setText("");
                } 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnueva, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrnueva.btnRegresar) {
            this.jfrnueva.dispose();
            this.jfrempresa.setVisible(true);
            getAllEmpresas();
        }
    }    

    public void getAllEmpresas() {
        try {
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrempresa.tableEmpresa.setModel(model);
            model.addColumn("Código");
            model.addColumn("Nombre de la empresa");
            model.addColumn("Teléfono");
            model.addColumn("Dirección");
            TableColumnModel tcm =  this.jfrempresa.tableEmpresa.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(250);

            Object [] data = new Object[4];                    

            if (this.jfrempresa.txtCodigo.getText().isEmpty()) {
                this.jfrempresa.tableEmpresa.removeAll();
                List<Empresa> empresas = edao.AllEmpresas();
                for (Empresa empresa : empresas) {
                    data[0] = empresa.getID_EMPRESA();
                    data[1] = empresa.getNOMBRE_EMPRESA();
                    data[2] = empresa.getTELEFONO();
                    data[3] = empresa.getDIRECCION();
                    model.addRow(data);
                }
            }else{
                this.jfrempresa.tableEmpresa.removeAll();
                Empresa empresa = edao.findEmpresaById(Integer.parseInt(this.jfrempresa.txtCodigo.getText().toString()));
                if (empresa != null) {
                    data[0] = empresa.getID_EMPRESA();
                    data[1] = empresa.getNOMBRE_EMPRESA();
                    data[2] = empresa.getTELEFONO();
                    data[3] = empresa.getDIRECCION();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrempresa, "El registro con codigo " + this.jfrempresa.txtCodigo.getText().toString() + " no existe");
                }            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jfrempresa, "Error: " + e.getMessage(), "Alerta", 0);
        }
    }
}
