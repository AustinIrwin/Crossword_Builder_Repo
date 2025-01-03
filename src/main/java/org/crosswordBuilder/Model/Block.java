package org.crosswordBuilder.Model;

public class Block implements IBlock {

  private char letter;
  private char guess;
  private final boolean closed;

  public Block(int letter) {
    if (letter == -1) {
      this.closed = true;

    } else {
      if(letter < 65 || letter > 122 || (letter > 90 && letter < 97)){
        throw new IllegalArgumentException("Not a valid character");
      }
      this.letter = Character.toUpperCase( (char) letter );
      this.closed = false;
      this.guess = 0;
    }
  }

  /**
   * @return true if this block is blacked out (is letter is set to -1)
   */
  @Override
  public boolean isClosed() {
    return closed;
  }

  /**
   * @param guess - a char that the user has guessed is contained in this block
   * @return true if this guess is correct
   */
  @Override
  public boolean setGuess(char guess) {
    if (closed) {
      throw new IllegalStateException("This block is closed");
    }
    this.guess = Character.toUpperCase( guess );
    return isCorrect();
  }

  /**
   * @return the value of guess, 0 if there is no guess and throws an exception if the block is
   *     closed
   */
  @Override
  public char getGuess() {
    if (closed) {
      throw new IllegalStateException("This block is closed");
    }
    return guess;
  }

  @Override
  public char getLetter(){
    if(closed) {
      throw new IllegalArgumentException("This block is closed");
    }
    return letter;
  }

  /** solves the block by setting the guess == to the letter */
  @Override
  public void solve() {
    if (!closed) {
      guess = letter;
    }
  }

  @Override
  public boolean isCorrect() {
    if (closed) {
      return true;
    }
    return guess == letter;
  }

  /**
   * if not blocked, will reset guess to null
   */
  @Override
  public void reset() {
    if(!closed){
      guess = 0;
    }
  }

}
