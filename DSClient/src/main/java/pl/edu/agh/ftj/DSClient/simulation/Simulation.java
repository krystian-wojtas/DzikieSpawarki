/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

import com.agh.StrategiaManger_Service;
import java.util.LinkedList;
import java.util.List;
import javax.xml.ws.WebServiceRef;


/**
 *
 * @author Monk
 */
public class Simulation
{
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/StrategiaManger/StrategiaManger.wsdl")
    private StrategiaManger_Service service;
    private com.agh.StrategiaManger port;
    private XMLParser parser;
    private LinkedList<SimulationClassCore> simulationList;
    private SimulationToExecute simulationToExecute;
    private LinkedList<SimulationFinished> resultList;
    
    /**
     * Creates a new instance of Simulation
     */
    public Simulation()
    {
        try { // Call Web Service Operation
            parser = new XMLParser();
            service = new StrategiaManger_Service();
            port = service.getStrategiaMangerPort();
        } catch (Exception ex) {
        System.err.println(ex);
        }

    }
    
    

    /**
     * @return the simulationList
     */
    public LinkedList<SimulationClassCore> getSimulationList()
    {
        try 
        {
            String result = port.simulationList();
            System.out.println(result);
            simulationList = parser.parseSimulationList(result);
            
        } catch (Exception ex) {System.err.println(ex); }

        return simulationList;
    }

    /**
     * @return the executeSimulation
     */
    
    public String executeSimulation()
    {
        try
        {  
        port.executeSimulation(getSimulationToExecute().getName(), getSimulationToExecute().getParametersInStringFormat());
        }
        catch (Exception ex) { }
        return "executeSimulation";
    }
    
    
    public String  SimulationParameters(String name)
    {
        try
        {
        String result = port.simulationParameters(name);
        simulationToExecute = parser.parseSimulationParameters(name, result);
        System.out.println(simulationToExecute.getParametersInStringFormat());
        }
        catch (Exception ex) { }
        return "gp";
        
    }

    /**
     * @return the resultList
     */
    public LinkedList<SimulationFinished> getResultList()
    {
        try
        {
          String result = port.resultList();
          resultList = parser.parseResult(result);
          System.out.println(resultList.size());
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }
        return resultList;
    }
    
    
    
    public void setParameter(String parameterName, String value)
    {
        for(int i=0; i<this.getSimulationToExecute().getParameters().size(); i++)
        {
            if(this.getSimulationToExecute().getParameters().get(i).getName().equals(parameterName))
            {
                this.getSimulationToExecute().getParameters().get(i).setType(value);
                break;
            }
        }
    }

    /**
     * @return the simulationToExecute
     */
    public SimulationToExecute getSimulationToExecute()
    {
        return simulationToExecute;
    }
    
}
