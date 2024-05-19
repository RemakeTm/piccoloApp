package com.example.picolotest;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.view.View;


public class Jeu {
	private List<Joueur> listeJoueurs = new ArrayList<>();;

    private List<Question> listeQuestions = new ArrayList<>();;

	private int indexCurrQuestion;


	static final int NBJOUEURMAX = 15;
	static final int NBQUESTIONCYCLEMAX = 50;

	public Jeu() {
	}

	public void addJoueur(Joueur joueur) {
		if (this.listeJoueurs.size() < NBJOUEURMAX) {
			this.listeJoueurs.add(joueur);
		}
	}
    public void addJoueur(String name, String sexe) {
        if (sexe.equals("1")) {
            this.addJoueur(new Joueur(name, Joueur.Sexe.HOMME));
        } else {
            this.addJoueur(new Joueur(name, Joueur.Sexe.FEMME));
        }
    }

    public void initCycleQuestion() throws FileNotFoundException {
        this.listeQuestions = Lecture.getQuestionsBundle(this.listeJoueurs);
    }

    public Question nextQuestion() {
        return listeQuestions.get(++indexCurrQuestion);
    }
	public int getIndexCurrQuestion() {
		return this.indexCurrQuestion;
	}

	public void game() {
		if (this.indexCurrQuestion < this.listeQuestions.size()) {
			Question currentQ = this.nextQuestion();
		} else {
			// Affichage Checkpoint //
			this.indexCurrQuestion = 0; //aucune idée de ce que ça fout là
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