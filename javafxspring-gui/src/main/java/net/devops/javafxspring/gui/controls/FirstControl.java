package net.devops.javafxspring.gui.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import lombok.extern.slf4j.Slf4j;
import net.devops.javafxspring.common.model.User;
import net.devops.javafxspring.gui.config.ScreensConfig;
import net.devops.javafxspring.gui.controller.LanguageController;
import net.devops.javafxspring.gui.model.LanguageModel.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

@Slf4j
public class FirstControl implements Control {

    @Autowired
    private ScreensConfig config;

    @Autowired
    private LanguageController langCtr;

    @Autowired
    private AsyncRestTemplate restTemplate;

    @FXML
    public RadioButton engRadio, romRadio;
    @FXML
    public ToggleGroup langGroup;

    @FXML
    public void nextView(ActionEvent event) {
        ListenableFuture<ResponseEntity<User[]>> usersFuture = restTemplate.getForEntity("http://localhost:8080/home/userList", User[].class);
        usersFuture.addCallback(result -> {
            log.info("success getting users");
            User[] users = result.getBody();
            for (User user : users) {
                log.info(user.toString());
            }
        }, ex -> {
            log.error("error getting users", ex);
        });

        config.loadSecond();
    }

    @FXML
    public void initialize() {
        if (Language.RO.equals(langCtr.getLanguage())) {
            engRadio.setSelected(false);
            romRadio.setSelected(true);
        }
        langGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            changeLanguage();
        });
    }

    private void changeLanguage() {
        if (engRadio.isSelected())
            langCtr.toEnglish();
        else
            langCtr.toRomanian();
    }
}
