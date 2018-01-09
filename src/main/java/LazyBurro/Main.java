package LazyBurro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Lazy Burro");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("images/icon.png")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}