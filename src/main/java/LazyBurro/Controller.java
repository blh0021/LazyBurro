package LazyBurro;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class Controller {
    @FXML private TextField requestUrl;

    @FXML private TextArea requestOutput;

    @FXML private void submitRequest(ActionEvent event) {
        try {
            requestOutput.setText(ProcessRequest.sendGet(requestUrl.getText()));
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
