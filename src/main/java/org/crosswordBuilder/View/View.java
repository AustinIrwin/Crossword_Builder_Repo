package org.crosswordBuilder.View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.IModel;
import org.crosswordBuilder.Model.ModelObserver;

public class View implements FXComponent, ModelObserver {

    private IModel model;
    private IController controller;
    private Stage stage;

    public View(IModel model, IController controller, Stage stage){
        this.model = model;
        this.controller = controller;
        this.stage = stage;
    }

    /**
     * Render the component and return the resulting Parent object
     */
    @Override
    public Parent render() {
        GridPane gridPane = new GridPane();
        Label label = new Label(model.getActivePuzzle().getTitle());
        label.getStyleClass().add("puzzleTitle");
        OptionsView optionsView = new OptionsView(model, controller);
        BoardView boardView = new BoardView(model, controller);
        ClueDashboardView clueDashboardView = new ClueDashboardView(model, controller);

        gridPane.add(label, 0, 0);
        gridPane.add(optionsView.render(), 1, 0);
        gridPane.add(boardView.render(), 0,1);
        gridPane.add(clueDashboardView.render(), 1, 1);
        return gridPane;
    }

    /**
     * When a model value is changed, the model calls update() on all active ModelObserver objects
     *
     * @param model
     */
    @Override
    public void update(IModel model) {
        Scene scene = new Scene(render());
        scene.getStylesheets().add("main.css");
        stage.setScene(scene);
    }
}
