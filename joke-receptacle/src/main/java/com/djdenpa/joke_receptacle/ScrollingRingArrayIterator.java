package com.djdenpa.joke_receptacle;

import java.util.Iterator;

/**
 * Created by Eugene on 4/26/2018.
 */

public class ScrollingRingArrayIterator<T> implements Iterator<T> {

  ScrollingRingArray<T> target;

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public T next() {
    return null;
  }
}
