package com.example.jokeandroidlibraray;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class ShowJokesActivity extends AppCompatActivity {

    private final String TAG = "TAGG";
    private ArrayList<String> jokesArrayList;
    private TextView textView;
    private int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jokes);

        Intent intent = getIntent();
        jokesArrayList = intent.getStringArrayListExtra("joke");

        if ( jokesArrayList != null && jokesArrayList.size() == 1){
            //to accomodate error message which has only one message entry
            randomNumber = new Random().nextInt(jokesArrayList.size());
        } else {
            randomNumber = new Random().nextInt(jokesArrayList.size()-1);
        }

        Log.d(TAG, "onCreate: List Size: "+jokesArrayList.size());



        textView = findViewById(R.id.joke_tv);

        showJoke(randomNumber);

        findViewById(R.id.button_next_joke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (randomNumber == jokesArrayList.size()-1){
                    randomNumber = 0;
                } else {
                    randomNumber = randomNumber+1;
                }

                showJoke(randomNumber);
            }
        });


        findViewById(R.id.button_previous_joke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (randomNumber == 0){
                    randomNumber = jokesArrayList.size()-1;
                } else {
                    randomNumber = randomNumber-1;
                }
                showJoke(randomNumber);

            }
        });


    }


    private void showJoke(int currentPosition) {

        textView.setText(jokesArrayList.get(currentPosition));
    }


}
