package net.devops.javafxspring.gui.config;

import net.devops.javafxspring.gui.controller.LanguageController;
import net.devops.javafxspring.gui.model.LanguageModel;
import net.devops.javafxspring.gui.model.MessageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ScreensConfig.class)
public class AppConfig {
    @Bean
    LanguageModel languageModel() {
        return new LanguageModel();
    }

    @Bean
    LanguageController languageController() {
        return new LanguageController(languageModel());
    }

    @Bean
    MessageModel messageModel() {
        return new MessageModel();
    }
}