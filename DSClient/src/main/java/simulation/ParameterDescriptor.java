/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

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
        return type;
    }

    
}
