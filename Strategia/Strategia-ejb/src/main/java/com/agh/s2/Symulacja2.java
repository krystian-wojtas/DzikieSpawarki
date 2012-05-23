/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh.s2;

import com.agh.Parameter;
import com.agh.Simulation;
import java.util.List;
import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 * Symulacja wahadła
 * @author Evcia
 */
@Stateless
public class Symulacja2 extends Simulation implements Symulacja2Local,Symulacja2Remote {

    @Override
    public String execute(String id,List<Parameter> param) 
    {
        SymulatorWahadla symulator;
        String wynikiNumeryczneString;
        double DlugoscLiny = 1.0, KatPoczatkowy = 0.02, CzasMaksymalny = 10.0, KrokCzasowy = 0.1;
        
        try
        {
            for (Parameter parm : param)
            {
                if (parm.name.equalsIgnoreCase("Dlugosc liny"))
                    DlugoscLiny = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("Kat poczatkowy"))
                    KatPoczatkowy = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("Czas maksymalny"))
                    CzasMaksymalny = ((double)parm.value);
                else if (parm.name.equalsIgnoreCase("Krok czasowy"))
                    KrokCzasowy = ((double) parm.value);
                
            }
            symulator = new SymulatorWahadla(DlugoscLiny, KatPoczatkowy, CzasMaksymalny, KrokCzasowy);
            ArrayList<WynikSymulacji> wynikiNumeryczne = symulator.SymulacjaNumeryczna();
            wynikiNumeryczneString = FormatWyniku.arrayToString(wynikiNumeryczne);
            
        }
        catch(Exception ex)
        { 
            ex.printStackTrace(); return "Wystąpił błąd.";
        }
        
        Simulation.saveRemote("http://fatcat.ftj.agh.edu.pl/~i7dunia/zespolowe/save.php", id, "data="+ wynikiNumeryczneString );
        return "DONE" ;
       

    }
    
}
