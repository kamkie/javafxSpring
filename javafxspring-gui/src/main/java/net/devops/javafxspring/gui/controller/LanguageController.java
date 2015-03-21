package net.devops.javafxspring.gui.controller;

import net.devops.javafxspring.gui.model.LanguageModel;
import net.devops.javafxspring.gui.model.LanguageModel.Language;

public class LanguageController {

    private LanguageModel model;

    public LanguageController(LanguageModel model) {
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