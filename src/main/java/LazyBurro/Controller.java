package LazyBurro;

import LazyBurro.Config.ConfigFile;
import LazyBurro.Helper.FileUtils;
import LazyBurro.Helper.JSON;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Controller {
    @FXML private TextArea logOutput;

    @FXML private ComboBox<String> requestMethod;

    @FXML private TextField requestUrl;

    @FXML private TextArea requestOutput;

    @FXML private TextArea requestHeaders;

    @FXML private TextArea responseHeaders;

    @FXML private void submitRequest(ActionEvent event) {
        Request request = new Request(requestMethod.getValue(), requestUrl.getText(), requestHeaders.getText());
        try {
            request.makeRequestCall();
            requestOutput.setText(request.formatResponse());
            responseHeaders.setText(request.getHeaders());
            logger("Response Code: " + String.valueOf(request.getResponseCode()));
        } catch(Exception e) {
            logger(e.toString());
            e.printStackTrace();
        }
    }

    @FXML private void openApplication(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        logger(file.toString());
        FileUtils fu = new FileUtils();
        ConfigFile cfg = new ConfigFile();
        try {
            cfg = fu.openJsonFile(file.toString());
        } catch(IOException ioe) {
            logger(ioe.toString());
        }
        requestUrl.setText(cfg.baseUrl + cfg.path);
        requestHeaders.setText(JSON.objectToString(cfg.header));
        requestMethod.setValue(cfg.method);
    }

    @FXML private void saveApplication(ActionEvent event) {

    }

    @FXML private void closeApplication(ActionEvent event) {
        Stage stage = (Stage) requestUrl.getScene().getWindow();
        stage.close();
    }

    @FXML private void aboutLazyBurro(ActionEvent event) {
        requestOutput.setText("Lazy Burro v1");
    }

    @FXML private void logger(String txt) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        logOutput.appendText(ts + " > " + txt + "\n");
    }
}
