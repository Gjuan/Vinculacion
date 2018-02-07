package com.vinculacion.app.controllers;

import com.vinculacion.app.views.JFrameEditTipoDocumentosPracticas;
import com.vinculacion.app.views.JFrameNuevoTipoDocumentoPracticas;
import com.vinculacion.app.views.JFrameTipoDocumentoPracticas;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorge
 */
public class TipoDocumentosPracticasController implements ActionListener{

    MenuPrincipal mp = new MenuPrincipal();
    JFrameTipoDocumentoPracticas jfrtipodoc = new JFrameTipoDocumentoPracticas(mp, false);
    JFrameNuevoTipoDocumentoPracticas jfrnuevotipo = new JFrameNuevoTipoDocumentoPracticas(mp, false);
    JFrameEditTipoDocumentosPracticas jfredit = new JFrameEditTipoDocumentosPracticas(mp, false);
    
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
    }   
    
    @Override
    public void actionPerformed(ActionEvent e) {

    } 
}
