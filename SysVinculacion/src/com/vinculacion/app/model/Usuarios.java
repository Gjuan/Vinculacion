package com.vinculacion.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USUARIOS")
public class Usuarios implements Serializable {
    
    @Id
    @Column(name="id_usuario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_usuario;
    
    @Column(name="nombres")
    private String nombres;
    
    @Column(name="apellidos")
    private String apellidos;
    
    @Column(name="correo")
    private String correo;
    
    @Column(name="nom_usuario")
    private String nom_usuario;
    
    @Column(name="contrasena")
    private String contrasena;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_perfil")    
    private Perfil perfil;
    
    @Column(name="estado")
    private String estado;

    public Usuarios() {
    }

    public Usuarios(int id_usuario, String nombres, String apellidos, String correo, String nom_usuario, String contrasena, Perfil perfil, String estado) {
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.nom_usuario = nom_usuario;
        this.contrasena = contrasena;
        this.perfil = perfil;
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
