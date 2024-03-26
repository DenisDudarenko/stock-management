package stock.management.services.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import stock.management.entities.DeliveryAndIssuanceItems;
import stock.management.entities.Products;
import stock.management.responses.ProductView;
import stock.management.services.dao.interfaces.IDeliveryAndIssuanceItemsDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeliveryAndIssuanceItemsDAO implements IDeliveryAndIssuanceItemsDAO
{
    private SessionFactory sessionFactory;

    @Autowired
    public DeliveryAndIssuanceItemsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ProductView> getProductsByDeliveryAndIssuance(Integer deliveryAndIssuanceId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            List<DeliveryAndIssuanceItems> items = session
                    .createQuery("from DeliveryAndIssuanceItems where deliveryAndIssuance.id = :deliveryAndIssuanceId", DeliveryAndIssuanceItems.class)
                    .setParameter("deliveryAndIssuanceId", deliveryAndIssuanceId)
                    .getResultList();

            List<ProductView> productViews = new ArrayList<ProductView>();

            for (var item : items) {
                Products product = item.getProduct();
                productViews.add(new ProductView(item.getId(), product.getName(), item.getQuantity()));
            }


            t.commit();
            session.close();

            return productViews;
        } catch (jakarta.persistence.NoResultException e) {
            if (session != null) session.close();

            System.out.println("DeliveryAndIssuanceItems not found");
            return null;
        }
    }
}
