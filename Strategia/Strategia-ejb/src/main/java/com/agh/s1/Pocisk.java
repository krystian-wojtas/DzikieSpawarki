/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agh.s1;

/**
 * Symulacja ruchu pocisku (rzut ukośny) z zadanej wysokości z oporem powietrza.
 * Jest możliwość zatrzymywania i wznawiania. Obliczenia wykonywane w osobnym watku,
 * rozpoczecie przy pomocy metody wystrzel, zatrzymanie poprzez stop, ewentualne
 * wznowienie przy pomocy start.
 * 
 * Wykorzystane wzory z wikipedii:
 * http://en.wikipedia.org/wiki/Trajectory_of_a_projectile
 */

public class Pocisk implements Runnable
{
        //<editor-fold defaultstate="collapsed" desc="Zmienne">
    
        /**
         * Parametry wykorzystywane do obliczen.
         * 
         * vx0, vy0 to poczatkowe wartosci skladowe predkosci
         * t oznacza czas obecny, t0 czas calego ruchu
         * vPocz, katPocz, yPocz oznaczaja wartosci poczatkowe
         * odpowiednio predkosci (calkowitej), kata (w radianach) i wysokosci 
         * wystrzelenia pocisku.
         * dt okresla krok czasowy
         * steps okresla ilosc krokow
         * wyniki - string zawierajace wyniki
         * 
         * Zmienne pomocnicze:
         * k - opor powietrza
         * m - masa pocisu
         * yMax - zapamietywanie maksymalnej wysokosci
         * ziemia - czy ziemia zostala osiagnieta
         * zatrzymaj - okresla czy obliczenia maja byc zatrzymane
         * watekObliczeniowy - watek w ktorym maja byc wykonywane obliczenia, null
         *            przy braku lub zawieszeniu
         * dokladnosc - dokladnosc uznania ze juz uderzylo o ziemie
         * 
         * stale:
         * g - sila grawitacji
         */
	double vx0, vy0;
	double vPocz, katPocz, yPocz;
	double t, t0;
	double dt;
        
	double yMax;
	static final double g = -9.81;
	double k = 0.0;
	double m = 1.0;
        double dokladnosc = Math.pow(10, -8);
	boolean ziemia;
	boolean zatrzymaj;
        boolean zakonczone = false;
        int kroki;
        String wyniki = "";
        
	Thread watekObliczeniowy;
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Konstruktory">
        /**
         * Konstruktor, domyslnie jest wylaczona mozliwosc wykonywania obliczen.
         * 
         * Krok czasowy = 0.08, kat 45 stopni (przechowywany w radianach), 
         * predkosc poczatkowa 30, wysokosc 0, opor 0
         */
	public Pocisk() 
        {
		ziemia = true;
		zatrzymaj = true;
		vPocz = 30.0;
		katPocz = Math.PI / 4;
                yPocz = 0;
                k = 0;
                dt = 0.08;
	}
        
         /**
         * Konstruktor, domyslnie jest wylaczona mozliwosc wykonywania obliczen.
         * 
         * @param v0 Predkosc poczatkowa
         * @param h0 Wysokosc poczatkowa
         * @param kat Kat poczatkowy
         * @param waga Waga pocisku
         * @param opor Opor powietrza (0-1)
         * @param ileKrokow Na ile krokow podzielic caly ruch
         * 
         * @throws IllegalArgumentException w przypadku blednych danych wyrzuca 
         *           blad o tresci "Niepoprawne dane wejsciowe!"
         */
	public Pocisk(double v0, double h0, double kat, double waga, 
                double opor, int ileKrokow) 
        {
                if (!sprawdz(v0, h0, kat, waga, opor, 0, ileKrokow))
                    throw new IllegalArgumentException("Nieporawne dane wejsciowe!");
		ziemia = true;
		zatrzymaj = true;
		setVPocz(v0);
                setYPocz(h0);
		setKatPocz(kat % 360);
                setWaga(waga);
                setOpor(opor);
                setIleKrokow(ileKrokow);
	}
        
        
         /**
         * Konstruktor, domyslnie jest wylaczona mozliwosc wykonywania obliczen.
         * @param v0 Predkosc poczatkowa
         * @param h0 Wysokosc poczatkowa
         * @param kat Kat poczatkowy
         * @param waga Masa pocisku
         * @param opor Wartosc oporu powietrza (0-1)
         * @param krok Krok czasowy
         * 
         * @throws IllegalArgumentException w przypadku blednych danych wyrzuca 
         *           blad o tresci "Niepoprawne dane wejsciowe!"
         */
        public Pocisk(double v0, double h0, double kat, double waga, 
                double opor, double krok) 
        {
                if (!sprawdz(v0, h0, kat, waga, opor, krok, 0))
                    throw new IllegalArgumentException("Nieporawne dane wejsciowe!");
		ziemia = true;
		zatrzymaj = true;
		setVPocz(v0);
                setYPocz(h0);
		setKatPocz(kat % 360);
                setWaga(waga);
                setOpor(opor);
                setKrokCzasowy(krok);
	}
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Akcesory ustawiajace parametry">
        
