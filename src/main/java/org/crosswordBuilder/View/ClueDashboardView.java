package org.crosswordBuilder.View;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.IModel;

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
        vbox.getChildren().add(across);
        vbox.getChildren().add(down);
        return vbox;
    }
}
