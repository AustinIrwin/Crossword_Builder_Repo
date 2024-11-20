package org.crosswordBuilder;

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
    boolean guess(char guess);

    /**
     *
     * @return the value of guess, 0 if there is no guess and
     * throws an exception if the block is closed
     */
    char getGuess();

    /**
     * if -1 then its blacked out (closed)
     * if 0 then its blank
     * need param checking to limit only letters and convert all letters to capital
     * @param letter -
     */
    void setLetter(char letter);


}
