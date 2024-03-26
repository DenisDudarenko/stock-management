package stock.management.services.dao.interfaces;

import stock.management.entities.DeliveryAndIssuanceItems;
import stock.management.entities.Products;
import stock.management.responses.ProductView;

import java.util.List;

public interface IDeliveryAndIssuanceItemsDAO {
    List<ProductView> getProductsByDeliveryAndIssuance(Integer deliveryAndIssuanceId);
}
