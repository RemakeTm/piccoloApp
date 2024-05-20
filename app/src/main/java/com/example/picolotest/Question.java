package com.example.picolotest;


import java.util.Random;


public class Question {

	private final int nbJoueur;
	private String questionStr;
	private final Jeu jeu;
	private final QuestionType questionType;

	public Question(int nbJoueur, String questionStr, Jeu jeu, QuestionType type) {
		this.nbJoueur = nbJoueur;
		this.questionStr = questionStr;
		this.jeu = jeu;
		this.questionType = type;
		buildFinalTxt(); //est-ce qu'on veut vraiment initialiser les questions une par une vis-à-vis du nombre de nomination ?
	}

	public String getQuestionStr() {
		return questionStr;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}
	public void buildFinalTxt() {
		// ptdr j'ai même pas envie de regarder la ligne en dessous mais vasy
		while(this.questionStr.contains("%")){
			this.questionStr = this.questionStr.replaceFirst("%",randomPlayer().getNom());
		}
	}

	public boolean isAlreadyPicked(Joueur joueur, Joueur[] tabJ) {
        for (Joueur value : tabJ) {
            if (joueur == value)
                return true;
        }
		return false;
	}

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
		System.out.println("Texte = " + questionStr);
	}

}

