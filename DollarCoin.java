package com.farida;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class DollarCoin {
	GImage coinImage;

	public DollarCoin(String imageName) {
		coinImage = new GImage(imageName);
	}

	public double getX() {
		return coinImage.getX();
	}

	public double getY() {
		return coinImage.getY();

	}

	public GRectangle getBounds() {
		return coinImage.getBounds();

	}
	

}