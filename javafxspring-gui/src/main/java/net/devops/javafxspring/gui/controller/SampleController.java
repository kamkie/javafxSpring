package net.devops.javafxspring.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SampleController {

    @FXML
    public Label label;

    @FXML
    public void sayHello(ActionEvent actionEvent) {
        label.setText("hello word");
    }
}
