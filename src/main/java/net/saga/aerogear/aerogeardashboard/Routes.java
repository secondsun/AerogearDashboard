/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard;

import net.saga.aerogear.aerogeardashboard.controller.Home;
import net.saga.aerogear.aerogeardashboard.vo.Pipe;
import net.saga.aerogear.aerogeardashboard.controller.PipeController;
import org.jboss.aerogear.controller.router.AbstractRoutingModule;
import org.jboss.aerogear.controller.router.MediaType;
import org.jboss.aerogear.controller.router.RequestMethod;

public class Routes extends AbstractRoutingModule {

@Override
public void configuration() {
    
    route()
           .from("/")
           .on(RequestMethod.GET)
           .to(Home.class).index();
    
    route()
           .from("/pipes")
           .on(RequestMethod.GET)
           .to(PipeController.class).index();
    

    route()
           .from("/pipes")
           .on(RequestMethod.GET)
           .consumes(MediaType.ANY)
           .produces(MediaType.JSON)
           .to(PipeController.class).getAll();
    
    route()
            .from("/pipes")
            .on(RequestMethod.POST, RequestMethod.PUT)
            .consumes(JSON, HTML)
            .produces(JSON)
            .to(PipeController.class).addPipe(param(Pipe.class));
    
    }



}