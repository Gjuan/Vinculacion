package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.PasantiasDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Pasantias;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class PasantiasDAO extends Config implements PasantiasDaoInterface{

    public PasantiasDAO() {
        super();
    }    

    @Override
    public void savePasantias(Pasantias pasantia) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into pasantias (titulo_proyecto, tiempo_completo, medio_tiempo, total_horas, fecha_inicio, fecha_culminacion, estado) values(?,?,?,?,?,?,?)");
            ps.setString(1, pasantia.getTITULO_PROYECTO());
            ps.setInt(2, pasantia.getTIEMPO_COMPLETO());
            ps.setInt(3, pasantia.getMEDIO_TIEMPO());
            ps.setInt(4, pasantia.getTOTAL_HORAS());
            ps.setString(5, pasantia.getFECHA_INICIO());
            ps.setString(6, pasantia.getFECHA_CULMINACION());
            ps.setString(7, pasantia.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Pasantias> AllPasantias() {
        List<Pasantias> lpasantia = new ArrayList<Pasantias>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pasantias WHERE ESTADO = 'ACTIVO' order by ID_PASANTIAS desc");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Pasantias pasantia = new Pasantias();
                pasantia.setID_PASANTIAS(rs.getInt("id_pasantias"));
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                pasantia.setTIEMPO_COMPLETO(rs.getInt("tiempo_completo"));
                pasantia.setMEDIO_TIEMPO(rs.getInt("medio_tiempo"));
                pasantia.setTOTAL_HORAS(rs.getInt("total_horas"));
                pasantia.setFECHA_INICIO(rs.getString("fecha_inicio"));
                pasantia.setFECHA_CULMINACION(rs.getString("fecha_culminacion"));
                pasantia.setESTADO(rs.getString("estado"));
                lpasantia.add(pasantia);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lpasantia;
    }

    @Override
    public Pasantias findPasantiaById(int id) {
        Pasantias pasantia = new Pasantias();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pasantias WHERE ESTADO = 'ACTIVO' and id_pasantias = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pasantia.setID_PASANTIAS(rs.getInt("id_pasantias"));
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                pasantia.setTIEMPO_COMPLETO(rs.getInt("tiempo_completo"));
                pasantia.setMEDIO_TIEMPO(rs.getInt("medio_tiempo"));
                pasantia.setTOTAL_HORAS(rs.getInt("total_horas"));
                pasantia.setFECHA_INICIO(rs.getString("fecha_inicio"));
                pasantia.setFECHA_CULMINACION(rs.getString("fecha_culminacion"));
                pasantia.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return pasantia;
    }

    @Override
    public List<Pasantias> findPasantiaByFechaInicio(String fecha_inicio) {
        List<Pasantias> lpasantia = new ArrayList<Pasantias>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pasantias WHERE FECHA_INICIO = ? order by ID_PASANTIAS desc");
            ps.setString(1, fecha_inicio);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Pasantias pasantia = new Pasantias();
                pasantia.setID_PASANTIAS(rs.getInt("id_pasantias"));
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                pasantia.setTIEMPO_COMPLETO(rs.getInt("tiempo_completo"));
                pasantia.setMEDIO_TIEMPO(rs.getInt("medio_tiempo"));
                pasantia.setTOTAL_HORAS(rs.getInt("total_horas"));
                pasantia.setFECHA_INICIO(rs.getString("fecha_inicio"));
                pasantia.setFECHA_CULMINACION(rs.getString("fecha_culminacion"));
                pasantia.setESTADO(rs.getString("estado"));
                lpasantia.add(pasantia);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lpasantia;
    }

    @Override
    public List<Pasantias> findPasantiaByFechaFin(String fecha_fin) {
        List<Pasantias> lpasantia = new ArrayList<Pasantias>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pasantias WHERE FECHA_CULMINACION = ? order by ID_PASANTIAS desc");
            ps.setString(1, fecha_fin);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Pasantias pasantia = new Pasantias();
                pasantia.setID_PASANTIAS(rs.getInt("id_pasantias"));
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                pasantia.setTIEMPO_COMPLETO(rs.getInt("tiempo_completo"));
                pasantia.setMEDIO_TIEMPO(rs.getInt("medio_tiempo"));
                pasantia.setTOTAL_HORAS(rs.getInt("total_horas"));
                pasantia.setFECHA_INICIO(rs.getString("fecha_inicio"));
                pasantia.setFECHA_CULMINACION(rs.getString("fecha_culminacion"));
                pasantia.setESTADO(rs.getString("estado"));
                lpasantia.add(pasantia);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lpasantia;
    }

    @Override
    public void updatePasantia(Pasantias pasantia) {
        try {
            PreparedStatement ps = con.prepareStatement("update pasantias set titulo_proyecto = ?, tiempo_completo = ?, medio_tiempo = ?, total_horas = ?, fecha_inicio = ?, fecha_culminacion = ?, estado = ? where id_pasantias = ?");
            ps.setString(1, pasantia.getTITULO_PROYECTO());
            ps.setInt(2, pasantia.getTIEMPO_COMPLETO());
            ps.setInt(3, pasantia.getMEDIO_TIEMPO());
            ps.setInt(4, pasantia.getTOTAL_HORAS());
            ps.setString(5, pasantia.getFECHA_INICIO());
            ps.setString(6, pasantia.getFECHA_CULMINACION());
            ps.setString(7, pasantia.getESTADO());
            ps.setInt(8, pasantia.getID_PASANTIAS());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deletePasantia(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from pasantias where id_pasantias = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Pasantias findPasantiaByTitulo(String titulo) {  
        Pasantias pasantia = new Pasantias();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pasantias WHERE TITULO_PROYECTO = ?");
            ps.setString(1, titulo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pasantia.setID_PASANTIAS(rs.getInt("id_pasantias"));
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                pasantia.setTIEMPO_COMPLETO(rs.getInt("tiempo_completo"));
                pasantia.setMEDIO_TIEMPO(rs.getInt("medio_tiempo"));
                pasantia.setTOTAL_HORAS(rs.getInt("total_horas"));
                pasantia.setFECHA_INICIO(rs.getString("fecha_inicio"));
                pasantia.setFECHA_CULMINACION(rs.getString("fecha_culminacion"));
                pasantia.setESTADO(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return pasantia;
    }
    
}
