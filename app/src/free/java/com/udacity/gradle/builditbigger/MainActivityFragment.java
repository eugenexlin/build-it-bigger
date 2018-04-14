package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.djdenpa.joke_receptacle.JokeReceiverActivity;
import com.djdenpa.joke_supply.JokingJoker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AsyncTaskWaitingInterface {

    public MainActivityFragment() {
    }

    @BindView(R.id.b_tell_joke)
    Button mTellJoke;

    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getContext();

        ButterKnife.bind(this, root );

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        final EndpointsAsyncTask task = new EndpointsAsyncTask(this);
        mTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.execute(mContext);

                //JokingJoker joker = new JokingJoker();
                //String joke = joker.tellJoke();
                //Toast.makeText(mContext, joke, Toast.LENGTH_SHORT).show();
                //launchJokeActivity(joke);
            }
        });

        return root;
    }

    @Override
    public void preAsyncTask() {
        mTellJoke.setClickable(false);
        //mTellJoke.setEnabled(false);
    }

    @Override
    public void postAsyncTask() {
        mTellJoke.setClickable(true);
        //mTellJoke.setEnabled(true);
    }

//    public void launchJokeActivity(String joke) {
//        Intent intent = new Intent(mContext, JokeReceiverActivity.class);
//        intent.putExtra(JokeReceiverActivity.EXTRA_JOKE, joke);
//        startActivity(intent);
//    }

}
