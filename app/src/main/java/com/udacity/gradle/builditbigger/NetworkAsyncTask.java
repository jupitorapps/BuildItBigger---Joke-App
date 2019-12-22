package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.jokeandroidlibraray.ShowJokesActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class NetworkAsyncTask extends AsyncTask<Context, Void, String> {

    private static final String TAG = "TAGG";
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... contexts) {

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
            Log.d(TAG, "doInBackground: "+e.getMessage());
            return e.getMessage();
        }


    }

    @Override
    protected void onPostExecute(String postResult) {

        showJoke(postResult);
    }


    private void showJoke(String joke){
        Intent intent = new Intent(context, ShowJokesActivity.class);
        intent.putExtra("joke",joke);
      //  intent.putStringArrayListExtra("joke", (ArrayList<String>) joke);
        context.startActivity(intent);
    }


}
