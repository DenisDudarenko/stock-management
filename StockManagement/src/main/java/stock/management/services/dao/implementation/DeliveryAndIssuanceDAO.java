package stock.management.services.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import stock.management.entities.DeliveryAndIssuance;
import stock.management.entities.Products;
import stock.management.services.dao.interfaces.IDeliveryAndIssuanceDAO;

@Repository
public class DeliveryAndIssuanceDAO implements IDeliveryAndIssuanceDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public DeliveryAndIssuanceDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DeliveryAndIssuance AddDeliveryAndIssuanceAndReturn(DeliveryAndIssuance deliveryAndIssuance) {
        try {
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();
            session.persist(deliveryAndIssuance);
            session.flush();
            t.commit();
            session.close();
            return deliveryAndIssuance;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Add DeliveryAndIssuance failed");
            return null;
        }
    }
}
