package stock.management.services.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock.management.entities.Products;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.requests.GetPaginatedProducts;
import stock.management.services.dao.implementation.CommonDAO;
import stock.management.services.dao.interfaces.ICommonDAO;
import stock.management.services.dao.interfaces.IProductsDAO;
import stock.management.services.interfaces.IProductsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService implements IProductsService {
    private final IProductsDAO productsDAO;
    private final ICommonDAO<Products> commonDAO;

    @Autowired
    public ProductsService(IProductsDAO productsDAO, SessionFactory sessionFactory)
    {
        this.productsDAO = productsDAO;
        this.commonDAO = new CommonDAO<Products>(sessionFactory, Products.class);;
    }

    @Override
    public List<Products> GetProductsPaginated(GetPaginatedProducts request){
        var products = commonDAO.getAll();
        if (request.getType() != null)
        {
            products = products.stream()
                    .filter(product -> product.getType() == request.getType())
                    .collect(Collectors.toList());
        }
        if (request.getLifeTime() != null)
        {
            products = products.stream()
                    .filter(product -> product.getLifeTime().before(request.getLifeTime()))
                    .collect(Collectors.toList());
        }

        int totalItems = products.size();
        int itemsToSkip = (request.getPage() - 1) * request.getCount();
        int startIndex = Math.min(itemsToSkip, totalItems);
        int endIndex = Math.min(startIndex + request.getCount(), totalItems);

        return products.subList(startIndex, endIndex);
    }

    @Override
    public Products GetProduct(String name) {
        var product = productsDAO.GetProductByName(name);
        return product;
    }

    @Override
    public void AddProduct(Products product) {
        commonDAO.add(product);
    }

    @Override
    public void UpdateProduct(Products product) {
        commonDAO.update(product);
    }
}
