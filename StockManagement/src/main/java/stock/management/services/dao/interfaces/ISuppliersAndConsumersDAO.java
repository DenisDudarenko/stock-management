package stock.management.services.dao.interfaces;

import stock.management.entities.SuppliersAndConsumers;

public interface ISuppliersAndConsumersDAO {
    SuppliersAndConsumers GetSuppliersAndConsumersByInn(String inn);
}
