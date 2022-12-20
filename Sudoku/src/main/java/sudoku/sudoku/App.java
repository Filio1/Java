package sudoku.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.css.PseudoClass;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @SuppressWarnings("FieldMayBeFinal")
    private Sudoku sudoku;
    private Stage primaryStage;
    public App() {
        this.primaryStage = new Stage();
        this.sudoku = new Sudoku();
    }

    Sudoku getSudoku() {
        return this.sudoku;
    }

    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        Parent pr = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("buttons.fxml")));
        Scene scene = new Scene(pr);
        this.primaryStage.setScene(scene);

        this.sudoku.fillValues();
        this.sudoku.removeKDigits();
        this.primaryStage.show();
    }

    public void DrawBoard() {
        GridPane board = new GridPane();

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                StackPane cell = new StackPane();
                cell.getStyleClass().add("cell");
                cell.pseudoClassStateChanged(right, col == 2 || col == 5);
                cell.pseudoClassStateChanged(bottom, row == 2 || row == 5);

                cell.getChildren().add(createTextField(row, col));

                board.add(cell, col, row);
            }
        }

        Scene scene = new Scene(board);
        scene.getStylesheets().add("sudoku.css");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
    private TextField createTextField(int row, int colum) {
        TextField textField = new TextField();

        if (!Integer.toString(this.sudoku.getMat()[row][colum]).equals("0")) {
            textField.setText(Integer.toString(this.sudoku.getMat()[row][colum]));
        }

        return textField ;
    }


    public static void main(String[] args) {
        launch(args);
    }
}