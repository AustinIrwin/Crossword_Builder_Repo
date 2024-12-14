package org.crosswordBuilder.Model;

import org.crosswordBuilder.Model.IBlock;

public interface IBoard {
    //has size
    //has a 2d array of block objects of size x size
    //sets blank spaces functionality (mirrored)
    //guess letters

    /**
     * not sure why you'd need this
     * @return length of one dimension of the array, is immutable
     */
    int getSize();

    /**
     * need parameter validation for x,y
     * @param x
     * @param y
     * @return block at index x,y
     */
    IBlock getBlock(int x, int y);

    /**
     *
     * sets each block guess to null
     * clears all progress
     */
    void reset();

    /**
     * Checks the value of each block
     * @return
     */
    boolean isSolved();


}