        /**
         * Zwracanie uzyskanych wynikow.
         * 
         * @return Wyniki uzyskane z symulacji (tylko calosciowe)
         */
        public String getWyniki()
        {
            return wyniki;
        }
        
        /**
         * Ustawianie predkosci poczatkowej.
         * @param dVal wartosc predkosci 
         */
        public final void setVPocz(double dVal)
	{
		if (dVal>0) vPocz = dVal;
	}

        /**
         * Ustawianie kata poczatkowego.
         * 
         * @param dVal wartosc kata poczatkowego w stopniach
         */
	public final void setKatPocz(double dVal)
	{
		if (dVal > 0 && dVal <= 90) katPocz = dVal * Math.PI / 180;
	}

        /**
         * Ustawianie masy pocisku.
         * 
         * @param dVal wartosc masy
         */
	public final void setWaga(double dVal)
	{
		if (dVal > 0.1) m = dVal;
	}

        /**
         * Ustawianie wartosci oporu powietrza.
         * 
         * @param dVal wartosc oporu (parametr k)
         */
	public final void setOpor(double dVal)
	{
		if (dVal >= 0.0) k = dVal;
	}
        
        /**
         * Ustawiania wysokosci poczatkowej.
         * 
         * @param init wysokosc poczatkowa
         */
        public final void setYPocz(double init)
        {
            yPocz = init;
        }

        /**
         * Ustawienie ilosci krokow na calosc lotu, nadpisuje ewentualnie ustawienie przez {@see #setKrokCzasowy(double)}.
         * 
         * @param steps ilosc krokow
         */
        public final void setIleKrokow(int steps)
        {
            kroki = steps;
        }
        
        /**
         * Ustawienie kroku czasowego, nadpisuje ewentualnie ustawienie przez {@see #setIleKrokow(int)}.
         * 
         * @param step wartosc kroku czasowego
         */
        public final void setKrokCzasowy(double step)
        {
            if (step > 0) dt = step;
        }
        
        //</editor-fold>
        
        public final boolean Koniec()
        {
            return ziemia;
        }
        //<editor-fold defaultstate="collapsed" desc="Elementy Runnable">
        /**
         * Wykonywanie obliczen przy pomocy {@see #calcPosition(double, double, 
         * double, double, double, double, double)}, dopoki nie jest wywolane
         * stop lub uderzy o ziemie.
         */
    @Override
        public void run() 
        {

                StringBuilder result = new StringBuilder();
                //result.append(String.format("%.4f\t%.4f\t%.4f\t%.4f\t%.4f\t%.4f\n",
                //        vPocz, yPocz, katPocz, m, k, dt));

                while (!zatrzymaj && !ziemia)
                {
                    double[] wynik = calcPosition(k/m, -g/vy0, t, vx0, vy0, yPocz);
                    if (wynik[1] > yMax) yMax = wynik[1];
                    //if (Math.abs(wynik[1]) < dokladnosc) ziemia = true;
                    if (wynik[1] < 0 || wynik[0] < 0) ziemia = true;
                    if (wynik[1] < 0) wynik[1] = 0;
                    
                    if (ziemia)
                    {
                        //result.append(String.format("%.4f\t%.4f\t%.4f\n", t0, wynik[0], 0f));
                        result.append(String.format("%.4f\t%.4f\n", wynik[0], 0f));
                    }
                    else
                    {

                        //result.append(String.format("%.4f\t%.4f\t%.4f\n", t, wynik[0], wynik[1]));
                        result.append(String.format("%.4f\t%.4f\n", wynik[0], wynik[1]));

                    }
                    
                    t = t + dt;
                }

                wyniki = result.toString();
                zakonczone = true;

                watekObliczeniowy = null;
        }
        
