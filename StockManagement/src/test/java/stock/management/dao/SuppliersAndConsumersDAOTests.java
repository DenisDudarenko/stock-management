package stock.management.dao;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.services.dao.implementation.CommonDAO;
import stock.management.services.dao.implementation.ProductsDAO;
import stock.management.services.dao.implementation.SuppliersAndConsumersDAO;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class SuppliersAndConsumersDAOTests {
    @Autowired
    private SuppliersAndConsumersDAO suppliersAndConsumersDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void GetSuppliersAndConsumersByInnTest()
    {
        var inn = "345678901234";
        var testId = 3;
        var result = suppliersAndConsumersDAO.GetSuppliersAndConsumersByInn(inn);

        var commonDAO = new CommonDAO<SuppliersAndConsumers>(sessionFactory, SuppliersAndConsumers.class);
        var test = commonDAO.getById(testId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(test, result);
    }
}
