package stock.management.services.interfaces;

import stock.management.entities.Products;
import stock.management.requests.GetPaginatedProducts;

import java.util.List;

public interface IProductsService {
    List<Products> GetProductsPaginated(GetPaginatedProducts request);

    Products GetProduct(String name);

    void AddProduct(Products product);

    void UpdateProduct(Products product);
}
