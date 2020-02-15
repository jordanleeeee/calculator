package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calculator.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Controller controller = loader.getController();
        TextField reader = controller.getReader();

        reader.textProperty().addListener((observable, oldValue, newValue) -> {
            int location = reader.getText().length();
            Platform.runLater(() -> reader.positionCaret(location));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
