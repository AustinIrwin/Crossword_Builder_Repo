package org.crosswordBuilder.Model;

public interface IPuzzleLibrary {


    boolean isSolved();

    boolean checkBlock(int x, int y);

    boolean checkWord(int x, int y, IPuzzle.Direction direction);
}
