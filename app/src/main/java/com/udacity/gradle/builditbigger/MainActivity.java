package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends AppCompatActivity {

    public static String TAG = "TAGG";
    private InterstitialAd mInterstitialAd;
    private boolean getJokeButtonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (BuildConfig.FLAVOR.contentEquals("free")) {
            loadInterstitialAds();
        }
        findViewById(R.id.button_show_jokes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJokeButtonClicked = true;
                if (BuildConfig.FLAVOR.contentEquals("free")) {
                    showAd();

                } else {
                    showJoke();
                }

            }
        });

    }

    private void showJoke() {
        NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask();
        networkAsyncTask.execute(this);

    }

    private void showAd(){

        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        } else {
            showJoke();
        }
        loadInterstitialAds();


    }



    private void loadInterstitialAds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.show();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d(TAG, "onAdLoaded: ");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.d(TAG, "onAdFailedToLoad: ");
           if (getJokeButtonClicked){
               showJoke();
           }

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                showJoke();
            }
        });
    }

}


