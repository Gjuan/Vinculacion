package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.FacultadDAO;
import com.vinculacion.app.model.Facultad;
import com.vinculacion.app.views.JFrameFacultad;
import com.vinculacion.app.views.JFrameNuevaFacultad;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
        List<Facultad> facultades = faculdao.AllFacultad();
        DefaultTableModel model = new DefaultTableModel();
        this.facultad.tableFacultad.setModel(model);
        model.addColumn("Código");
        model.addColumn("Descripción");
        model.addColumn("Siglas");
        TableColumnModel tcm =  this.facultad.tableFacultad.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(5);
        tcm.getColumn(1).setPreferredWidth(250);
        
        Object [] data = new Object[3];                    
        for (Facultad facultad : facultades) {
            data[0] = facultad.getID_FACULTAD();
            data[1] = facultad.getDESCRIPCION();
            data[2] = facultad.getSIGLAS();
            model.addRow(data);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.facultad.btnNuevaFacultad) {
            this.facultad.dispose();
            this.newfacultad.setVisible(true);            
        }
    }
    
}
