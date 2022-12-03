package com.example.picolotest;


import android.widget.ImageView;


public class Virus extends Question {

	private String endTxt;
	private int counter = 20;
	private ImageView icon;

	public Virus(String tabTxt, int nbJoueur, Jeu jeu, String endTxt, int type, ImageView icon) {
		super(tabTxt, nbJoueur, jeu, type);
		this.endTxt = endTxt;
		this.icon = icon;
	}

	public String getEndTxt() {
		return endTxt;
	}

	public int getCounter() {
		return counter;
	}

	public boolean isEnded() {
		return counter == 0;
	}

	public void reduceCounter() {
		counter--;
	}
	
	public ImageView getIcon() {
		return icon;
	}
}
