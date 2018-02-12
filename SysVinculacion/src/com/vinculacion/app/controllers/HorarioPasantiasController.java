package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.HorariosPasantiasDAO;
import com.vinculacion.app.model.HorarioPasantias;
import com.vinculacion.app.views.JFrameEditHorarioPasantias;
import com.vinculacion.app.views.JFrameHorarioPasantias;
import com.vinculacion.app.views.JFrameNuevoHorarioPasantias;
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
public class HorarioPasantiasController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameHorarioPasantias jfrhorario = new JFrameHorarioPasantias(mp, false);
    JFrameNuevoHorarioPasantias jfrnuevo = new JFrameNuevoHorarioPasantias(mp, false);
    JFrameEditHorarioPasantias jfredit = new JFrameEditHorarioPasantias(mp, false);
    HorariosPasantiasDAO hdao;
    
    public HorarioPasantiasController(MenuPrincipal menup, JFrameHorarioPasantias horario, JFrameNuevoHorarioPasantias nuevo, JFrameEditHorarioPasantias edit) {
        this.mp = menup;
        this.jfrhorario = horario;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrhorario.btnBuscar.addActionListener(this);
        this.jfrhorario.btnEditar.addActionListener(this);
        this.jfrhorario.btnNuevoHorario.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        hdao = new HorariosPasantiasDAO();
        
        getAllHorarios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrhorario.btnBuscar) {
            getAllHorarios();
        }
        if (e.getSource() == this.jfrhorario.btnEditar) {
            try {
                if (!this.jfrhorario.tableHorarios.getValueAt(this.jfrhorario.tableHorarios.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.txtCodigo.setText(this.jfrhorario.tableHorarios.getValueAt(this.jfrhorario.tableHorarios.getSelectedRow(), 0).toString());
                    this.jfredit.txtDescripcion.setText(jfrhorario.tableHorarios.getValueAt(this.jfrhorario.tableHorarios.getSelectedRow(), 1).toString());
                }else{
                    JOptionPane.showMessageDialog(this.jfrhorario,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrhorario,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrhorario.btnNuevoHorario) {
            this.jfrnuevo.setVisible(true);
            this.jfrhorario.dispose();
        }
        /*NUEVO HORARIO*/
        if (e.getSource() == this.jfrnuevo.btnGuardar) {
             try {
                if (this.jfrnuevo.txtDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevo, "La descripción del horario es requerida");
                }else{
                    HorarioPasantias horario = new HorarioPasantias();
                    horario.setDESCRIPCION_HORARIO(this.jfrnuevo.txtDescripcion.getText().toString());
                    hdao.saveHorarioPasantias(horario);
                    JOptionPane.showMessageDialog(this.jfrnuevo, "Horario de pasantías registrado!!");
                    this.jfrnuevo.txtDescripcion.setText("");
                    this.jfrnuevo.txtDescripcion.requestFocus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevo, "Error: " + ex.getMessage());       
            }
        }
        if (e.getSource() == this.jfrnuevo.btnRegresar) {
            this.jfrhorario.setVisible(true);
            getAllHorarios();
            this.jfrnuevo.dispose();
        }
        /*EDITAR HORARIO*/
        if (e.getSource() == this.jfredit.btnGuardar) {
             try {
                 HorarioPasantias horario = new HorarioPasantias();
                horario.setID_HORARIO_PASANTIAS(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                horario.setDESCRIPCION_HORARIO(this.jfredit.txtDescripcion.getText().toString());
                hdao.updateHorarioPasantia(horario);
                JOptionPane.showMessageDialog(this.jfredit,"Se ha actualizado este horario de pasantías");
                getAllHorarios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
    }  

    public void getAllHorarios() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrhorario.tableHorarios.setModel(model);
            model.addColumn("Código");
            model.addColumn("Descripción del horario");
            TableColumnModel tcm =  this.jfrhorario.tableHorarios.getColumnModel();
            tcm.getColumn(0).setPreferredWidth(5);
            tcm.getColumn(1).setPreferredWidth(280);

            Object [] data = new Object[2];                    

            if (this.jfrhorario.txtCodigo.getText().isEmpty()) {
                this.jfrhorario.tableHorarios.removeAll();
                List<HorarioPasantias> lhorario = hdao.AllHorariosPasantias();
                for (HorarioPasantias hp : lhorario) {
                    data[0] = hp.getID_HORARIO_PASANTIAS();
                    data[1] = hp.getDESCRIPCION_HORARIO();
                    model.addRow(data);
                }
            }else{
                this.jfrhorario.tableHorarios.removeAll();              
                HorarioPasantias hp= hdao.findHorarioPasantiaById(Integer.parseInt(this.jfrhorario.txtCodigo.getText().toString()));
                if (hp != null) {
                    data[0] = hp.getID_HORARIO_PASANTIAS();
                    data[1] = hp.getDESCRIPCION_HORARIO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrhorario, "El registro con codigo " + this.jfrhorario.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrhorario, "Error: " + ex.getMessage());
        }
    }
}
