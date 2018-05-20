package com.djdenpa.joke_receptacle;

import org.junit.Test;

import java.util.Random;

/**
 * Created by Eugene on 4/26/2018.
 *
 * Test sliding window, we want
 */

public class TestScrollingArrayVSPrimitiveCopy {

  public static final int DATA_LENGTH = 102400;
  public static final int WINDOW_SIZE = 1024;
  public static final int SCROLL_SIZE = 256;
  public static final int MAX_VAL = 1024;
  public static final int MAX_ITERATIONS = 100;


  @Test
  public void Test() {
    Random rand = new Random();
    float[] data = new float[DATA_LENGTH];

    for (int i = 0; i < DATA_LENGTH; i++) {
      data[i] = rand.nextFloat();
    }

    long startTimeMillis;
    long endTimeMillis;

    //array implementation
    startTimeMillis = System.currentTimeMillis();
    float[] current = new float[WINDOW_SIZE];
    for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
      for (int start = 0; start <= DATA_LENGTH - WINDOW_SIZE; start += SCROLL_SIZE) {
        for (int i = 0; i < WINDOW_SIZE; i++) {
          current[i] = data[start + i];
        }
        for (int i = 0; i < WINDOW_SIZE; i++) {
          int partnerIndex = (i * 10) % WINDOW_SIZE;
          current[i] = (current[i] * current[partnerIndex]) % MAX_VAL;
        }

      }
    }
    endTimeMillis = System.currentTimeMillis();

    long arrTimeMillis = endTimeMillis - startTimeMillis;

    System.out.println("array result millis:");
    System.out.println(arrTimeMillis);

    // do the ring array implementation
    startTimeMillis = System.currentTimeMillis();
    ScrollingRingArray<Float> buffer = new ScrollingRingArray<>(WINDOW_SIZE);
    for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
      int index = 0;
      while (index < WINDOW_SIZE) {
        buffer.queue(data[index]);
        index++;
      }
      do {
        if (index % SCROLL_SIZE == 0) {
          for (int i = 0; i < WINDOW_SIZE; i++) {
            int partnerIndex = (i * 10) % WINDOW_SIZE;
            current[i] = (buffer.get(i) * buffer.get(partnerIndex)) % MAX_VAL;
          }
        }
        index++;
      } while (index < DATA_LENGTH);
    }
    endTimeMillis = System.currentTimeMillis();

    long arrListTimeMillis = endTimeMillis - startTimeMillis;

    System.out.println("ArrayList result millis:");
    System.out.println(arrListTimeMillis);


    System.out.println("The ratio is: ");
    System.out.println((double) arrTimeMillis / (double) arrListTimeMillis);
  }
}
