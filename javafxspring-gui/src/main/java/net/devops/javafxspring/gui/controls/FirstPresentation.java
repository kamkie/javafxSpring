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
import org.springframework.web.client.RestTemplate;

@Slf4j
public class FirstPresentation extends Presentation {

    public FirstPresentation(ScreensConfig config) {
        super(config);
    }

    @FXML
    RadioButton engRadio, romRadio;
    @FXML
    ToggleGroup langGroup;

    @Autowired
    private LanguageController langCtr;

    @Autowired
    RestTemplate restTemplate;

    @FXML
    void nextView(ActionEvent event) {
        User[] users = restTemplate.getForObject("http://localhost:8080/home/userList", User[].class);
        for (User user : users) {
            log.info(user.toString());
        }
        config.loadSecond();
    }

    @FXML
    void initialize() {
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
