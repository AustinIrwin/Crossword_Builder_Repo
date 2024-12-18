package org.crosswordBuilder.Model;

public interface IPuzzle {
    //stores a board instance
    //stores clues for the words
    //solved state?
    enum Direction { ACROSS, DOWN}

    boolean isSolved();

    IClue[] getClues();

    IBoard getBoard();

    void makeGuess(int x, int y, char guess);
}
