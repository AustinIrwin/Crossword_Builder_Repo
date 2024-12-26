package org.crosswordBuilder.View;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.Clue;
import org.crosswordBuilder.Model.IClue;
import org.crosswordBuilder.Model.IModel;

public class ClueView implements FXComponent{
    private IClue clue;

    public ClueView(IClue clue){
        this.clue = clue;
    }

    /**
     * Render the component and return the resulting Parent object
     */
    @Override
    public Parent render() {
        GridPane gridPane = new GridPane();
        Label num = new Label("" + this.clue.getNum());


        gridPane.add(num, 0, 0);
        num.setAlignment(Pos.TOP_CENTER);
        Label clueText = new Label(this.clue.getClue());
        clueText.setWrapText(true);
        clueText.setMaxWidth(150);
        gridPane.add(clueText, 1,0);
        gridPane.setHgap(10);
        //gridPane.setGridLinesVisible(true);
        gridPane.getStylesheets().add("clue");

        return gridPane;
    }
}
