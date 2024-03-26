package stock.management.services.interfaces;

import stock.management.entities.DeliveryAndIssuance;
import stock.management.entities.Products;
import stock.management.requests.GetPaginatedDeliveryAndIssuance;
import stock.management.requests.GetPaginatedProducts;
import stock.management.responses.DeliveryAndIssuanceView;

import java.util.List;

public interface IDeliveryAndIssuanceService {
    List<DeliveryAndIssuanceView> GetDeliveriesAndIssuancePaginated(GetPaginatedDeliveryAndIssuance request);

    DeliveryAndIssuanceView GetDeliveryAndIssuance(Integer id);

    void AddDeliveryAndIssuance(DeliveryAndIssuanceView product);

    void UpdateDeliveryAndIssuance(DeliveryAndIssuanceView product);
}
