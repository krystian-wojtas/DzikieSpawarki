/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

import java.util.LinkedList;

/**
 *
 * @author Monk
 */
public class SimulationToExecute extends SimulationClassCore
{
    private LinkedList<ParameterDescriptor> parameters;
    
    SimulationToExecute(String name, LinkedList<ParameterDescriptor> parameters)
    {
        super(name);
        if(parameters !=null)
            this.parameters = parameters;
        else
            this.parameters = new LinkedList<ParameterDescriptor>();
    }

    /**
     * @return the parameters
     */
    public LinkedList<ParameterDescriptor> getParameters()
    {
        return parameters;
    }
}
