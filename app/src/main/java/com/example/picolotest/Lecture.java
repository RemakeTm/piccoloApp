package com.example.picolotest;

import android.util.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;


public class Lecture {

    private static List<Question> retrieveAllQuestions() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("app/src/main/assets/fonts/listeQuestion.json"));
        return gson.fromJson(reader, new TypeToken<List<Question>>(){}.getType());
    }

    private static List<Question> pickQuestions(List<Question> questions){
        List<Question> pickedQuestions = new ArrayList<>();
        List<Question> tmpQuestion = questions; //In order to not modify the original data
        for (int i = 0 ; i < Jeu.NBQUESTIONCYCLEMAX ; i++) {
            pickedQuestions.add(tmpQuestion.remove(new Random().nextInt(tmpQuestion.size())));
        }
        return pickedQuestions;
    }

    public static List<Question> getQuestionsBundle(List<Joueur> joueurs) throws FileNotFoundException {
        List<Question> questions = pickQuestions(retrieveAllQuestions());
        return formatQuestions(questions, joueurs);
    }

}