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
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class PaidMainActivityFragment extends Fragment implements AsyncTaskWaitingInterface {

    public PaidMainActivityFragment() {
    }

    @BindView(R.id.b_tell_joke)
    Button mTellJoke;

    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.paid_fragment_main, container, false);
        mContext = getContext();

        ButterKnife.bind(this, root );



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
    }

    @Override
    public void postAsyncTask() {
        mTellJoke.setClickable(true);
    }


}
