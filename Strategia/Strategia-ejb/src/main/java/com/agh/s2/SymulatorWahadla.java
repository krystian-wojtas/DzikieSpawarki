package com.agh.s2;

import java.util.*;
//import MetodaRK.*;

/**
 * Klasa reprezentująca symulator wahadła.
 * @author Evcia
 */

public class SymulatorWahadla 
{
     private double DlugoscLiny, KatPoczatkowy, CzasMaksymalny, KrokCzasowy;

     /**
      * Konstruktor klasy Symulatora wahadła
      * @param dl  Długość liny
      * @param kat Kąt początkowy
      * @param tMax  Maksymalny czas symulacji
      * @param dt Krok czasowy
      */
     public SymulatorWahadla(double dl, double kat, double tMax, double dt)
     {
         DlugoscLiny = dl;
         KatPoczatkowy = kat;
         CzasMaksymalny = tMax;
         KrokCzasowy = dt;
     }
     
     public ArrayList<WynikSymulacji> SymulacjaAnalityczna() throws Exception
     {
    	 ArrayList<WynikSymulacji> wynik = new ArrayList<WynikSymulacji>();         
         double czas = 0;

         while (czas < CzasMaksymalny)
         {
             wynik.add(WynikWChwili(czas));

             czas += KrokCzasowy;
         }

         return wynik;
     }
     
     /**
      * Metoda obliczająca położenie wahadła w czasie.
      * @return Lista wyników w formacie: krok czasowy, x, y, kąt o jaki jest wychylone wahadło.
      * @throws Exception 
      */
     public ArrayList<WynikSymulacji> SymulacjaNumeryczna() throws Exception
     {
         ResultRK  wynik = RKgeneric.ode(0, KrokCzasowy, (int)(CzasMaksymalny/KrokCzasowy), new double[] {KatPoczatkowy, 0}, new PendulumFunction(0, DlugoscLiny));
         
         return PrzeksztalcWynik(wynik);
     }
     
     /**
      * Metoda przekształcajaca wyniki otrzymane metodą RK na odpowiedni format.
      * @param wynikRK Wyniki otrzymane metodą RK.
      * @return Lista wyników w odpowiednim formacie.
      */
     public ArrayList<WynikSymulacji> PrzeksztalcWynik(ResultRK wynikRK)
     {
         ArrayList<WynikSymulacji> wynik = new ArrayList<WynikSymulacji>();
         
         double [] x = wynikRK.getColumn(0);
         double [] czas = wynikRK.t;
         
         for(int i = 0; i< wynikRK.t.length; ++i)
         {
             WynikSymulacji w = new WynikSymulacji(x[i], 0, DlugoscLiny, czas[i]);
             wynik.add(w);
         }
         
         return wynik;
     }

     /**
      * Metoda obliczająca wynik w zadanej chwili czasowej.
      * @param czas Chwila czasowa
      * @return Wynik w zadanej chwili czasowej.
      * @throws Exception 
      */
     public WynikSymulacji WynikWChwili(double czas) throws Exception
     {
         return new WynikSymulacji(KatWChwili(czas), DlugoscLiny, czas);
     }

     /**
      * Metoda obliczająca wychylenie w zadanej chwili czasowej.
      * @param czas Chwila czasowa
      * @return Wychylenie w zadanej chwili czasowej.
      * @throws Exception 
      */
     public double KatWChwili(double czas) throws Exception
     {
         if (czas < 0)
             throw new Exception("Czas nie moze byc ujemny!");

         return KatPoczatkowy * Math.cos(Math.sqrt(g / DlugoscLiny) * czas);  //
     }
     

    
	private static double g = 9.81; 
	
	public double getDlugoscLiny() { return DlugoscLiny; }
	public void setDlugoscLiny(double dlugoscLiny) { DlugoscLiny = dlugoscLiny; }
	public double getKatPoczatkowy() { return KatPoczatkowy; }
	public void setKatPoczatkowy(double katPoczatkowy) { KatPoczatkowy = katPoczatkowy; }
	public double getCzasMaksymalny() { return CzasMaksymalny; }
	public void setCzasMaksymalny(double czasMaksymalny) { CzasMaksymalny = czasMaksymalny; }
	public double getKrokCzasowy() { return KrokCzasowy; }
	public void setKrokCzasowy(double krokCzasowy) { KrokCzasowy = krokCzasowy; }
}
