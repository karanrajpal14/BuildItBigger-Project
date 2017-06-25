package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.android.jokedisplaylibrary.JokeDisplayActivity;
import com.example.karan.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    public static String fetchedJoke = null;
    private static MyApi myApiService = null;
    private Context context;
    private onJokeFetched listener;

    public EndpointsAsyncTask() {

    }

    public EndpointsAsyncTask(onJokeFetched listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-171401.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            fetchedJoke = result;
            Intent jokeIntent;
            jokeIntent = new Intent(context, JokeDisplayActivity.class);
            Bundle jokeBundle = new Bundle();
            jokeBundle.putString("jokeKey", result);
            jokeIntent.putExtra(Intent.EXTRA_TEXT, jokeBundle);
            context.startActivity(jokeIntent);
            listener.onJokeFetched();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}