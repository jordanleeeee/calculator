package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.solver.Solver;

public class Controller {

    @FXML
    private TextField reader;
    private boolean isDisplayingAns = false;

    TextField getReader() {
        return reader;
    }

    @FXML
    void add() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "+");
    }

    @FXML
    void backspace() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
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
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + ")");
    }

    @FXML
    void divide() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "÷");
    }

    @FXML
    void dot() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + ".");
    }

    @FXML
    void equal() {
        Solver solver = new Solver(reader.getText());
        reader.setText(solver.getAnswer());
        isDisplayingAns = true;
    }

    @FXML
    void minus() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "-");
    }

    @FXML
    void openParentheses() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "(");
    }

    @FXML
    void percentage() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "%");
    }

    @FXML
    void pressing1Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "1");
    }

    @FXML
    void pressing2Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "2");
    }

    @FXML
    void pressing3Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "3");
    }

    @FXML
    void pressing4Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "4");
    }

    @FXML
    void pressing5Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "5");
    }

    @FXML
    void pressing6Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "6");
    }

    @FXML
    void pressing7Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "7");
    }

    @FXML
    void pressing8Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "8");
    }

    @FXML
    void pressing9Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "9");
    }

    @FXML
    void pressing0Btn() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "0");
    }

    @FXML
    void times() {
        if(isDisplayingAns){
            clear();
            isDisplayingAns = false;
        }
        reader.setText( reader.getText() + "×");
    }

}

