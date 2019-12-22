package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    private static final String TAG = "TAGG";
    private static MyApi myApiService = null;
    private Context context;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mActivityTestRule.launchActivity(new Intent());
    }


    @Test
    public void asynchTaskTest() throws Throwable {
        Log.d("TAGG", "asynchTaskTest: Came here");

        AsyncTask<Context, Void, List<String>> task = new AsyncTask<Context, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Context... contexts) {

                if (myApiService == null) {
                    MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                    request.setDisableGZipContent(true);

                                }
                            });

                    myApiService = builder.build();
                }
                context = contexts[0];

                try {

                    return myApiService.setJoke().execute().getData();

                } catch (IOException e) {

                    return Collections.singletonList(e.getMessage());
                }
            }


            @Override
            protected void onPostExecute(List<String> postResult) {

                //13 jokes are retrived from the java library
                assertEquals(13, postResult.size());

            }

        };

        task.execute(context);
        Espresso.onView(withId(android.R.id.content)).perform(ViewActions.click());


    }
}
