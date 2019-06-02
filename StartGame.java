package com.farida;
import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class StartGame extends GCompound {

	public StartGame(double x, double y) {
		GRect rect = new GRect(x, y);
		rect.setFilled(true);
		rect.setColor(Color.ORANGE);
		add(rect);
		GLabel welcomeInstruction = new GLabel("START A GAME ", getWidth() / 2 - 200, getHeight() / 2);
		welcomeInstruction.setFont("Timesnewroman-bold-50");
		welcomeInstruction.setColor(Color.BLACK);
		add(welcomeInstruction);
		GImage racing_flags = new GImage("racing_flags.png");
		racing_flags.scale(0.5, 0.5);
		add(racing_flags, x / 4, y / 4);
	}
}
