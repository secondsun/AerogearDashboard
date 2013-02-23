/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
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

    private static final List<Pipe> pipes = new ArrayList<Pipe>();
    
    @Inject RuntimeRouter router;
    
    public List<Pipe> getAll() {
        return Collections.unmodifiableList(pipes);
    }
    
    public  List<Pipe>  addPipe(Pipe name) {
        pipes.add(name);
        addRoutes(name);
        return Collections.unmodifiableList(pipes);
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
