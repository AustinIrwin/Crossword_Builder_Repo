package org.crosswordBuilder.Model;

import java.util.ArrayList;
import java.util.List;

public class Model implements IModel{

    private IPuzzleLibrary puzzleLibrary;
    private int activePuzzleIndex;
    private int activeBlockX;
    private int activeBlockY;
    private IPuzzle.Direction activeDirection;
    private boolean showCorrect;
    private List<ModelObserver> modelObservers;

    public Model(IPuzzleLibrary library){
        this.puzzleLibrary = library;
        this.activeDirection = IPuzzle.Direction.ACROSS;
        this.activePuzzleIndex = 0;
        this.activeBlockX = 0;
        this.activeBlockY = firstY();
        modelObservers = new ArrayList<>();
    }
    @Override
    public IPuzzle getActivePuzzle() {
        return puzzleLibrary.getPuzzle(activePuzzleIndex);
    }

    @Override
    public IBlock getActiveBlock() {
        return getActivePuzzle().getBoard().getBlock(activeBlockX, activeBlockY);
    }

    @Override
    public IPuzzle.Direction getDirection() {
        return activeDirection;
    }

    @Override
    public int getActiveBlockX(){
        return activeBlockX;
    }

    @Override
    public int getActiveBlockY(){
        return activeBlockY;
    }

    @Override
    public boolean getShowCorrect() {
        return showCorrect;
    }

    @Override
    public void nextPuzzle() {
        if(activePuzzleIndex + 1 < puzzleLibrary.size()){
            activePuzzleIndex++;
        }
        notifyObservers();
    }

    @Override
    public void prevPuzzle() {
        if(activePuzzleIndex > 0){
            activePuzzleIndex--;
        }
        notifyObservers();
    }

    @Override
    public void nextBlock() {
        switch(activeDirection){
            case DOWN -> {
                //if there is space left in this word
                if(activeBlockX + 1 < getActivePuzzle().getBoard().getSize() && !getActivePuzzle().getBoard().getBlock(activeBlockX+1, activeBlockY).isClosed()){
                    activeBlockX++;
                }
                //otherwise go to the next word
                else{nextWord();}
            }
            case ACROSS -> {
                if(activeBlockY + 1 < getActivePuzzle().getBoard().getSize() && !getActivePuzzle().getBoard().getBlock(activeBlockX, activeBlockY+1).isClosed()){
                    activeBlockY++;
                }
                else{nextWord();}
            }
        }
        notifyObservers();
    }

    @Override
    public void prevBlock() {
        //go backwards through a word until hitting a closed space
        //if this is the first block in the word then sit tight
        switch(activeDirection){
            case DOWN -> {
                //if there is space left in this word
                if(activeBlockX - 1 >= 0 && !getActivePuzzle().getBoard().getBlock(activeBlockX-1, activeBlockY).isClosed()){
                    activeBlockX--;
                }
                //otherwise go to the next word
            }
            case ACROSS -> {
                if(activeBlockY - 1 >= 0 && !getActivePuzzle().getBoard().getBlock(activeBlockX, activeBlockY-1).isClosed()){
                    activeBlockY--;
                }
            }
        }
        notifyObservers();
    }

