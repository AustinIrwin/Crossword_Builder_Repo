package org.crosswordBuilder.Controller;

public interface IController {
    void clickBlock(int x, int y);

    void guessBlock(char guess);

    void nextWord();

    void backspace();

    void blockRight();

    void blockDown();

    void blockUp();

    void blockLeft();

    void clickShowCorrect();

    void clickReset();

    void selectPuzzle(int index);
}
