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
            con = DriverManager.getConnection("jdbc:postgresql://192.168.1.6:5432/vinculo", user, pass);
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
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Pasantes.jasper"));
            JasperPrint print = JasperFillManager.fillReport(reporte,null, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Estudiantes Pasantes");
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    /********************************Nuevos informes*****************************************************/
    public void InformeEstudiantes(){
        try {       
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Estudiantes.jasper"));
            JasperPrint print = JasperFillManager.fillReport(reporte,null, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes");
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformePasantesByCarrera (String descripcion) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByCarrera.jasper"));
            Map mp = new HashMap();
            mp.put("des", descripcion);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes de la carrera de " + descripcion);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformePasantesByCarreraAndPeriodo (String descripcion, String periodo) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByCarreraAndPeriodo.jasper"));
            Map mp = new HashMap();
            mp.put("descripcion", descripcion);
            mp.put("periodo", periodo);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes de la carrera de " + descripcion + " en el periodo de " + periodo);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformePasantesByDocentesAndPeriodo (String name, String lastName, String periodo) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByDocenteAndPeriodo.jasper"));
            Map mp = new HashMap();
            mp.put("nombre", name);
            mp.put("apellidos", lastName);
            mp.put("periodo", periodo);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes del docente " + name + " " + lastName + " en el periodo " + periodo);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
     
    public void InformePasantesByDocentes (String name, String lastName) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByDocente.jasper"));
            Map mp = new HashMap();
            mp.put("nombre", name);
            mp.put("apellidos", lastName);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes del docente " + name + " " + lastName);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformePasantesByDocumento (String descripcion) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByDocumento.jasper"));
            Map mp = new HashMap();
            mp.put("descripcion", descripcion);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes con " + descripcion);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    public void InformePasantesByDocumentoAndPeriodo (String descripcion, String periodo) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByDocumentoAndPeriodo.jasper"));
            Map mp = new HashMap();
            mp.put("descripcion", descripcion);
            mp.put("periodo", periodo);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes con " + descripcion + " en el periodo " + periodo);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    public void InformePasantesByEmpleado (String name, String lastName) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByEmpleado.jasper"));
            Map mp = new HashMap();
            mp.put("nombre", name);
            mp.put("apellidos", lastName);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes del tutor empresarial " + name + " " + lastName);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformePasantesByEmpleadoAndPeriodo (String name, String lastName, String periodo) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByEmpleadoAndPeriodo.jasper"));
            Map mp = new HashMap();
            mp.put("nombre", name);
            mp.put("apellidos", lastName);
            mp.put("periodo", periodo);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes del tutor empresarial " + name + " " + lastName + " en el periodo " + periodo);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void InformePasantesByPeriodo (String periodo) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/PasanteByPeriodo.jasper"));
            Map mp = new HashMap();
            mp.put("periodo", periodo);
            JasperPrint print = JasperFillManager.fillReport(reporte, mp, con);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Pasantes del periodo " + periodo);
            visor.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

  /*************************************************************************************/      
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
            System.out.println("Conexión cerrada");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
