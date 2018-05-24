package com.vinculacion.app.model;

public class TipoDocumentoPracticas{
    
    private int id_tipo_documento_practicas;
    
    private String descripcion;
    
    public TipoDocumentoPracticas(int id_tipo_documento_practicas, String descripcion) {
        this.id_tipo_documento_practicas = id_tipo_documento_practicas;
        this.descripcion = descripcion;
    }

    public TipoDocumentoPracticas() {
    }

    public int getId_tipo_documento_practicas() {
        return id_tipo_documento_practicas;
    }

    public void setId_tipo_documento_practicas(int id_tipo_documento_practicas) {
        this.id_tipo_documento_practicas = id_tipo_documento_practicas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
