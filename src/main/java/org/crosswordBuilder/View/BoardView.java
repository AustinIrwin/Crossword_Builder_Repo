package org.crosswordBuilder.View;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.IModel;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.YELLOW;

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
        int wordNum = 1;
        for(int x = 0; x < model.getActivePuzzle().getBoard().getSize(); x++){
            for(int y = 0; y < model.getActivePuzzle().getBoard().getSize(); y++){
                if(model.getActivePuzzle().getBoard().getBlock(x, y).isClosed()){
                    Rectangle closed = new Rectangle(blockSize, blockSize);
                    closed.setFill(BLACK);
                    gridPane.add(closed, y, x);
                }
                else{
                    VBox vbox = new VBox();
                    Label number;
                    if(x == 0 || model.getActivePuzzle().getBoard().getBlock(x-1, y).isClosed()){
                         number = new Label("" + wordNum);
                        wordNum++;
                    }
                    else{
                        number = new Label();
                    }
                    number.setMinWidth(blockSize);
                    number.setMaxWidth(blockSize);
                    number.setMinHeight(blockSize * 0.25);
                    number.setMaxHeight(blockSize * 0.25);
                    number.getStyleClass().add("wordNum");
                    Font numberFont = new Font(blockSize* .25);
                    number.setFont(numberFont);

                    model.getActivePuzzle().getBoard().getBlock(x, y).setGuess('A');
                    Label letter = new Label("" + model.getActivePuzzle().getBoard().getBlock(x, y).getGuess());
                    letter.setMinHeight(blockSize * 0.75);
                    letter.setMinWidth(blockSize);
                    letter.setMaxWidth(blockSize);
                    Font letterFont = new Font(blockSize*.75);

                    letter.setFont(letterFont);
                    letter.setMaxHeight(blockSize * 0.75);
                    letter.getStyleClass().add("guess");
                    letter.setAlignment(Pos.CENTER);
                    if(model.getShowCorrect() && !model.getActivePuzzle().getBoard().getBlock(x, y).isCorrect()){
                        letter.getStyleClass().add("wrong");
                    }

                    vbox.getChildren().add(number);
                    vbox.getChildren().add(letter);

                    if (x == model.getActiveBlockX() && y == model.getActiveBlockY()){
                        vbox.getStyleClass().add("activeBlock");
                    }

                    gridPane.add(vbox, y, x);
                }
            }


        }

        //gridPane.setMinSize(70,70);
        return gridPane;
    }
}
