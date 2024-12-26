package org.crosswordBuilder.Model;

public interface IModel {

    IPuzzle getActivePuzzle();
    IBlock getActiveBlock();
    IPuzzle.Direction getDirection();

    int getActiveBlockX();
    int getActiveBlockY();

    boolean getShowCorrect();

    void nextPuzzle();

    void prevPuzzle();

    void nextBlock();

    void prevBlock();

    void nextWord();

    void toggleShowCorrect();

    void toggleDirection();

    void setActiveBlock(int x, int y);

    void setActivePuzzleIndex(int index);

    void addObserver(ModelObserver observer);

    void removeObserver(ModelObserver observer);

    void notifyObservers();

}
