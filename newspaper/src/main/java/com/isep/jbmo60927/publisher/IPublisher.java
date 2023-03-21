package com.isep.jbmo60927.publisher;

import com.isep.jbmo60927.message.Message;
import com.isep.jbmo60927.observer.Observer;

public interface IPublisher {
    public void attach(Observer o);
    public void detach(Observer o);
    public void notifyUpdate(Message m);
}
