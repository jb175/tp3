package com.isep.jbmo60927.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.isep.jbmo60927.App;
import com.isep.jbmo60927.message.Message;

public class Observer implements IObserver {

    private static final Logger LOGGER = Logger.getLogger(Observer.class.getName());

    private final App app; //not used fyet
    private final String name;

    private Message[] messageList = new Message[0];

    public Observer(App app, String name) {
        LOGGER.setLevel(Level.INFO);
        this.app = app;
        this.name = name;
    }
    
    public void execute() {
        String msgs = displayMessages();
        LOGGER.log(Level.INFO, msgs);
    }

    private String displayMessages() {
        StringBuilder stBuilder = new StringBuilder(String.format("You have %d new message:%n", messageList.length));

        for (int i = 0; i < messageList.length; i++) {
            stBuilder.append(String.format("%d\\ %s%n", i+1, messageList[i].toString()));
        }

        //remove new message
        messageList = new Message[0];

        return stBuilder.toString();
    }

    @Override
    public void update(Message message) {
        ArrayList<Message> messageArray = new ArrayList<>();
        Collections.addAll(messageArray, messageList);
        messageArray.add(message);
        messageList = messageArray.toArray(new Message[messageArray.size()]);
    }

    @Override
    public String toString() {
        return name;
    }
}
