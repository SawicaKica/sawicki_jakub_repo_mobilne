package com.example.klikajwubra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    Score score;
    TextView textViewProc;
    TextView textViewPkt;
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        score = new ViewModelProvider(this).get(Score.class);
        setContentView(R.layout.activity_main);
        ImageButton ibtnClick = findViewById(R.id.ibtnClick);
        Button btn50Pkt = findViewById(R.id.btn50Pkt);
        Button btn500Pkt = findViewById(R.id.btn500Pkt);
        textViewProc = findViewById(R.id.textViewProc);
        textViewPkt = findViewById(R.id.textViewPkt);

        ibtnClick.setOnClickListener(view -> {
            if (score.getScore2() < 400) {
                score.setScore(score.getScore() + score.getScore2());
                textViewPkt.setText("" + ((double) score.getScore() / 10));
            }
        });

        btn50Pkt.setOnClickListener(view -> {
            if (score.getScore() >= 500) {
                score.setScore(score.getScore() - 500);
                score.setScore2(score.getScore2() + 1);
                onResume();
            }
        });

        btn500Pkt.setOnClickListener(view -> {
            if (score.getScore() >= 5000) {
                score.setScore(score.getScore() - 5000);
                score.setScore2(score.getScore2() + 10);
                onResume();
            }
        });

        String filename = "text.txt";
        Context context = this;

        f = new File(context.getFilesDir(), filename);
        try {
            Scanner sc = new Scanner(f);
            String line = null;
            if (sc.hasNextLine()) {
                line = sc.nextLine();
            }
            if (line != null) {
                String[] tab = line.split(";");
                score.setScore(Integer.parseInt(tab[0]));
                score.setScore2(Integer.parseInt(tab[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewPkt.setText("" + ((double) score.getScore() / 10));
        textViewProc.setText("" + ((double) score.getScore2() / 10) + "%");
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(score.getScore() + ";" + score.getScore2());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}