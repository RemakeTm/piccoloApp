package com.example.picolotest;


import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import java.io.FileNotFoundException;


public class GameActivity extends AppCompatActivity {

    private Jeu jeu;
    private TextView typeQuestion;
    private TextView texteQuestion;
    private TextView numQuestion;
    private ImageView iconeQuestion;
    private ConstraintLayout layoutQuestion;
    private Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

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
        this.face = Typeface.createFromAsset(getAssets(),"fonts/subwaynoir.ttf");

        for (int cmp = 0 ; cmp < nbJoueur ; cmp++) {
            this.jeu.addJoueur(intent.getStringExtra("nomJoueur" + cmp),
                    intent.getStringExtra("sexeJoueur" + cmp));
        }

        // Création de la liste des questions //

        try {
            this.jeu.initCycleQuestion();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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
        Question currentQuestion = this.jeu.nextQuestion();

        this.typeQuestion.setText(currentQuestion.getQuestionType().getCategory());
        this.typeQuestion.setTypeface(this.face);
        this.typeQuestion.setTextSize(30);
        System.out.println("LE TYPE EST : " + currentQuestion.getQuestionType().getCategory());

        String texte = currentQuestion.getQuestionStr();
        this.texteQuestion.setText(texte);
        this.texteQuestion.setTypeface(this.face);
        this.texteQuestion.setTextSize(24);
        currentQuestion.printFinalTxt();

        this.numQuestion.setText(this.jeu.getIndexCurrQuestion());
        System.out.println("LE NUMERO EST : " + this.jeu.getIndexCurrQuestion());

        this.numQuestion.setTypeface(this.face);
        this.numQuestion.setTextSize(30);

        this.iconeQuestion.setImageResource(currentQuestion.getQuestionType().getIcon());
        this.layoutQuestion.setBackgroundResource(currentQuestion.getQuestionType().getIcon());

        if (currentQuestion.getQuestionType().getType() == 4) {
            // Ajouter l'icone en bas + délire des compteur //
        }
    }

    public void onWindowFocusChanged(boolean hasfocus){
        super.onWindowFocusChanged(hasfocus);
        if(hasfocus){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY); ;
        }
    }
}
