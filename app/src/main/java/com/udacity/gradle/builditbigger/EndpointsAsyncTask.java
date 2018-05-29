package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.djdenpa.joke_receptacle.JokeReceiverActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by denpa on 4/1/2018.
 */

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
  private static MyApi myApiService = null;
  private Context context;
  private AsyncTaskWaitingInterface mCallback;

  public void RegisterAsyncTaskWaitingInterface(AsyncTaskWaitingInterface callback){
    mCallback = callback;
  }

  public EndpointsAsyncTask(AsyncTaskWaitingInterface callback){
    mCallback = callback;
  }

  protected void tryInit(){
    if(myApiService == null) {  // Only do this once
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
              new AndroidJsonFactory(), null)
              // options for running against local devappserver
              // - 10.0.2.2 is localhost's IP address in Android emulator
              // - turn off compression when running against local devappserver
              .setRootUrl("http://10.0.2.2:8080/_ah/api/")
              .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                  abstractGoogleClientRequest.setDisableGZipContent(true);
                }
              });
      // end options for devappserver

      myApiService = builder.build();
    }
  }
  protected String tryGet() throws IOException {
    return myApiService.getJoke().execute().getData();
  }

  @Override
  protected String doInBackground(Context... param) {
    mCallback.preAsyncTask();
    tryInit();

    context = param[0];

    try {
      return tryGet();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override
  protected void onPostExecute(String result) {
    mCallback.postAsyncTask(result);

    //Toast.makeText(context, result, Toast.LENGTH_LONG).show();

//    Intent intent = new Intent(context, JokeReceiverActivity.class);
//    intent.putExtra(JokeReceiverActivity.EXTRA_JOKE, result);
//    context.startActivity(intent);

    // nice suggestion by anonymous reviewer
    Intent intent = JokeReceiverActivity.generateIntent(context, result);
    context.startActivity(intent);
  }




}