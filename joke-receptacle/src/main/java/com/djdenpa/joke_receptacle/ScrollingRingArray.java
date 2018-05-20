package com.djdenpa.joke_receptacle;

import java.util.ArrayList;

/**
 * Created by Eugene on 4/26/2018.
 *
 * The point of this class is to use the least amount of space to emulate
 * steaming a sliding window of a large array.
 * One application is since FFT needs to read in blocks of a power of 2
 * but we might get more information from for example using an FFT of size 2^10
 * but scrolling every 2^8
 */

public class ScrollingRingArray<T> {

  protected ArrayList<T> buffer;
  protected int currentZeroIndex;
  protected int maxSize;

  public ScrollingRingArray(int windowSize){
    if (windowSize < 1){
      windowSize = 1;
    }
//    for(int i = 0; i< windowSize; i++){
//      buffer[i] = null;
//    }
    buffer = new ArrayList<>(windowSize);
    for(int i = 0; i < windowSize; i++){
      buffer.add(null);
    }
    currentZeroIndex = 0;
    maxSize = windowSize;
  }

  public void queue(T value){
    buffer.set(currentZeroIndex, value);
    currentZeroIndex += 1;
    if (currentZeroIndex > maxSize - 1){
      currentZeroIndex = currentZeroIndex % maxSize;
    }
  }

  /*
    Gets the value of the array relative to the current zero index
   */
  public T get(int index) {
    int lookupIndex = (currentZeroIndex + index) % maxSize;
    return buffer.get(lookupIndex);
  }
}
