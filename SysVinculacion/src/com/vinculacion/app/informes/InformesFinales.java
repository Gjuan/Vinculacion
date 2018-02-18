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
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vinculo", user, pass);
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
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/vinculacion/app/informes/DocenteByCedula.jasper"));
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
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/vinculacion/app/informes/DocenteCargos.jasper"));
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
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/vinculacion/app/informes/Pasante.jasper"));
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
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/vinculacion/app/informes/Empresas.jasper"));
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