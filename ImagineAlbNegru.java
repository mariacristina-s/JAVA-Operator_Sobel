package proiect2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//clasa ImagineAlbNegru e derivata din clasa ImagineColor
public class ImagineAlbNegru extends ImagineColor {
	protected int[][] grey;
	
	// metoda constructor fara param extinsa din clasa ImagineColor
	public ImagineAlbNegru() {
		super();
		Grey();
	}
	
	// metoda constructor cu param String extinsa din clasa ImagineColor
	public ImagineAlbNegru(String argument){
		super(argument);
		Grey();
	}
	
	// metoda constructor cu param File extinsa din clasa ImagineColor
	public ImagineAlbNegru(File argument){
		super(argument);
		Grey();
	}
	
	// metoda constructor cu param BufferedImage extinsa din clasa ImagineColor
	ImagineAlbNegru(BufferedImage argument){
		super(argument);
		Grey();
	}
	
	void Grey() {
		// declaram matricea grey de tip int cu width x si height y
		grey = new int[x][y];
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				// convertim sa fie greyscale
				int r = (rgb[i][j] >> redShift) & 0xff;
		        int g = (rgb[i][j] >> greenShift) & 0xff;
		        int b = (rgb[j][j] >> blueShift) & 0xff;
		        
		        // extragem greyscale folosindu-ne de coeficientii de luminiscenta liniara ai fiecarei
		        // culori in parte
				grey[i][j] = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
			}
		}
	}
	
	// metoda ce returneaza atributul grey
	public int[][] getGrey() {
		return grey;
	}
	
	// metoda ce salveaza imaginea rezultata in urma aplicarii operatorului Sobel pe img dorita
	public void saveSobelGri(String... args) {
		// declarare timpul de start
		float timp1 = System.nanoTime();
		
		for(String arg : args) {
			try {
				// path va lua valoarea numelui path-ului fisierului (de forma d:\..)
				// care este unique si absolute
				// altfel va opri programul din cauza aparitiei unei erori
				path = fisier.getCanonicalPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (arg.length()>0) {
				// aflam noul fisier in care vom salva imaginea modificata
				newFisier = new File(currentDir + legChar + arg + path.substring(path.lastIndexOf('.'), path.length()));
			} else {
				// in cazul in care nu e specificat un alt nume, imaginile create vor fi numite "sobelGrey(nume sursa).extensie"
				newFisier = new File("sobelGrey" + path);
			}
	        try {
	        	// verificam daca putem operatorul sobel a fost aplicat in mod corect
				// in caz contrar returnam o eroare si un mesaj ca imaginea nu a putut fi salvata
				ImageIO.write(sobelGrey(), "png", newFisier);
			} catch (IOException e) {
				System.out.println("Imaginea nu a putut fi salvata.");
				e.printStackTrace();
			}
		}
		
		// declarare timp stop
        float timp2 = System.nanoTime();
        System.out.println("Salvarea Sobel Grey a durat: " + ((timp2 - timp1) / 1000000) + " nanosecunde");
	}
	
	public BufferedImage sobelGrey() {
		// declarare timp start
		float timp1 = System.nanoTime();
		
		System.out.println("Se aplica operatorul Sobel Grey pe " + path);
		
		// declarare imagine aux de tip BufferedImage ca imaginea dorita
		BufferedImage imageaux = image;
		
		// algoritmul este similar ca cel din clasa ImagineColor
		// doar Gx si Gy se vor aplica pe grey in loc de r
        int[][] edgeColors = new int[x][y];
        int maxGradient = -1;
		for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                int temp00 = (grey[i - 1][j - 1]);
                int temp01 = (grey[i - 1][j]);
                int temp02 = (grey[i - 1][j + 1]);

                int temp10 = (grey[i][j - 1]);
                int temp11 = (grey[i][j]);
                int temp12 = (grey[i][j + 1]);

                int temp20 = (grey[i + 1][j - 1]);
                int temp21 = (grey[i + 1][j]);
                int temp22 = (grey[i + 1][j + 1]);

                int gx =  ((-1 * temp00) + (0 * temp01) + (1 * temp02)) 
                        + ((-2 * temp10) + (0 * temp11) + (2 * temp12))
                        + ((-1 * temp20) + (0 * temp21) + (1 * temp22));

                int gy =  ((-1 * temp00) + (-2 * temp01) + (-1 * temp02))
                        + ((0 * temp10) + (0 * temp11) + (0 * temp12))
                        + ((1 * temp20) + (2 * temp21) + (1 * temp22));

                double gval = Math.sqrt((gx * gx) + (gy * gy));
                int g = (int) gval;

                if(maxGradient < g) {
                    maxGradient = g;
                }

                edgeColors[i][j] = g;
            }
        }
		
		double scale = 255.0 / maxGradient;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << redShift) | (edgeColor << greenShift) | (edgeColor << blueShift);

                imageaux.setRGB(i, j, edgeColor);
            }
        }
        
        // declarare timp oprire
        float timp2 = System.nanoTime();
        
        System.out.println("Algoritmul Sobel Grey a durat: " + ((timp2 - timp1) / 1000000) + " nanosecunde");
        
		return imageaux;
	}
}
