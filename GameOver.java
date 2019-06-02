package com.farida;
import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GImage;

import acm.graphics.GRect;

public class GameOver extends GCompound {

	public GameOver(double x, double y) {
		GRect rect = new GRect(x, y);
		rect.setFilled(true);
		rect.setColor(Color.ORANGE);
		add(rect);
		GImage carr = new GImage("caraccident1.png");
		carr.scale(0.6);
		add(carr, 80, 500);
	}

}
