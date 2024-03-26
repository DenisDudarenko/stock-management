package stock.management.services.dao.interfaces;

import stock.management.entities.DeliveryAndIssuance;

public interface IDeliveryAndIssuanceDAO {
    DeliveryAndIssuance AddDeliveryAndIssuanceAndReturn(DeliveryAndIssuance deliveryAndIssuance);
}
