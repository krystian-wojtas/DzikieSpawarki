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
 *Na dobrą sprawę klient webserwisu, ale równocześnie fasolka która odpowiada za całą magię. Przy dostawaniu się do odpowiednich pól wywoływane są odpowiednie zapytania do webserwisu mające na celu pobranie list symulacji/parametrów. 
 * @author Michał Zimnicki
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
     * Tworzy nową instancje klasy. Inicjalizuje wszystkie uchwyty do webserwiu.
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
     * Zwraca liste możliwych symulacji do wykonania
     * @return liste symulacji do wykonania
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
     * Wysyła do webserwisu listę parametrów wraz z ich wartościami oraz polecenie wykonania danej symulacji. Nie oczekuje na jej zakończenie.
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
    
    /**
     * Zapytanie o parametry symulacji o zadanej nazwie
     * @param name nazwa rodzaju symulacji
     * @return gp
     */
    public String SimulationParameters(String name)
    {
        try
        {
        String result = port.simulationParameters(name);
        simulationToExecute = parser.parseSimulationParameters(name, result);
        System.out.println(result);
        }
        catch (Exception ex) { }
        return "gp";
        
    }

    /**
     * Zwraca listę wykonanych symulacji wszystkich typów. 
     * @return lista wykonanych symulacji
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
    
    
    /**
     * Ustawia wartość parametru o zadanej nazwie na zadaną wartość w symulaji do wykonania
     * @param parameterName nazwa parametru
     * @param value wartość parametru
     */
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
     * Zwraca symulację wraz z opisem jej parametrów by była gotowa do wykonania
     * @return symulacja do wykonania
     */
    public SimulationToExecute getSimulationToExecute()
    {
        return simulationToExecute;
    }
    
    
    public String shit()
    {
        return System.getProperty("java.io.tmpdir");
    }
}
