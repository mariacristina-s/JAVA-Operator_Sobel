package proiect2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// clasa ImagineColor e derivata din clasa ImagineAbstracta
public class ImagineColor extends ImagineAbstracta {
	// declaratii 
	protected int[][] rgb; // rgb
	protected int[][] r; // red
	protected int[][] g; // green
	protected int[][] b; // blue
	
	protected String newPath; // path
	protected File newFisier; // fisier
	
	// metoda constructor fara parametrii
	public ImagineColor() {
		// setam path-ul pentru poza dorita
		// luam path-ul din package-ul pt proiect22, unde se afla si main-ul
		setPath("src\\proiect22\\powerpuff.bmp");
		// setam fisierul din directorul curent + \\ + path
		setFile(new File(currentDir + legChar + path));
		
		// citim imaginea din fisier
		// altfel oprim programul din cauza aparitiei unei erori
		try {
			setImage(ImageIO.read(fisier));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// metoda constructor cu parametrii unde argumentul primit este un String
	// prin care se transmite path-ul imaginii
	public ImagineColor(String argument) {
		// setam path-ul dat ca parametru ca argument
		setPath(argument);
		// setam fisierul din directorul curent + \\ + path
		setFile(new File(currentDir + legChar + path));
		
		// citim imaginea din fisier
		// altfel oprim programul din cauza aparitiei unei erori
		try {
			setImage(ImageIO.read(fisier));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// metoda constructor cu parametrii unde argumentul primit este un File
	// prin care se transmite un fisier
	public ImagineColor(File argument) {
		// setam fisierul dat ca argument
		setFile(argument);
		
		// citim imaginea din fisier
		// altfel oprim programul din cauza aparitiei unei erori
		try {
			setImage(ImageIO.read(fisier));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// metoda constructor cu parametru prin care este setata imaginea primita ca argument
	ImagineColor(BufferedImage argument){
		setImage(argument);
	}
	
	// metoda public void de setare a imaginii primite ca argument
	// si setare height, width si rgb
	public void setImage(BufferedImage argument) {
		// TODO Auto-generated method stub
		image = argument;
		setHeight();
		setWidth();
		RGB();
	}
	
	// metoda care seteaza width
	protected void setWidth() {
		x = image.getWidth();
	}
	
	// metoda care seteaza height
	protected void setHeight() {
		y = image.getHeight();
	}
	
	// metoda pentru rgb
	public void RGB() {
		// declaratii
		// de aux ne folosim pentru a realiza shiftarile
		int aux;
		// rgb, r, g si b sunt matrici de width x si height y
		rgb = new int[x][y];
		r = new int[x][y];
		g = new int[x][y];
		b = new int[x][y];
		
		for (int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				// luam valoarea rgb de la coord i si j
				aux = image.getRGB(i, j);
				// realizam conversia pt greyscale
				rgb[i][j] = aux;
				r[i][j] = (aux >> redShift) & 0xff;
				g[i][j] = (aux >> greenShift) & 0xff;
				b[i][j] = (aux >> blueShift) & 0xff;
			}
		}
		
	}
	
	// metoda ce returneaza rgb
	public int[][] getRGB() {
		return rgb;
	}
	
	// metoda ce returneaza red
	public int[][] getRed() {
		return r;
	}
	
	// metoda ce returneaza green
	public int[][] getGreen() {
		return g;
	}
	
	// metoda ce returneaza blue
	public int[][] getBlue() {
		return b;
	}

	// metoda pentru salvarea imaginii dupa aplicarea operatorului Sobel
	public void saveSobel(String... args) {
		// declararea timp1 pt a calcula timpul de executie
		float timp1 = System.nanoTime();
		
		for(String arg : args) {
			
			if (arg.length() > 0) {
				try {
					// path va lua valoarea numelui path-ului fisierului (de forma d:\..)
					// care este unique si absolute
					// altfel va opri programul din cauza aparitiei unei erori
					path = fisier.getCanonicalPath();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// aflam noul fisier in care vom salva imaginea modificata
				newFisier = new File(currentDir + legChar + arg + path.substring(path.lastIndexOf('.'), path.length()));
			} else {
				// in cazul in care nu e specificat un alt nume, imaginile create vor fi numite "sobel(nume sursa).extensie"
				newFisier = new File("sobel" + path);
			}
			
			// aplicam operatorul sobel
			// in caz contrar returnam o eroare si un mesaj ca imaginea nu a putut fi salvata
	        try {
				ImageIO.write(sobel(), "png", newFisier);
			} catch (IOException e) {
				System.out.println("Imaginea nu a putut fi salvata.");
				e.printStackTrace();
			}	
		}
		// declararea timpului la finalul salvarii
        float timp2 = System.nanoTime();
        
        System.out.println("Salvarea Imaginii dupa aplicarea operatorului Sobel a durat: " + ((timp2 - timp1) / 1000000) + " milisecunde");
	}
	
	// metoda pentru aplicarea operatorului sobel
	public BufferedImage sobel() {
		// declararea timpului de start
		float timp1 = System.nanoTime();
			
		System.out.println("Se aplica Sobel pe " + path);
		
		// declararea unei imagini aux de tip BufferedImage, care ia valoarea imaginii noastre
		BufferedImage imageaux = image;
		
		// declarare matrice de width x height edgeColors de tip int
        int[][] edgeColors = new int[x][y];
        
        // declarare gradient cu o valoare initiala
        int maxGradient = -1;
        
		for (int i = 1; i < x - 1; i++) { 
            for (int j = 1; j < y - 1; j++) { 

                int temp00 = (r[i - 1][j - 1]);
                int temp01 = (r[i - 1][j]);
                int temp02 = (r[i - 1][j + 1]);

                int temp10 = (r[i][j - 1]);
                int temp11 = (r[i][j]);
                int temp12 = (r[i][j + 1]);

                int temp20 = (r[i + 1][j - 1]);
                int temp21 = (r[i + 1][j]);
                int temp22 = (r[i + 1][j + 1]);
                
                // realizam cele doua matrici Gx si Gy pt cele doua directii (vertical si orizontal)
                // prin convolutarea cu cele doua matrici ajutatoare
                // [-1 0 1; -2 0 2; -1 0 1] stanga - dreapta
                // [-1 -2 -1; 0 0 0; 1 2 1] sus - jos
                int Gx =  ((-1 * temp00) + (0 * temp01) + (1 * temp02)) 
                        + ((-2 * temp10) + (0 * temp11) + (2 * temp12))
                        + ((-1 * temp20) + (0 * temp21) + (1 * temp22));

                int Gy =  ((-1 * temp00) + (-2 * temp01) + (-1 * temp02))
                        + ((0 * temp10) + (0 * temp11) + (0 * temp12))
                        + ((1 * temp20) + (2 * temp21) + (1 * temp22));
                
                // aplicam formula G = sqrt ( Gx^2 + Gy^2) si scapam de semn prin radical
                double g_radical = Math.sqrt((Gx * Gx) + (Gy * Gy)); // pixel
                
                // g va lua valoarea lui gval
                int g = (int)g_radical;
                	
                // daca g este mai mare decat maxGradient, asta inseamna ca maxGradient
                // trebuie sa ia valoarea lui g, deoarece s-a gasit o valoare mai mare
                // pt g
                if(maxGradient < g) {
                    maxGradient = g;
                }

                edgeColors[i][j] = g;
            }
        }
		
		// normalizam gradientul si realizam conversia pt fiecare pixel
		double scale = 255.0 / maxGradient;
        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << redShift) | (edgeColor << greenShift) | (edgeColor << blueShift);
                imageaux.setRGB(i, j, edgeColor);
            }
        }
        
        // declararea timpului final dupa aplicarea operatorului sobel
        float timp2 = System.nanoTime();
        
        // calcul timp + afisarea lui
        System.out.println("Algoritmul Sobel a durat: " + (timp2 - timp1) + " milisecunde");
        
		return imageaux;
	}
	
}
