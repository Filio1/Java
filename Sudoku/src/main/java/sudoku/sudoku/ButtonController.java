package sudoku.sudoku;

import javafx.fxml.FXML;

public class ButtonController {
    @SuppressWarnings("FieldMayBeFinal")
    private sudoku.sudoku.App app =  new sudoku.sudoku.App();
    @FXML
    protected void onSolveClick() {
        this.app.getSudoku().solveBoard();
        this.app.DrawBoard();
    }
    @FXML
    protected void onGenerateClick() {
        this.app.getSudoku().fillValues();
        this.app.DrawBoard();
    }
}