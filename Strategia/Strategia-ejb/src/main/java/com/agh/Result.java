/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh;

/**
 *
 * @author Cube
 */
public class Result {
    public int id;
    public String name,status,url,params;
    
    @Override
    public String toString(){
        return "<simulation>" 
                + "<id>" + id + "</id>"
                + "<name>" + name + "</name>"
                + "<status>" + status + "</status>"
                + "<url>" + url + "</url>"
                + params
                +"</simulation>";
    }
}
