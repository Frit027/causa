package com.site.dao;

import com.site.entities.Position;
import com.site.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PositionDao implements Dao<Position> {

    @Override
    public Position get(int id) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Position position = session.get(Position.class, id);
        session.close();
        return position;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> getAll() {
        Session session = HibernateSessionFactoryUtil.openSession();

        Query query = session.createQuery("From Position");
        List<Position> positions = query.list();
        session.close();

        return positions;
    }

    @Override
    public void save(Position position) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(position);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Position position) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.update(position);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Position position) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(position);
        tx.commit();
        session.close();
    }

    public int getIdByName(String name) {
        name = name.toLowerCase();
        List<Position> list = getAll();

        for (Position p : getAll()) {
            if (p.getName().toLowerCase().equals(name)) {
                return p.getId();
            }
        }
        return list.get(0).getId();
    }
}
