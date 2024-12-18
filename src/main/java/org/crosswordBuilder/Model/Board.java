package org.crosswordBuilder.Model;

public class Board implements IBoard {

  private IBlock[][] board;

  public Board(IBlock[][] board) {
    this.board = board;
  }

  public Board(int[][] frame) {
    IBlock[][] board = new IBlock[frame.length][frame[0].length];
    for (int x = 0; x < frame.length; x++) {
      for (int y = 0; y < frame[0].length; y++) {
        board[x][y] = new Block(frame[x][y]);
      }
    }
    this.board = board;
  }

  /**
   * not sure why you'd need this
   *
   * @return length of one dimension of the array, is immutable
   */
  @Override
  public int getSize() {
    return board.length;
  }

  /**
   * need parameter validation for x,y
   *
   * @param x
   * @param y
   * @return block at index x,y
   */
  @Override
  public IBlock getBlock(int x, int y) {
    if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
      throw new IllegalArgumentException("Invalid block index");
    }

    return board[x][y];
  }

  /** sets each block guess to null clears all progress */
  @Override
  public void reset() {
    for (IBlock[] row : board) {
      for (IBlock block : row) {
        block.reset();
      }
    }
  }

  /**
   * Checks the value of each block
   *
   * @return
   */
  @Override
  public boolean isSolved() {
    for (IBlock[] row : board) {
      for (IBlock block : row) {
        if (!block.isCorrect()) return false;
      }
    }
    return true;
  }
}
