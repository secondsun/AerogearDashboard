/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author summers
 */
@Entity
public class Pipe implements Serializable {
    
    @Id
    @SequenceGenerator( name = "seq", sequenceName = "CAR_SEQ", allocationSize = 10, initialValue = 100 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq" )
    private Long id;

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
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
