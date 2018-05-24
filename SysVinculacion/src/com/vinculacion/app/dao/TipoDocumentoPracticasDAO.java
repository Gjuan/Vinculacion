package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.TipoDocumentosPracticasDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.TipoDocumentoPracticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class TipoDocumentoPracticasDAO extends Config implements TipoDocumentosPracticasDaoInterface{

    public TipoDocumentoPracticasDAO() {
        super();
    }
       
    @Override
    public void saveTipoDocumentoPracticas(TipoDocumentoPracticas tdp) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into tipo_documento_practicas (descripcion) values(?)");
            ps.setString(1, tdp.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<TipoDocumentoPracticas> AllTiposDocumentosPracticas() {
        List<TipoDocumentoPracticas> ltdp = new ArrayList<TipoDocumentoPracticas>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tipo_documento_practicas order by id_tipo_documento_practicas desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
                tdp.setId_tipo_documento_practicas(rs.getInt("id_tipo_documento_practicas"));
                tdp.setDescripcion(rs.getString("descripcion"));
                ltdp.add(tdp);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ltdp;
    }

    @Override
    public void updateTipoDocumentoPracticas(TipoDocumentoPracticas tdp) {
        try {
            PreparedStatement ps = con.prepareStatement("update tipo_documento_practicas set descripcion = ? where id_tipo_documento_practicas = ?");
            ps.setString(1, tdp.getDescripcion());
            ps.setInt(2, tdp.getId_tipo_documento_practicas());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public TipoDocumentoPracticas findTipoDocumentoPracticasByDescription(String description) {
        TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from tipo_documento_practicas where descripcion = ?");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tdp.setId_tipo_documento_practicas(rs.getInt("id_tipo_documento_practicas"));
                tdp.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return tdp;
    }

    @Override
    public TipoDocumentoPracticas findTipoDocumentoPracticasById(int id) {
        TipoDocumentoPracticas tdp = new TipoDocumentoPracticas();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from tipo_documento_practicas where id_tipo_documento_practicas = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tdp.setId_tipo_documento_practicas(rs.getInt("id_tipo_documento_practicas"));
                tdp.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return tdp;
    }
    
}
