package stock.management.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import stock.management.entities.Products;
import stock.management.services.dao.implementation.CommonDAO;
import stock.management.services.dao.implementation.DeliveryAndIssuanceDAO;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class CommonDAOTests {
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void AddTest()
    {
        var commonDAO = new CommonDAO<Products>(sessionFactory, Products.class);
        var test = commonDAO.getById(1);
        test.setId(null);
        test.setName("test");
        var all = commonDAO.getAll();
        var before = all.size();
        commonDAO.add(test);
        all = commonDAO.getAll();
        var after = all.size();
        Assertions.assertEquals(before + 1, after);
    }

    @Test
    void UpdateTest()
    {
        var commonDAO = new CommonDAO<Products>(sessionFactory, Products.class);
        var test = commonDAO.getById(14);
        test.setName("TEST");
        commonDAO.update(test);
        test = commonDAO.getById(14);
        Assertions.assertEquals("TEST", test.getName());
    }
}
