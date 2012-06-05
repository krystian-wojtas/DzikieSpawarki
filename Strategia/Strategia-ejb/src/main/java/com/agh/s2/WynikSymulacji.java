package com.agh.s2;

/**
 * Klasa reprezentująca wyniki symulacji w odpowiednim formacie.
 * Położenia X oraz Y wyliczane na podstawia długości liny oraz wychylenia.
 * @author Evcia
 */

public class WynikSymulacji 
{

	private double X, Y, Kat, Czas;
	
	public WynikSymulacji(double alfa, double dlugosc, double czas)
	{
		Czas = czas;
		Kat = alfa;
		X = dlugosc * Math.sin(alfa);
		Y = dlugosc * Math.cos(alfa);		
	}
        
        public WynikSymulacji(double x, double v, double dlugosc, double czas)
        {
                Czas = czas;
		X = x;
                Kat = Math.asin(X/dlugosc);
		Y = dlugosc * Math.cos(Kat);	
        }
	
	public String toString()
	{
            return String.format("%.4f\t%.4f\t%.4f\t%.4f\n", Czas, X, Y, Kat);
	}
	
	public double getX() { return X; }
	public void setX(double x) { X = x; }
	public double getY() { return Y; }
	public void setY(double y) { Y = y; }
	public double getKat() { return Kat; }
	public void setKat(double kat) { Kat = kat; }
	public double getCzas() { return Czas; }
	public void setCzas(double czas) { Czas = czas; }	//sciana
}
