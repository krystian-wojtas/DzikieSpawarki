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
public class SimulationFinished extends SimulationToExecute
{
    private boolean status;
    private String url;
    private int id;

    public SimulationFinished(boolean status, String url, int id, String name, LinkedList<ParameterDescriptor> parameters)
    {
        super(name, parameters);
        this.status = status;
        this.url = url;
        this.id = id;
    }

    /**
     * @return the status
     */
    public boolean isStatus()
    {
        return status;
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    
}
