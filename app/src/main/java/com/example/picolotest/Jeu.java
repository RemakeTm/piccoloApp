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

}