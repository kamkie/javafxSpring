package net.devops.javafxspring.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class SampleController {

    public Label label;

    public void sayHello(ActionEvent actionEvent) {
        label.setText("hello word");
    }
}
