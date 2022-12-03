package com.example.picolotest;


import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import org.w3c.dom.Text;


public class GameActivity extends AppCompatActivity {

    private Jeu jeu;

    private TextView typeQuestion;
    private TextView texteQuestion;
    private TextView numQuestion;
    private ImageView iconeQuestion;
    private ConstraintLayout layoutQuestion;

    private Question tmpInfos;
    private int nbJoueur;

    private Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Initialisation des variables


        View layoutQuestion = (View) getLayoutInflater().inflate(R.layout.layout_joueur, null, false);
        View layoutCheckpoint = (View) getLayoutInflater().inflate(R.layout.checkpoint, null, false);


        this.typeQuestion = findViewById(R.id.TypeQuestion);
        this.texteQuestion = findViewById(R.id.TexteQuestion);
        this.numQuestion = findViewById(R.id.NumeroQuestion);
        this.iconeQuestion = findViewById(R.id.IconeQuestion);
        this.layoutQuestion = (ConstraintLayout) findViewById(R.id.layout_question);


        this.jeu = new Jeu();


        // Création de la liste des joueurs //

        Intent intent = getIntent();
        int nbJoueur = intent.getIntExtra("nbJoueur", 3);
        String[][] tabInfosJoueurs = new String[nbJoueur][2];
        this.face = Typeface.createFromAsset(getAssets(),"fonts/subwaynoir.ttf");

        System.out.println("Y'A " + nbJoueur + " JOUEURS MEC");


        for (int cmp = 0 ; cmp < tabInfosJoueurs.length ; cmp++) {
            tabInfosJoueurs[cmp][0] = intent.getStringExtra("nomJoueur" + cmp);
            tabInfosJoueurs[cmp][1] = intent.getStringExtra("sexeJoueur" + cmp);
        }

        for (int i = 0 ; i < tabInfosJoueurs.length ; i++) {
            System.out.println("Nom : " + tabInfosJoueurs[i][0] + "\nSexe : " + tabInfosJoueurs[i][1]);
        }

        jeu.createTabJoueur(tabInfosJoueurs);

        System.out.println("ON A LES JOUEURS C PASIMOCHE");


        // Création de la liste des questions //


        Lecture lecture = new Lecture (getBaseContext().getAssets());
        System.out.println("PTDR JE SAIS LIRE C DROL");

        String [][] tabQuestions = lecture.getURL();
        System.out.println(tabQuestions[10][0] + "et" + tabQuestions[10][1] + "et" + tabQuestions[10][2]);

        jeu.createQuestTotal(tabQuestions);
        System.out.println("SERAIT CE ?????");

        jeu.createQuestCycle();
        System.out.println("LA LISTE DES QUESTIONS ?");


        // Début du jeu //


        action();
        texteQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                action();

            }
        });


        // Fin de jeu //

        layoutCheckpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remakeQuestion();
                action();
            }
        });

    }

    public void action() {
        this.jeu.game();
        this.tmpInfos = this.getInfosAffichage();

        this.typeQuestion.setText(this.tmpInfos.getStringType());
        this.typeQuestion.setTypeface(this.face);
        this.typeQuestion.setTextSize(30);
        System.out.println("LE TYPE EST : " + this.tmpInfos.getStringType());

        String texte =this.tmpInfos.getFinalTxt();
        this.texteQuestion.setText(texte);
        this.texteQuestion.setTypeface(this.face);
        this.texteQuestion.setTextSize(24);
        this.tmpInfos.printFinalTxt();

        this.numQuestion.setText(Integer.toString(jeu.getNbCurrentQuestion()));
        System.out.println("LE NUMERO EST : " + jeu.getNbCurrentQuestion());

        this.numQuestion.setTypeface(this.face);
        this.numQuestion.setTextSize(30);

        this.iconeQuestion.setImageResource(this.tmpInfos.getResIconeType());
        this.layoutQuestion.setBackgroundResource(this.tmpInfos.getResBackgroundType());

        if (tmpInfos.getType() == 4) {

            // Ajouter l'icone en bas + délire des compteur //
        }
    }


    public void remakeQuestion() {
        jeu.createQuestCycle();
    }


    public Question getInfosAffichage() {
        return this.jeu.getCurrentQuestion();
    }

    public void onWindowFocusChanged(boolean hasfocus){
        super.onWindowFocusChanged(hasfocus);
        if(hasfocus){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY); ;
        }
    }
}
