package com.isep.jbmo60927.message;

public class Message {

    private final String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
