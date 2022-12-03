package com.example.picolotest;


public class Joueur {

	// Enumération pour le sexe (disponible dans tout le programme)
	enum sexe {
		HOMME, FEMME;
	}

	private String nom;
	private sexe sexeJ;
	private int nbNomin = 0;

	// sexe est donc une instance de l'�num�ration qui est accessible dans les
	// autres classes: Joueur.sexe(.HOMME/FEMME)
	// Constructeur
	public Joueur(String nom, sexe sexeJ) {
		this.nom = nom;
		this.sexeJ = sexeJ;
	}

	// Methodes en lien avec l'attribut nom
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	// Methodes en lien avec l'attribut nbNomin
	public void addNbNomin() {
		nbNomin++;
	}

	public int getNbNomin() {
		return nbNomin;
	}

	// Methodes en lien avec l'attribut énuméré sexe
	// Conversion directe en string pour éviter les erreurs plus tard dans le code
	public String getSexe() {
		if (sexeJ == sexe.FEMME)
			return "femme";
		else
			return "homme";
	}

}
