package net.devops.javafxspring.gui.controls;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class ModalDialog extends Stage {

    private Scene scene;

    public ModalDialog(Modal controller, URL fxml, Window owner, ResourceBundle bundle) {
        this(controller, fxml, owner, StageStyle.DECORATED, Modality.APPLICATION_MODAL, bundle);
    }

    public ModalDialog(final Modal controller, URL fxml, Window owner, StageStyle style, Modality modality, ResourceBundle bundle) {
        super(style);
        initOwner(owner);
        initModality(modality);
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setResources(bundle);
        try {
            loader.setControllerFactory(aClass -> controller);
            controller.setDialog(this);
            scene = new Scene(loader.load());
            setScene(scene);
        } catch (IOException e) {
            log.error("Error loading modal class", e);
            throw new RuntimeException(e);
        }
    }

    public ObservableList<String> getStyleSheets() {
        return scene.getStylesheets();
    }
}