package org.crosswordBuilder.Model;

public class Puzzle implements IPuzzle {

  private IBoard board;
  private IClue[] clues;

  public Puzzle(IBoard board, IClue[] clues){
    this.board = board;
    this.clues = clues;
  }
  @Override
  public boolean isSolved() {
    return board.isSolved();
  }

  @Override
  public IClue[] getClues() {
    return clues;
  }

  @Override
  public IBoard getBoard() {
    return board;
  }

  @Override
  public void makeGuess(int x, int y, char guess) {
    board.getBlock(x, y).setGuess(guess);
  }
}
