package stock.management.services.interfaces;

import stock.management.entities.Products;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.requests.GetPaginatedSuppliersAndConsumers;

import java.util.List;

public interface ISuppliersAndConsumersService {
    List<SuppliersAndConsumers> GetPaginatedSuppliersAndConsumers(GetPaginatedSuppliersAndConsumers request);

    SuppliersAndConsumers GetSuppliersAndConsumers(String inn);

    void AddSuppliersAndConsumers(SuppliersAndConsumers suppliersAndConsumers);

    void UpdateSuppliersAndConsumers(SuppliersAndConsumers suppliersAndConsumers);
}
