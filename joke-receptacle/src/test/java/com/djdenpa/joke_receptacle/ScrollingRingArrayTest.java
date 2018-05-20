package com.djdenpa.joke_receptacle;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Scrolling Ring Array
 */
public class ScrollingRingArrayTest {

  @Test
  public void TestRingArrayAccess(){
    ScrollingRingArray<Integer> arr = new ScrollingRingArray<>(8);
    for (int i = 0; i < 8; i++){
      arr.queue(i);
    }
    for (Integer i = 0; i < 8; i++){
      Assert.assertEquals(i, arr.get(i));
    }

    for (Integer i = 0; i < 8; i++){
      Assert.assertEquals(i, arr.get(i));
    }
  }

  @Test
  public void TestRingArrayWrapHalf(){
    ScrollingRingArray<Integer> arr = new ScrollingRingArray<>(8);
    for (int i = 0; i < 12; i++){
      arr.queue(i);
    }

    Assert.assertNotEquals(new Integer(0), arr.get(0));

    for (Integer i = 0; i < 8; i++){
      Assert.assertEquals(new Integer(i + 4), arr.get(i));
    }
  }

  @Test
  public void TestOverload(){
    ScrollingRingArray<Integer> arr = new ScrollingRingArray<>(4);
    for(int i = 0; i < 1000000; i++){
      arr.queue(i);
    }
    Assert.assertEquals(new Integer(999996), arr.get(0));
    Assert.assertEquals(new Integer(999997), arr.get(1));
    Assert.assertEquals(new Integer(999998), arr.get(2));
    Assert.assertEquals(new Integer(999999), arr.get(3));
  }


}