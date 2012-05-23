/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    
    // data format "format for post data, dataname=something"
    public static String saveRemote(String url, String filename, String data) {
        return sendData(url+"?filename="+filename, data, true);
    }
    
    public static String sendData(String link, String data, boolean allowResponse) {
        String result = null;
        try {
            byte[] parameterAsBytes = data.getBytes();

            URL url = new URL(link);
            URLConnection con = url.openConnection();
            con.setDoOutput(allowResponse);
            con.setRequestProperty("Content=length", String.valueOf(parameterAsBytes.length));

            OutputStream oStream = con.getOutputStream();
            oStream.write(parameterAsBytes);
            oStream.flush();

            if (allowResponse) {
                result = "";
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                }
                in.close();
            }
            oStream.close();
        } catch (Exception e) {
            result = e.toString();
            e.printStackTrace();
        }

        return result;
    }
    
    public static String getData(String link) {
		String result = "";
		try {
			URL kfin = new URL(link);
			URLConnection conn = kfin.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				result += inputLine;
			in.close();
		} catch (Exception e) {
			result = e.toString();
			e.printStackTrace();
		}

		return result;
	}

    protected String generateRandom(int lines) {
        String result = "";
        Random r = new Random();
        for (int i = 0; i < lines; ++i) {
            result += i + "\t" + r.nextFloat() * 100 + "\t" + r.nextFloat() * 100;
        }

        return result;
    }
}
