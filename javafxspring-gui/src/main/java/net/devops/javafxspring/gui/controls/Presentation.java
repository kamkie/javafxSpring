package net.devops.javafxspring.gui.controls;

import net.devops.javafxspring.gui.config.ScreensConfig;

public abstract class Presentation {

    protected ScreensConfig config;

    public Presentation(ScreensConfig config) {
        this.config = config;
    }
}
