package com.vinculacion.app.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactorFactory {
    
    private Session sesion;
	
    public FactorFactory(){
	super();
	SessionFactory sessionFact;
        Configuration conf = new Configuration();
        conf.configure();
        sessionFact = conf.buildSessionFactory();
        sesion = sessionFact.openSession();
        sesion.beginTransaction();
    }
	
    public Session getSession(){	
        return sesion;
    }		
}
