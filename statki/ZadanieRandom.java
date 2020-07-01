//PROSTA GRA W STATKI
//PLANSZ 4x4 - 16 POL
// PROGRAM MA 1 STATEK ZAJMUJACY 2 POLA - odpowiednia strategia umozliwia szybka lokalizacje statku przeciwnika
//UZYTKOWNIK MA 1 STATEK ZAJMUJACY 1 POLE


import java.util.*;

public class ZadanieRandom {

	public static void main(String[] param)  throws InterruptedException
	{
		
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		Wspolrzedne zbite_pole = new Wspolrzedne(-1,-1);
		int pion, poziom;
		int zbite_pola_playera=0;
		int zbite_pola_computera=0;
		Plansza player = new Plansza();
		Plansza computer = new Plansza();
		
		//wspolrzedne do ktorych celuje komputer - lista wspolrzednych 16 pol
		ArrayList<Wspolrzedne> lista = new ArrayList<Wspolrzedne>(); int pozostale=15;
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				lista.add(new Wspolrzedne(i, j));
		
		//LOSOWANIE POLOZENIA STATKU KOMPUTERA
		ArrayList<Wspolrzedne> lista_statek_computer = new ArrayList<Wspolrzedne>();
		
		Wspolrzedne statek_computer1;
		Wspolrzedne statek_computer2;
		
		if(rand.nextInt(1)==1)//jezeli polozenie pionowe
		{
			pion=rand.nextInt(2);
			poziom=rand.nextInt(3);
			statek_computer1 = new Wspolrzedne(poziom, pion);
			statek_computer2 = new Wspolrzedne(poziom, pion+1);
		}
		else
		{//jezeli polozenie statku poziome
			pion=rand.nextInt(3);
			poziom=rand.nextInt(2);
			statek_computer1 = new Wspolrzedne(poziom, pion);
			statek_computer2 = new Wspolrzedne(poziom, pion+1);
		}
		lista_statek_computer.add(statek_computer1);
		lista_statek_computer.add(statek_computer2);
		
		
		//LOSOWANIE STATKU GRACZA
				ArrayList<Wspolrzedne> lista_statek_player = new ArrayList<Wspolrzedne>();
				
				Wspolrzedne statek_player1;
				pion=rand.nextInt(3);
				poziom=rand.nextInt(3);
				statek_player1 = new Wspolrzedne(poziom, pion);
				lista_statek_player.add(statek_player1);
				
		//WLASCIWA ROZGRYWKA		
		do {
			clearConsole();
			System.out.println("\n\n\n\n\n----------------------------------------------");
			System.out.println("Plansza gracza, do ktorej strzela komputer.");
			player.drukuj();
			System.out.println("\n\nPlansza komputera, do ktorej strzelasz");
			computer.drukuj();
			System.out.println("\nX-pudlo, *-trafienie\nPodaj wspolrzedne do jakich strzelasz(np. b2 i nacisnij enter):");
			
			String wczytaj_wspolrzedne = sc.nextLine();
			int strzal_poziom = ((int) wczytaj_wspolrzedne.charAt(0))-97;
			int strzal_pion = ((int) wczytaj_wspolrzedne.charAt(1))-48-1;
			
			if(strzal_poziom == statek_computer1.getx() && strzal_pion == statek_computer1.gety() )
			{
				//zabezpieczenie przed oszukiwaniem - nie mozna 2 razy zbic tego samego pola
				if(strzal_poziom == zbite_pole.getx() && strzal_pion == zbite_pole.gety() ) continue;
				zbite_pole = new Wspolrzedne(strzal_poziom,strzal_pion);
				//zbijanie
				zbite_pola_computera++;
				computer.tablica[strzal_poziom][strzal_pion] = "*";
								
			}
			else if(strzal_poziom == statek_computer2.getx() && strzal_pion == statek_computer2.gety() )
			{
				//zabezpieczenie przed oszukiwaniem - nie mozna 2 razy zbic tego samego pola
				if(strzal_poziom == zbite_pole.getx() && strzal_pion == zbite_pole.gety() ) continue;
				zbite_pole = new Wspolrzedne(strzal_poziom,strzal_pion);
				//zbijanie
				zbite_pola_computera++;
				computer.tablica[strzal_poziom][strzal_pion] = "*";
				
			}
			else
			{
				computer.tablica[strzal_poziom][strzal_pion] = "x";
				
			}
			
			//strzal kopmutera
			int los = rand.nextInt(pozostale);
			pozostale--;
			strzal_poziom = lista.get(los).getx();
			strzal_pion = lista.get(los).gety();
			lista.remove(los);
			if(strzal_poziom == statek_player1.getx() && strzal_pion == statek_player1.gety() )
			{
				zbite_pola_playera++;
				player.tablica[strzal_poziom][strzal_pion] = "*";
			}
			else
			{
				player.tablica[strzal_poziom][strzal_pion] = "x";
				
			}
			
				
		}while(zbite_pola_computera !=2 && zbite_pola_playera !=1);
		
		//wydruk koncowych wynikow
		clearConsole();
		System.out.println("\n\n\n\n\n----------------------------------------------");
		System.out.println("Krajobraz po bitwie:\n Zbite statki gracza");
		player.drukuj();
		System.out.println("\n\nZbite statki komputera");
		computer.drukuj();
		if(zbite_pola_computera==2 && zbite_pola_playera==1)
			System.out.println("\n\nRemis.");
		else if(zbite_pola_playera==1) 
			System.out.println("\n\nKomputer wygral.");
		else 
			System.out.println("\n\nBrawo wygrales!!!");
		System.out.println("Koniec programu, koniec gry.");
		
	}
	
	public final static void clearConsole()
	{
	    try{
	        final String os = System.getProperty("os.name");
	
	        if (os.contains("Windows")){
	            Runtime.getRuntime().exec("cls");
	        }
	        else{
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e){
	        //  na wszelki wypadek
	    }
	}
}




class Plansza
{
	String tablica[][] = new String[4][4];
	//konstruktor
	Plansza() {
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				tablica[i][j]=" ";
			}
		}
	}
	
	//drukowanie planszy komputera i gracza
	void drukuj() {
		char znak='a';
			System.out.println("  _______");
			for(int i=0; i<4; i++)
			{
				znak = (char) (i+97);
				System.out.print(znak+"|");
				for(int j=0; j<4; j++)
				{
					System.out.print(tablica[i][j]+"|");
				}
				System.out.print("\n");
			}
			System.out.println("  1 2 3 4");
		}
}

class Wspolrzedne
{
	private int x;
	private int y;
	Wspolrzedne(int x, int y){
		this.x=x;
		this.y=y;
	}
	int getx(){return this.x;}
	int gety() {return this.y;}
}