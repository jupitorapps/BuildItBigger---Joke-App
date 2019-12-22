package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private String TAG = "TAGG";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (BuildConfig.FLAVOR.contentEquals("free")) {

            View root = inflater.inflate(R.layout.fragment_main, container, false);
            AdView bannerAdView = root.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            bannerAdView.loadAd(adRequest);

            return root;


        } else {

            View root = inflater.inflate(R.layout.fragment_main_paid, container, false);
            return root;

        }

    }
}
