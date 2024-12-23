package org.crosswordBuilder.Model;

public class Puzzle implements IPuzzle {

  private String title;
  private IBoard board;
  private IClue[] clues;

  public Puzzle(String title, IBoard board, IClue[] clues){
    this.title = title;
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
  public String getTitle(){ return title;}

  @Override
  public void makeGuess(int x, int y, char guess) {
    board.getBlock(x, y).setGuess(guess);
  }
}
