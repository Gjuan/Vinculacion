package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.EstudiantesDAO;
import com.vinculacion.app.dao.InformePasantiasDAO;
import com.vinculacion.app.dao.PasantiasDAO;
import com.vinculacion.app.model.Estudiantes;
import com.vinculacion.app.model.InformePasantias;
import com.vinculacion.app.model.Pasantias;
import com.vinculacion.app.views.JFrameEditInformePasantias;
import com.vinculacion.app.views.JFrameInformePasantias;
import com.vinculacion.app.views.JFrameNuevoInformePasantias;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class InformePasantiasController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameEditInformePasantias jfredit = new JFrameEditInformePasantias(mp, false);
    JFrameNuevoInformePasantias jfrnuevo = new JFrameNuevoInformePasantias(mp, false);
    JFrameInformePasantias jfrinforme = new JFrameInformePasantias(mp, false);
    
    InformePasantiasDAO infdao;
    PasantiasDAO pdao;
    EstudiantesDAO estdao;
    
    public InformePasantiasController(MenuPrincipal menu,JFrameInformePasantias informe, JFrameEditInformePasantias edit, JFrameNuevoInformePasantias nuevo) {
        this.mp = menu;
        this.jfrinforme = informe;
        this.jfrnuevo = nuevo;
        this.jfredit = edit;
        
        this.jfrinforme.btnBuscar.addActionListener(this);
        this.jfrinforme.btnDarBaja.addActionListener(this);
        this.jfrinforme.btnEditar.addActionListener(this);
        this.jfrinforme.btnNuevo.addActionListener(this);
        
        this.jfrnuevo.btnGuardar.addActionListener(this);
        this.jfrnuevo.btnRegresar.addActionListener(this);
        
        this.jfredit.btnGuardar.addActionListener(this);
        this.jfredit.btnRegresar.addActionListener(this);
        
        infdao = new InformePasantiasDAO();
        pdao = new PasantiasDAO();
        estdao = new EstudiantesDAO();
        
        getAllInformesPasantias();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrinforme.btnBuscar) {
            getAllInformesPasantias();
        }
        if (e.getSource() == this.jfrinforme.btnDarBaja) {
             try {
                int id = Integer.parseInt(this.jfrinforme.tableInformes.getValueAt(this.jfrinforme.tableInformes.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrinforme, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    infdao.deleteInformePasantia(id);
                    JOptionPane.showMessageDialog(this.jfrinforme,  "Se ha dado de baja al registro!!");
                    getAllInformesPasantias();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrinforme, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrinforme.btnEditar) {
            try {
                if (!this.jfrinforme.tableInformes.getValueAt(this.jfrinforme.tableInformes.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredit.setVisible(true);
                    this.jfredit.comboPasantia.removeAllItems();
                    List<Pasantias> lpasantia = pdao.AllPasantias();
                    for (Pasantias pasantias : lpasantia) {
                        this.jfredit.comboPasantia.addItem(pasantias.getTITULO_PROYECTO());
                    }

                    this.jfredit.comboPasante.removeAllItems();
                    List<Estudiantes> lest = estdao.AllEstudiantes();
                    for (Estudiantes estudiantes : lest) {
                        this.jfredit.comboPasante.addItem(estudiantes.getCEDULA()+"-"+estudiantes.getNOMBRES() + "-" + estudiantes.getAPELLIDOS());
                    }    
                    
                    this.jfredit.txtCodigo.setText(this.jfrinforme.tableInformes.getValueAt(this.jfrinforme.tableInformes.getSelectedRow(), 0).toString());
                    InformePasantias informe = infdao.findInformePasantiaById(Integer.parseInt(this.jfrinforme.tableInformes.getValueAt(this.jfrinforme.tableInformes.getSelectedRow(), 0).toString()));
                    this.jfredit.comboPasante.setSelectedItem(informe.getEstudiantes().getCEDULA()+"-"+informe.getEstudiantes().getNOMBRES()+"-"+informe.getEstudiantes().getAPELLIDOS());
                    this.jfredit.comboPasantia.setSelectedItem(informe.getPasantias().getTITULO_PROYECTO());
                    
                    Date fecha_entrega = new SimpleDateFormat("yyyy-MM-dd").parse((String) informe.getFECHA_ENTREGA_INFORME());
                    this.jfredit.dateFechaEntrega.setDate(fecha_entrega);
                    
                    this.jfredit.comboEstado.setSelectedItem(informe.getESTADO());
                }else{
                    JOptionPane.showMessageDialog(this.jfrinforme,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrinforme,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrinforme.btnNuevo) {
            try {
                this.jfrnuevo.setVisible(true);

                this.jfrnuevo.comboPasantia.removeAllItems();
                List<Pasantias> lpasantia = pdao.AllPasantias();
                for (Pasantias pasantias : lpasantia) {
                    this.jfrnuevo.comboPasantia.addItem(pasantias.getTITULO_PROYECTO());
                }

                this.jfrnuevo.comboPasante.removeAllItems();
                List<Estudiantes> lest = estdao.AllEstudiantes();
                for (Estudiantes estudiantes : lest) {
                    this.jfrnuevo.comboPasante.addItem(estudiantes.getCEDULA()+"-"+estudiantes.getNOMBRES() + "-" + estudiantes.getAPELLIDOS());
                }
                this.jfrinforme.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(jfrinforme, "Error: " + ex.getMessage());
            }                
        }
        /*Nuevo Informe de pasantías*/
        if (e.getSource() == this.jfrnuevo.btnGuardar) {
            try {
                InformePasantias info = new InformePasantias();
                
                Pasantias pasantia = pdao.findPasantiaByTitulo(this.jfrnuevo.comboPasantia.getSelectedItem().toString());
                info.setPasantias(pasantia);                
                
                String pasante [] = this.jfrnuevo.comboPasante.getSelectedItem().toString().split("-");
                Estudiantes estudiante = estdao.findEstudianteByCedula(pasante[0]);
                info.setEstudiantes(estudiante);
                
                Date fecha_entrega = this.jfrnuevo.dateFechaEntrega.getDate();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                info.setFECHA_ENTREGA_INFORME(format.format(fecha_entrega));
                
                info.setESTADO("ACTIVO");
                infdao.saveInformePasantias(info);
                JOptionPane.showMessageDialog(this.jfrnuevo, "Informe ingresado correctamente!!");
                this.jfrnuevo.dateFechaEntrega.setDate(null);   
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevo, "Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrnuevo.btnRegresar) {
            this.jfrnuevo.dispose();
            this.jfrinforme.setVisible(true);
            getAllInformesPasantias();
        }
        /*Editar informe*/
        if (e.getSource() == this.jfredit.btnGuardar) {
            try {
                InformePasantias info = new InformePasantias();
                info.setID_PASANTIAS_ESTUDIANTES(Integer.parseInt(this.jfredit.txtCodigo.getText().toString()));
                Pasantias pasantia = pdao.findPasantiaByTitulo(this.jfredit.comboPasantia.getSelectedItem().toString());
                info.setPasantias(pasantia);
                String pasante[] = this.jfredit.comboPasante.getSelectedItem().toString().split("-");
                Estudiantes estudiante = estdao.findEstudianteByCedula(pasante[0]);
                info.setEstudiantes(estudiante);
                
                Date fecha_entrega = this.jfredit.dateFechaEntrega.getDate();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                info.setFECHA_ENTREGA_INFORME(format.format(fecha_entrega));
                    
                info.setESTADO(this.jfredit.comboEstado.getSelectedItem().toString());
                
                infdao.updateInformPasantia(info);
                JOptionPane.showMessageDialog(this.jfredit,"Se ha actualizado este informe !!");
                getAllInformesPasantias();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredit, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfredit.btnRegresar) {
            this.jfredit.dispose();
        }
    }        

    public void getAllInformesPasantias() {
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }           
            };
            this.jfrinforme.tableInformes.setModel(model);
            model.addColumn("Código");
            model.addColumn("Pasantía");
            model.addColumn("Cédula del pasante");
            model.addColumn("Pasante");
            model.addColumn("Fecha de entrega");
            model.addColumn("Estado");
            
            Object [] data = new Object[6]; 
            if (this.jfrinforme.txtCedula.getText().isEmpty()) {               
                this.jfrinforme.tableInformes.removeAll();
                List<InformePasantias> linforme = infdao.AllInformes();                
                for (InformePasantias informe : linforme) {
                    data[0] = informe.getID_PASANTIAS_ESTUDIANTES();
                    data[1] = informe.getPasantias().getTITULO_PROYECTO();
                    data[2] = informe.getEstudiantes().getCEDULA();
                    data[3] = informe.getEstudiantes().getNOMBRES() +" "+informe.getEstudiantes().getAPELLIDOS();
                    data[4] = informe.getFECHA_ENTREGA_INFORME();
                    data[5] = informe.getESTADO();
                    model.addRow(data);
                }
            }else{
                this.jfrinforme.tableInformes.removeAll();
                List<InformePasantias> linforme = infdao.findInformeByCedulaEstudiante(this.jfrinforme.txtCedula.getText().toString());                           
                for (InformePasantias informe : linforme) {
                    data[0] = informe.getID_PASANTIAS_ESTUDIANTES();
                    data[1] = informe.getPasantias().getTITULO_PROYECTO();
                    data[2] = informe.getEstudiantes().getCEDULA();
                    data[3] = informe.getEstudiantes().getNOMBRES() +" "+informe.getEstudiantes().getAPELLIDOS();
                    data[4] = informe.getFECHA_ENTREGA_INFORME();
                    data[5] = informe.getESTADO();
                    model.addRow(data);
                }
            }            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrinforme, "Error: " + ex.getMessage());
        }
    }
}
