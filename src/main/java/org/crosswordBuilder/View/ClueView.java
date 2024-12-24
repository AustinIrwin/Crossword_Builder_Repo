package org.crosswordBuilder.View;

import javafx.scene.Parent;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.IModel;

public class ClueView implements FXComponent{
    private IModel model;
    private IController controller;
    int clueNum;

    public ClueView(int clueNum, IModel model, IController controller){
        this.clueNum = clueNum;
        this.model = model;
        this.controller = controller;
    }

    /**
     * Render the component and return the resulting Parent object
     */
    @Override
    public Parent render() {
        return null;
    }
}
