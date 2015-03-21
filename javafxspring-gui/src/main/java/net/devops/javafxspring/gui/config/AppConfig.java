package net.devops.javafxspring.gui.config;

import net.devops.javafxspring.gui.controller.LanguageController;
import net.devops.javafxspring.gui.model.LanguageModel;
import net.devops.javafxspring.gui.model.MessageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
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

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}