    @Override
    public void nextWord() {
        switch(activeDirection){
            case ACROSS -> {
                //increment y until you get to a closed space or the edge
                boolean sameRow = true;

                while(!getActivePuzzle().getBoard().getBlock(activeBlockX, activeBlockY).isClosed() && sameRow){
                    if(activeBlockY + 1 == getActivePuzzle().getBoard().getSize()){
                        //made it to the right edge
                        sameRow = false;
                        if(activeBlockX + 1 == getActivePuzzle().getBoard().getSize()) {
                            //made it to the bottom edge as well
                            //switch direction and go to the first word
                            toggleDirection();
                            activeBlockX = 0;
                            activeBlockY = firstY();
                            return;
                        }
                        else{
                            //go to next row and increment y until it is not closed
                            activeBlockX++;
                            activeBlockY = 0;
                        }
                    }
                    else{
                        activeBlockY++;
                    }
                }
                //sameRow = true;
                //word is over or at a new row, increment y until you've found a not closed block
                while(getActivePuzzle().getBoard().getBlock(activeBlockX, activeBlockY).isClosed()){
                    if(activeBlockY + 1 == getActivePuzzle().getBoard().getSize()){
                        //made it to the right edge
                        //sameRow = false;
                        if(activeBlockX + 1 == getActivePuzzle().getBoard().getSize()) {
                            //made it to the bottom edge as well
                            //switch direction and go to the first word
                            toggleDirection();
                            activeBlockX = 0;
                            activeBlockY = firstY();
                            return;
                        }
                        else{
                            //go to next row and increment y until it is not closed
                            activeBlockX++;
                            activeBlockY = 0;
                        }
                    }
                    else{
                        activeBlockY++;
                    }
                }

            }
            case DOWN -> {
                //gets to the top of the word
                while(activeBlockX - 1 >= 0 && !getActivePuzzle().getBoard().getBlock(activeBlockX-1, activeBlockY).isClosed()){
                    activeBlockX--;
                }
                do{
                    if(activeBlockY + 1 < getActivePuzzle().getBoard().getSize()){
                        //still in row, move one to the right and check again
                        activeBlockY = activeBlockY + 1;
                    }
                    else{
                        //hit the end of a row
                        if(activeBlockX + 1 < getActivePuzzle().getBoard().getSize()){
                            //hit the end of the row but there are still 2 rows below, so we can move down and keep checking
                            activeBlockY = 0;
                            activeBlockX++;
                        }
                        else{
                            //at the edge of the puzzle, reset to first word
                            toggleDirection();
                            activeBlockX = 0;
                            activeBlockY = firstY();
                        }
                    }
                }while( ( activeBlockX != 0  && !getActivePuzzle().getBoard().getBlock(activeBlockX - 1, activeBlockY).isClosed()  ) || getActivePuzzle().getBoard().getBlock(activeBlockX, activeBlockY).isClosed());
                // This will loop until the active block is not closed and the block above it is either closed
                // or is at the upper edge of the puzzle
                // I hope it works
            }
        }
        notifyObservers();
    }

    public int firstY(){
        //int x = 0;
        int y = 0;
        while(getActivePuzzle().getBoard().getBlock(0, y).isClosed()){
            y++;
        }
        return y;
    }

    @Override
    public void toggleShowCorrect() {
        System.out.println("Toggling showCorrect");
        showCorrect = !showCorrect;
        notifyObservers();
    }

    @Override
    public void toggleDirection() {
        if(activeDirection == IPuzzle.Direction.ACROSS){
            System.out.println("Direction is Across");
            activeDirection = IPuzzle.Direction.DOWN;
            System.out.println("Just changed direction to Down");
        }
        else{
            System.out.println("Direction is Down");
            activeDirection = IPuzzle.Direction.ACROSS;
            System.out.println("Just changed direction to Across");
        }
        notifyObservers();
    }

    @Override
    public void setActiveBlock(int x, int y){
        if(x < 0 || y < 0 || x >= getActivePuzzle().getBoard().getSize() || y >= getActivePuzzle().getBoard().getSize()){
            throw new IllegalArgumentException("Desired block index is out of bounds");
        }
        this.activeBlockY = y;
        this.activeBlockX = x;
        notifyObservers();
    }

    @Override
    public void setActivePuzzleIndex(int index){
        if(index < 0 || index >= puzzleLibrary.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        this.activePuzzleIndex = index;
        notifyObservers();
    }

    @Override
    public void addObserver(ModelObserver observer) {
        modelObservers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        modelObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(ModelObserver observer : modelObservers){
            observer.update(this);
        }
    }
}
