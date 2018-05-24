package com.vinculacion.app.dao;

import com.vinculacion.app.Interface.InformePasantiasDaoInterface;
import com.vinculacion.app.Persistence.Config;
import com.vinculacion.app.model.Estudiantes;
import com.vinculacion.app.model.InformePasantias;
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
public class InformePasantiasDAO extends Config implements InformePasantiasDaoInterface{

    public InformePasantiasDAO() {
        super();
    }

    @Override
    public void saveInformePasantias(InformePasantias informe) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into informe_pasantias (id_pasantias, codigo_estudiante, fecha_entrega_informe, estado) values(?,?,?,?)");
            ps.setInt(1, informe.getPasantias().getID_PASANTIAS());
            ps.setInt(2, informe.getEstudiantes().getCODIGO());
            ps.setString(3, informe.getFECHA_ENTREGA_INFORME());
            ps.setString(4, informe.getESTADO());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<InformePasantias> AllInformes() {
        List<InformePasantias> linforme = new ArrayList<InformePasantias>();
        try {
            PreparedStatement ps = con.prepareStatement("select ip.id_pasantias_estudiantes as cod_inf_pasantias, p.titulo_proyecto, e.cedula, e.nombres, e.apellidos, ip.fecha_entrega_informe, ip.estado as estado_informe_pasantias from informe_pasantias ip, estudiantes e, pasantias p where ip.ESTADO = 'ACTIVO' and ip.id_pasantias = p.id_pasantias and e.codigo = ip.codigo_estudiante order by ip.ID_PASANTIAS_ESTUDIANTES desc;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                InformePasantias informe = new InformePasantias();
                informe.setID_PASANTIAS_ESTUDIANTES(rs.getInt("cod_inf_pasantias"));
                Pasantias pasantia = new Pasantias();
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                informe.setPasantias(pasantia);
                Estudiantes est = new Estudiantes();
                est.setCEDULA(rs.getString("cedula"));
                est.setNOMBRES(rs.getString("nombres"));
                est.setAPELLIDOS(rs.getString("apellidos"));
                informe.setEstudiantes(est);
                informe.setFECHA_ENTREGA_INFORME(rs.getString("fecha_entrega_informe"));
                informe.setESTADO(rs.getString("estado_informe_pasantias"));
                linforme.add(informe);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return linforme;
    }

    @Override
    public void updateInformPasantia(InformePasantias informe) {
        try {
            PreparedStatement ps = con.prepareStatement("update informe_pasantias set id_pasantias = ?, codigo_estudiante = ?, fecha_entrega_informe = ?, estado = ? where id_pasantias_estudiantes = ?");
            ps.setInt(1, informe.getPasantias().getID_PASANTIAS());
            ps.setInt(2, informe.getEstudiantes().getCODIGO());
            ps.setString(3, informe.getFECHA_ENTREGA_INFORME());
            ps.setString(4, informe.getESTADO());
            ps.setInt(5, informe.getID_PASANTIAS_ESTUDIANTES());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteInformePasantia(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from informe_pasantias where id_pasantias_estudiantes = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<InformePasantias> findInformeByCedulaEstudiante(String cedulaEstudiante) {
        List<InformePasantias> linforme = new ArrayList<InformePasantias>();
        try {
            PreparedStatement ps = con.prepareStatement("select ip.id_pasantias_estudiantes as cod_inf_pasantias, p.titulo_proyecto, e.cedula, e.nombres, e.apellidos, ip.fecha_entrega_informe, ip.estado as estado_informe_pasantias from informe_pasantias ip, estudiantes e, pasantias p where ip.ESTADO = 'ACTIVO' and ip.id_pasantias = p.id_pasantias and e.codigo = ip.codigo_estudiante and e.cedula = ? order by ip.ID_PASANTIAS_ESTUDIANTES desc;");
            ps.setString(1, cedulaEstudiante);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                InformePasantias informe = new InformePasantias();
                informe.setID_PASANTIAS_ESTUDIANTES(rs.getInt("cod_inf_pasantias"));
                Pasantias pasantia = new Pasantias();
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                informe.setPasantias(pasantia);
                Estudiantes est = new Estudiantes();
                est.setCEDULA(rs.getString("cedula"));
                est.setNOMBRES(rs.getString("nombres"));
                est.setAPELLIDOS(rs.getString("apellidos"));
                informe.setEstudiantes(est);
                informe.setFECHA_ENTREGA_INFORME(rs.getString("fecha_entrega_informe"));
                informe.setESTADO(rs.getString("estado_informe_pasantias"));
                linforme.add(informe);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return linforme;
    }

    @Override
    public List<InformePasantias> findInformesByFechaEntrega(String fecha_entrega) {
        List<InformePasantias> linforme = new ArrayList<InformePasantias>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT ip.id_pasantias_estudiantes as cod_inf_pasantias, p.titulo_proyecto, e.cedula, e.nombres, e.apellidos, ip.fecha_entrega_informe, ip.estado as estado_informe_pasantias FROM informe_pasantias ip, estudiantes e, pasantias p where ip.ESTADO = 'ACTIVO' and ip.id_pasantias = p.id_pasantias and e.codigo = ip.codigo_estudiante and ip.fecha_entrega_informe = ? ORDER BY ip.ID_PASANTIAS_ESTUDIANTES desc;");
            ps.setString(1, fecha_entrega);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                InformePasantias informe = new InformePasantias();
                informe.setID_PASANTIAS_ESTUDIANTES(rs.getInt("cod_inf_pasantias"));
                Pasantias pasantia = new Pasantias();
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                informe.setPasantias(pasantia);
                Estudiantes est = new Estudiantes();
                est.setCEDULA(rs.getString("cedula"));
                est.setNOMBRES(rs.getString("nombres"));
                est.setAPELLIDOS(rs.getString("apellidos"));
                informe.setEstudiantes(est);
                informe.setFECHA_ENTREGA_INFORME(rs.getString("fecha_entrega_informe"));
                informe.setESTADO(rs.getString("estado_informe_pasantias"));
                linforme.add(informe);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return linforme;
    }

    @Override
    public InformePasantias findInformePasantiaById(int id) {
        InformePasantias informe = new InformePasantias();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT ip.id_pasantias_estudiantes as cod_inf_pasantias, p.titulo_proyecto, e.cedula, e.nombres, e.apellidos, ip.fecha_entrega_informe, ip.estado as estado_informe_pasantias FROM informe_pasantias ip, estudiantes e, pasantias p where ip.ESTADO = 'ACTIVO' and ip.id_pasantias = p.id_pasantias and e.codigo = ip.codigo_estudiante and ip.id_pasantias_estudiantes = ? ORDER BY ip.ID_PASANTIAS_ESTUDIANTES desc;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                informe.setID_PASANTIAS_ESTUDIANTES(rs.getInt("cod_inf_pasantias"));
                Pasantias pasantia = new Pasantias();
                pasantia.setTITULO_PROYECTO(rs.getString("titulo_proyecto"));
                informe.setPasantias(pasantia);
                Estudiantes est = new Estudiantes();
                est.setCEDULA(rs.getString("cedula"));
                est.setNOMBRES(rs.getString("nombres"));
                est.setAPELLIDOS(rs.getString("apellidos"));
                informe.setEstudiantes(est);
                informe.setFECHA_ENTREGA_INFORME(rs.getString("fecha_entrega_informe"));
                informe.setESTADO(rs.getString("estado_informe_pasantias"));
            }
        } catch (SQLException e) {
            System.out.println("ErroR: " + e.getMessage());
        }
        return informe;
    }
        
}
