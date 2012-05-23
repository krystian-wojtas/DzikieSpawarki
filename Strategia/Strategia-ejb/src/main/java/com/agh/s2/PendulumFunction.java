package com.agh.s2;

//import MetodaRK.FunctionRK;

/**
 * Klasa implementująca funkcję wahadła dla metody RK
 * @author Eva
 */
public class PendulumFunction extends FunctionRK{
    
    public PendulumFunction(double b, double L)
    {
        this.b = b;
        gl = 9.81 / L;
    }
    
    double b, gl;
  
    public double [] function(double t, double [] par)
    {
        double res[]=new double[2];
        
        //skladowa x-0wa
        res[0]=par[1];
        //skladowa v-owa
        res[1]=(-b*par[1]-gl*Math.sin(par[0]));
        
        return res;
    }
}