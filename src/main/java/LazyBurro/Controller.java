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
        logger("Request returned response in: " + request.responseTime + "ms");
    }

    @FXML private void openApplication(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            logger(file.toString());
            FileUtils fu = new FileUtils();
            ConfigFile cfg = new ConfigFile();
            try {
                cfg = fu.openJsonFile(file.toString());
            } catch (IOException ioe) {
                logger(ioe.toString());
            }
            requestUrl.setText(cfg.baseUrl + cfg.path);
            requestHeaders.setText(JSON.objectToString(cfg.header));
            requestMethod.setValue(cfg.method);
        }
    }

    @FXML private void saveApplication(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = chooser.showSaveDialog(new Stage());
        if (file != null) {
            ConfigFile cfg = new ConfigFile();
            cfg.baseUrl = requestUrl.getText();
            cfg.header = JSON.stringtoObject(requestHeaders.getText());
            cfg.method = requestMethod.getValue();

            FileUtils fu = new FileUtils();
            try {
                fu.saveFile(file, cfg);
                logger("Saved file to: " + file.toString());
            } catch(IOException ioe) {
                logger(ioe.toString());
            }


        }
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
