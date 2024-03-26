package stock.management.services.dao.interfaces;

import jdk.jfr.Timespan;
import stock.management.entities.Products;
import stock.management.enums.ProductType;
import stock.management.requests.GetPaginatedProducts;

import java.util.List;

public interface IProductsDAO {
    Products GetProductByName(String Name);
}
