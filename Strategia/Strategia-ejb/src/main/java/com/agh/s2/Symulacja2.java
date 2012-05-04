/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh.s2;

import com.agh.Parameter;
import com.agh.Simulation;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Cube
 */
@Stateless
public class Symulacja2 extends Simulation implements Symulacja2Local,Symulacja2Remote {

    @Override
    public String execute(String id,List<Parameter> param) {
        String result = this.generateRandom(1000);
        this.save("Symulacja1-"+id, result);
        return "DONE" ;
    }
    
}
