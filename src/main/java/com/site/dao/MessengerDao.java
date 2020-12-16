package com.site.dao;

import com.site.entities.Messenger;
import com.site.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MessengerDao implements Dao<Messenger> {
    @Override
    public Messenger get(int id) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Messenger messenger = session.get(Messenger.class, id);
        session.close();
        return messenger;
    }

    @Override
    public List<Messenger> getAll() {
        Session session = HibernateSessionFactoryUtil.openSession();

        Query query = session.createQuery("From Messenger");
        List<Messenger> list = query.list();
        session.close();

        return list;
    }

    @Override
    public void save(Messenger messenger) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(messenger);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Messenger messenger) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.update(messenger);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Messenger messenger) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(messenger);
        tx.commit();
        session.close();
    }
}
