package stock.management.services.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import stock.management.entities.Products;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.requests.GetPaginatedSuppliersAndConsumers;
import stock.management.services.dao.implementation.CommonDAO;
import stock.management.services.dao.interfaces.ICommonDAO;
import stock.management.services.dao.interfaces.ISuppliersAndConsumersDAO;
import stock.management.services.interfaces.ISuppliersAndConsumersService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuppliersAndConsumersService implements ISuppliersAndConsumersService {
    private final ISuppliersAndConsumersDAO suppliersAndConsumersDAO;
    private final ICommonDAO<SuppliersAndConsumers> commonDAO;

    @Autowired
    public SuppliersAndConsumersService(ISuppliersAndConsumersDAO suppliersAndConsumersDAO, SessionFactory sessionFactory) {
        this.suppliersAndConsumersDAO = suppliersAndConsumersDAO;
        this.commonDAO = new CommonDAO<SuppliersAndConsumers>(sessionFactory, SuppliersAndConsumers.class);
    }

    @Override
    public List<SuppliersAndConsumers> GetPaginatedSuppliersAndConsumers(GetPaginatedSuppliersAndConsumers request) {
        var suppliersAndConsumers = commonDAO.getAll();
        if (request.getSupplierInn() != null && request.getSupplierInn() != "")
        {
            return suppliersAndConsumers.stream()
                    .filter(suppliersAndConsumer -> suppliersAndConsumer.getInn().equals(request.getSupplierInn()))
                    .collect(Collectors.toList());
        }

        int totalItems = suppliersAndConsumers.size();
        int itemsToSkip = (request.getPage() - 1) * request.getCount();
        int startIndex = Math.min(itemsToSkip, totalItems);
        int endIndex = Math.min(startIndex + request.getCount(), totalItems);

        return suppliersAndConsumers.subList(startIndex, endIndex);
    }

    @Override
    public SuppliersAndConsumers GetSuppliersAndConsumers(String inn) {
        var product = suppliersAndConsumersDAO.GetSuppliersAndConsumersByInn(inn);
        return product;
    }

    @Override
    public void AddSuppliersAndConsumers(SuppliersAndConsumers suppliersAndConsumers) {
        commonDAO.add(suppliersAndConsumers);
    }

    @Override
    public void UpdateSuppliersAndConsumers(SuppliersAndConsumers suppliersAndConsumers) {
        commonDAO.update(suppliersAndConsumers);
    }
}
