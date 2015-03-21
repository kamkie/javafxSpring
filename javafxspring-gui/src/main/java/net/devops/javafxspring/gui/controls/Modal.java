package net.devops.javafxspring.gui.controls;

import net.devops.javafxspring.gui.config.ScreensConfig;

public abstract class Modal extends Presentation {

    protected ModalDialog dialog;

    public Modal(ScreensConfig config) {
        super(config);
    }

    public void setDialog(ModalDialog dialog) {
        this.dialog = dialog;
    }
}