package org.crosswordBuilder.Controller;

import org.crosswordBuilder.Model.IModel;
import org.crosswordBuilder.Model.IPuzzle;

public class Controller implements IController{

    private IModel model;

    public Controller(IModel model){
        this.model = model;
    }

    @Override
    public void clickBlock(int x, int y) {
        if(model.getActiveBlockX() == x && model.getActiveBlockY() == y){
            model.toggleDirection();
        }
        else{
            model.setActiveBlock(x, y);
        }
    }

    @Override
    public void guessBlock(char guess) {
        model.getActiveBlock().setGuess(guess);
        model.notifyObservers();
    }

    @Override
    public void nextWord() {
        model.nextWord();
    }

    @Override
    public void backspace() {
        model.getActiveBlock().reset();
        model.prevBlock();
    }

    @Override
    public void blockRight() {
        if(model.getDirection() == IPuzzle.Direction.DOWN){
            model.toggleDirection();
        }
        model.nextBlock();
    }

    @Override
    public void blockDown() {
        if(model.getDirection() == IPuzzle.Direction.ACROSS){
            model.toggleDirection();
        }
        model.nextBlock();
    }

    @Override
    public void blockUp() {
        if(model.getDirection() == IPuzzle.Direction.ACROSS){
            model.toggleDirection();
        }
        model.prevBlock();
    }

    @Override
    public void blockLeft() {
        if(model.getDirection() == IPuzzle.Direction.DOWN){
            model.toggleDirection();
        }
        model.prevBlock();

    }

    @Override
    public void clickShowCorrect() {
        model.toggleShowCorrect();
    }

    @Override
    public void clickReset() {
        model.getActivePuzzle().getBoard().reset();
        model.notifyObservers();
    }


    @Override
    public void selectPuzzle(int index) {
        model.setActivePuzzleIndex(index);
    }
}
