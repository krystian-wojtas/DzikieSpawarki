/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
/**
 *
 * @author bezkarny
 */
public class XMLParser 
{
    XPathFactory factory;
    XPath xPath;
    
    /**
     * Creates a new instance of XMLParser
     */
    public XMLParser()
    {
        factory = XPathFactory.newInstance();
        xPath = factory.newXPath();
    }
    /**
     * @return the list of SimulationClassCore objects
     */
    public LinkedList<SimulationClassCore> parseSimulationList(String doc) throws XPathExpressionException, UnsupportedEncodingException
    {
        doc = "<?xml version=\"1.0\"?><root>" + doc + "</root>";
        LinkedList<SimulationClassCore> result = new LinkedList<SimulationClassCore>();
        InputStream is = new ByteArrayInputStream(doc.getBytes("UTF-8"));
        InputSource input = new InputSource(is);
        
        NodeList nodes = (NodeList)xPath.evaluate("//simulation/name",input,XPathConstants.NODESET);
        for(int i=0;i<nodes.getLength();i++)
        {
            result.add(new SimulationClassCore(nodes.item(i).getTextContent()));
        }
        return result;
    }
    /**
     * @return the SimulationToExecute object
     */
    
    public SimulationToExecute parseSimulationParameters(String name, String doc) throws XPathExpressionException, UnsupportedEncodingException
    {
        doc = "<?xml version=\"1.0\"?><root><simulation>" + doc + "</simulation></root>";
        LinkedList<ParameterDescriptor> result = new LinkedList<ParameterDescriptor>();
        InputStream is = new ByteArrayInputStream(doc.getBytes("UTF-8"));
        InputSource input = new InputSource(is);
        
        NodeList nodes = (NodeList)xPath.evaluate("//simulation",input,XPathConstants.NODESET);
        NodeList params = null;
        for(int i=0;i<nodes.getLength();i++)
        {
            if(name.equals(nodes.item(i).getFirstChild().getTextContent()))
                params = nodes.item(i).getChildNodes();
        }
        if(params!=null)
            for(int i=0;i<params.getLength()-1;i++)
                {
                    result.add(new ParameterDescriptor(params.item(i+1).getFirstChild().getTextContent(), params.item(i+1).getLastChild().getTextContent()));
                }

        return new SimulationToExecute(name, result);
    }
    
    /**
     * @return the list of SimulationFinished objects
     */
    public LinkedList<SimulationFinished> parseResult(String doc) throws XPathExpressionException, UnsupportedEncodingException
    {
        doc = "<?xml version=\"1.0\"?><root>" + doc + "</root>";
        LinkedList<SimulationFinished> result = new LinkedList<SimulationFinished>();
        InputStream is = new ByteArrayInputStream(doc.getBytes("UTF-8"));
        InputSource input = new InputSource(is);
        
        NodeList nodes = (NodeList)xPath.evaluate("//simulation",input,XPathConstants.NODESET);
        
        for(int i=0;i<nodes.getLength();i++){
            NodeList simulation = nodes.item(i).getChildNodes();
            LinkedList<ParameterDescriptor> par = new LinkedList<ParameterDescriptor>();
            for(int j=4;j<simulation.getLength();j++)
            {
                NodeList params = simulation.item(j).getChildNodes();
                par.add(new ParameterDescriptor(params.item(0).getTextContent(), params.item(1).getTextContent()));
            }
            result.add(new SimulationFinished(Boolean.parseBoolean(simulation.item(2).getTextContent()),simulation.item(3).getTextContent(),Integer.parseInt(simulation.item(1).getTextContent()), simulation.item(0).getTextContent(), par));
        }
        
        return result;
    }
}
