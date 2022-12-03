package com.example.picolotest;


import java.util.Random;
import android.view.View;


public class Jeu {
	// nb joueur max place a 15 pour le moment (modifiable)
	private Joueur[] tabJoueur;
	private int nbJoueur;


	// nb total de question place a 150 pour le moment (modifiable)
	private Question[] questionTotal;
	private int nbQuestionTotal;


	// nb question d'un cycle place a 50 comme prevu
	private Question[] questionCycle;
	private int nbQuestionCycle;


	// nb question en cours
	private int nbCurrentQuestion;


	static final int NBJOUEURMAX = 15;
	static final int NBQUESTIONMAX = 141;
	static final int NBQUESTIONCYCLEMAX = 50;

	public Jeu() {
		this.nbCurrentQuestion = 0;
		this.nbJoueur = 0;
		this.nbQuestionTotal = 0;
		this.nbQuestionCycle = 0;

		this.tabJoueur = new Joueur[NBJOUEURMAX];
		this.questionTotal = new Question[NBQUESTIONMAX];
		this.questionCycle = new Question[NBQUESTIONCYCLEMAX];
	}

	public void addJoueur(Joueur joueur) {
		if (this.nbJoueur < 15) {
			this.tabJoueur[nbJoueur] = joueur;
			this.nbJoueur++;
		}
	}

	public void addQuestionTotal(Question question) {
		if (this.nbQuestionTotal < 150) {
			this.questionTotal[nbQuestionTotal] = question;
			this.nbQuestionTotal++;
		}
	}

	public void addQuestionCycle(Question question) {
		if (this.nbQuestionCycle < 50) {
			this.questionCycle[nbQuestionCycle] = question;
			this.nbQuestionCycle++;
		}
	}

	public void upNbCurrent() {
		this.nbCurrentQuestion++;
	}

	public Question[] getQuestionTotal() {
		return this.questionTotal;
	}

	public int getNbQuestionTotal() {
		return this.nbQuestionTotal;
	}

	public Question[] getQuestionCycle() {
		return this.questionCycle;
	}

	public int getNbQuestionCycle() {
		return this.nbQuestionCycle;
	}

	public Joueur[] getTabJoueur() {
		return this.tabJoueur;
	}

	public int getNbJoueur() {
		return this.nbJoueur;
	}

	public Joueur getJoueurI(int id) {
		return this.tabJoueur[id];
	}

	public Question getQuestionICycle(int id) {
		return this.questionCycle[id];
	}

	public Question getQuestionItotal(int id) {
		return this.questionTotal[id];
	}

	public int getNbCurrentQuestion() {
		return this.nbCurrentQuestion;
	}

	public Question getCurrentQuestion() {
		return this.questionCycle[this.nbCurrentQuestion - 1];
	}

	public void printScore() {
		for (int i = 0; i < nbJoueur; i++) {
			// A modifier affichage xml
			System.out.println(tabJoueur[i].getNom() + "a déjà répondu à " + tabJoueur[i].getNbNomin() + " questions !");
			System.out.println("ok");
		}
	}

	public void game() {
		// Les questions sont déjà assemblées (avec les joueurs etc)

		if (this.nbCurrentQuestion < this.nbQuestionCycle) {
			Question currentQ = this.questionCycle[this.nbCurrentQuestion];
			this.nbCurrentQuestion++;
		} else {
			// Affichage Checkpoint //
			this.nbCurrentQuestion = 0;
		}

	}

	public void createQuestTotal(String[][] tabString) {
		// On cree toutes les questions
		for (int i = 0; i < tabString.length; i++) {
			// pos 0 = string / pos 1 = type / pos 2 = nbJ
			String strQuestion = tabString[i][0];
			int type = Integer.parseInt(tabString[i][1]);
			int nbJ = Integer.parseInt(tabString[i][2]);
			System.out.println("question :"+i);
			Question newQuest = new Question(strQuestion, nbJ, this, type);
			addQuestionTotal(newQuest);
		}
	}

	public void createTabJoueur(String[][] tabString) {
		for (int i = 0; i < tabString.length; i++) {
			Joueur newJoueur;
			if (tabString[i][1].equals("1")) {
				newJoueur = new Joueur(tabString[i][0], Joueur.Sexe.HOMME);
			} else {
				newJoueur = new Joueur(tabString[i][0], Joueur.Sexe.FEMME);
			}
			addJoueur(newJoueur);
		}
	}


	public static boolean intInTab(int[] tab, int nb, int size) {
		for (int i = 0; i < size; i++) {
			if (tab[i] == nb)
				return true;
		}
		return false;
	}


	public void createQuestCycle() {
		Random rnd = new Random();
		int newRnd;
		int i = 0;
		int taille = this.questionCycle.length;
		//Tableau pour éviter d'avoir deux fois la même question dans le cycle
		int[] tabTampon = new int[taille];
		while (i < taille) {
			newRnd = rnd.nextInt(nbQuestionTotal);
			if (!intInTab(tabTampon, newRnd, i)) {
				addQuestionCycle(this.questionTotal[newRnd]);
				tabTampon[i] = newRnd;
				i++;
			}
		}
	}
}