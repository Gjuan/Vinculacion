package com.vinculacion.app.controllers;

import com.vinculacion.app.dao.PerfilDAO;
import com.vinculacion.app.dao.UsuariosDAO;
import com.vinculacion.app.model.Perfil;
import com.vinculacion.app.model.Usuarios;
import com.vinculacion.app.views.JFrameEditUsuario;
import com.vinculacion.app.views.JFrameNuevoUsuario;
import com.vinculacion.app.views.JFrameUsuarios;
import com.vinculacion.app.views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class UsuariosController implements ActionListener{
    
    MenuPrincipal mp = new MenuPrincipal();
    JFrameUsuarios jfrusuarios = new JFrameUsuarios(mp, false);
    JFrameNuevoUsuario jfrnuevouser = new JFrameNuevoUsuario(mp, false);
    JFrameEditUsuario jfredituser= new JFrameEditUsuario(mp, false);
        
    UsuariosDAO userdao ;
    PerfilDAO pdao;
    
    public UsuariosController(MenuPrincipal menup, JFrameUsuarios juser, JFrameNuevoUsuario jnew, JFrameEditUsuario jedit) {
        this.mp = menup;
        this.jfrusuarios = juser;
        this.jfrnuevouser = jnew;
        this.jfredituser = jedit;
        
        this.jfrusuarios.btnBuscar.addActionListener(this);
        this.jfrusuarios.btnDarBaja.addActionListener(this);
        this.jfrusuarios.btnEditar.addActionListener(this);
        this.jfrusuarios.btnNuevo.addActionListener(this);
        
        this.jfrnuevouser.btnGuardar.addActionListener(this);
        this.jfrnuevouser.btnRegresar.addActionListener(this);
        
        this.jfredituser.btnGuardar.addActionListener(this);
        this.jfredituser.btnRegresar.addActionListener(this);
        
        this.jfrusuarios.comboPerfil.addActionListener(this);
        
        userdao = new UsuariosDAO();
        pdao = new PerfilDAO();
        List<Perfil> lperfil = pdao.AllPerfil();
        this.jfrusuarios.comboPerfil.addItem("SELECCIONE");
        for (Perfil perfil : lperfil) {
            this.jfrusuarios.comboPerfil.addItem(perfil.getDescripcion());
        }
    }
    
    public void setDefaultTable(DefaultTableModel model){
        model.addColumn("Código");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Correo");
        model.addColumn("Nombre de usuario");
        model.addColumn("Contraseña");
        model.addColumn("Perfil");
        model.addColumn("Estado");
        TableColumnModel tcm =  this.jfrusuarios.tableUsuarios.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(3);
        tcm.getColumn(1).setPreferredWidth(80);
        tcm.getColumn(1).setPreferredWidth(80);
    }
    
    public void getAllUsers(){
        try{      
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrusuarios.tableUsuarios.setModel(model);
            setDefaultTable(model);            
            Object [] data = new Object[8];                    

            if (this.jfrusuarios.txtCodigo.getText().isEmpty()) {
                this.jfrusuarios.tableUsuarios.removeAll();
                List<Usuarios> luser = userdao.AllUsuarios();
                for (Usuarios u : luser) {
                    data[0] = u.getId_usuario();
                    data[1] = u.getNombres();
                    data[2] = u.getApellidos();
                    data[3] = u.getCorreo();
                    data[4] = u.getNom_usuario();
                    data[5] = u.getContrasena();
                    data[6] = u.getPerfil().getDescripcion();
                    data[7] = u.getEstado();
                    model.addRow(data);
                }
            }else{
                this.jfrusuarios.tableUsuarios.removeAll();
                Usuarios user = userdao.findUsuarioById(Integer.parseInt(this.jfrusuarios.txtCodigo.getText().toString()));
                if (user != null) {
                    data[0] = user.getId_usuario();
                    data[1] = user.getNombres();
                    data[2] = user.getApellidos();
                    data[3] = user.getCorreo();
                    data[4] = user.getNom_usuario();
                    data[5] = user.getContrasena();
                    data[6] = user.getPerfil().getDescripcion();
                    data[7] = user.getEstado();
                    model.addRow(data);
                }else{
                    JOptionPane.showMessageDialog(this.jfrusuarios, "El registro con codigo " + this.jfrusuarios.txtCodigo.getText().toString() + " no existe");
                }            
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.jfrusuarios, "Error: " + ex.getMessage());
        }
    }
    
    private void getAllUserByPerfil() {
        try {
            DefaultTableModel model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }           
            };
            this.jfrusuarios.tableUsuarios.setModel(model);
            setDefaultTable(model);
            
            this.jfrusuarios.tableUsuarios.removeAll();
            Object [] data = new Object[8];    
            List<Usuarios> luser = userdao.findUserByPerfil(this.jfrusuarios.comboPerfil.getSelectedItem().toString());
            for (Usuarios u : luser) {
                data[0] = u.getId_usuario();
                data[1] = u.getNombres();
                data[2] = u.getApellidos();
                data[3] = u.getCorreo();
                data[4] = u.getNom_usuario();
                data[5] = u.getContrasena();
                data[6] = u.getPerfil().getDescripcion();
                data[7] = u.getEstado();
                model.addRow(data);
            }
        } catch (Exception ex) {
            getAllUsers();       
        }    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jfrusuarios.btnBuscar) {
            getAllUsers();
        }
        if (e.getSource() == this.jfrusuarios.comboPerfil) {
            getAllUserByPerfil();
        }
        if (e.getSource() == this.jfrusuarios.btnDarBaja) {
            try {
                int id = Integer.parseInt(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 0).toString());
                int i = JOptionPane.showConfirmDialog(this.jfrusuarios, "¿Desea dejar inactivo el registro con el código "+id+" ?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    userdao.deleteUsuarioById(id);
                    JOptionPane.showMessageDialog(this.jfrusuarios,  "Se ha dado de baja al registro!!");
                    getAllUsers();
                }               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrusuarios, "Debe seleccionar una registro y luego darle de baja");
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == this.jfrusuarios.btnEditar) {
            try {
                if (!this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 0).toString().isEmpty()) {
                    this.jfredituser.setVisible(true);
                    this.jfredituser.comboPerfil.removeAllItems();
                    List<Perfil>  lperfil = pdao.AllPerfil();
                    for (Perfil perfil : lperfil) {
                        this.jfredituser.comboPerfil.addItem(perfil.getDescripcion());
                    }
                    this.jfredituser.txtCodigo.setText(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 0).toString());
                    this.jfredituser.txtNombres.setText(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 1).toString());                    
                    this.jfredituser.txtApellidos.setText(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 2).toString());
                    this.jfredituser.txtCorreo.setText(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 3).toString());
                    this.jfredituser.txtNomUsuario.setText(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 4).toString());
                    this.jfredituser.txtContrasena.setText(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 5).toString());
                    this.jfredituser.comboPerfil.setSelectedItem(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 6).toString());
                    this.jfredituser.comboEstado.setSelectedItem(this.jfrusuarios.tableUsuarios.getValueAt(this.jfrusuarios.tableUsuarios.getSelectedRow(), 7).toString());
                }else{
                    JOptionPane.showMessageDialog(this.jfrusuarios,"Seleccione un registro y luego de click en editar");
                }                              
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this.jfrusuarios,"Seleccione un registro y luego de click en editar");
            }
        }
        if (e.getSource() == this.jfrusuarios.btnNuevo) {
            this.jfrnuevouser.setVisible(true);
            this.jfrnuevouser.comboPerfil.removeAllItems();
            List<Perfil> lperfil = pdao.AllPerfil();
            for (Perfil perfil : lperfil) {
                this.jfrnuevouser.comboPerfil.addItem(perfil.getDescripcion());
            }
            this.jfrusuarios.dispose();
        }
        /*NUEVO USUARIO*/
        if (e.getSource() == this.jfrnuevouser.btnGuardar) {
            try {
                if (this.jfrnuevouser.txtNombres.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.jfrnuevouser, "El nombre es requerido");                 
                }else if(this.jfrnuevouser.txtApellidos.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnuevouser, "El apellido es requerido");                                 
                }else if(this.jfrnuevouser.txtNomUsuario.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnuevouser, "El nombre de usuario es requerido");                                                 
                }else if(this.jfrnuevouser.txtContrasena.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this.jfrnuevouser, "La contraseña es requerida");                                                                 
                }else{
                    Usuarios user = new Usuarios();
                    user.setNombres((this.jfrnuevouser.txtNombres.getText().toString()).toUpperCase());
                    user.setApellidos((this.jfrnuevouser.txtApellidos.getText().toString()).toUpperCase());
                    user.setCorreo((this.jfrnuevouser.txtCorreo.getText().toString()).toLowerCase());
                    user.setNom_usuario(this.jfrnuevouser.txtNomUsuario.getText().toString());
                    user.setContrasena(this.jfrnuevouser.txtContrasena.getText().toString());
                    Perfil perfil = pdao.findPerfilByDescription(this.jfrnuevouser.comboPerfil.getSelectedItem().toString());
                    user.setPerfil(perfil);
                    user.setEstado(("ACTIVO").toUpperCase());
                    userdao.saveUsuarios(user);
                    JOptionPane.showMessageDialog(this.jfrnuevouser, "Usuario registrado correctamente !!");
                    this.jfrnuevouser.txtNombres.setText("");
                    this.jfrnuevouser.txtNombres.requestFocus();
                    this.jfrnuevouser.txtApellidos.setText("");
                    this.jfrnuevouser.txtContrasena.setText("");
                    this.jfrnuevouser.txtCorreo.setText("");
                    this.jfrnuevouser.txtNomUsuario.setText("");
                    this.jfrnuevouser.comboPerfil.setSelectedIndex(0);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfrnuevouser, "Error: " + ex.getMessage());                 
            }
        }
        if (e.getSource() == this.jfrnuevouser.btnRegresar) {
            this.jfrnuevouser.dispose();
            getAllUsers();
            this.jfrusuarios.setVisible(true);
        }
        /*EDITAR USUARIO*/
        if (e.getSource() == this.jfredituser.btnGuardar) {
            try {
                Usuarios user = new Usuarios();
                user.setId_usuario(Integer.parseInt(this.jfredituser.txtCodigo.getText().toString()));
                user.setNombres((this.jfredituser.txtNombres.getText().toString()).toUpperCase());
                user.setApellidos((this.jfredituser.txtApellidos.getText().toString()).toUpperCase());
                user.setCorreo((this.jfredituser.txtCorreo.getText().toString()).toLowerCase());
                user.setNom_usuario(this.jfredituser.txtNomUsuario.getText().toString());
                user.setContrasena(this.jfredituser.txtContrasena.getText().toString());
                Perfil perfil = pdao.findPerfilByDescription(this.jfredituser.comboPerfil.getSelectedItem().toString());
                user.setPerfil(perfil);
                user.setEstado(this.jfredituser.comboEstado.getSelectedItem().toString());
                userdao.updateUsuario(user);
                JOptionPane.showMessageDialog(this.jfredituser,"Se ha actualizado este usuario");
                getAllUsers();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.jfredituser, "Error: " + ex.getMessage());               
            }
        }
        if (e.getSource() == this.jfredituser.btnRegresar) {
            this.jfredituser.dispose();
        }
    }  
}
