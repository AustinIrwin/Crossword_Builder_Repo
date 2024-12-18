package org.crosswordBuilder.Model;

public class Clue implements IClue{

    private String clue;
    private IPuzzle.Direction direction;
    private int num;

    private boolean themed;

    public Clue(String clue, IPuzzle.Direction direction, int num, boolean themed){
        this.clue = clue;
        this.direction = direction;
        this.num = num;
        this.themed = themed;
    }

    @Override
    public void setDirection(IPuzzle.Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setClue(String clue) {
        this.clue = clue;
    }

    @Override
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void setThemed(boolean themed) {
        this.themed = themed;
    }

    @Override
    public IPuzzle.Direction getDirection() {
        return direction;
    }

    @Override
    public String getClue() {
        return clue;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public boolean isThemed() {
        return themed;
    }
}
