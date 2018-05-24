package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.GeneroDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Genero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jorge
 */
public class GeneroDAO extends Config implements GeneroDaoInterface{

    public GeneroDAO() {
        super();
    }

    @Override
    public void saveGenero(Genero genero) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into genero (descripcion, estado) values(?,?)");
            ps.setString(1, genero.getDESCRIPCION());
            ps.setString(2, genero.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Genero> AllGeneros() {
        List<Genero> lgenero = new ArrayList<Genero>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM genero WHERE ESTADO = 'ACTIVO' order by ID_GENERO desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Genero genero = new Genero();
                genero.setID_GENERO(rs.getInt("ID_GENERO"));
                genero.setDESCRIPCION(rs.getString("descripcion"));
                genero.setESTADO(rs.getString("estado"));
                lgenero.add(genero);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lgenero;
    }

    @Override
    public void updateGenero(Genero genero) {
        try {
            PreparedStatement ps = con.prepareStatement("update genero set descripcion = ?, estado = ? where ID_GENERO = ?");
            ps.setString(1, genero.getDESCRIPCION());
            ps.setString(2, genero.getESTADO());
            ps.setInt(3, genero.getID_GENERO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteGenero(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from genero where ID_GENERO = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Genero findGeneroById(int id) {
        Genero genero = new Genero();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM genero WHERE ESTADO = 'ACTIVO' and id_genero = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                genero.setID_GENERO(rs.getInt("ID_GENERO"));
                genero.setDESCRIPCION(rs.getString("descripcion"));
                genero.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return genero;
    }

    @Override
    public Genero findGeneroByDescription(String description) {
        Genero genero = new Genero();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM genero WHERE ESTADO = 'ACTIVO' and descripcion = ?");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                genero.setID_GENERO(rs.getInt("ID_GENERO"));
                genero.setDESCRIPCION(rs.getString("descripcion"));
                genero.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return genero;
    }
    
}
