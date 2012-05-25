/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh;

import com.agh.s1.Symulacja1Remote;
import com.agh.s2.Symulacja2Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;import javax.ejb.Stateless;

/**
 *
 * @author Cube
 */
@WebService(serviceName = "StrategiaManger")
@Stateless()
public class StrategiaManger {

  
    @EJB
    private Symulacja1Remote s1;
    @EJB
    private Symulacja2Remote s2;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return Simulation.saveRemote("http://fatcat.ftj.agh.edu.pl/~i7dunia/zespolowe/save.php", txt, "data=test text");
    }
    
    @WebMethod(operationName = "test")
    public String test(@WebParam(name = "name") String name) {
       String parameters[] = new String[11];
        parameters[0]="2";
        parameters[1]="3";
        parameters[2]="45";
        parameters[3]="1";
        parameters[4]="0.1";
        parameters[5]="0.2";
        parameters[6]="20";
        //..............
        parameters[7]="1";
        parameters[8]="0.02";
        parameters[9]="10";
        parameters[10]="0.1";
        
        if (name.equals("rzut")) {
            List<Parameter> tmp = new ArrayList<Parameter>();
            tmp.add(new Parameter("Predkosc poczatkowa",new Float(parameters[0])));
            tmp.add(new Parameter("Wysokosc poczatkowa",new Float(parameters[1])));
            tmp.add(new Parameter("Kat poczatkowy",new Float(parameters[2])));
            tmp.add(new Parameter("Masa",new Float(parameters[3])));
            tmp.add(new Parameter("Wspolczynnik oporu",new Float(parameters[4])));
            tmp.add(new Parameter("Krok czasowy",new Float(parameters[5])));
            //tmp.add(new Parameter("kroki",new Float(parameters[6])));
            
            Random r = new Random();
            return s1.execute("rzut-"+r.nextInt(100000)+"-"
                    +parameters[0]+"_"+parameters[1]+"_"+parameters[2]+"_"+parameters[3]+"_"+parameters[4]+"_"+parameters[5]//+"_"+parameters[6]
                    +"-txt"
                    , tmp);
        }
        
        if (name.equals("wahadlo")) {
            List<Parameter> tmp = new ArrayList<Parameter>();
            
            tmp.add(new Parameter("Dlugosc liny",new Float(parameters[7])));
            tmp.add(new Parameter("Kat poczatkowy",new Float(parameters[8])));
            tmp.add(new Parameter("Czas maksymalny",new Float(parameters[9])));
            tmp.add(new Parameter("Krok czasowy",new Float(parameters[10])));
            
            Random r = new Random();
            return s2.execute("wahadlo-"+r.nextInt(100000)+"-"
                    +parameters[7]+"_"+parameters[8]+"_"+parameters[9]+"_"+parameters[10]
                    +"-txt"
                    , tmp);
        }
        return "BRAK";
    }
    
    @WebMethod(operationName = "simulationList")
    public String simulationList() {
        return "<simulation>"
                +"<name>rzut</name>"
                +"</simulation>"
                +"<simulation>"
                +"<name>wahadlo</name>"
                +"</simulation>";
    }
        
    @WebMethod(operationName = "simulationParameters")
    public String simulationParameters(@WebParam(name = "name") String name) {
        String result = "<name>"+name+"</name>";
        if (name.equals("wahadlo")) {
            
            result +="<parameter>";
            result +="<name>Dlugosc liny</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>Kat poczatkowy</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>Czas maksymalny</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>Krok czasowy</name>";
            result +="<type>float</type>";
            result +="</parameter>";
           
            
            return result;
        }

        if (name.equals("rzut")) {
            
            result +="<parameter>";
            result +="<name>Predkosc poczatkowa</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>Wysokosc poczatkowa</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>Kat poczatkowy</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>Masa</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>Wspolczynnik oporu</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            result +="<parameter>";
            result +="<name>Krok czasowy</name>";
            result +="<type>float</type>";
            result +="</parameter>";
            
            //result +="<parameter>";
            //result +="<name>kroki</name>";
            //result +="<type>int</type>";
            //result +="</parameter>";
            
            return result;
        }
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "executeSimulation")
    public String executeSimulation(@WebParam(name = "name") String name, @WebParam(name = "parameters") java.lang.String[] parameters) {
        
        if (name.equals("rzut")) {
            List<Parameter> tmp = new ArrayList<Parameter>();
            tmp.add(new Parameter("Predkosc poczatkowa",new Float(parameters[0])));
            tmp.add(new Parameter("Wysokosc poczatkowa",new Float(parameters[1])));
            tmp.add(new Parameter("Kat poczatkowy",new Float(parameters[2])));
            tmp.add(new Parameter("Masa",new Float(parameters[3])));
            tmp.add(new Parameter("Wspolczynnik oporu",new Float(parameters[4])));
            tmp.add(new Parameter("Krok czasowy",new Float(parameters[5])));
            //tmp.add(new Parameter("kroki",new Float(parameters[6])));
            
            Random r = new Random();
            return s1.execute("rzut-"+r.nextInt(100000)+"-"
                    +parameters[0]+"_"+parameters[1]+"_"+parameters[2]+"_"+parameters[3]+"_"+parameters[4]+"_"+parameters[5]//+"_"+parameters[6]
                    +"-txt"
                    , tmp);
        }
        
        if (name.equals("wahadlo")) {
            List<Parameter> tmp = new ArrayList<Parameter>();
            tmp.add(new Parameter("Dlugosc liny",new Float(parameters[0])));
            tmp.add(new Parameter("Kat poczatkowy",new Float(parameters[1])));
            tmp.add(new Parameter("Czas maksymalny",new Float(parameters[2])));
            tmp.add(new Parameter("Krok czasowy",new Float(parameters[3])));
            
            Random r = new Random();
            return s2.execute("wahadlo-"+r.nextInt(100000)+"-"
                    +parameters[0]+"_"+parameters[1]+"_"+parameters[2]+"_"+parameters[3]
                    +"-txt"
                    , tmp);
        }
        return "BRAK";
    }
    
    @WebMethod(operationName = "resultList")
    public String resultList() {
        String resultList = Simulation.getData("http://fatcat.ftj.agh.edu.pl/~i7dunia/zespolowe/ls.php");
        String files[] = resultList.split(";");
        
        String result="";
        for(int i=0;i<files.length;++i){
            if(files[i].startsWith("rzut") || files[i].startsWith("wahadlo")){
                Result r = new Result();
                
                String tmp[] = files[i].split("-");
                r.name = tmp[0];
                r.id = new Integer(tmp[1]);
                r.status = "true";
                r.url = "http://fatcat.ftj.agh.edu.pl/~i7dunia/zespolowe/"+files[i];
                
                if(files[i].startsWith("rzut")){
                    String params[] = tmp[2].split("_");
                    r.params+="<parameter><name>Predkosc poczatkowa</name><value>"+params[0]+"</value></parameter>";
                    r.params+="<parameter><name>Wysokosc poczatkowa</name><value>"+params[1]+"</value></parameter>";
                    r.params+="<parameter><name>Kat poczatkowy</name><value>"+params[2]+"</value></parameter>";
                    r.params+="<parameter><name>Masa</name><value>"+params[3]+"</value></parameter>";
                    r.params+="<parameter><name>Wspolczynnik oporu</name><value>"+params[4]+"</value></parameter>";
                    r.params+="<parameter><name>Krok czasowy</name><value>"+params[5]+"</value></parameter>";
                    //r.params+="<parameter><name>kroki</name><value>"+params[6]+"</value></parameter>";
                }
                
                if(files[i].startsWith("wahadlo"))
                {
                    String params[] = tmp[2].split("_");
                    r.params+="<parameter><name>Dlugosc liny</name><value>"+params[0]+"</value></parameter>";
                    r.params+="<parameter><name>Kat poczatkowy</name><value>"+params[1]+"</value></parameter>";
                    r.params+="<parameter><name>Czas maksymalny</name><value>"+params[2]+"</value></parameter>";
                    r.params+="<parameter><name>Krok czasowy</name><value>"+params[3]+"</value></parameter>";
                }
                
                result+=r;
            }
        }
        
        // r1.params = "<parameter><name>masa</name><value>1</value></parameter>";

        return result;
    }
}

