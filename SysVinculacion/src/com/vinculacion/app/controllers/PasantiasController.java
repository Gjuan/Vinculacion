package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.PasantiasDAO;
import com.vinculacion.app.model.Pasantias;
import com.vinculacion.app.views.JFrameEditPasantia;
import com.vinculacion.app.views.JFrameNuevaPasantia;
import com.vinculacion.app.views.JFramePasantias;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jorge
 */
public class PasantiasController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameNuevaPasantia jfrnuevapasantia = new JFrameNuevaPasantia(mp, false);
    JFramePasantias jfrpasantia = new JFramePasantias(mp, false);
    JFrameEditPasantia jfreditpasantia = new JFrameEditPasantia(mp, false);
    PasantiasDAO pasantiadao;
    
    public PasantiasController(MenuPrincipal menu, JFramePasantias pasantia, JFrameNuevaPasantia nueva, JFrameEditPasantia edit) {
        this.mp = menu;
        this.jfrnuevapasantia = nueva;
        this.jfrpasantia = pasantia;
        this.jfreditpasantia = edit;
        
        this.jfrnuevapasantia.btnGuardar.addActionListener(this);
        this.jfrnuevapasantia.btnRegresar.addActionListener(this);
        
        this.jfrpasantia.btnBuscar.addActionListener(this);
        this.jfrpasantia.btnBuscarByFechaInicio.addActionListener(this);
        this.jfrpasantia.btnDarBaja.addActionListener(this);
        this.jfrpasantia.btnEditar.addActionListener(this);
        this.jfrpasantia.btnNueva.addActionListener(this);
        
        this.jfreditpasantia.btnGuardar.addActionListener(this);
        this.jfreditpasantia.btnRegresar.addActionListener(this);
        pasantiadao = new PasantiasDAO();
        getAllPasantias();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrpasantia.btnBuscar) {
            getAllPasantias();
        }
        if (e.getSource() == this.jfrpasantia.btnBuscarByFechaInicio) {
            getAllPasantiasByFechaInicio();
        }
        if (e.getSource() == this.jfrpasantia.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrpasantia.tablePasantias.getValueAt(this.jfrpasantia.tablePasantias.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrpasantia, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    pasantiadao.deletePasantia(id);
                    JOptionPane.showMessageDialog(this.jfrpasantia, "Se ha dado de baja al registro!!");
                    getAllPasantias();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrpasantia, "Debe seleccionar un registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrpasantia.btnEditar) {
            try {
                if (!this.jfrpasantia.tablePasantias.getValueAt(this.jfrpasantia.tablePasantias.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfreditpasantia.setVisible(true);
                    this.jfreditpasantia.txtCodigo.setText(this.jfrpasantia.tablePasantias.getValueAt(this.jfrpasantia.tablePasantias.getSelectedRow(), 0).toString());
                    this.jfreditpasantia.txtTiempoCompleto.setValue(Integer.parseInt(this.jfrpasantia.tablePasantias.getValueAt(this.jfrpasantia.tablePasantias.getSelectedRow(), 1).toString()));
                    this.jfreditpasantia.txtMedioTiempo.setValue(Integer.parseInt(this.jfrpasantia.tablePasantias.getValueAt(this.jfrpasantia.tablePasantias.getSelectedRow(), 2).toString()));
                    
                    Date dateinit = new SimpleDateFormat("yyyy-MM-dd").parse((String) this.jfrpasantia.tablePasantias.getValueAt(this.jfrpasantia.tablePasantias.getSelectedRow(), 4));
                    this.jfreditpasantia.dateFechaInicio.setDate(dateinit);
                    
                    Date datefin = new SimpleDateFormat("yyyy-MM-dd").parse((String) this.jfrpasantia.tablePasantias.getValueAt(this.jfrpasantia.tablePasantias.getSelectedRow(), 5));
                    this.jfreditpasantia.dateFechaCulminacion.setDate(datefin);
                    
                    this.jfreditpasantia.comboEstado.setSelectedItem(this.jfrpasantia.tablePasantias.getValueAt(this.jfrpasantia.tablePasantias.getSelectedRow(), 6));                   
                }else{
                    JOptionPane.showMessageDialog(this.jfrpasantia,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrpasantia,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrpasantia.btnNueva) {
            this.jfrnuevapasantia.setVisible(true);
            this.jfrpasantia.dispose();
        }
        /*Nueva pasantía*/
        if (e.getSource() == this.jfrnuevapasantia.btnGuardar) {
            try {
                if (this.jfrnuevapasantia.txtMedioTiempo.getValue().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevapasantia, "El valor de medio tiempo es requerido");
                }else if(this.jfrnuevapasantia.txtTiempoCompleto.getValue().toString().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnuevapasantia, "El valor de tiempo completo es requerido");
                }else{
                    Pasantias pa = new Pasantias();
                    int medioTiempo = (int)this.jfrnuevapasantia.txtMedioTiempo.getValue();
                    int tiempoCompleto = (int) this.jfrnuevapasantia.txtTiempoCompleto.getValue();
                    pa.setMEDIO_TIEMPO(medioTiempo);
                    pa.setTIEMPO_COMPLETO(tiempoCompleto);
                    pa.setTOTAL_HORAS(medioTiempo + tiempoCompleto);
                    
                    Date dateinit = this.jfrnuevapasantia.dateFechaInicio.getDate();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    pa.setFECHA_INICIO(format.format(dateinit));
                    
                    Date datefin = this.jfrnuevapasantia.dateFechaCulminacion.getDate();
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                    pa.setFECHA_CULMINACION(format2.format(datefin));
                    
                    pa.setESTADO("ACTIVO");
                    pasantiadao.savePasantias(pa);
                    
                    JOptionPane.showMessageDialog(this.jfrpasantia, "Pasantía registrada!!");
                    this.jfrnuevapasantia.txtMedioTiempo.setValue(0);
                    this.jfrnuevapasantia.txtTiempoCompleto.setValue(0);
                    this.jfrnuevapasantia.dateFechaCulminacion.setDate(null);
                    this.jfrnuevapasantia.dateFechaInicio.setDate(null);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevapasantia, "Error: " + ex.getMessage());       
            }
        }
        if (e.getSource() == this.jfrnuevapasantia.btnRegresar) {
            this.jfrnuevapasantia.dispose();
            this.jfrpasantia.setVisible(true);
            getAllPasantias();
        }
        /*Editar pasantía*/
        if (e.getSource() == this.jfreditpasantia.btnGuardar) {
              try {
                Pasantias pa = new Pasantias();
                pa.setID_PASANTIAS(Integer.parseInt(this.jfreditpasantia.txtCodigo.getText().toString()));
                int tiempoCompleto = (int) this.jfreditpasantia.txtTiempoCompleto.getValue();
                int medioTiempo = (int) this.jfreditpasantia.txtMedioTiempo.getValue();
                pa.setTIEMPO_COMPLETO(tiempoCompleto);
                pa.setMEDIO_TIEMPO(medioTiempo);              
                pa.setTOTAL_HORAS(tiempoCompleto + medioTiempo);
                
                Date dateinit = this.jfreditpasantia.dateFechaInicio.getDate();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                pa.setFECHA_INICIO(format.format(dateinit));
                  
                Date datefin = this.jfreditpasantia.dateFechaCulminacion.getDate();
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                pa.setFECHA_CULMINACION(format2.format(datefin));
                    
                pa.setESTADO(this.jfreditpasantia.comboEstado.getSelectedItem().toString());
                pasantiadao.updatePasantia(pa);
                JOptionPane.showMessageDialog(this.jfreditpasantia,"Se ha actualizado esta pasantía !!");
                getAllPasantias();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfreditpasantia, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfreditpasantia.btnRegresar) {
            this.jfreditpasantia.dispose();
        }
    }        

    public void setDefaultTable(DefaultTableModel dtm){
        this.jfrpasantia.tablePasantias.setModel(dtm);
        dtm.addColumn("Código");
        dtm.addColumn("Tiempo completo");
        dtm.addColumn("Medio tiempo");
        dtm.addColumn("Total de horas");
        dtm.addColumn("Fecha de inicio");
        dtm.addColumn("Fecha de culminación");
        dtm.addColumn("Estado");
            
        TableColumnModel tcm =  this.jfrpasantia.tablePasantias.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(5);
        tcm.getColumn(1).setPreferredWidth(10);          
        tcm.getColumn(2).setPreferredWidth(50);          
        tcm.getColumn(3).setPreferredWidth(20);
        tcm.getColumn(4).setPreferredWidth(20);
    }
    
    public void getAllPasantias() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            setDefaultTable(model);
            Object [] data = new Object[7];                    

            if (this.jfrpasantia.txtCodigo.getText().isEmpty()) {
                this.jfrpasantia.tablePasantias.removeAll();
                List<Pasantias> lpasantias = pasantiadao.AllPasantias();
                for (Pasantias pa : lpasantias) {
                    data[0] = pa.getID_PASANTIAS();
                    data[1] = pa.getTIEMPO_COMPLETO();
                    data[2] = pa.getMEDIO_TIEMPO();
                    data[3] = pa.getTOTAL_HORAS();
                    data[4] = pa.getFECHA_INICIO();
                    data[5] = pa.getFECHA_CULMINACION();
                    data[6] = pa.getESTADO();                    
                    model.addRow(data);
                }
            }else{
                this.jfrpasantia.tablePasantias.removeAll();
                Pasantias pa = pasantiadao.findPasantiaById(Integer.parseInt(this.jfrpasantia.txtCodigo.getText().toString()));
                if (pa != null) {
                    data[0] = pa.getID_PASANTIAS();
                    data[1] = pa.getTIEMPO_COMPLETO();
                    data[2] = pa.getMEDIO_TIEMPO();
                    data[3] = pa.getTOTAL_HORAS();
                    data[4] = pa.getFECHA_INICIO();
                    data[5] = pa.getFECHA_CULMINACION();
                    data[6] = pa.getESTADO();   
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrpasantia, "El registro con codigo " + this.jfrpasantia.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrpasantia, "Error: " + ex.getMessage());
        }
    }
    public void getAllPasantiasByFechaInicio() {
        try {
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            setDefaultTable(model);
            Object [] data = new Object[7];  
            Date fecha_inicio = this.jfrpasantia.dateFechaInicio.getDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            List<Pasantias> lpasantias = pasantiadao.findPasantiaByFechaInicio(format.format(fecha_inicio));
            for (Pasantias pa : lpasantias) {
                data[0] = pa.getID_PASANTIAS();
                data[1] = pa.getTIEMPO_COMPLETO();
                data[2] = pa.getMEDIO_TIEMPO();
                data[3] = pa.getTOTAL_HORAS();
                data[4] = pa.getFECHA_INICIO();
                data[5] = pa.getFECHA_CULMINACION();
                data[6] = pa.getESTADO();                    
                model.addRow(data);
            }                       
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.jfrpasantia,"Seleccione la fecha correcta que inició la pasantía");
            getAllPasantias();
        }
    }
}
