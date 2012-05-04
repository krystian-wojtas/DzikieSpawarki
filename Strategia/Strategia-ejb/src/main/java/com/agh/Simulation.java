/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 *
 * @author Cube
 */
public abstract class Simulation {

    protected void save(String urlStr, String data) {
        try {
            URL url = new URL(urlStr);
            URLConnection urlconnection = url.openConnection();
            OutputStream output = urlconnection.getOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            out.write(data);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    protected String generateRandom(int lines){
        String result = "";
        Random r = new Random();
        for(int i=0;i<lines;++i){
            result += i + "\t" + r.nextFloat()*100 + "\t" + r.nextFloat()*100;
        }
        
        return result;
    }
}
