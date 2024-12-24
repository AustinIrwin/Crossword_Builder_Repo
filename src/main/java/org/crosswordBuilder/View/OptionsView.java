package org.crosswordBuilder.View;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.IModel;

public class OptionsView implements FXComponent{

    private IModel model;

    private IController controller;

    public OptionsView(IModel model, IController controller){
        this.model = model;
        this.controller = controller;
    }
    /**
     * Render the component and return the resulting Parent object
     */
    @Override
    public Parent render() {
        HBox hbox = new HBox();
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(
                (ActionEvent e) -> {
                    controller.clickReset();
                }
        );

        Button checkButton = new Button("Check");
        checkButton.setOnAction(
                (ActionEvent e) -> {
                    controller.clickShowCorrect();
                }
        );

        Button homeButton = new Button("Home");
        homeButton.setOnAction(
                (ActionEvent e) -> {
                    //still working on it
                }
        );

        hbox.getChildren().add(resetButton);
        hbox.getChildren().add(checkButton);
        hbox.getChildren().add(homeButton);
        return hbox;
    }
}
