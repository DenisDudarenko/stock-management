package stock.management.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import stock.management.entities.ContactInfo;
import stock.management.entities.DeliveryAndIssuance;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.enums.OperationType;
import stock.management.services.dao.implementation.CommonDAO;
import stock.management.services.dao.implementation.DeliveryAndIssuanceDAO;

import java.sql.Timestamp;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class DeliveryAndIssuanceDAOTests {
    @Autowired
    private DeliveryAndIssuanceDAO deliveryAndIssuanceDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void AddDeliveryAndIssuanceAndReturnTest()
    {
        var contactInfo = new ContactInfo("Москва, улица Ленина, дом 1", "+7(495)123-45-67", "info@rosavto.ru");
        var suppliersAndConsumers = new SuppliersAndConsumers(1, "ООО \"РосАвто\"", "123456789012", contactInfo);
        var newDeliveryAndIssuance = new DeliveryAndIssuance(null, suppliersAndConsumers, new Timestamp(2024, 2, 10, 1, 1, 1, 0), OperationType.Issuance);

        var result = deliveryAndIssuanceDAO.AddDeliveryAndIssuanceAndReturn(newDeliveryAndIssuance);
        Assertions.assertNotNull(result);

        CommonDAO<DeliveryAndIssuance> commonDAO = new CommonDAO<>(sessionFactory, DeliveryAndIssuance.class);
        var test = commonDAO.getById(result.getId());
        Assertions.assertEquals(test, result);
    }
}
