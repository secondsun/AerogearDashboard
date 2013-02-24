/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard.util;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import net.saga.aerogear.aerogeardashboard.controller.DynamicsController;
import net.saga.aerogear.aerogeardashboard.vo.Pipe;
import org.jboss.aerogear.controller.router.MediaType;
import org.jboss.aerogear.controller.router.RequestMethod;
import org.jboss.aerogear.controller.router.RouteBuilderImpl;
import org.jboss.aerogear.controller.router.parameter.Parameter;

/**
 *
 * @author summers
 */
@Singleton
@javax.ejb.Startup
public class Startup {

    @PersistenceUnit(unitName = "AerogearDashboard")
    private EntityManagerFactory emf;
    
    @Inject
    private RuntimeRouter router;

    @PostConstruct
    void init() {
        EntityManager manager = emf.createEntityManager();
        List<Pipe> results = null;
        try {
            results = manager.createQuery("from Pipe", Pipe.class).getResultList();
        } finally {
            manager.close();
        }
        for (Pipe pipe : results) {
            RouteBuilderImpl route = new RouteBuilderImpl();
            route.from("/" + pipe.getName())
                    .on(RequestMethod.GET)
                    .consumes(MediaType.ANY)
                    .produces(MediaType.JSON)
                    .to(DynamicsController.class).json("");
            route.getRouteDescriptor().addParameter(Parameter.param("someGibberishNameBecauseIWantADefaultValue", pipe.getName(), String.class));
            router.addRoute(route);
        }
    }
}
