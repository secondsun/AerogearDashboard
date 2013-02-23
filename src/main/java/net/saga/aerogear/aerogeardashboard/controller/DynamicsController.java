/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.saga.aerogear.aerogeardashboard.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author summers
 */
public class DynamicsController {

    private Map<String, Set< Map<String, Object>>> dynamics = new HashMap<>();
    
    public Set<? extends Map<String, Object>> json(String name) {
        return dynamics.get(name);
    }

    public void putDynamic(String name, Map<String, Object> value) {
        dynamics.get(name).add(value);
    }
    
    public void addDynamic(String name) {
        if (dynamics.get(name) != null) {
            return;
        } else {
            dynamics.put(name, new HashSet<Map<String, Object>>());
        }
    }
    
}
