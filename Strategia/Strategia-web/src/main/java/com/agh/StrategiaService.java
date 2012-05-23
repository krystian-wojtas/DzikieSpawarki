/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh;

import com.agh.s1.Symulacja1Remote;
import com.agh.s2.Symulacja2Remote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Cube
 */
@WebService(serviceName = "StrategiaService")
public class StrategiaService {
    
    @EJB
    private Symulacja1Remote s1;
    @EJB
    private Symulacja2Remote s2;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "simulationList")
    public String simulationList() {
        return "<simulation>"
                +"<name>rzut ukosny</name>"
                +"</simulation>"
                +"<simulation>"
                +"<name>wahadło</name>"
                +"</simulation>";
    }
        
    @WebMethod(operationName = "simulationParameters")
    public String simulationParameters(@WebParam(name = "name") String name) {
        String result = "<name>"+name+"</name>";
        if (name.equals("wahadlo")) {
            
            result +="<parameter>";
            result +="<name>masa</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>kat</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>dlugosc_linki</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>dt</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>t_max</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            return result;
        }

        if (name.equals("rzut")) {
            
            result +="<parameter>";
            result +="<name>predkosc</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>wysokosc</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>kat</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>masa</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>opor</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>krok</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>kroki</name>";
            result +="<type>int</type>";
            result +="</parameter>";
            
            
            return result;
        }
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "executeSimulation")
    public String executeSimulation(@WebParam(name = "name") String name, @WebParam(name = "parameters") java.lang.String[] parameters) {
        List<Parameter> tmp = new ArrayList<Parameter>();
        for (int i = 0; i < parameters.length - 1; i=i+2) {
            Parameter param = new Parameter();
            param.name = parameters[i];
            param.value = new Float(parameters[i+1]);
            tmp.add(param);
        }
        if (name.equals("rzut")) {
            return s1.execute("random-generated-id", tmp);
        }
        if (name.equals("wahadlo")) {
            return s2.execute("random-generated-id", tmp);
        }
        return "BRAK";
    }
    
}
