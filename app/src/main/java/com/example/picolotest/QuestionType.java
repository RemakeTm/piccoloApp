package com.example.picolotest;

public enum QuestionType {

    A_POIL(1, "A poil !", R.drawable.calliente, R.drawable.fond_calliente),
    NAKED(2, "Tout nu ?", R.drawable.calliente, R.drawable.fond_calliente),
    VOTE(3, "Vote !", R.drawable.votezensemble, R.drawable.fond_votezensemble),
    VIRUS(4, "Virus !", R.drawable.virus, R.drawable.fond_virus),
    DUEL(5, "Duel !", R.drawable.entredeux, R.drawable.fond_entredeux),
    VERITE(6, "Vérité !", R.drawable.simple, R.drawable.fond_simple),
    ACTION(7,"Action !", R.drawable.calliente, R.drawable.fond_calliente),
    NORMAL(8, "Normal ...", R.drawable.simple, R.drawable.fond_simple),
    TOUR_DE_TABLE(9, "Tour de table !", R.drawable.tourdetable, R.drawable.fond_tourdetable),
    HECATOMBE(10, "Hécatombe ! :)", R.drawable.hecatombe, R.drawable.fond_hecatombe);
    private int type;
    private String category;
    private int icon;
    private int background;


    QuestionType(int type, String category, int icon, int background) {
        this.type = type;
        this.category = category;
        this.icon = icon;
        this.background = background;
    }

    public int getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public int getIcon() {
        return icon;
    }

    public int getBackground() {
        return background;
    }

    public QuestionType getQuestionType(int type) {
        for (QuestionType questionType : this.getClass().getEnumConstants()) {
            if (questionType.getType() == type) {
                return questionType;
            }
        }
        return null;
    }
}
