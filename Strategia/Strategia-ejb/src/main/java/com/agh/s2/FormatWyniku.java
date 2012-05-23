package com.agh.s2;

import java.util.ArrayList;
import java.lang.StringBuilder;
/**
 *
 * @author Evcia
 */
public class FormatWyniku 
{
    public static String arrayToString(ArrayList<WynikSymulacji> wyniki)
    {
        StringBuilder wynik = new StringBuilder();
        for(WynikSymulacji w: wyniki)
			{
				wynik.append(w.toString());
			}
        return wynik.toString();
    }
    
}
