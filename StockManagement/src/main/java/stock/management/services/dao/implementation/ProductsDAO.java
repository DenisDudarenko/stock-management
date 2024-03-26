package stock.management.services.dao.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import stock.management.entities.Products;
import stock.management.services.dao.interfaces.IProductsDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Repository
public class ProductsDAO extends CommonDAO<Products> implements IProductsDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public ProductsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Products GetProductByName(String name) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            Products products = session
                    .createQuery("from Products where name = :name", Products.class)
                    .setParameter("name", name)
                    .getSingleResult();

            t.commit();
            session.close();

            return products;
        } catch (jakarta.persistence.NoResultException e) {
            if (session != null) session.close();

            System.out.println("Product with name " + name + " not found");
            return null;
        }
    }

//    public Boolean AddProduct(Products product) {
//        try {
//            Session session = sessionFactory.openSession();
//            Transaction t = session.beginTransaction();
//            session.persist(product);
//            session.flush();
//            t.commit();
//            session.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Add product with name " + product.getName() + " failed");
//            return false;
//        }
//    }
//
//    public Boolean UpdateProduct(Products product) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
//            String sql = "Update products set name = ?, type = ?, quantity = ?, measurement = ?, lifeTime = ?, storage_place = ? WHERE products.id = ?";
//            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setObject(2, product.getType().toString(), Types.OTHER); // Types.OTHER - для перечисления
//            preparedStatement.setInt(3, product.getQuantity());
//            preparedStatement.setObject(4, product.getMeasurement().toString(), Types.OTHER);
//            preparedStatement.setTimestamp(5, product.getLifeTime());
//            ObjectMapper objectMapper = new ObjectMapper();
//            String storagePlaceJson = objectMapper.writeValueAsString(product.getStoragePlace());
//            preparedStatement.setObject(6, storagePlaceJson, Types.OTHER);
//            preparedStatement.setInt(7, product.getId());
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected == 1) {
//                System.out.println("Product added successfully");
//                return true;
//            } else {
//                System.out.println("Failed to add product");
//                return false;
//            }
//        } catch (Exception e) {
//            System.out.println("Add product with name " + product.getName() + " failed");
//            return false;
//        }
//    }
}
