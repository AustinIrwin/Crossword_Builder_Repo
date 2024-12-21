package org.crosswordBuilder.Model;

public class Model implements IModel{

    IPuzzleLibrary puzzleLibrary;
    int activePuzzleIndex;
    int activeBlockX;
    int activeBlockY;
    IPuzzle.Direction activeDirection;
    boolean showCorrect;
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
    public void nextPuzzle() {
        if(activePuzzleIndex + 1 < puzzleLibrary.size()){
            activePuzzleIndex++;
        }
    }

    @Override
    public void prevPuzzle() {
        if(activePuzzleIndex > 0){
            activePuzzleIndex--;
        }
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
    }

    @Override
    public void prevBlock() {
        //go backwards through a word until hitting a closed space
        //if this is the first block in the word then sit tight
        switch(activeDirection){
            case DOWN -> {
                //if there is space left in this word
                if(activeBlockX - 1 < 0 && !getActivePuzzle().getBoard().getBlock(activeBlockX-1, activeBlockY).isClosed()){
                    activeBlockX--;
                }
                //otherwise go to the next word
            }
            case ACROSS -> {
                if(activeBlockY - 1 < getActivePuzzle().getBoard().getSize() && !getActivePuzzle().getBoard().getBlock(activeBlockX, activeBlockY-1).isClosed()){
                    activeBlockY--;
                }
            }
        }
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
                //word is over or at a new row, increment y until youve found a not closed block
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
                while(activeBlockX - 1 > 0 && !getActivePuzzle().getBoard().getBlock(activeBlockX-1, activeBlockY).isClosed()){
                    activeBlockX--;
                }


                do{

                    if(activeBlockY + 1 < getActivePuzzle().getBoard().getSize()){
                        //still in row, move one to the right and check again
                        activeBlockY = activeBlockY+1;
                    }
                    else{
                        //hit the end of a row
                        if(activeBlockX + 1 < getActivePuzzle().getBoard().getSize()){
                            //hit the end of the row but there are still 2 rows below so we can move down and keep checking
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
    public void randPuzzle() {

    }

    @Override
    public void toggleShowCorrect() {
        showCorrect = !showCorrect;
    }

    @Override
    public void toggleDirection() {
        if(activeDirection == IPuzzle.Direction.DOWN){
            activeDirection = IPuzzle.Direction.ACROSS;
        }
        else{
            activeDirection = IPuzzle.Direction.DOWN;
        }
    }
}
