/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard.controller;

import net.saga.aerogear.aerogeardashboard.controller.DynamicsController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import net.saga.aerogear.aerogeardashboard.vo.Pipe;
import net.saga.aerogear.aerogeardashboard.util.RuntimeRouter;
import org.jboss.aerogear.controller.router.MediaType;
import org.jboss.aerogear.controller.router.RequestMethod;
import org.jboss.aerogear.controller.router.RouteBuilder;
import org.jboss.aerogear.controller.router.RouteBuilderImpl;
import org.jboss.aerogear.controller.router.parameter.Parameter;

/**
 *
 * @author summers
 */
public class PipeController {

    @PersistenceUnit(unitName = "AerogearDashboard")
    private EntityManagerFactory emf;
    @Inject
    RuntimeRouter router;

    public List<Pipe> getAll() {
        EntityManager manager = emf.createEntityManager();
        try {
            return manager.createQuery("from Pipe", Pipe.class).getResultList();
        } finally {
            manager.close();
        }
    }

    public List<Pipe> addPipe(Pipe pipe) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(pipe);
            tx.commit();
        } catch (final Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        addRoutes(pipe);
        return emf.createEntityManager().createQuery("from Pipe", Pipe.class).getResultList();
    }

    public void index() {
    }

    private void addRoutes(Pipe pipe) {


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
