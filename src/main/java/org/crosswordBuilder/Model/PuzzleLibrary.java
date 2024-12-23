package org.crosswordBuilder.Model;

import java.util.ArrayList;
import java.util.List;

public class PuzzleLibrary implements IPuzzleLibrary {

  private final List<IPuzzle> puzzles;

  public PuzzleLibrary() {
    this.puzzles = new ArrayList<>();
  }

  @Override
  public void addPuzzle(IPuzzle puzzle) {
    if (puzzle == null) {
      throw new IllegalArgumentException("Cannot add a null puzzle");
    }
    puzzles.add(puzzle);
  }

  @Override
  public IPuzzle getPuzzle(int index) {
    if(index < 0 || index >= size()){
      throw new ArrayIndexOutOfBoundsException();
    }
    return puzzles.get(index);
  }

  @Override
  public int size() {
    return puzzles.size();
  }
}
