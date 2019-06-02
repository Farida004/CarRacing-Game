package com.farida;

import acm.graphics.GImage;

public class EnemyCar {

	GImage enemyCarImage;
	private boolean carToLeft = true;

	public EnemyCar(String imageName) {
		enemyCarImage = new GImage(imageName);
	}

	/**
	 * This method moves enemy car from one corner of the screen to the opposite
	 * during the game.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 */
	public void moveEnemy(double x, double y, double width) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				if (carToLeft) {
					enemyCarImage.move(-x, y);
					if (enemyCarImage.getX() <= 0) {
						carToLeft = false;
					}
				} else {
					enemyCarImage.move(x, y);
					if (enemyCarImage.getX() >= width - Constants.CAR_WIDTH) {
						carToLeft = true;
					}
				}
			}
		});
		thread.start();
	}

	public int getY() {
		return (int) enemyCarImage.getY();

	}

	public int getX() {
		return (int) enemyCarImage.getX();
	}

}