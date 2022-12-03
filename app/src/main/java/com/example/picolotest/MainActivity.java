package com.example.picolotest;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    private ImageView add;
    private Button boutonJouer;
    private LinearLayout listeJoueur;
    private int nbJoueurDisplay;


    private String[][] tabInfosJoueurs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        this.add = (ImageView) findViewById(R.id.BoutonAdd);
        this.boutonJouer = (Button) findViewById(R.id.BoutonJouer);
        this.listeJoueur = (LinearLayout) findViewById(R.id.ListeJoueur);
        this.nbJoueurDisplay = 3;

        this.tabInfosJoueurs = new String[15][2];


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getNbJoueurDisplay() + 1;
                nbJoueurDisplay++;
                System.out.println("STPPPPPPPPP ON COMMENCE A " + nbJoueurDisplay);
                ViewGroup parent = (ViewGroup) getListeJoueur();


                LinearLayout layoutJoueur = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_joueur, parent, false);
                EditText fieldJoueur = (EditText) getLayoutInflater().inflate(R.layout.field_joueur, layoutJoueur, false);
                ToggleButton boutonSexe = (ToggleButton) getLayoutInflater().inflate(R.layout.bouton_joueur, layoutJoueur, false);


                layoutJoueur.setId(id);
                fieldJoueur.setId(100 + id);
                boutonSexe.setId(200 + id);


                fieldJoueur.setHint("Joueur " + id);
                fieldJoueur.setHintTextColor(Color.WHITE);
                Typeface face = Typeface.createFromAsset(getAssets(),"fonts/subwayblanc.ttf");
                fieldJoueur.setTypeface(face);


                layoutJoueur.addView(fieldJoueur);
                layoutJoueur.addView(boutonSexe);
                getListeJoueur().addView(layoutJoueur);

                System.out.println("MAINTENANT ON EST A " + nbJoueurDisplay);

            }
        });

        boutonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                tabInfosJoueurs = createTabInfosJoueur();

                System.out.println("IMPOSSIBLE CA BUG LA FREROT");

                for (int cmp = 0 ; cmp < nbJoueurDisplay ; cmp++) {
                    intent.putExtra("nomJoueur" + cmp, tabInfosJoueurs[cmp][0]);
                    intent.putExtra("sexeJoueur" + cmp, tabInfosJoueurs[cmp][1]);
                }

                intent.putExtra("nbJoueur", nbJoueurDisplay);
                System.out.println("SHALLAH CA PASSE");

                startActivity(intent);

                System.out.println("JURE");
                finish();
            }
        });
    }



    public int getNbJoueurDisplay() {
        return nbJoueurDisplay;
    }


    public void setNbJoueurDisplay(int nbJoueurDisplay) {
        this.nbJoueurDisplay = nbJoueurDisplay;
    }


    public LinearLayout getListeJoueur() {
        return listeJoueur;
    }


    public String[][] createTabInfosJoueur() {
        EditText tmpEdit = findViewById(R.id.FieldJoueur1);
        ToggleButton tmpToggle = findViewById(R.id.BoutonJoueur1);

        this.tabInfosJoueurs[0][0] = tmpEdit.getText().toString();
        this.tabInfosJoueurs[0][1] = sexeToString(tmpToggle);

        System.out.println("ET DE 1");

        tmpEdit = findViewById(R.id.FieldJoueur2);
        tmpToggle = findViewById(R.id.BoutonJoueur2);


        this.tabInfosJoueurs[1][0] = tmpEdit.getText().toString();
        this.tabInfosJoueurs[1][1] = sexeToString(tmpToggle);

        System.out.println("ET DE 2");

        tmpEdit = findViewById(R.id.FieldJoueur3);
        tmpToggle = findViewById(R.id.BoutonJoueur3);


        this.tabInfosJoueurs[2][0] = tmpEdit.getText().toString();
        this.tabInfosJoueurs[2][1] = sexeToString(tmpToggle);

        System.out.println("ET DE 3 MAIS SURTOUT YEN A" + nbJoueurDisplay);

        for (int i = 3 ; i < nbJoueurDisplay ; i++) {
            tmpEdit = findViewById(100 + i + 1);
            tmpToggle = findViewById(200 + i + 1);

            this.tabInfosJoueurs[i][0] = tmpEdit.getText().toString();
            this.tabInfosJoueurs[i][1] = sexeToString(tmpToggle);

            System.out.println("ET DE " + i);
        }

        return this.tabInfosJoueurs;
    }


    public String sexeToString(ToggleButton button) {
        if (button.isChecked())
            return "0";
        return "1";
    }
}
