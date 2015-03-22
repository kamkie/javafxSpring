package net.devops.javafxspring.gui.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.devops.javafxspring.gui.config.ScreensConfig;
import net.devops.javafxspring.gui.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;

public class PopupControl implements Control {

    private Stage stage;

    @Autowired
    private ScreensConfig screensConfig;

    @Autowired
    private MessageModel model;

    @FXML
    TextField messageTf;

    @FXML
    void initialize() {
        messageTf.setText(model.getMessage());
    }

    @FXML
    void clickedOk(ActionEvent event) {
        stage.close();
    }

    @FXML
    void onApply(ActionEvent event) {
        model.setMessage(messageTf.getText());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}