package net.devops.javafxspring.gui.controller;

import net.devops.javafxspring.gui.viewmodel.LanguageViewModel;
import net.devops.javafxspring.gui.viewmodel.LanguageViewModel.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageController {

    private LanguageViewModel model;

    @Autowired
    public LanguageController(LanguageViewModel model) {
        this.model = model;
        toEnglish();
    }

    public void toEnglish() {
        model.setBundle(Language.EN);
    }

    public void toRomanian() {
        model.setBundle(Language.RO);
    }

    public Language getLanguage() {
        return model.getLanguage();
    }
}
