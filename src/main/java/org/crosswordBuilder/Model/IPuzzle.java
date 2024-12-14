package org.crosswordBuilder.Model;

public interface IPuzzle {
    //stores a board instance
    //stores clues for the words
    //solved state?

    boolean isSolved();

    String getClue();
}
