package org.crosswordBuilder.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.crosswordBuilder.Controller.Controller;
import org.crosswordBuilder.Controller.IController;
import org.crosswordBuilder.Model.IModel;
import org.crosswordBuilder.Model.IPuzzleLibrary;
import org.crosswordBuilder.Model.Model;
import org.crosswordBuilder.Model.PuzzleLibrary;

import static org.crosswordBuilder.SamplePuzzle.medium_Puzzle;
import static org.crosswordBuilder.SamplePuzzle.mini_Puzzle;

public class AppLauncher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Crushword 3000");

        IModel model = new Model(createLibrary());
        model.nextPuzzle();
        IController controller = new Controller(model);
        View view = new View(model, controller, stage);
        model.addObserver(view);

        Scene scene = new Scene(view.render());
        scene.getStylesheets().add("main.css");
        stage.setScene(scene);
        stage.show();
    }

    public static IPuzzleLibrary createLibrary(){
        IPuzzleLibrary puzzleLibrary = new PuzzleLibrary();
        puzzleLibrary.addPuzzle(mini_Puzzle());
        puzzleLibrary.addPuzzle(medium_Puzzle());
        return puzzleLibrary;
    }
}
