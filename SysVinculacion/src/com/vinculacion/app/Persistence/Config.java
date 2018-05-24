package com.vinculacion.app.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class Config {
    
    protected Connection con = null;
    private String user = "postgres";
    private String pass = "admin";
   
    public Config(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://192.168.1.5:5432/vinculo", user, pass);
            if (con != null)
                System.out.println("Conectado!!");
            else
                System.out.println("Error al conectarse!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
