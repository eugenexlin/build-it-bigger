package com.djdenpa.joke_supply;

import java.util.Random;

public class JokingJoker {
  private static final String[] JOKE_ARRAY = {
          "I have a Polish friend who's a sound technician. Oh, and a Czech one too. Czech one too. Czech one too.",
          "A Mexican magician says he will disappear on the count of 3. He says \"uno, dos...\" poof. He disappeared without a tres.",
          "The Secret Service just had to change protocol for when the president is in danger. Instead of yelling \"get down!\", they have to yell \"Donald, duck!\"",
          "A woman walks into a library and asked if they had any books about paranoia.\n" +
                  "\n" +
                  "Librarian: \"They're right behind you!!\".",

          "What happened to the man running in front of the car?\n" +
                  "\n" +
                  "--He was tired\n" +
                  "\n" +
                  "What happened to the man running behind the car?\n" +
                  "\n" +
                  "--He was exhausted",
          "How does a rabbi make his coffee?\n" +
                  "\n" +
                  "Hebrews it.",
          "Why do blind people hate skydiving?\n" +
                  "\n" +
                  "It scares the hell out of their dogs.",
          "Guy walks into a bar and orders a fruit punch.\n" +
                  "Bartender says \"Pal, if you want a punch you'll have to stand in line\" Guy looks around, but there is no punch line.",
          "What's the difference between a good joke and a bad joke timing.\n" +
                  "\n",
          "Why don't ants get sick?\n" +
                  "\n" +
                  "Because they have little antybodies.\n" +
                  "\n",
          "Where does the king keep his armies?\n" +
                  "\n" +
                  "In his sleevies.",
          "I went bobsleighing the other day, killed 250 bobs",
          "My wife told me I had to stop acting like a flamingo. So I had to put my foot down.",
          "Why did the old man fall in the well?\n" +
                  "\n" +
                  "Because he couldn't see that well.",
          "This is my step ladder. I never knew my real ladder.",
          "Whatdya call a frenchman wearing sandals?\n" +
                  "\n" +
                  "Phillipe Phillope.",

          };

  public String tellJoke() {
    Random rand = new Random();
    int index = rand.nextInt((JOKE_ARRAY.length-1) + 1);
    return JOKE_ARRAY[index];
  }
}
