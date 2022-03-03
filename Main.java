package proiect22;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import proiect2.*;

public class Main {
	
	    // declaratii pentru numele fisierului
		static String mainfilename = "powerpuff.bmp"; // va fi suprascris ulterior
		
		// declararea timpilor de executie
		static long timp1;
		static long timp2;
		
		// citim si scriem de la tastatura path-ul, numele fisierului si extensia pentru
		// care dorim sa aplicam operatorul Sobel
		public static void main(String[] args) {
			BufferedReader buffr = new BufferedReader(new InputStreamReader(System.in));
		    System.out.print("Introduceti path-ul, numele fisierului si extensia sa: ");
		    // ex. se va introduce de la tastatura src\proiect2\numepoza.bmp
		    try {
		    	// fisierul nostru va lua valoarea introdusa de la tastatura de utilizator,
		    	// care va fi considerat un string pe care il citim,
		    	// in caz contrar va fi generata o eroare si programul se va opri
				String str = buffr.readLine();
				mainfilename = str;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	    
		// apelul a 3 functii pentru aplicarea operatorului Sobel pe imaginea dorita
		// si pt a verifica functionalitatea tuturor metodelor si claselor
		//functie1();
		functie2();
		//functie3();
	}
	
	// intai trebuie facuta conversia imaginii pt a fi greyscale
	// iar de abia dupa aceea aplicam operatorul sobel pe imaginea rezultata
		
	/*public static void functie1() {
		// declaratii pentru 2 obiecte de tip imagine si imagineGri
		ImagineColor imagine = new ImagineColor();
		ImagineAlbNegru imagineGri = new ImagineAlbNegru();
		
		// calculul timpului pentru executia functiei saveSobel pe imaginea dorita
		long timp1 = System.currentTimeMillis();
		timp1 = System.currentTimeMillis();
		
		// salvarea a 3 imagini pe care s a aplicat operatorul Sobel
		imagine.saveSobel("load11", "load12", "load13");
		
		timp2 = System.currentTimeMillis();
		System.out.println("Load1 a durat: " + (timp2 - timp1) + " milisecunde");
		
		// calculul timpului pentru executia functiei saveSobelGri pt imaginea gri a 
		// imaginii initiale
		timp1 = System.currentTimeMillis();
		imagineGri.saveSobelGri("load14");
		timp2 = System.currentTimeMillis();
		// gri
		System.out.println("Load14 a durat: " + (timp2 - timp1) + " milisecunde");
	}*/
	
	public static void functie2() {
		// declaratii pt 2 obiecte de tip imagine color si imagine alb-negru
		ImagineColor imagine = new ImagineColor(mainfilename);
		ImagineAlbNegru imagineGri = new ImagineAlbNegru(mainfilename);
		
		// calculul timpului pentru executia functiei saveSobel pe imaginea dorita
		timp1 = System.currentTimeMillis();
		imagine.saveSobel("Poza1");
		timp2 = System.currentTimeMillis();
		System.out.println("! Crearea pozei 1 a durat: " + (timp2 - timp1) + " milisecunde.");
		
		// calculul timpului pentru executia functiei saveSobel pe imaginea dorita
		timp1 = System.currentTimeMillis();
		imagineGri.saveSobelGri("Poza2");
		timp2 = System.currentTimeMillis();
		System.out.println("! Crearea pozei 2 a durat: " + (timp2 - timp1) + " milisecunde.");
	}
	
	/*public static void functie3() {
		// declararea fisierului din care dorim sa luam imaginea
		File fisier = new File("D:\\Anul 3, Semestrul 1\\Aplicatii Web Java\\proiect2\\" + mainfilename);
		
		// declararea a 2 obiecte de tip imagine color si imagine alb-negru
		ImagineColor imagine = new ImagineColor(fisier);
		ImagineAlbNegru imagineGri = new ImagineAlbNegru(fisier);
		
		// calculul timpului pentru executia functiei saveSobel pe imaginea dorita
		timp1 = System.currentTimeMillis();
		imagine.saveSobel("functie3");
		timp2 = System.currentTimeMillis();
		System.out.println("Load3 a durat: " + (timp2 - timp1) + " milisecunde");
		
		// calculul timpului pentru executia functiei saveSobelGri pe imaginea dorita
		timp1 = System.currentTimeMillis();
		imagineGri.saveSobelGri("load31grey","load32grey","load33grey");
		timp2 = System.currentTimeMillis();
		System.out.println("Load3grey a durat: " + (timp2 - timp1) + " milisecunde");
	}*/
}

