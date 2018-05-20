package com.djdenpa.joke_receptacle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eugene on 4/26/2018.
 *
 * FINDINGS
 *
 * ArrayList is not much slower at all.
 * with exact same process, performance ratio is .9
 *
 * I found for fixed length array, just use a constant, as calls to array.length and ArrayList.size
 * both slow things down at a .9 ratio.
 *
 * also the calls to random were taking a lot of time, it turns out without them,
 * array beats arraylist with a ratio of .70 ~ .75
 */

public class TestArrayPerformance {

  public static final float MAX_VAL = 10000;
  public static final int NUM_VALUES = 1024;
  public static final int NUM_ITERATIONS = 30000;

  @Test
  public void CompareArrayAndArrayList(){
    Random rand = new Random();
    long startTimeMillis;
    long endTimeMillis;

    //try array
    startTimeMillis = System.currentTimeMillis();
    float[] arr = new float[NUM_VALUES];
    for(int iteration = 0; iteration < NUM_ITERATIONS; iteration++){
      for (int i = 0; i < NUM_VALUES; i++){
        arr[i] = 1.1f;
      }
      //rand.nextFloat()

      for (int i = 0; i < NUM_VALUES; i++)
      {
        int partnerIndex = (i*10) % NUM_VALUES;
        arr[i] = (arr[i]* arr[partnerIndex]) % MAX_VAL;
      }
    }
    endTimeMillis = System.currentTimeMillis();

    long arrTimeMillis = endTimeMillis - startTimeMillis;

    System.out.println("array result millis:");
    System.out.println(arrTimeMillis);

    //try ArrayList
    startTimeMillis = System.currentTimeMillis();
    ArrayList<Float> arrList = new ArrayList<>(NUM_VALUES);
    for (int i = 0; i < NUM_VALUES; i++){
        arrList.add(null);
    }
    for(int iteration = 0; iteration < NUM_ITERATIONS; iteration++){
      for (int i = 0; i < NUM_VALUES; i++){
        arrList.set(i, 1.1f);
      }

      for (int i = 0; i < NUM_VALUES; i++)
      {
        int partnerIndex = (i*10) % NUM_VALUES;
        arrList.set(i, (arrList.get(i) * arrList.get(partnerIndex)) % MAX_VAL);
      }
    }
    endTimeMillis = System.currentTimeMillis();

    long arrListTimeMillis = endTimeMillis - startTimeMillis;

    System.out.println("ArrayList result millis:");
    System.out.println(arrListTimeMillis);

    System.out.println("The ratio is: ");
    System.out.println((double)arrTimeMillis/(double)arrListTimeMillis);

  }

}
