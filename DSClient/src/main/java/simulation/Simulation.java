/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Monk
 */
public class Simulation
{

    factory.SimulationFactory_Service service;
    factory.SimulationFactory port;
    LinkedList<SimulationClassCore> simulationList;
    SimulationToExecute executeSimulation;
    LinkedList<SimulationFinished> resultList;
    
    /**
     * Creates a new instance of Simulation
     */
    public Simulation()
    {
        service = new factory.SimulationFactory_Service();
        port = service.getSimulationFactoryPort();
    }
    
    public String sayHello()
    {
    try {
            
            String name = "";
            String result = port.hello(name);
            return "Result = "+result;
        } catch (Exception ex) {
            return "exception";
        }
    }
    
    public String simulationList()
    {
        return port.simulationList();
    }
    
    public String simulationParameters(String name)
    {
        return port.simulationParameters(name);
    }
    
    public String executeSimulation(String name, List<String> parameters)
    {
        return port.executeSimulation(name, parameters);
    }
    
    public String resultList()
    {
        return port.resultList();
    }
    
}
