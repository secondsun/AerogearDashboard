/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard.vo;

import java.io.Serializable;
import javax.persistence.Id;

/**
 *
 * @author summers
 */
public class Pipe implements Serializable {
    
    @Id
    private int id = -1;

    private String name;
    
    public Pipe() {
    }

    Pipe(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
