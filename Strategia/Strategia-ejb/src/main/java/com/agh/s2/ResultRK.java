package com.agh.s2;

/**
 * Klasa przechowująca wyniki z metody RK.
 */
public class ResultRK {
    
    public double t[];
    public double res[][];
    
    public ResultRK(){}
    
    public ResultRK(int rows,int columns)
    {
        res=new double[rows][columns];  
        t=new double [rows];
    }
    
    public double [] getColumn(int c)
    {
    int i;
    double col[]=new double [res.length];
            for(i=0;i<res.length;i++)
                col[i]=res[i][c];
     return col;        
    }
}
