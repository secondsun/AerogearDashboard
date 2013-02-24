/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard.controller;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author summers
 */
public class ProjectController {
    @PersistenceUnit (unitName = "AerogearDashboard")
    private EntityManagerFactory emf;
    
    @PreDestroy
    public void closeEntityManagerFactory() {
        emf.close();
    }
}
