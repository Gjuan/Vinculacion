package com.vinculacion.app.Persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactorFactory {
    
    protected EntityManagerFactory emf;
    
    public FactorFactory(){
        emf = Persistence.createEntityManagerFactory("SysVinculacionPU");
    }	
}
