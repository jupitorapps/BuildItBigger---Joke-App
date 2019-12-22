package com.example.jokeandroidlibraray;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowJokesActivity extends AppCompatActivity {

    private final String TAG = "TAGG";
    private ArrayList<String> jokesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jokes);

        Intent intent = getIntent();
     //   String joke = intent.getStringExtra("joke");

        jokesArrayList = intent.getStringArrayListExtra("joke");

        TextView textView = findViewById(R.id.joke_tv);
        textView.setText(jokesArrayList.get(5));

        findViewById(R.id.button_next_joke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Next Called");
            }
        });


    }



}
