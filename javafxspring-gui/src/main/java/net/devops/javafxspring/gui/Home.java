package net.devops.javafxspring.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import net.devops.javafxspring.gui.config.ScreensConfig;
import net.devops.javafxspring.gui.model.LanguageModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class Home extends Application {

    static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Home.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        log.info("Starting application");

        Platform.setImplicitExit(true);

        ScreensConfig screens = applicationContext.getBean(ScreensConfig.class);
        LanguageModel lang = applicationContext.getBean(LanguageModel.class);

        screens.setLangModel(lang);
        screens.setPrimaryStage(primaryStage);
        screens.showMainScreen();
        screens.loadFirst();
    }

    @Override
    public void stop() throws Exception {
        log.info("Stopping application");
        super.stop();
    }

}
