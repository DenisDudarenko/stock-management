package stock.management.services.dao.implementation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import stock.management.services.dao.interfaces.ICommonDAO;

import java.util.List;

@Service
public class CommonDAO<T> implements ICommonDAO<T> {
    private Class<T> classT;

    private SessionFactory sessionFactory;

    public CommonDAO(){}
    public CommonDAO(SessionFactory sessionFactory, Class<T> classT) {
        this.sessionFactory = sessionFactory;
        this.classT = classT;
    }

    @Override
    public List<T> getAll()
    {
        try
        {
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();
            var result = session.createQuery("FROM " + classT.getSimpleName(), classT).getResultList();
            t.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T getById(int id)
    {
        try
        {
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();
            var result = session.get(classT, id);
            t.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(T obj) {
        try {
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();
            session.persist(obj);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T obj) {
        try {
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();
            session.merge(obj);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
