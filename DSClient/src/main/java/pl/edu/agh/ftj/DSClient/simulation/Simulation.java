/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Monk
 */
public class Simulation
{

    LinkedList<SimulationClassCore> simulationList;
    SimulationToExecute simulationToExecute;
    LinkedList<SimulationFinished> resultList;
    
    /**
     * Creates a new instance of Simulation
     */
    public Simulation()
    {
    }
    
    

    /**
     * @return the simulationList
     */
    public LinkedList<SimulationClassCore> getSimulationList()
    {
        return simulationList;
    }

    /**
     * @return the executeSimulation
     */
    
    public SimulationToExecute getSimulationToExecute()
    {
        return simulationToExecute;
    }

    /**
     * @return the resultList
     */
    public LinkedList<SimulationFinished> getResultList()
    {
        return resultList;
    }
    
    
    
    public void setParameter(String parameterName, String value)
    {
        for(int i=0; i<this.simulationToExecute.getParameters().size(); i++)
        {
            if(this.simulationToExecute.getParameters().get(i).getName().equals(parameterName))
            {
                this.simulationToExecute.getParameters().get(i).setType(value);
                break;
            }
        }
    }
    
}
