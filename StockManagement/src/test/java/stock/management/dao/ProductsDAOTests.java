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
import stock.management.services.dao.implementation.ProductsDAO;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class ProductsDAOTests {
    @Autowired
    private ProductsDAO productsDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void GetProductByNameTest()
    {
        var name = "Яблоко";
        var result = productsDAO.GetProductByName(name);
        var testId = 1;
        var commonDAO = new CommonDAO<Products>(sessionFactory, Products.class);
        var test = commonDAO.getById(testId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(test, result);
    }
}
