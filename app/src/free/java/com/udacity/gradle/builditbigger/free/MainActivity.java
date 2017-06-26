package com.udacity.gradle.builditbigger.free;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.udacity.gradle.builditbigger.BuildConfig;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "FreeDebug";
    PublisherInterstitialAd publisherInterstitialAd = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.ADS) {

            //Set up for pre-fetching interstitial ad request
            publisherInterstitialAd = new PublisherInterstitialAd(getApplicationContext());
            publisherInterstitialAd.setAdUnitId("/6499/example/interstitial");

            publisherInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    tellJoke();
                    //Prefetch next ad
                    requestNewInterstitial();
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    Log.d(LOG_TAG, "onAdFailedToLoad(): Failed to load ad with error code: " + i);

                    //Prefetch next ad
                    requestNewInterstitial();
                }

                @Override
                public void onAdLoaded() {
                    Log.d(LOG_TAG, "onAdLoaded(): Ad loaded successfully");
                    super.onAdLoaded();
                }
            });

            requestNewInterstitial();

            Button tellJokeButton = (Button) findViewById(R.id.tell_joke_button);
            tellJokeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (publisherInterstitialAd.isLoaded()) {
                        Log.d(LOG_TAG, "onClick(): Interstatial ad ready");
                        publisherInterstitialAd.show();
                    } else {
                        Log.d(LOG_TAG, "onClick(): Interstatial ad was not ready");
                        tellJoke();
                    }
                }
            });

            AdView mAdView = (AdView) findViewById(R.id.adView);
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke() {
        new EndpointsAsyncTask().execute(this);
    }

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice("EA27D37DF5448BF42AA5F7A6D4F11A9B")
                .build();

        publisherInterstitialAd.loadAd(adRequest);
    }

}