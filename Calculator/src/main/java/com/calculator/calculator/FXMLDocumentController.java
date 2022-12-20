package com.calculator.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDocumentController //implements Initializable
{
    @FXML
    private TextField tfNumber1, tfNumber2, tfResult;
    @FXML
    private void addButtonAction(ActionEvent event){
        tfResult.setText(getResult('+') + "");
    }
    @FXML
    private void subtractButtonAction(ActionEvent event) {
        tfResult.setText(getResult('-') + "");
    }
    @FXML
    private void multiplyButtonAction(ActionEvent event) {
        tfResult.setText(getResult('*') + "");
    }
    @FXML
    private void divideButtonAction(ActionEvent event) {
        tfResult.setText(getResult('/') + "");
    }
    private double getResult(char op) {
        double number1 = Double.parseDouble(tfNumber1.getText());
        double number2 = Double.parseDouble(tfNumber2.getText());
        return switch (op) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case '*' -> number1 * number2;
            case '/' -> number1 / number2;
            default -> Double.NaN;
        };
    }
}
