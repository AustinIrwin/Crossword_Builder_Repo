package org.crosswordBuilder.Model;

public interface IClue {

    void setDirection(IPuzzle.Direction direction);
    void setClue(String clue);
    void setNum(int num);
    void setThemed(boolean themed);

    IPuzzle.Direction getDirection();
    String getClue();
    int getNum();
    boolean isThemed();


}
