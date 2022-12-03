package com.example.picolotest;


import android.widget.ImageView;

import java.util.Random;


public class Question {

	private int nbJoueur;
	private String tabTxt;
	private String finalTxt;
	private Jeu jeu;
	private int type;
	// private boolean isSEXED;//faudra en parler au gars (en attendant je fais
	// sans)

	public Question(String Txt, int nbJoueur, Jeu jeu, int type) {
		// Le tableau doit étre alloué en fonction du nombre de part de la question, les
		// calculs seront éxecutés en fonction de cette taille et du nombre de joueur
		// (doit correspondre pour éviter les erreurs pointeur NULL)
		// Pourra étre modifié en fonction du passage XML/Java
		this.tabTxt = Txt;
		finalTxt = "";
		this.nbJoueur = nbJoueur;
		this.jeu = jeu;
		this.type = type;
		buildFinalTxt();
	}

	public String getFinalTxt() {

		return finalTxt;
	}

	public String getItabTxt(int id) {
		return tabTxt;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public int getType() {
		return type;
	}

	// Peut être modifié en fonction de l'allocation de la mémoire des tableaux ->
	// voir le constructeur
	public void buildFinalTxt() {
		finalTxt =tabTxt;
		while(finalTxt.contains("%")){
			finalTxt=finalTxt.replaceFirst("%",randomPlayer().getNom());

		}

	}

	public boolean isAlreadyPicked(Joueur joueur, Joueur[] tabJ) {
		for (int i = 0; i < tabJ.length; i++) {
			if (joueur == tabJ[i])
				return true;
		}
		return false;
	}

	// ou alors passer le jeu en paramètre (voir avis des autres)
	// l'histoire du sexe des joueurs n'est pas encore traîté (voir avis des autres)	// pour la conception)
	public Joueur randomPlayer() {
		Random rnd = new Random();
		Joueur[] tabJoueur = new Joueur[nbJoueur];
		Joueur[] tabJJeu = jeu.getTabJoueur();

		// passage par une boucle while pour vérifier qu'un joueur n'est pas pick 2 fois
		int i = 0;
		while (i < nbJoueur) {
			int idPick = rnd.nextInt(jeu.getNbJoueur() + 1);
			if (!isAlreadyPicked(tabJJeu[idPick], tabJoueur)) {
				tabJoueur[i] = tabJJeu[idPick];
				i++;
			}
		}
		return tabJJeu[0];
	}

	// Pour les tests - à modifier plus tard pour passer par l'affichage sur
	// l'interface
	public void printFinalTxt() {
		System.out.println("Texte = " + finalTxt);
	}

	public String getStringType() {
		if (this.type == 1)
			return "A poil !";

		if (this.type == 2)
			return "Naked or not naked ?";

		if (this.type == 3)
			return "Vote";

		if (this.type == 4)
			return "Virus";

		if (this.type == 5)
			return "Duel";

		if (this.type == 6)
			return "Vérité";

		if (this.type == 7)
			return "Action";

		if (this.type == 8)
			return "Normale";

		if (this.type == 9)
			return "Tour de table";

		else
			return "Hecatombe";
	}

	public int getResIconeType() {
		if (this.type == 1)
			return R.drawable.calliente;

		if (this.type == 2)
			return R.drawable.calliente;

		if (this.type == 3)
			return R.drawable.votezensemble;

		if (this.type == 4)
			return R.drawable.virus;

		if (this.type == 5)
			return R.drawable.entredeux;

		if (this.type == 6)
			return R.drawable.simple;

		if (this.type == 7)
			return R.drawable.calliente;

		if (this.type == 8)
			return R.drawable.simple;

		if (this.type == 9)
			return R.drawable.tourdetable;

		else
			return R.drawable.hecatombe;
	}

	public int getResBackgroundType() {
		if (this.type == 1)
			return R.drawable.fond_calliente;

		if (this.type == 2)
			return R.drawable.fond_calliente;

		if (this.type == 3)
			return R.drawable.fond_votezensemble;

		if (this.type == 4)
			return R.drawable.fond_virus;

		if (this.type == 5)
			return R.drawable.fond_entredeux;

		if (this.type == 6)
			return R.drawable.fond_simple;

		if (this.type == 7)
			return R.drawable.fond_calliente;

		if (this.type == 8)
			return R.drawable.fond_simple;

		if (this.type == 9)
			return R.drawable.fond_tourdetable;

		else
			return R.drawable.fond_hecatombe;
	}

}

