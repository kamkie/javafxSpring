package net.devops.javafxspring.gui.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import net.devops.javafxspring.gui.controls.Control;
import net.devops.javafxspring.gui.controls.FirstControl;
import net.devops.javafxspring.gui.controls.PopupControl;
import net.devops.javafxspring.gui.controls.SecondControl;
import net.devops.javafxspring.gui.model.LanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

@Configuration
@Lazy
@Slf4j
public class ScreensConfig implements Observer {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 320;
    public static final String STYLE_FILE = "style/main.css";

    @Value("${spring.application.name}")
    private String title;

    @Autowired
    ApplicationContext applicationContext;

    private Stage stage;
    private Scene scene;
    private LanguageModel lang;
    private StackPane root;
    private final ClassLoader classLoader = getClass().getClassLoader();

    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void setLangModel(LanguageModel lang) {
        if (this.lang != null) {
            this.lang.deleteObserver(this);
        }
        lang.addObserver(this);
        this.lang = lang;
    }

    public void showMainScreen() {
        root = new StackPane();
        root.getStylesheets().add(STYLE_FILE);
        root.getStyleClass().add("main-window");
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
        setNode(getNode(applicationContext.getBean(FirstControl.class), classLoader.getResource("views/First.fxml")));
    }

    public void loadSecond() {
        setNode(getNode(applicationContext.getBean(SecondControl.class), classLoader.getResource("views/Second.fxml")));
    }

    public void loadPopup() {
        showPopup(applicationContext.getBean(PopupControl.class), classLoader.getResource("views/Popup.fxml"));
    }

    @Bean
    @Scope("prototype")
    FirstControl firstPresentation() {
        return new FirstControl();
    }

    @Bean
    @Scope("prototype")
    SecondControl secondPresentation() {
        return new SecondControl();
    }

    @Bean
    @Scope("prototype")
    PopupControl popupPresentation() {
        return new PopupControl();
    }

    private Node getNode(final Control control, URL location) {
        FXMLLoader loader = new FXMLLoader(location, lang.getBundle());
        loader.setControllerFactory(aClass -> control);

        try {
            return loader.load();
        } catch (Exception e) {
            log.error("Error casting node", e);
            return null;
        }
    }

    private void showPopup(final PopupControl control, URL location) {
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
            throw new RuntimeException(e);
        }
        scene.getStylesheets().add(STYLE_FILE);

        popupStage.setScene(scene);
        popupStage.show();
    }

    public void update(Observable o, Object arg) {
        loadFirst();
    }

}
