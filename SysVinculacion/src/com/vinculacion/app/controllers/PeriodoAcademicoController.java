package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.PeriodosAcademicosDAO;
import com.vinculacion.app.model.PeriodoAcademico;
import com.vinculacion.app.views.JFrameEditPeriodoAcademico;
import com.vinculacion.app.views.JFrameNuevoPeriodoAcademico;
import com.vinculacion.app.views.JFramePeriodoAcademico;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.postgresql.jdbc2.optional.SimpleDataSource;

public class PeriodoAcademicoController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFramePeriodoAcademico jfrperiodo = new JFramePeriodoAcademico(mp, false);
    JFrameNuevoPeriodoAcademico jfrnuevoperiodo = new JFrameNuevoPeriodoAcademico(mp, false);
    JFrameEditPeriodoAcademico editperiodo = new JFrameEditPeriodoAcademico(mp, false);
    PeriodosAcademicosDAO padao;
    
    public PeriodoAcademicoController(MenuPrincipal menup, JFramePeriodoAcademico periodo, JFrameNuevoPeriodoAcademico nuevo, JFrameEditPeriodoAcademico edit) {
        this.mp = menup;
        this.jfrperiodo = periodo;
        this.jfrnuevoperiodo = nuevo;
        this.editperiodo = edit;
        
        this.jfrperiodo.btnBuscar.addActionListener(this);
        this.jfrperiodo.btnBuscarByFechaInicio.addActionListener(this);
        this.jfrperiodo.btnDarBaja.addActionListener(this);
        this.jfrperiodo.btnEditar.addActionListener(this);
        this.jfrperiodo.btnNuevo.addActionListener(this);
        
        this.jfrnuevoperiodo.btnGuardar.addActionListener(this);
        this.jfrnuevoperiodo.btnRegresar.addActionListener(this);
        
        this.editperiodo.btnGuardar.addActionListener(this);
        this.editperiodo.btnRegresar.addActionListener(this);
        
        padao = new PeriodosAcademicosDAO();
        getAllPeriodos();
    }
    
    public void setDefaultTable(DefaultTableModel dtm){
        this.jfrperiodo.tablePeriodosAcademicos.setModel(dtm);
        dtm.addColumn("Código");
        dtm.addColumn("Año académico");
        dtm.addColumn("Nombre del periodo académico");
        dtm.addColumn("Fecha de inicio");
        dtm.addColumn("Fecha de finalización");
        dtm.addColumn("Estado");
            
        TableColumnModel tcm =  this.jfrperiodo.tablePeriodosAcademicos.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(5);
        tcm.getColumn(1).setPreferredWidth(10);          
        tcm.getColumn(2).setPreferredWidth(50);          
        tcm.getColumn(3).setPreferredWidth(20);
        tcm.getColumn(4).setPreferredWidth(20);
    }
    
    public void getAllPeriodos(){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            setDefaultTable(model);
            Object [] data = new Object[6];                    

            if (this.jfrperiodo.txtCodigo.getText().isEmpty()) {
                this.jfrperiodo.tablePeriodosAcademicos.removeAll();
                List<PeriodoAcademico> lperiodos = padao.AllPeriodosAcademicos();
                for (PeriodoAcademico pa : lperiodos) {
                    data[0] = pa.getID_PERIODO_ACADEMICO();
                    data[1] = pa.getANIO_ACADEMICO();
                    data[2] = pa.getNOMBRE_PERIODO();
                    data[3] = pa.getFECHA_INICIO_PERIODO();
                    data[4] = pa.getFECHA_FIN_PERIODO();
                    data[5] = pa.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrperiodo.tablePeriodosAcademicos.removeAll();                
                PeriodoAcademico pa = padao.findPeriodoAcademicoById(Integer.parseInt(this.jfrperiodo.txtCodigo.getText().toString()));
                if (pa != null) {
                    data[0] = pa.getID_PERIODO_ACADEMICO();
                    data[1] = pa.getANIO_ACADEMICO();
                    data[2] = pa.getNOMBRE_PERIODO();
                    data[3] = pa.getFECHA_INICIO_PERIODO();
                    data[4] = pa.getFECHA_FIN_PERIODO();
                    data[5] = pa.getESTADO();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrperiodo, "El registro con codigo " + this.jfrperiodo.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrperiodo, "Error: " + ex.getMessage());
        }
    }
    
    public void getAllPeriodosByFechaInicio() {
        try {
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            setDefaultTable(model);
            Object [] data = new Object[6];  
            Date fecha_inicio = this.jfrperiodo.dateFechaInicio.getDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            PeriodoAcademico pa = padao.findPeriodoAcademicoByStartDate(format.format(fecha_inicio));
            if (pa != null) {
                data[0] = pa.getID_PERIODO_ACADEMICO();
                data[1] = pa.getANIO_ACADEMICO();
                data[2] = pa.getNOMBRE_PERIODO();
                data[3] = pa.getFECHA_INICIO_PERIODO();
                data[4] = pa.getFECHA_FIN_PERIODO();
                data[5] = pa.getESTADO();
                model.addRow(data);
            }else{
                JOptionPane.showMessageDialog(this.jfrperiodo, "El registro con codigo " + this.jfrperiodo.txtCodigo.getText().toString() + " no existe");
            }                       
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jfrperiodo,"Seleccione la fecha correcta que inicio el periodo");
            getAllPeriodos();
        }
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrperiodo.btnBuscar) {
            getAllPeriodos();
        }
        if (e.getSource() == this.jfrperiodo.btnBuscarByFechaInicio) {
            getAllPeriodosByFechaInicio();
        }
        if (e.getSource() == this.jfrperiodo.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrperiodo.tablePeriodosAcademicos.getValueAt(this.jfrperiodo.tablePeriodosAcademicos.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrperiodo, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    padao.deletePeriodoAcademico(id);
                    JOptionPane.showMessageDialog(this.jfrperiodo,  "Se ha dado de baja al registro!!");
                    getAllPeriodos();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrperiodo, "Debe seleccionar un registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrperiodo.btnEditar) {
             try {
                if (!this.jfrperiodo.tablePeriodosAcademicos.getValueAt(this.jfrperiodo.tablePeriodosAcademicos.getSelectedRow(), 0).toString().isEmpty()) {
                    this.editperiodo.setVisible(true);
                    this.editperiodo.txtCodigo.setText(this.jfrperiodo.tablePeriodosAcademicos.getValueAt(this.jfrperiodo.tablePeriodosAcademicos.getSelectedRow(), 0).toString());
                    this.editperiodo.spinnerAnio.setValue(Integer.parseInt(this.jfrperiodo.tablePeriodosAcademicos.getValueAt(this.jfrperiodo.tablePeriodosAcademicos.getSelectedRow(), 1).toString()));
                    this.editperiodo.txtNombrePeriodo.setText(this.jfrperiodo.tablePeriodosAcademicos.getValueAt(this.jfrperiodo.tablePeriodosAcademicos.getSelectedRow(), 2).toString());                    
                    Date dateinit = new SimpleDateFormat("yyyy-MM-dd").parse((String) this.jfrperiodo.tablePeriodosAcademicos.getValueAt(this.jfrperiodo.tablePeriodosAcademicos.getSelectedRow(), 3));
                    this.editperiodo.dateFechaInicio.setDate(dateinit);
                    Date datefin = new SimpleDateFormat("yyyy-MM-dd").parse((String) this.jfrperiodo.tablePeriodosAcademicos.getValueAt(this.jfrperiodo.tablePeriodosAcademicos.getSelectedRow(), 4));
                    this.editperiodo.dateFechaFin.setDate(datefin);
                    this.editperiodo.comboEstado.setSelectedItem(this.jfrperiodo.tablePeriodosAcademicos.getValueAt(this.jfrperiodo.tablePeriodosAcademicos.getSelectedRow(), 5));                   
                }else{
                    JOptionPane.showMessageDialog(this.jfrperiodo,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrperiodo,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrperiodo.btnNuevo) {
            this.jfrnuevoperiodo.setVisible(true);
            this.jfrperiodo.dispose();
        }
        /***NUEVO PERIODO***/
        if (e.getSource() == this.jfrnuevoperiodo.btnGuardar) {
            try {
                if (this.jfrnuevoperiodo.txtNombrePeriodo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevoperiodo, "El nombre del periodo es requerido");
                }else{
                    PeriodoAcademico pa = new PeriodoAcademico();
                    pa.setANIO_ACADEMICO((int)this.jfrnuevoperiodo.spinnerAnio.getValue());
                    pa.setNOMBRE_PERIODO((this.jfrnuevoperiodo.txtNombrePeriodo.getText().toString()).toUpperCase());
                    Date dateinit = this.jfrnuevoperiodo.dateFechaInicio.getDate();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    pa.setFECHA_INICIO_PERIODO(format.format(dateinit));
                    
                    Date datefin = this.jfrnuevoperiodo.dateFechaFin.getDate();
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                    pa.setFECHA_FIN_PERIODO(format2.format(datefin));
                    
                    pa.setESTADO("ACTIVO");
                    padao.savePeriodosAcademicos(pa);
                    JOptionPane.showMessageDialog(this.jfrperiodo, "Periodo académico registrado!!");
                    this.jfrnuevoperiodo.txtNombrePeriodo.setText("");
                    this.jfrnuevoperiodo.txtNombrePeriodo.requestFocus();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrperiodo, "Error: " + ex.getMessage());       
            }
        }
        if (e.getSource() == this.jfrnuevoperiodo.btnRegresar) {
            this.jfrperiodo.setVisible(true);
            getAllPeriodos();
            this.jfrnuevoperiodo.dispose();
        }
        /***EDITAR PERIODO***/
        if (e.getSource() == this.editperiodo.btnGuardar) {
             try {
                PeriodoAcademico pa = new PeriodoAcademico();
                pa.setID_PERIODO_ACADEMICO(Integer.parseInt(this.editperiodo.txtCodigo.getText().toString()));
                pa.setANIO_ACADEMICO((int) this.editperiodo.spinnerAnio.getValue());
                pa.setNOMBRE_PERIODO((this.editperiodo.txtNombrePeriodo.getText().toString()).toUpperCase());
                
                Date dateinit = this.editperiodo.dateFechaInicio.getDate();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                pa.setFECHA_INICIO_PERIODO(format.format(dateinit));
                  
                Date datefin = this.editperiodo.dateFechaFin.getDate();
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                pa.setFECHA_FIN_PERIODO(format2.format(datefin));
                    
                pa.setESTADO(this.editperiodo.comboEstado.getSelectedItem().toString());
                padao.updatePeriodoAcademico(pa);
                JOptionPane.showMessageDialog(this.editperiodo,"Se ha actualizado este periodo !!");
                getAllPeriodos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.editperiodo, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.editperiodo.btnRegresar) {
            this.jfrperiodo.setVisible(true);
            this.editperiodo.dispose();
        }
    }   
}
