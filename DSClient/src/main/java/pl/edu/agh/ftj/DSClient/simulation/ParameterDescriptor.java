/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

/**
 * Klasa do przechowywania informacji o parametrach symulacji. 
 * Przechowuje 2 informacjie: o nazwie parametru i o typie
 * @author Michał Zimnicki
 */
public class ParameterDescriptor
{
    private String name;
    private String type;

    /**
     * Inicjalizacja instancji klasy
     * @param name nazwa parametru
     * @param type typ parametru
     */
    public ParameterDescriptor(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    /**
     * Zwraca nazwę parametru
     * @return nazwa
     */
    public String getName()
    {
        return name;
    }


    /**
     * Zwraca typ parametru
     * @return typ
     */
    public String getType()
    {
        return "";
    }


    /**
     * Zwraca wartość parametru
     * @return wartość
     */
    public String getValue()
    {
        return type;
    }
    
    /**
     * Ustawia typ parametru
     * @param type typ parametru
     */
    public void setType(String type)
    {
        this.type = type;
    }

    
}
