package LazyBurro;

import LazyBurro.Request;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class Controller {
    @FXML private ComboBox<String> requestMethod;

    @FXML private TextField requestUrl;

    @FXML private TextArea requestOutput;

    @FXML private TextArea responseHeaders;

    @FXML private void submitRequest(ActionEvent event) {
        Request request = new Request(requestMethod.getValue(), requestUrl.getText());
        try {
            request.makeRequestCall();
            requestOutput.setText(request.formatResponse());
            responseHeaders.setText(request.getHeaders());
        } catch(Exception e) {
            System.out.println(e);
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
