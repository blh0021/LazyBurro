package LazyBurro;

import LazyBurro.Helper.FileUtils;

import javafx.fxml.FXML;

import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Controller {
    @FXML private TextArea logOutput;

    @FXML private TextArea requestOutput;

    @FXML private TextArea requestHeaders;

    @FXML private TextArea responseHeaders;

    @FXML private void submitRequest(ActionEvent event) {
        RequestHandler rh = new RequestHandler(requestHeaders.getText());
        try {
            rh.makeRequestCall();
            requestOutput.setText(rh.formatResponse());
            responseHeaders.setText(rh.getHeaders());
        } catch(Exception e) {
            logger("ERROR: " + e.getMessage());
        }
        logger("Request returned response in: " + rh.getResponseTime() + "ms");
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
            JSONObject cfg=new JSONObject();
            try {
                cfg = fu.openJsonFile(file.toString());
            } catch (IOException ioe) {
                logger(ioe.toString());
            }
            requestHeaders.setText(cfg.toString(4));
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
            String req = requestHeaders.getText();

            FileUtils fu = new FileUtils();
            try {
                fu.saveFile(file, req);
                logger("Saved file to: " + file.toString());
            } catch(IOException ioe) {
                logger(ioe.toString());
            }
        }
    }

    @FXML private void closeApplication(ActionEvent event) {
        Stage stage = (Stage) requestOutput.getScene().getWindow();
        stage.close();
    }

    @FXML private void aboutLazyBurro(ActionEvent event) {
        requestOutput.setText("Lazy Burro v0.0.1");
    }

    @FXML private void logger(String txt) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        logOutput.appendText(ts + " > " + txt + "\n");
    }
}
