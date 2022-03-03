package proiect2;

import java.awt.image.BufferedImage;

public interface InterfataImagine {
	// declaratii pt shiftarile si fctia sobel() din clasa ImagineColor
	final int redShift = 16;
	final int greenShift = 8;
	final int blueShift = 0;
	final String legChar = "\\";
	public BufferedImage sobel();
}
