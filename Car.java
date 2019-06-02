package com.farida;
import acm.graphics.GImage;

public class Car {

	GImage carImage;

	public Car(String imageName) {
		carImage = new GImage(imageName);
	}

	public void moveLeft(double x, double y) {

		carImage.move(-x, y);

	}

	public void moveRight(double x, double y) {
		carImage.move(x, y);
	}

	public void moveUp(double x, double y) {
		carImage.move(x, -y);
	}

	public void moveDown(double x, double y) {
		carImage.move(x, y);
	}

	public double getX() {
		return carImage.getX();
	}

	public double getY() {
		return carImage.getY();

	}

}
