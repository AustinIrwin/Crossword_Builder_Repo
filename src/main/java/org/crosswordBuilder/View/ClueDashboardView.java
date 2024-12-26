package org.crosswordBuilder.View;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.IModel;
import org.crosswordBuilder.Model.IPuzzle;

public class ClueDashboardView implements FXComponent{
    private IModel model;

    private IController controller;

    public ClueDashboardView(IModel model, IController controller){
        this.controller = controller;
        this.model = model;
    }
    /**
     * Render the component and return the resulting Parent object
     */
    @Override
    public Parent render() {
        VBox vbox = new VBox();
        Label across = new Label("ACROSS");
        Label down = new Label("DOWN");
        across.getStyleClass().add("cluesHeader");
        down.getStyleClass().add("cluesHeader");
        VBox acrossClues = new VBox();
        VBox downClues = new VBox();

        for(int a = 0; a < model.getActivePuzzle().getClues().length; a++){
            if(model.getActivePuzzle().getClues()[a].getDirection() == IPuzzle.Direction.ACROSS){
                acrossClues.getChildren().add((new ClueView(model.getActivePuzzle().getClues()[a])).render());
            }
            else{
                downClues.getChildren().add((new ClueView(model.getActivePuzzle().getClues()[a])).render());
            }
        }

        ScrollPane acrossScroll = new ScrollPane(acrossClues);
        ScrollPane downScroll = new ScrollPane(downClues);
        //acrossScroll.setFitToHeight(true);
        //downScroll.setFitToHeight(true);
        acrossScroll.setPrefViewportHeight(200);
        downScroll.setPrefViewportHeight(200);
        acrossScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        acrossScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        downScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        downScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        vbox.getChildren().add(across);
        vbox.getChildren().add(acrossScroll);
        vbox.getChildren().add(down);
        vbox.getChildren().add(downScroll);
        return vbox;
    }
}
