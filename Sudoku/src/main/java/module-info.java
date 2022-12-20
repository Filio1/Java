module sudoku.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens sudoku.sudoku to javafx.fxml;
    exports sudoku.sudoku;
}