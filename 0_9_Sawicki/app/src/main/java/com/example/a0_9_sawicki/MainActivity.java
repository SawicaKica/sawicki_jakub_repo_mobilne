package com.example.a0_9_sawicki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button js_SaveButton = findViewById(R.id.js_SaveButton);
        Button js_LoadButton = findViewById(R.id.js_LoadButton);
        EditText js_EdiText = findViewById(R.id.js_EdiText);

        String filename = "text.txt";
        Context context = this;

        File f = new File(context.getFilesDir(), filename);

        js_SaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text = js_EdiText.getText().toString();
                try{
                    FileWriter fw = new FileWriter(f);
                    fw.write(text);
                    fw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        js_LoadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try{
                    Scanner sc = new Scanner(f);
                    String text = "";
                    while(sc.hasNextLine()){
                        text += sc.nextLine();
                        System.out.print(text);
                    }
                    sc.close();
                    js_EdiText.setText(text);
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
    }
}