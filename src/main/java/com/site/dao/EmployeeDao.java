package com.site.dao;

import com.site.entities.Employee;
import com.site.entities.Position;
import com.site.entities.Task;
import com.site.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao implements Dao<Employee>{

    @Override
    public Employee get(int id) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Employee emp = session.get(Employee.class, id);
        session.close();
        return emp;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getAll() {
        Session session = HibernateSessionFactoryUtil.openSession();

        Query query = session.createQuery("From Employee order by id");
        List<Employee> employees = query.list();
        session.close();

        return employees;
    }

    @Override
    public void save(Employee employee) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(employee);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Employee employee) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.update(employee);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Employee employee) {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(employee);
        tx.commit();
        session.close();
    }

    public List<Task> gelAllTasks(int id) {
        Employee emp = get(id);
        return emp.getTasks();
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getWorkedEmployees() {
        Session session = HibernateSessionFactoryUtil.openSession();

        Query query = session.createQuery("From Employee where fired is null order by id");
        List<Employee> employees = query.list();
        session.close();

        return employees;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getFiredEmployees() {
        Session session = HibernateSessionFactoryUtil.openSession();

        Query query = session.createQuery("From Employee where fired is not null");
        List<Employee> employees = query.list();
        session.close();

        return employees;
    }

    public void updateMonetaryData() {
        Session session = HibernateSessionFactoryUtil.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update Employee set countBonuses = 0, countFines = 0, premium = 0");
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}
