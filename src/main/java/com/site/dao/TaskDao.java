package com.site.dao;

import com.site.entities.Task;
import com.site.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TaskDao implements Dao<Task> {

    @Override
    public Task get(int id) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Task task = session.get(Task.class, id);
        session.close();
        return task;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> getAll() {
        Session session = HibernateSessionFactoryUtil.openSession();

        Query query = session.createQuery("From Task order by id");
        List<Task> tasks = query.list();
        session.close();

        return tasks;
    }

    @Override
    public void save(Task task) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(task);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Task task) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.update(task);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Task task) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(task);
        tx.commit();
        session.close();
    }
}
