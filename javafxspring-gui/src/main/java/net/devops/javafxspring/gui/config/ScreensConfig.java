package net.devops.javafxspring.gui.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import net.devops.javafxspring.gui.controller.Controller;
import net.devops.javafxspring.gui.controller.FirstController;
import net.devops.javafxspring.gui.controller.PopupController;
import net.devops.javafxspring.gui.controller.SecondController;
import net.devops.javafxspring.gui.viewmodel.LanguageViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

@Slf4j
@Component
public class ScreensConfig implements Observer {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 320;

    @Value("${spring.application.name}")
    private String title;

    @Autowired
    FirstController firstControl;

    @Autowired
    SecondController secondControl;

    @Autowired
    PopupController popupControl;

    private Stage stage;
    private Scene scene;
    private LanguageViewModel lang;
    private StackPane root;
    private final ClassLoader classLoader = getClass().getClassLoader();

    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void setLangModel(LanguageViewModel lang) {
        if (this.lang != null) {
            this.lang.deleteObserver(this);
        }
        lang.addObserver(this);
        this.lang = lang;
    }

    public void showMainScreen() {
        root = new StackPane();
        stage.setTitle(title);
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setOnHiding(event -> {
            // add code to open an "are you sure you want to exit?" dialog
            System.exit(0);
        });

        stage.show();
    }

    private void setNode(Node node) {
        root.getChildren().setAll(node);
    }

    private void setNodeOnTop(Node node) {
        root.getChildren().add(node);
    }

    public void removeNode(Node node) {
        root.getChildren().remove(node);
    }

    public void loadFirst() {
        setNode(getNode(firstControl, classLoader.getResource("views/First.fxml")));
    }

    public void loadSecond() {
        setNode(getNode(secondControl, classLoader.getResource("views/Second.fxml")));
    }

    public void loadPopup() {
        showPopup(popupControl, classLoader.getResource("views/Popup.fxml"));
    }

    private Node getNode(final Controller controller, URL location) {
        FXMLLoader loader = new FXMLLoader(location, lang.getBundle());
        loader.setControllerFactory(aClass -> controller);

        try {
            return loader.load();
        } catch (Exception e) {
            log.error("Error casting node", e);
            return null;
        }
    }

    private void showPopup(final PopupController control, URL location) {
        FXMLLoader loader = new FXMLLoader(location, lang.getBundle());
        loader.setControllerFactory(aClass -> control);

        Stage popupStage = new Stage();
        control.setStage(popupStage);

        popupStage.setTitle(lang.getBundle().getString("popup.title"));
        popupStage.initStyle(StageStyle.DECORATED);
        popupStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            log.error("unable to load fxml", e);
            return;
        }

        popupStage.setScene(scene);
        popupStage.show();
    }

    public void update(Observable o, Object arg) {
        loadFirst();
    }

}
