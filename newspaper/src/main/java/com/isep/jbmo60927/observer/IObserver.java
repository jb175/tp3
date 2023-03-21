package com.isep.jbmo60927.observer;

import com.isep.jbmo60927.message.Message;

public interface IObserver {
    public void update(Message message);
}
