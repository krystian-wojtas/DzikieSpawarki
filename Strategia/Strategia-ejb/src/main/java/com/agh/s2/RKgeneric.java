package com.agh.s2;

/**
 * Klasa implementująca metodę RK.
 * @author Eva
 */

public class RKgeneric {
    
    public static ResultRK ode(double tp,double dt,int N,double initState[],FunctionRK frk)
    {
     double k1[],k2[],k3[],k4[];
     double t=tp;    
     final int size=initState.length;
     ResultRK rk=new ResultRK(N,size); // rezultaty obliczen         
     double w[]=new double[size];//wektor pomocniczy
     int i,j;
        
                //frk.dt=dt;
                rk.t[0]=tp;
                System.arraycopy(initState,0,rk.res[0],0,size);
     
                for(i=0;i<N-1;i++){            
                    
                    k1=frk.function(t,rk.res[i]);
                    for(j=0;j<size;j++) {
                        k1[j]=dt*k1[j];
                        w[j]=rk.res[i][j]+k1[j]/2.0f;
                    }
                    
                    k2=frk.function(t,w);
                    for(j=0;j<size;j++){
                        k2[j]=dt*k2[j];
                        w[j]=rk.res[i][j]+k2[j]/2.0f;
                    }
                    
                    k3=frk.function(t,w);
                    for(j=0;j<size;j++){
                        k3[j]=dt*k3[j];
                        w[j]=rk.res[i][j]+k3[j];
                    }
                    
                    k4=frk.function(t,w);                    
                    for(j=0;j<size;j++){
                        k4[j]=dt*k4[j];
                        rk.res[i+1][j]=rk.res[i][j]+(k1[j]+2*(k2[j]+k3[j])+k4[j])/6.0f;
                    }  
                    
                    t+=dt;
                    rk.t[i+1]=t;                    
                }        
    return rk; 
    }
}        
