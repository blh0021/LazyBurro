package LazyBurro;

//import LazyBurro.Request;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

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
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            logOutput.appendText(ts + " > Response Code: " + String.valueOf(request.getResponseCode())+"\n");
        } catch(Exception e) {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            logOutput.appendText(ts + " > " + e.toString() + "\n");
            e.printStackTrace();
        }
    }

    @FXML private void closeApplication(ActionEvent event) {
        Stage stage = (Stage) requestUrl.getScene().getWindow();
        stage.close();
    }

    @FXML private void aboutLazyBurro(ActionEvent event) {
        requestOutput.setText("Lazy Burro v1");
    }
}
