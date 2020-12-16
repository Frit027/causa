package com.site.dao;

import com.site.entities.ValidMonth;
import com.site.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ValidMonthDao implements Dao<ValidMonth> {
    @Override
    public ValidMonth get(int id) {
        Session session = HibernateSessionFactoryUtil.openSession();
        ValidMonth month = session.get(ValidMonth.class, id);
        session.close();
        return month;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ValidMonth> getAll() {
        Session session = HibernateSessionFactoryUtil.openSession();

        Query query = session.createQuery("From ValidMonth order by id");
        List<ValidMonth> months = query.list();
        session.close();

        return months;
    }

    @Override
    public void save(ValidMonth validMonth) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(validMonth);
        tx.commit();
        session.close();
    }

    @Override
    public void update(ValidMonth validMonth) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.update(validMonth);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(ValidMonth validMonth) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(validMonth);
        tx.commit();
        session.close();
    }
}
