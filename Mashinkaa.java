package com.farida;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class Mashinkaa extends GraphicsProgram {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 700;
	Car car = new Car("car.png");
	EnemyCar enemyCar = new EnemyCar("enemyCar.png");
	private GImage road1 = new GImage("fffff.png");
	private GImage road2 = new GImage("fffff.png");
	DollarCoin dollar = new DollarCoin("dollar.png");
	private GImage car_crash = new GImage("car-crash.png");
	private GLabel scoreLabel, soulLabel, coinsLabel;
	private int score = 0;
	private int coins = 0;
	private int timer = 0;
	private int life = 3;

	public static void main(String[] args) {
		Mashinkaa app = new Mashinkaa();
		app.start();

	}

	public void run() {
		Random farida = new Random();
		int x = farida.nextInt(350);
		scaleImages();
		startGame();
		while (life >= 1) {
			moveCar();
			if (car.carImage.getBounds().intersects(enemyCar.enemyCarImage.getBounds())) {
				life--;
				remove(car.carImage);
				remove(enemyCar.enemyCarImage);
				remove(dollar.coinImage);
				add(car_crash, 0, 173);
				waitForClick();
				remove(car_crash);
				addDollar();
				add(car.carImage, x, getHeight() / 2 + 100);
				add(enemyCar.enemyCarImage, x, -300);
				createScorePanel();
				refreshSoul();
				if (life == 0) {
					createGameOver();
				}
			}
		}
	}

	/**
	 * This method scales images to appropriate dimensions which are used in
	 * this game.
	 */
	public void scaleImages() {
		road1.scale(0.5, 0.5);
		road2.scale(0.5, 0.5);
		car.carImage.scale(0.125, 0.125);
		enemyCar.enemyCarImage.scale(0.8, 0.8);
		dollar.coinImage.scale(0.1, 0.1);
	}

	/**
	 * This method initializes a game by adding road coins cars and score
	 * labels.
	 */
	private void startGame() {
		Random farida = new Random();
		int x = farida.nextInt(350);
		createWelcome();
		createRoad();
		addDollar();
		add(car.carImage, Constants.xcar, Constants.ycar);
		add(enemyCar.enemyCarImage, x, -300);
		createScorePanel();
		addKeyListeners();

	}

	/**
	 * This method creates a start game window and asks the user for a click.
	 */
	private void createWelcome() {
		StartGame game = new StartGame(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(game);
		waitForClick();
		removeAll();
	}

	/**
	 * This method adds road.
	 */
	public void createRoad() {
		add(road1, 0, 0);
		add(road2, 0, -Constants.ROAD_HEIGHT);
	}

	/**
	 * This method adds coin on random location.
	 */
	public void addDollar() {
		Random random = new Random();
		int x = random.nextInt(350);
		Random gener = new Random();
		int y = gener.nextInt(getHeight() / 2);
		add(dollar.coinImage, x, y);
	}

	/**
	 * This method creates a score panel which shows scores earned a life spent
	 * during the game.
	 */
	private void createScorePanel() {
		GRect panel = new GRect(0, 0, APPLICATION_WIDTH, 50);
		panel.setFilled(true);
		panel.setFillColor(Color.GRAY);
		add(panel);
		scoreLabel = new GLabel("Score " + score, 10, 35);
		scoreLabel.setFont("Timesnewroman-bold-30");
		scoreLabel.setColor(Color.WHITE);
		add(scoreLabel);
		soulLabel = new GLabel("Life " + life, 340, 35);
		soulLabel.setFont("Timesnewroman-bold-30");
		soulLabel.setColor(Color.WHITE);
		add(soulLabel);

	}

	/**
	 * This method creates an animation in game and checks for intersection with
	 * enemy car and coins.
	 */
	public void moveCar() {
		timer++;
		int roadSpeed = 60;
		int carSpeed = 15;
		Random farida = new Random();
		int x = farida.nextInt(350);
		road1.move(0, roadSpeed);
		road2.move(0, roadSpeed);
		enemyCar.moveEnemy(7, carSpeed, getWidth());
		if (dollar != null) {
			dollar.coinImage.move(0, 15);
		}
		pause(Constants.DELAY);
		if (road1.getY() >= getHeight()) {
			road1.setLocation(0, -Constants.ROAD_LOCATION);
		}
		if (road2.getY() >= getHeight()) {
			road2.setLocation(0, -Constants.ROAD_LOCATION);
		}
		if (enemyCar.getY() >= getHeight()) {
			enemyCar.enemyCarImage.setLocation(x, -300);
		}

		if (timer % 10 == 0) {
			score += 10;
			refreshScore();
		}
		if (dollar != null && car.carImage.getBounds().intersects(dollar.getBounds()) || dollar.getY() >= getHeight()) {
			Random random = new Random();
			int x1 = random.nextInt(350);
			Random gener = new Random();
			int y = gener.nextInt(getHeight() / 2);
			dollar.coinImage.setLocation(x1, -y / 2);
			coins += 1;

		}

	}

	/**
	 * This method refreshes score earned during the game.
	 */
	public void refreshScore() {
		scoreLabel.setLabel("Score " + score);
	}

	/**
	 * This method refreshes number of lives spent during the game
	 */
	public void refreshSoul() {
		soulLabel.setLabel("Life " + life);
	}

	/**
	 * This method creates a game over message and shows the number of coins
	 * earned during the game and the amount of score earned during the game.
	 */
	private void createGameOver() {
		removeAll();
		GameOver game = new GameOver(Constants.APPLICATION_WIDTH, Constants.APPLICATION_HEIGHT);
		add(game);
		GLabel gameOverInstruction = new GLabel("GAME OVER ", getWidth() / 2 - 150, 0);
		gameOverInstruction.setFont("Timesnewroman-bold-50");
		gameOverInstruction.setColor(Color.BLACK);
		add(gameOverInstruction);
		while (gameOverInstruction.getY() < getHeight() / 2) {
			gameOverInstruction.move(0, 10);
			pause(Constants.DELAY);
		}
		scoreLabel = new GLabel("Score " + score, getWidth() / 2 - 80, getHeight() / 2 + 50);
		scoreLabel.setFont("Timesnewroman-bold-40");
		scoreLabel.setColor(Color.BLACK);
		add(scoreLabel);
		coinsLabel = new GLabel("Coins " + coins, getWidth() / 2 - 80, getHeight() / 2 + 90);
		coinsLabel.setFont("Timesnewroman-bold-40");
		coinsLabel.setColor(Color.BLACK);
		add(coinsLabel);

	}

	/**
	 * This method shows the reaction of car on the Key pressed.
	 */
	public void keyPressed(KeyEvent e) {
		if (car != null) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				car.moveLeft(5, 0);
				if (car.getX() <= 0) {
					car.moveRight(5, 0);
				}
				break;
			case KeyEvent.VK_RIGHT:
				car.moveRight(5, 0);
				if (car.getX() >= getWidth() - Constants.CAR_WIDTH) {
					car.moveLeft(5, 0);
				}
				break;
			case KeyEvent.VK_UP:
				if (car.getY() > 50) {
					car.moveUp(0, 5);
				}
				break;
			}
		}
	}

	/**
	 * This method is used to return car to the initial position after using
	 * acceleration.
	 */
	public void keyReleased(KeyEvent e) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (car.getY() + Constants.CAR_HEIGHT <= getHeight()) {
					car.moveDown(0, 2);
					pause(Constants.DELAY);
				}
			}
		});
		thread.start();
	}

}