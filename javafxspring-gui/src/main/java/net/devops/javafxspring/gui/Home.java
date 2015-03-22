package net.devops.javafxspring.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import net.devops.javafxspring.gui.config.ScreensConfig;
import net.devops.javafxspring.gui.viewmodel.LanguageViewModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@Slf4j
@ComponentScan
@SpringBootApplication
public class Home extends Application {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Home.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        log.info("Starting application");

        Platform.setImplicitExit(true);

        ScreensConfig screensConfig = applicationContext.getBean(ScreensConfig.class);
        LanguageViewModel languageViewModel = applicationContext.getBean(LanguageViewModel.class);

        screensConfig.setLangModel(languageViewModel);
        screensConfig.setPrimaryStage(primaryStage);
        screensConfig.showMainScreen();
        screensConfig.loadFirst();
    }

    @Override
    public void stop() throws Exception {
        log.info("Stopping application");
        super.stop();
    }

}
