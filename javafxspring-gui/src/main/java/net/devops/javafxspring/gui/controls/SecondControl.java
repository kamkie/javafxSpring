package net.devops.javafxspring.gui.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.devops.javafxspring.gui.config.ScreensConfig;
import net.devops.javafxspring.gui.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;

public class SecondControl implements Control {

    @Autowired
    private ScreensConfig config;

    @Autowired
    private MessageModel model;

    @FXML
    public TextField messageTf;

    @FXML
    public void initialize() {
        messageTf.setText(model.getMessage());

        model.addObserver((o, arg) -> messageTf.setText(model.getMessage()));
    }

    @FXML
    public void onApply(ActionEvent event) {
        model.setMessage(messageTf.getText());
    }

    @FXML
    public void prevView(ActionEvent event) {
        config.loadFirst();
    }

    @FXML
    public void openPopup(ActionEvent event) {
        config.loadPopup();
    }


}