        /**
         * Wznowienie ewentualnych obliczen zatrzymanych poprzez {@see #stop()}.
         */
	public void start() 
        {
		if ((watekObliczeniowy == null) && (ziemia == false))
                {
			zatrzymaj = false;
			watekObliczeniowy = new Thread(this);
			watekObliczeniowy.start();
		}
	}
        
        /**
         * Wstrzymywanie obliczen, watek po chwili zostanie zatrzymwany, ponowne
         * uruchomienie poprzez {@see #start()}.
         */
	public void stop() { zatrzymaj = true; }
        //</editor-fold>
        
        /**
         * Metoda obliczen wedlug wzorow z wikipedii
         * @param kappa Wartosc k/m
         * @param gamma Wartosc g/predkosc poczatkowa pionowa
         * @param t Czas dla ktorego maja byc obliczenia
         * @param vx0 Predkosc pozioma poczatkowa
         * @param vyPocz Predkosc pionowa poczatkowa
         * @param yStart Wysokosc poczatkowa
         * @return Tablica z odpowiednio pozycja x, y, predkosc X, predkosc Y
         */
        public static double[] calcPosition(double kappa, double gamma, double t,
                double vx0, double vyPocz, double yStart)
        {
                double gk, ekt;
                double[] wynik = new double[4];
                gk = gamma / kappa;

                if (kappa > 0.0000001) 
                {
                        ekt = Math.exp(-kappa * t);
                        wynik[3] = vyPocz * ((1 + gk) * ekt - gk);
                        wynik[1] = vyPocz * ((1 + gk) * (1 - ekt) / kappa - gk * t) + yStart;
                        wynik[2] = vx0 * ekt;
                        wynik[0] = vx0 * (1 - ekt) / kappa;
                } else 
                {
                        wynik[3] = vyPocz * (1 - gamma * t);
                        wynik[1] = vyPocz * (t - 0.5 * gamma * t * t) + yStart;
                        wynik[2] = vx0;
                        wynik[0] = vx0 * t;
                }
                return wynik;
        }


	/**
         * Rozkaz wystrzelenie (rozpoczecia) obliczen dla aktualnych danych, wykonuje
         * obliczenia pomocnicze.
         */
	public void wystrzel() 
        {
		//Gdyby jakis pocisk lecial to wpierw go zatrzymujemy,
                //potem odpalamy od nowa
		if (watekObliczeniowy != null) 
                {
			zatrzymaj = true;
			try
                        {
				watekObliczeniowy.join(1000);
			} 
                        catch (Exception e)
                        {
                            wyniki = "Błąd uruchomienia:\n";
                            wyniki += e.getMessage();
                        }
		}
                
		if (watekObliczeniowy == null) 
                {
			ziemia = false;
			
			vx0 = vPocz*Math.cos(katPocz);
			vy0 = vPocz*Math.sin(katPocz);

			t = 0;
			t0 = (vy0 + Math.sqrt(vy0*vy0 + 2.0 * yPocz * Math.abs(g))) / Math.abs(g);

			yMax = 0;
                        if (kroki > 0)dt = t0/kroki;

			zatrzymaj = false;
			watekObliczeniowy = new Thread(this);

			watekObliczeniowy.start();
		}
	}
        
        /**
        * Sprawdzenie poprawnosci danych poczatkowych.
        * Waga musi byc dodatnia. Opor, predkosc poczatkowa, wysokosc poczatkowa
        * nie moga byc ujemne. Kat poczatkowy (w stopniach) musi byc 
        * w przedziale (0, 90)
        * 
        * @return True jak poprawne, inaczej false
        */
        public static boolean sprawdz(double v0, double h0, double kat, double waga, 
                double opor, double krok, int ileKrokow)
        {
            if (waga <= 0) return false;
            if (krok <= Math.pow(10, -8) && ileKrokow <= 0) return false;
            if (opor < 0) return false;
            if (v0 < 0) return false;
            if (h0 < 0) return false;
            if (kat < 0 || kat > 90) return false;
            
            
            return true;
        }


        

}
