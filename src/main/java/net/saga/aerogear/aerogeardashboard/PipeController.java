/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard;


import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author summers
 */
public class PipeController {

    private static final List<Pipe> pipes = new ArrayList<Pipe>();
    
    public List<Pipe> getAll() {
        return Collections.unmodifiableList(pipes);
    }
    
    public  List<Pipe>  addPipe(Pipe name) {
        pipes.add(name);
        return Collections.unmodifiableList(pipes);
    }

    public void index() {
    }
    
}
