package com.udacity.gradle.builditbigger;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by denpa on 5/28/2018.
 */

@RunWith(AndroidJUnit4.class)
public class EspressoTestFree {

  @Rule
  public final ActivityTestRule<MainActivity> mActivityTestRule =
          new ActivityTestRule<>(MainActivity.class);

  @Test
  public void TestStepDetails() {

    onView(withId(R.id.adView)).check(matches(isDisplayed()));

    onView(withId(R.id.b_tell_joke))
            .perform(click());

    onView(withId(R.id.tv_joke)).check(matches(isDisplayed()));

  }
}
