package stock.management.services.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import stock.management.entities.Products;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.services.dao.interfaces.ISuppliersAndConsumersDAO;

@Repository
public class SuppliersAndConsumersDAO implements ISuppliersAndConsumersDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public SuppliersAndConsumersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SuppliersAndConsumers GetSuppliersAndConsumersByInn(String inn) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            SuppliersAndConsumers suppliersAndConsumers = session
                    .createQuery("from SuppliersAndConsumers where inn = :inn", SuppliersAndConsumers.class)
                    .setParameter("inn", inn)
                    .getSingleResult();

            t.commit();
            session.close();

            return suppliersAndConsumers;
        } catch (jakarta.persistence.NoResultException e) {
            if (session != null) session.close();

            System.out.println("SuppliersAndConsumers with inn " + inn + " not found");
            return null;
        }
    }
}
