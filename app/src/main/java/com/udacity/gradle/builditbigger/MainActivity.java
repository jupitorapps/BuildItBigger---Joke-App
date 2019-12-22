package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static String TAG = "TAGG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_show_jokes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

    }

    public void tellJoke() {

        NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask();
        networkAsyncTask.execute(this);

    }


}
