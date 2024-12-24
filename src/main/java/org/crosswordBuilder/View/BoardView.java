package org.crosswordBuilder.View;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.IModel;

import static javafx.scene.paint.Color.BLACK;

public class BoardView implements FXComponent{

    private IModel model;

    private IController controller;

    public BoardView(IModel model, IController controller){
        this.model = model;
        this.controller = controller;

    }
    /**
     * Render the component and return the resulting Parent object
     */
    @Override
    public Parent render() {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        int blockSize = 400 / model.getActivePuzzle().getBoard().getSize();

        for(int x = 0; x < model.getActivePuzzle().getBoard().getSize(); x++){
            for(int y = 0; y < model.getActivePuzzle().getBoard().getSize(); y++){
                if(model.getActivePuzzle().getBoard().getBlock(x, y).isClosed()){
                    Rectangle closed = new Rectangle(blockSize, blockSize);
                    closed.setFill(BLACK);
                    gridPane.add(closed, y, x);
                }
                else{
                    Label letter = new Label("" + model.getActivePuzzle().getBoard().getBlock(x, y).getLetter());
                    letter.setMinHeight(blockSize);
                    letter.setMinWidth(blockSize);
                    letter.setMaxWidth(blockSize);
                    letter.setMaxHeight(blockSize);
                    gridPane.add(letter, y, x);
                }
            }


        }

        //gridPane.setMinSize(70,70);
        return gridPane;
    }
}
