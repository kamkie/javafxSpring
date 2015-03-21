package net.devops.javafxspring.gui.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.devops.javafxspring.gui.config.ScreensConfig;
import net.devops.javafxspring.gui.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;

public class PopupPresentation extends Modal {

    public PopupPresentation(ScreensConfig config) {
        super(config);
    }

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
        dialog.close();
    }

    @FXML
    void onApply(ActionEvent event) {
        model.setMessage(messageTf.getText());
    }
}
