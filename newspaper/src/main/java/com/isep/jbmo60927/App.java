package com.isep.jbmo60927;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.isep.jbmo60927.logger.MyLogger;
import com.isep.jbmo60927.observer.Observer;
import com.isep.jbmo60927.publisher.Publisher;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    private Scanner sc = new Scanner(System.in);

    private Publisher[] publishers = new Publisher[0];
    private Observer[] observers = new Observer[0];

    public App() {
        LOGGER.setLevel(Level.INFO);
        this.loggin();
    }

    public void loggin() {
        boolean exit = false;
        while (!exit) {
            LOGGER.log(Level.INFO, "Who are you\n0\\ exit\n1\\ an observer\n2\\ a publisher");
            switch (getNextInt()) {
                case 1:
                    logAsObserver();
                    break;
                case 2:
                    logAsPublisher();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    LOGGER.log(Level.INFO, "unrecognize");
            }
        }
    }

    public void logAsObserver() {
        StringBuilder stBuilder = new StringBuilder("Which one are you:\n0\\ create a new one");
        stBuilder.append(getObserversToString());

        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.log(Level.INFO, stBuilder.toString());

        int selected = getNextInt();
        switch (selected) {
            case 0:
                LOGGER.log(Level.INFO, "Set a name for the observer");
                String name = sc.nextLine();

                //add observer
                ArrayList<Observer> observersList = new ArrayList<>();
                Collections.addAll(observersList, this.observers);
                observersList.add(new Observer(this, name));
                this.observers = observersList.toArray(new Observer[observersList.size()]);

                //launch is console
                getObserver(this.observers.length).execute();
                break;
        
            default:
                getObserver(selected).execute();
                break;
        }
    }

    public void logAsPublisher() {
        StringBuilder stBuilder = new StringBuilder("Which one are you:\n0\\ create a new one");
        stBuilder.append(getPublishersToString());
        
        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.log(Level.INFO, stBuilder.toString());

        int selected = getNextInt();
        switch (selected) {
            case 0:
                LOGGER.log(Level.INFO, "Set a name for the publisher");
                String name = sc.nextLine();

                //add publisher
                ArrayList<Publisher> publisherList = new ArrayList<>();
                Collections.addAll(publisherList, this.publishers);
                publisherList.add(new Publisher(this, name));
                this.publishers = publisherList.toArray(new Publisher[publisherList.size()]);

                //launch is console
                getPublisher(this.publishers.length).execute();
                break;
        
            default:
                getPublisher(selected).execute();
                break;
        }
    }

    public String getPublishersToString() {
        StringBuilder stBuilder = new StringBuilder();
        for (int i = 0; i < publishers.length; i++) {
            stBuilder.append(String.format("%n%d\\ %s", i+1, publishers[i].toString()));
        }
        return stBuilder.toString();
    }

    public String getObserversToString() {
        StringBuilder stBuilder = new StringBuilder();
        for (int i = 0; i < observers.length; i++) {
            stBuilder.append(String.format("%n%d\\ %s", i+1, observers[i].toString()));
        }
        return stBuilder.toString();
    }

    public Publisher getPublisher(int i) {
        if (i > 0 && i <= this.publishers.length)
            return publishers[i-1];
        return null;
    }

    public Observer getObserver(int i) {
        if (i > 0 && i <= this.observers.length)
            return observers[i-1];
        return null;
    }

    public Publisher[] getPublishers() {
        return publishers;
    }

    public Observer[] getObservers() {
        return observers;
    }

    public int getNextInt() {
        String line = sc.nextLine();
        try {
            return Integer.parseInt(line);
        } catch (Exception e) {
            return -1;
        }
    }

    public String getNextString() {
        return sc.nextLine();
    }

    public String getMessage() {
        StringBuilder msg = new StringBuilder();
        String line = sc.nextLine();
        while (!"exit".equals(line)) {
            msg.append(line+"\n");
            line = sc.nextLine();
            LOGGER.log(Level.FINER, line);
        }
        return msg.toString();
    }

    public static void main( String[] args ) throws IOException {
        MyLogger.setup(); //setup the logger for the app
        new App();
    }
}
