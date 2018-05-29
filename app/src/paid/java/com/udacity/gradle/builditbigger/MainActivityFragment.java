package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
    @BindView(R.id.ll_loading_joke)
    LinearLayout mLoadingLayout;

    Context mContext;
    MainActivityFragment mThis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getContext();
        mThis = this;

        ButterKnife.bind(this, root );

        mTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mLoadingLayout.setVisibility(View.VISIBLE);
                mTellJoke.setClickable(false);
                mTellJoke.setEnabled(false);

                EndpointsAsyncTask task = new EndpointsAsyncTask(mThis);
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
    public void postAsyncTask(String result) {
        mTellJoke.setClickable(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        //Because only main thread can affect views
        mLoadingLayout.setVisibility(View.GONE);
        mTellJoke.setClickable(true);
        mTellJoke.setEnabled(true);
    }

}
