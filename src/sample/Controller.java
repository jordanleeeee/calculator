package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.solver.Solver;

public class Controller {

    @FXML
    private TextField reader;

    TextField getReader() {
        return reader;
    }

    @FXML
    void add() {
        reader.setText( reader.getText() + "+");
    }

    @FXML
    void backspace() {
        int length = reader.getLength();
        if(length != 0) {
            reader.setText(reader.getText(0, length-1));
        }
    }

    @FXML
    void clear() {
        reader.clear();
    }

    @FXML
    void closeParentheses() {
        reader.setText( reader.getText() + ")");
    }

    @FXML
    void divide() {
        reader.setText( reader.getText() + "÷");
    }

    @FXML
    void dot() {
        reader.setText( reader.getText() + ".");
    }

    @FXML
    void equal() {
        Solver solver = new Solver(reader.getText());
        reader.setText(solver.getAnswer());
    }

    @FXML
    void minus() {
        reader.setText( reader.getText() + "-");
    }

    @FXML
    void openParentheses() {
        reader.setText( reader.getText() + "(");
    }

    @FXML
    void percentage() {
        reader.setText( reader.getText() + "%");
    }

    @FXML
    void pressing1Btn() {
        reader.setText( reader.getText() + "1");
    }

    @FXML
    void pressing2Btn() {
        reader.setText( reader.getText() + "2");
    }

    @FXML
    void pressing3Btn() {
        reader.setText( reader.getText() + "3");
    }

    @FXML
    void pressing4Btn() {
        reader.setText( reader.getText() + "4");
    }

    @FXML
    void pressing5Btn() {
        reader.setText( reader.getText() + "5");
    }

    @FXML
    void pressing6Btn() {
        reader.setText( reader.getText() + "6");
    }

    @FXML
    void pressing7Btn() {
        reader.setText( reader.getText() + "7");
    }

    @FXML
    void pressing8Btn() {
        reader.setText( reader.getText() + "8");
    }

    @FXML
    void pressing9Btn() {
        reader.setText( reader.getText() + "9");
    }

    @FXML
    void pressing0Btn() {
        reader.setText( reader.getText() + "0");
    }

    @FXML
    void times() {
        reader.setText( reader.getText() + "×");
    }

}

