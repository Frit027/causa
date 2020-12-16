package com.site.dao;

import com.site.entities.Finance;
import com.site.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FinanceDao implements Dao<Finance> {
    @Override
    public Finance get(int id) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Finance finance = session.get(Finance.class, id);
        session.close();
        return finance;
    }

    @Override
    public List<Finance> getAll() {
        Session session = HibernateSessionFactoryUtil.openSession();

        Query query = session.createQuery("From Finance order by id");
        List<Finance> list = query.list();
        session.close();

        return list;
    }

    @Override
    public void save(Finance finance) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(finance);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Finance finance) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.update(finance);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Finance finance) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(finance);
        tx.commit();
        session.close();
    }
}
