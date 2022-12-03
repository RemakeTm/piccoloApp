package com.example.picolotest;


public class Joueur {

	enum Sexe {
		HOMME, FEMME;
	}

	private String nom;
	private Sexe sexe;
	private int nbNomin = 0;


	public Joueur(String nom, Sexe sexe) {
		this.nom = nom;
		this.sexe = sexe;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void addNbNomin() {
		nbNomin++;
	}

	public int getNbNomin() {
		return nbNomin;
	}

	public String getSexe() {
		return this.sexe.name();
	}
}
