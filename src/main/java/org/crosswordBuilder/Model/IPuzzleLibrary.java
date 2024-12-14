package org.crosswordBuilder.Model;

public interface IPuzzleLibrary {

    enum Direction { ACROSS, DOWN}
    boolean isSolved();

    boolean checkBlock(int x, int y);

    boolean checkWord(int x, int y, Direction direction);
}
