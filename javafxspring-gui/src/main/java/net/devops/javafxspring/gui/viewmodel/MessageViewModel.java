package net.devops.javafxspring.gui.viewmodel;

import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class MessageViewModel extends Observable {

    private String message;

    public MessageViewModel() {
        setMessage("Default Message!");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message == null || message.equals(this.message)) return;
        this.message = message;
        setChanged();
        notifyObservers();
    }
}
