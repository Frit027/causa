package com.site.services;

import com.site.dao.MessengerDao;
import com.site.entities.Messenger;

import java.util.List;

public class MessengerService {
    private final MessengerDao messengerDao = new MessengerDao();

    public Messenger getMessenger(int id) {
        return messengerDao.get(id);
    }

    public List<Messenger> getAllMessengers() {
        return messengerDao.getAll();
    }
}
