package com.theironyard;

/**
 * Created by Nigel on 7/18/16.
 */
public class Message {
    int id;
    String text;

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
