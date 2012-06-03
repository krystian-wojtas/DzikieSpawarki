/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

import java.util.LinkedList;

/**
 *Klasa przechowująca wszystkie parametry zwiazane z zakończoną symulacją - gotową do wizualizacji.
 * @author Michał Zimnicki
 */
public class SimulationFinished extends SimulationToExecute
{
    private boolean status;
    private String url;
    private int id;

    /**
     * Inicjalizacji instancji klasy
     * @param status status symulacji
     * @param url adres pod którym znajduje się plik z wynikami
     * @param id unikatowy numer symulacji
     * @param name nazwa symulacji
     * @param parameters parametry symulacji
     */
    public SimulationFinished(boolean status, String url, int id, String name, LinkedList<ParameterDescriptor> parameters)
    {
        super(name, parameters);
        this.status = status;
        this.url = url;
        this.id = id;
    }

    /**
     * Zwraca status symulacji (zakończona lub nie)
     * @return status symulacji 
     */
    public boolean isStatus()
    {
        return status;
    }

    /**
     * Zwraca url pod którym znajduje się plik wynikowy symulacji
     * @return url z plikiem wynikowym
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Zwraca id zakończonej symulacji do jej jednoznacznej identyfikacji
     * @return id
     */
    public int getId()
    {
        return id;
    }
    
}
