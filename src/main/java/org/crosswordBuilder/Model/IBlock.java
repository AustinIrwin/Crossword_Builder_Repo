package org.crosswordBuilder.Model;

public interface IBlock {
    //set to blank space
    //store one correct letter
    //store one guess letter
    //store a correct state
    //set to be the beginning of a word
    //

    /**
     *
     * @return true if this block is blacked out (is letter is set to -1)
     */
    boolean isClosed();


    /**
     *
     * @param guess - a char that the user has guessed is contained in this block
     * @return true if this guess is correct
     */
    boolean setGuess(char guess);

    /**
     *
     * @return the value of guess, 0 if there is no guess and
     * throws an exception if the block is closed
     */
    char getGuess();

    /**
     * solves the block by setting the guess == to the letter
     */
    void solve();

    boolean isCorrect();

}
