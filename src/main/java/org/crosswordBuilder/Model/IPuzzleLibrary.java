package org.crosswordBuilder.Model;

public interface IPuzzleLibrary {

    void addPuzzle(IPuzzle puzzle);

    IPuzzle getPuzzle(int index);
    int size();


}
