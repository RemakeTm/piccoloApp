package com.example.picolotest;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.ToggleButton;

public class JoueurLayout extends LinearLayout {

    private EditText FieldJoueur;
    private ToggleButton BoutonSexe;

    public JoueurLayout (Context context, int id){
        super(context);

        this.FieldJoueur = new EditText(context);
        this.BoutonSexe = new ToggleButton(context);

        this.FieldJoueur.setId(id);
        this.BoutonSexe.setId(id);

        this.setMinimumWidth(this.getMeasuredWidth());
        this.FieldJoueur.setWidth(200);
        this.BoutonSexe.setWidth(this.BoutonSexe.getMeasuredWidth());

        this.setMinimumWidth(100);
        this.FieldJoueur.setHeight(50);
        this.BoutonSexe.setHeight(this.BoutonSexe.getMeasuredHeight());

        TableLayout.LayoutParams paramField = new TableLayout.LayoutParams();
        TableLayout.LayoutParams paramBouton = new TableLayout.LayoutParams();
        paramField.setMargins(50, 25, 0, 0);
        paramBouton.setMargins(50, 25, 0, 0);
        this.FieldJoueur.setLayoutParams(paramField);
        this.BoutonSexe.setLayoutParams(paramBouton);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "Sylfaen.ttf");
        this.FieldJoueur.setTypeface(font);
        this.FieldJoueur.setInputType(1);
        this.FieldJoueur.setText("Joueur" + id);
        this.FieldJoueur.setTextSize(20);

        this.BoutonSexe.setTextOff("Homme");
        this.BoutonSexe.setTextOn("Femme");

    }
}
