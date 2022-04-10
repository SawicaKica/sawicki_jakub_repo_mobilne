package com.example.klikajwubra;

import androidx.lifecycle.ViewModel;

public class Score extends ViewModel {
    private int score = 0;
    private int score2 = 10;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
