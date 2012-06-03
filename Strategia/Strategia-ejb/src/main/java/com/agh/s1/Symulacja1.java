/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh.s1;

import com.agh.Parameter;
import com.agh.Simulation;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Cube
 */
@Stateless
public class Symulacja1 extends Simulation implements Symulacja1Local,Symulacja1Remote {

    @Override
    public String execute(String id,List<Parameter> param) {
        Pocisk pocisk;
        double vPocz = -1, yPocz = -1, katPocz = -1, m = -1, k = -1, dt = -1;
        int n = -1;
        try
        {
            for (Parameter parm : param)
            {
                if (parm.name.equalsIgnoreCase("predkosc"))
                    vPocz = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("wysokosc"))
                    yPocz = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("kat"))
                    katPocz = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("masa"))
                    m = ((double) parm.value);
                else if (parm.name.equalsIgnoreCase("opor"))
                    k = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("krok"))
                    dt = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("kroki"))
                    n = ((int)parm.value);
                else if (parm.name.equalsIgnoreCase("predkosc poczatkowa"))
                    vPocz = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("wysokosc poczatkowa"))
                    yPocz = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("kat poczatkowy"))
                    katPocz = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("masa"))
                    m = ((double) parm.value);
                else if (parm.name.equalsIgnoreCase("wspolczynnik oporu"))
                    k = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("krok czasowy"))
                    dt = ((double)parm.value);

            }
            if (n < 0) pocisk = new Pocisk(vPocz, yPocz, katPocz, m, k, dt);
            else  pocisk = new Pocisk(vPocz, yPocz, katPocz, m, k, n);
            
            pocisk.wystrzel();

            while(!pocisk.Koniec())
            {
                Thread.sleep(1000);
            }
        }
        catch(Exception ex)
        { ex.printStackTrace(); return "Wystąpił błąd.";}
        
        Simulation.saveRemote("http://fatcat.ftj.agh.edu.pl/~i7dunia/zespolowe/save.php", id, "data="+pocisk.getWyniki());
        return "DONE" ;
    }

}
