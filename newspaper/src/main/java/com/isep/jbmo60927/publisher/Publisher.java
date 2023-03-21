package com.isep.jbmo60927.publisher;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.isep.jbmo60927.App;
import com.isep.jbmo60927.message.Message;
import com.isep.jbmo60927.observer.Observer;

public class Publisher implements IPublisher {

    private static final Logger LOGGER = Logger.getLogger(Publisher.class.getName());
    private static final String[] OPTIONS = new String[] {
        "Attach",
        "Detach",
        "Notify",
        "List"
    };

    private final App app;
    private final String name;

    private ArrayList<Observer> messagePublisher = new ArrayList<>();

    public Publisher(App app, String name) {
        LOGGER.setLevel(Level.INFO);
        this.app = app;
        this.name = name;
    }
    
    public void execute() {
        while (true) {
            StringBuilder stringBuilder = new StringBuilder("What do you wants to do\nExit\\ exit this publisher\n");
            for (String string : OPTIONS) {
                switch (string) {
                    case "Attach":
                        stringBuilder.append("Attach\\ attach a new observer\n");
                        break;
                    case "Detach":
                        stringBuilder.append("Detach\\ detach an observer\n");
                        break;
                    case "Notify":
                        stringBuilder.append("Notify\\ notify all your observers\n");
                        break;
                    case "List":
                        stringBuilder.append("List\\ list all your observers\n");
                        break;
                    default:
                        break;
                }
            }

            if (LOGGER.isLoggable(Level.INFO))
                LOGGER.log(Level.INFO, stringBuilder.toString());
            
            String selected = this.app.getNextString();
            switch (selected.toLowerCase().trim()) {
                case "attach":
                    if (LOGGER.isLoggable(Level.INFO))
                        LOGGER.log(Level.INFO, String.format("Which observer do you wants to add%s", this.app.getObserversToString()));
                    Observer toAdd = this.app.getObserver(this.app.getNextInt()); //add this observer to the list
                    this.attach(toAdd);
                    break;
                case "detach":
                    if (LOGGER.isLoggable(Level.INFO))
                        LOGGER.log(Level.INFO, String.format("Which observer do you wants to remove%s", this.app.getObserversToString()));
                    Observer toRemove = this.app.getObserver(this.app.getNextInt()); //remove this observer from the list
                    this.detach(toRemove);
                    break;
                case "notify":
                    if (LOGGER.isLoggable(Level.INFO))
                        LOGGER.log(Level.INFO, "What message do you wants to send to your observers (type exit to exit):");
                    notifyUpdate(new Message(this.app.getMessage()));
                    break;
                case "list":
                    if (LOGGER.isLoggable(Level.INFO))
                        LOGGER.log(Level.INFO, this.app.getObserversToString());
                    break;
                case "exit":
                    return;
                default:
                    if (LOGGER.isLoggable(Level.INFO))
                        LOGGER.log(Level.INFO, String.format("Unrecognize input \"%s\"", selected));
                    break;
            }
        }
    }

    @Override
    public void attach(Observer toAdd) {
        if (messagePublisher.contains(toAdd) && LOGGER.isLoggable(Level.INFO))
            LOGGER.log(Level.INFO, String.format("%s was already in this list", toAdd.toString()));
        else {
            messagePublisher.add(toAdd);
            if (LOGGER.isLoggable(Level.INFO))
                LOGGER.log(Level.INFO, String.format("%s sucessfully added to the list", toAdd.toString()));
        }
    }

    @Override
    public void detach(Observer toRemove) {
        if (messagePublisher.contains(toRemove) && LOGGER.isLoggable(Level.INFO)) {
            messagePublisher.remove(toRemove);
            if (LOGGER.isLoggable(Level.INFO))
                LOGGER.log(Level.INFO, String.format("%s sucessfully removed from the list", toRemove.toString()));
        } else {
            LOGGER.log(Level.INFO, String.format("%s was not in this list", toRemove.toString()));
        }
    }

    @Override
    public void notifyUpdate(Message m) {
        for (Observer observer : messagePublisher) {
            observer.update(m);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
