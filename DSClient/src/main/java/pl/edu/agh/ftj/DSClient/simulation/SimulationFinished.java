/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.DSClient.simulation;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa przechowująca wszystkie parametry zwiazane z zakończoną symulacją -
 * gotową do wizualizacji.
 *
 * @author Michał Zimnicki
 */
public class SimulationFinished extends SimulationToExecute
{

    private boolean status;
    private String url;
    private int id;

    /**
     * Inicjalizacji instancji klasy
     *
     * @param status status symulacji
     * @param url adres pod którym znajduje się plik z wynikami
     * @param id unikatowy numer symulacji
     * @param name nazwa symulacji
     * @param parameters parametry symulacji
     */
    public SimulationFinished(boolean status, String url, int id, String name, LinkedList<ParameterDescriptor> parameters)
    {
        super(name, parameters);
        this.status = status;
        this.url = url;
        this.id = id;
    }

    /**
     * Zwraca status symulacji (zakończona lub nie)
     *
     * @return status symulacji
     */
    public boolean isStatus()
    {
        return status;
    }

    /**
     * Zwraca url pod którym znajduje się plik wynikowy symulacji
     *
     * @return url z plikiem wynikowym
     */
    public String getUrl()
    {

        URL u;
        String[] tb = url.split("/");
        try
        {
            u = new URL(url);
        
        URLConnection uc = u.openConnection();
        uc.connect();
        InputStream in = uc.getInputStream();
        String path = "/opt/glassfish3/glassfish/domains/testdomain/docroot/" + tb[tb.length-1];
        FileOutputStream out = new FileOutputStream(path);
        final int BUF_SIZE = 1 << 8;
        byte[] buffer = new byte[BUF_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) > -1)
        {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
        return "http://prgzsp.ftj.agh.edu.pl:8080/"+ tb[tb.length-1];
        }
        catch (IOException ex)
        {
            Logger.getLogger(SimulationFinished.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        //return null;



/*
        File f;
        File o;
        try
        {
            URL u = new URL(url);
            try
            {
                System.out.println(url);
                f = new File(url);

            }
            catch (Exception e)
            {
                f = new File(u.getPath());
            }
            o = new File(System.getProperty("java.io.tmpdir") + "/" + f.getName());

            FileReader in = new FileReader(f);
            FileWriter out = new FileWriter(o);
            int c;

            while ((c = in.read()) != -1)
            {
                out.write(c);
            }

            in.close();
            out.close();
            return o.getPath();
        }
        catch (MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return null;
        */


    }

    /**
     * Zwraca id zakończonej symulacji do jej jednoznacznej identyfikacji
     *
     * @return id
     */
    public int getId()
    {
        return id;
    }
}
