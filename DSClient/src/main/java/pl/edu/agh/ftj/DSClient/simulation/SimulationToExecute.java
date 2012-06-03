/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

import java.util.LinkedList;

/**
 *Klasa odpowiadająca symulacji do wykonania. Zawiera listę parametrów oraz może ją zwrócić w formie listy stringów
 * @author Michał Zimnicki
 */
public class SimulationToExecute extends SimulationClassCore
{
    private LinkedList<ParameterDescriptor> parameters;
    
    /**
     * Inicjalizacja instancji klasy
     * @param name nazwa symulacji
     * @param parameters parametry symulacji
     */
    SimulationToExecute(String name, LinkedList<ParameterDescriptor> parameters)
    {
        super(name);
        if(parameters !=null)
            this.parameters = parameters;
        else
            this.parameters = new LinkedList<ParameterDescriptor>();
    }

    /**
     * Zwraca listę parametrów
     * @return lista parametrów
     */
    public LinkedList<ParameterDescriptor> getParameters()
    {
        return parameters;
    }
    /**
     * Zwraca listę parametrów w formie listy stringów.
     * <p>
     * Łatwe do wysyłania i ewentualnego parsowania po stronie odbiorcy.
     * @return lista stringów
     */
    public LinkedList<String> getParametersInStringFormat()
    {
        LinkedList<String> result = new LinkedList<String>();
        for(int i=0;i<parameters.size();i++)
        {
            result.add(parameters.get(i).getValue());
        }
        return result;
    }
}
