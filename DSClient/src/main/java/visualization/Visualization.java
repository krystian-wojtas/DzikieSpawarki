/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import factory.VisualizationFactory;
import factory.VisualizationFactory_Service;

/**
 *
 * @author Monk
 */
public class Visualization
{
    private VisualizationFactory_Service service;
    private VisualizationFactory port;
    

    /**
     * Creates a new instance of Visualization
     */
    public Visualization()
    {
        service = new VisualizationFactory_Service();
        port = service.getVisualizationFactoryPort();
        
    }
    
    public String visualization(String url)
    {
        return port.visualization(url);
    }
}


