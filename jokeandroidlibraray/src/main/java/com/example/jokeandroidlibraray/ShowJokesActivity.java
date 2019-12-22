package com.example.jokeandroidlibraray;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowJokesActivity extends AppCompatActivity {

    private final String TAG = "TAGG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jokes);

        Intent intent = getIntent();
        String joke = intent.getStringExtra("joke");

      //  jokesArrayList = intent.getStringArrayListExtra("jokesList");

        TextView textView = findViewById(R.id.joke_tv);
        textView.setText(joke);

        findViewById(R.id.button_next_joke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Next Called");
            }
        });


    }



}
