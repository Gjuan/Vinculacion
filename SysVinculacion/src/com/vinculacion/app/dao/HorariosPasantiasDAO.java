package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.HorarioPasantiasDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.HorarioPasantias;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class HorariosPasantiasDAO extends Config implements HorarioPasantiasDaoInterface {

    public HorariosPasantiasDAO() {
        super();
    }
        
    @Override
    public void saveHorarioPasantias(HorarioPasantias horario) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into horario_pasantias (descripcion_horario) values(?)");
            ps.setString(1, horario.getDESCRIPCION_HORARIO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<HorarioPasantias> AllHorariosPasantias() {
        List<HorarioPasantias> lhorarios = new ArrayList<HorarioPasantias>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM horario_pasantias order by ID_HORARIO_PASANTIAS desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HorarioPasantias horario = new HorarioPasantias();
                horario.setID_HORARIO_PASANTIAS(rs.getInt("id_horario_pasantias"));
                horario.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
                lhorarios.add(horario);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lhorarios;
    }

    @Override
    public void deleteHorariosPasantias(int id) {
    }

    @Override
    public void updateHorarioPasantia(HorarioPasantias horario) {
        try {
            PreparedStatement ps = con.prepareStatement("update horario_pasantias set descripcion_horario = ? where id_horario_pasantias = ?");
            ps.setString(1, horario.getDESCRIPCION_HORARIO());
            ps.setInt(2, horario.getID_HORARIO_PASANTIAS());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public HorarioPasantias findHorarioPasantiaById(int id) {
        HorarioPasantias horario = new HorarioPasantias();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM horario_pasantias where id_horario_pasantias = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                horario.setID_HORARIO_PASANTIAS(rs.getInt("id_horario_pasantias"));
                horario.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return horario;
    }

    @Override
    public HorarioPasantias findHorarioPasantiaByDescription(String description) {
        HorarioPasantias horario = new HorarioPasantias();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM horario_pasantias where descripcion_horario = ?;");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                horario.setID_HORARIO_PASANTIAS(rs.getInt("id_horario_pasantias"));
                horario.setDESCRIPCION_HORARIO(rs.getString("descripcion_horario"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return horario;
    }
    
}
