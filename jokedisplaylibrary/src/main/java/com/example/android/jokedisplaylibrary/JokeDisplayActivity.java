package com.example.android.jokedisplaylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        Bundle jokeBundle = getIntent().getBundleExtra(Intent.EXTRA_TEXT);
        String joke = jokeBundle.getString("jokeKey");
        TextView textView = (TextView) findViewById(R.id.jokeTextView);
        if (joke != null) {
            textView.setText(joke);
        } else {
            textView.setText("Jokes couldn't be fetched. Ensure you're connected to the internet and try again.");
        }
    }
}
