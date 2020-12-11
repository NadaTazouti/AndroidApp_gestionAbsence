package com.example.ledger;

public class Question {
    /*la classe question est destinée à l'activité Intro, comme vous voyez layout d'intro ne contient pas de text ..... cest le role de question
    d'encapsuler a chaque clic de bouton les textes voulus
     */
    private int mtextoResId;

    public Question(int mtextoResId) {
        this.mtextoResId = mtextoResId;
    }

    public int getMtextoResId() {
        return mtextoResId;
    }

    public void setMtextoResId(int mtextoResId) {
        this.mtextoResId = mtextoResId;
    }
}
