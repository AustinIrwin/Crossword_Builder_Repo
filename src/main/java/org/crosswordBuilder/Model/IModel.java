package org.crosswordBuilder.Model;

public interface IModel {

    IPuzzle getActivePuzzle();
    IBlock getActiveBlock();
    IPuzzle.Direction getDirection();

    void nextPuzzle();

    void prevPuzzle();

    void nextBlock();

    void prevBlock();

    void nextWord();

    void toggleShowCorrect();

    void toggleDirection();

    void setActiveBlock(int x, int y);




}
