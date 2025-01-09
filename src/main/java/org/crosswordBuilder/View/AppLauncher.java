package org.crosswordBuilder.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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

        scene.setOnKeyPressed( keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            switch(keyCode) {
                case UP:
                    controller.blockUp();
                    break;
                case DOWN:
                    controller.blockDown();
                    break;
                case LEFT:
                    controller.blockLeft();
                    break;
                case RIGHT:
                    controller.blockRight();
                    break;
                case TAB:
                    controller.nextWord();
                    break;
                case BACK_SPACE:
                    controller.backspace();
                    break;
                case DELETE:
                    controller.backspace();
                default:
                    break;
            }
            char input = keyCode.getChar().charAt(0);
            if ((input >= 65 && input <= 90) || (input >= 97 && input <= 122)) {
                controller.guessBlock(input);
                model.nextBlock();;
            };
        });

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
