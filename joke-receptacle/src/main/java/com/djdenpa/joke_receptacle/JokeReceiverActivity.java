package com.djdenpa.joke_receptacle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by denpa on 3/24/2018.
 */

public class JokeReceiverActivity extends AppCompatActivity {

  protected static final String EXTRA_JOKE = "com.djdenpa.joke_receptacle_EXTRA_JOKE";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_joke_receptacle);

    TextView tv_joke = findViewById(R.id.tv_joke);

    Intent intent = getIntent();
    if(intent.hasExtra(EXTRA_JOKE)) {
      String joke = intent.getExtras().getString(EXTRA_JOKE);
      tv_joke.setText(joke);
    }else{
      String message = getString(R.string.no_joke_to_show);
      tv_joke.setText(message);
    }

  }

  // nice suggestion by anonymous reviewer
  public static Intent generateIntent(Context context, String joke){
    Intent intent = new Intent(context, JokeReceiverActivity.class);
    intent.putExtra(JokeReceiverActivity.EXTRA_JOKE, joke);
    return intent;
  }


}
