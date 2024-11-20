package org.crosswordBuilder;

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
}
