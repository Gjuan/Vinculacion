package com.vinculacion.app.Persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactorFactory {
    
    protected EntityManagerFactory emf;
    
    public FactorFactory(){
        emf = Persistence.createEntityManagerFactory("SysVinculacionPU");
    }
    /*protected Connection con = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;
    private String user = "postgres";
    private String pass = "admin";
    
    public FactorFactory(){
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vinculo", user, pass);
            if (con != null)
                System.out.println("Conectado!!");
            else
                System.out.println("Error al conectarse!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }*/	
}
