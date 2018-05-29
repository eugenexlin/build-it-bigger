package com.udacity.gradle.builditbigger;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by denpa on 5/28/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class JokeFetchTaskTest {

  @Mock
  Context mMockContext;

  class TestWaitingInterface implements AsyncTaskWaitingInterface {

    @Override
    public void preAsyncTask() {

    }

    @Override
    public void postAsyncTask(String result) {
      Assert.assertTrue(result != null);
      Assert.assertTrue(result.length() > 0);
      Assert.assertTrue(result.length() == 0);
      signal.countDown();
    }
  }
  final CountDownLatch signal = new CountDownLatch(1);

  @Test
  public void TestStepDetails() throws IOException {
    TestWaitingInterface callback = new TestWaitingInterface();
    EndpointsAsyncTask task = new EndpointsAsyncTask(callback);
//    task.execute(mMockContext);
//    signal.await(10, TimeUnit.SECONDS);
//    if(signal.getCount() == 1){
//      Assert.fail("Request Timed Out");
//    }

//    task.tryInit();
//    task.tryGet();

    // TODO I need help on how to do this android async test.
  }
}
