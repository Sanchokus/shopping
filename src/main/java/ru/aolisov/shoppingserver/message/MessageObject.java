package ru.aolisov.shoppingserver.message;

import java.io.Serializable;

/**
 * Created by Alex on 3/4/2016.
 */
public class MessageObject implements Serializable {
    private String message = "";

    public MessageObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
