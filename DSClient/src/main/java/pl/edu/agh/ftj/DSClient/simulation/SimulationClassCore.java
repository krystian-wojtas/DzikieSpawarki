/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

/**
 *Bazowa klasa dla symulacji, przechowująca jedynie jej nazwę
 * @author Michał Zimnicki
 */
public class SimulationClassCore
{

    private String name;
    
    /**
     * Inicjalizacja instancji klasy
     * @param name nazwa symulacji
     */
    public SimulationClassCore(String name)
    {
        this.name = name;
    }

    /**
     * Zwraca nazwę symulacji
     * @return nazwa symulacji
     */
    public String getName()
    {
        return name;
    }
    
}
