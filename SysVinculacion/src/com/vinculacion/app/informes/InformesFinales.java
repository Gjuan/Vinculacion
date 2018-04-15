package com.vinculacion.app.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jorge
 */
public class InformesFinales {
    
    private Connection con = null;
    private String user = "postgres";
    private String pass = "admin";
   
    public InformesFinales(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://192.168.1.7:5432/vinculo", user, pass);
            if (con != null)
                System.out.println("Conectado!!");
            else
                System.out.println("Error al conectarse!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void InformeDocenteByCedula(String cedula){
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(""));
            Map mp = new HashMap();
            mp.put("cedula", cedula);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Docente");
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformeDocenteCargos(){
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/DocenteCargos.jasper"));            
            JasperPrint print = JasperFillManager.fillReport(reporte,null, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Docentes por cargos");
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformePasantes(){
        try {       
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Pasante.jasper"));
            JasperPrint print = JasperFillManager.fillReport(reporte,null, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Estudiantes Pasantes");
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformeEmpresas(){
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Empresas.jasper"));
            JasperPrint print = JasperFillManager.fillReport(reporte,null, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Empresas");
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void cerrar(){
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
