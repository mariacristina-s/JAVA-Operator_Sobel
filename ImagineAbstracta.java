package proiect2;

import java.awt.image.BufferedImage;
import java.io.File;

// clasa ce are doar metode abstracte
// si este utilizata pt a implementa interfata aplicatiei
public abstract class ImagineAbstracta implements InterfataImagine {
	// declarare pt obtinerea directorului curent
	final String currentDir = System.getProperty("user.dir");
	
	protected int x; // width
	protected int y; // height
	protected String path; // path
	protected File fisier; // fisierul
	protected BufferedImage image; // imaginea
	
	// metoda ce initializeaza variabila de tip String path cu valoarea String-ului dat ca argument
	public void setPath(String argument) {
		path = argument;
	}
	
	// metoda ce initializeaza variabila de tip File fisier cu valoarea File-ului dat ca argument
	public void setFile(File argument) {
		fisier = argument;	
	}
	
	public abstract void setImage(BufferedImage arg);
	
	protected abstract void setHeight();

	protected abstract void setWidth();
	
	// functie ce returneaza width
	public int getWidth() {
		return x;
	}
	
	// functie ce returneaza height
	public int getHeight() {
		return y;
	}
	
	// functie ce returneaza path-ul
	public String getPath() {
		return path;
	}
	
	public abstract BufferedImage sobel();
}