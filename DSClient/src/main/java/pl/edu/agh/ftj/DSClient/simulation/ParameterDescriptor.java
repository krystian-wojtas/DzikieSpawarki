/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

/**
 *
 * @author Monk
 */
public class ParameterDescriptor
{
    private String name;
    private String type;

    public ParameterDescriptor(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }


    /**
     * @return the type
     */
    public String getType()
    {
        return "";
    }

    public String getValue()
    {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }

    
}
