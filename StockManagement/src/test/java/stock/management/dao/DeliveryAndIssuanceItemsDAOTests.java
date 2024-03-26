package stock.management.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import stock.management.entities.DeliveryAndIssuanceItems;
import stock.management.services.dao.implementation.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class DeliveryAndIssuanceItemsDAOTests {
    @Autowired
    private DeliveryAndIssuanceItemsDAO deliveryAndIssuanceDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void getProductsByDeliveryAndIssuanceTest(){
        var commonDAO = new CommonDAO<DeliveryAndIssuanceItems>(sessionFactory, DeliveryAndIssuanceItems.class);
        var deliveryId = 1;
        var products = deliveryAndIssuanceDAO.getProductsByDeliveryAndIssuance(deliveryId);

        Assertions.assertNotNull(products);
        Assertions.assertEquals(2, products.size());
        Assertions.assertTrue(products.stream().allMatch(p -> p.getName().equals("Отбеливатель") || p.getName().equals("Футболка")));
    }
}
