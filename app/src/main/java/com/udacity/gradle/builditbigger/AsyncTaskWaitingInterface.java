package com.udacity.gradle.builditbigger;

/**
 * Created by denpa on 4/3/2018.
 *
 * This task was originally created because i was hoping to use it to undo the loader spinner
 * and such, but it turns out only the main thread can do UI stuff.
 * RIP.
 */

public interface AsyncTaskWaitingInterface {
  void preAsyncTask();
  void postAsyncTask(String result);
}
