package com.lucapiras.snk.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
public class BasicModel implements Model {

    protected HashMap<String, Object> map;

    public BasicModel() {
        this.map = new HashMap<>();
    }
    
    
    @Override
    public Model addAttribute(String string, Object o) {
        this.map.put(string, o);
        
        return this;
    }

    @Override
    public Model addAttribute(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Model addAllAttributes(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Model addAllAttributes(Map<String, ?> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Model mergeAttributes(Map<String, ?> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAttribute(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> asMap() {
        return this.map;
    }
}