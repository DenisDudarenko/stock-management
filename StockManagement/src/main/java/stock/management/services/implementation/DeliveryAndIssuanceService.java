package stock.management.services.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock.management.entities.DeliveryAndIssuance;
import stock.management.entities.DeliveryAndIssuanceItems;
import stock.management.entities.Products;
import stock.management.requests.GetPaginatedDeliveryAndIssuance;
import stock.management.responses.DeliveryAndIssuanceView;
import stock.management.responses.ProductView;
import stock.management.services.dao.implementation.CommonDAO;
import stock.management.services.dao.interfaces.*;
import stock.management.services.interfaces.IDeliveryAndIssuanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryAndIssuanceService implements IDeliveryAndIssuanceService {
    private final IDeliveryAndIssuanceItemsDAO deliveryAndIssuanceItemsDAO;
    private final IDeliveryAndIssuanceDAO deliveryAndIssuanceDAO;
    private final ICommonDAO<DeliveryAndIssuance> commonDAO;
    private final ICommonDAO<DeliveryAndIssuanceItems> commonItemsDAO;
    private final IProductsDAO productsDAO;
    private final ISuppliersAndConsumersDAO suppliersAndConsumersDAO;

    @Autowired
    public DeliveryAndIssuanceService(IDeliveryAndIssuanceItemsDAO deliveryAndIssuanceItemsDAO, IDeliveryAndIssuanceDAO deliveryAndIssuanceDAO, SessionFactory sessionFactory, IProductsDAO productsDAO, ISuppliersAndConsumersDAO suppliersAndConsumersDAO)
    {
        this.deliveryAndIssuanceItemsDAO = deliveryAndIssuanceItemsDAO;
        this.deliveryAndIssuanceDAO = deliveryAndIssuanceDAO;
        this.commonDAO = new CommonDAO<DeliveryAndIssuance>(sessionFactory, DeliveryAndIssuance.class);
        this.commonItemsDAO = new CommonDAO<DeliveryAndIssuanceItems>(sessionFactory, DeliveryAndIssuanceItems.class);
        this.productsDAO = productsDAO;
        this.suppliersAndConsumersDAO = suppliersAndConsumersDAO;
    }

    @Override
    public List<DeliveryAndIssuanceView> GetDeliveriesAndIssuancePaginated(GetPaginatedDeliveryAndIssuance request) {
        var deliveriesAndIssuance = commonDAO.getAll();

        if (request.getType() != null)
        {
            deliveriesAndIssuance = deliveriesAndIssuance.stream()
                    .filter(deliveryAndIssuance -> deliveryAndIssuance.getOperationType() == request.getType())
                    .collect(Collectors.toList());
        }
        if (request.getOperationTime() != null)
        {
            deliveriesAndIssuance = deliveriesAndIssuance.stream()
                    .filter(deliveryAndIssuance -> deliveryAndIssuance.getOperationTime().before(request.getOperationTime()))
                    .collect(Collectors.toList());
        }

        List<DeliveryAndIssuanceView> result = new ArrayList<DeliveryAndIssuanceView>();

        for (int i = 0; i < deliveriesAndIssuance.size(); i++)
        {
            var tmp = deliveriesAndIssuance.get(i);
            var newView = new DeliveryAndIssuanceView(tmp.getId(), tmp.getOperationTime(), tmp.getOperationType(), tmp.getOwnerId().getInn());

            var products = deliveryAndIssuanceItemsDAO.getProductsByDeliveryAndIssuance(tmp.getId());
            newView.setProducts(products);
            result.add(newView);
        }

        int totalItems = deliveriesAndIssuance.size();
        int itemsToSkip = (request.getPage() - 1) * request.getCount();
        int startIndex = Math.min(itemsToSkip, totalItems);
        int endIndex = Math.min(startIndex + request.getCount(), totalItems);

        return result.subList(startIndex, endIndex);
    }

    @Override
    public DeliveryAndIssuanceView GetDeliveryAndIssuance(Integer id) {
        var deliveryAndIssuance = commonDAO.getById(id);

        var newView = new DeliveryAndIssuanceView(deliveryAndIssuance.getId(), deliveryAndIssuance.getOperationTime(), deliveryAndIssuance.getOperationType(), deliveryAndIssuance.getOwnerId().getInn());

        var products = deliveryAndIssuanceItemsDAO.getProductsByDeliveryAndIssuance(deliveryAndIssuance.getId());
        newView.setProducts(products);

        return newView;
    }

    @Override
    public void AddDeliveryAndIssuance(DeliveryAndIssuanceView deliveryAndIssuanceView) {
        var supplierAndConsumer = suppliersAndConsumersDAO.GetSuppliersAndConsumersByInn(deliveryAndIssuanceView.getInn());
        var deliveryAndIssuance = new DeliveryAndIssuance(null, supplierAndConsumer, deliveryAndIssuanceView.getOperationTime(), deliveryAndIssuanceView.getOperationType());

        var deliveryAndIssuanceNew = deliveryAndIssuanceDAO.AddDeliveryAndIssuanceAndReturn(deliveryAndIssuance);

        for(int i = 0; i < deliveryAndIssuanceView.getProducts().size(); i++)
        {
            var product = deliveryAndIssuanceView.getProducts().get(i);
            var productId = productsDAO.GetProductByName(product.getName());

            var deliveryAndIssuanceItems = new DeliveryAndIssuanceItems(null, deliveryAndIssuanceNew, productId, product.getQuantity());

            commonItemsDAO.add(deliveryAndIssuanceItems);
        }
    }

    @Override
    public void UpdateDeliveryAndIssuance(DeliveryAndIssuanceView deliveryAndIssuanceView) {
        var supplierAndConsumer = suppliersAndConsumersDAO.GetSuppliersAndConsumersByInn(deliveryAndIssuanceView.getInn());
        var deliveryAndIssuance = new DeliveryAndIssuance(deliveryAndIssuanceView.getId(), supplierAndConsumer, deliveryAndIssuanceView.getOperationTime(), deliveryAndIssuanceView.getOperationType());
        commonDAO.update(deliveryAndIssuance);

        for(int i = 0; i < deliveryAndIssuanceView.getProducts().size(); i++)
        {
            var productView = deliveryAndIssuanceView.getProducts().get(i);
            var product = productsDAO.GetProductByName(productView.getName());

            var deliveryAndIssuanceItems = new DeliveryAndIssuanceItems(productView.getId(), deliveryAndIssuance, product, productView.getQuantity());

            commonItemsDAO.update(deliveryAndIssuanceItems);
        }
    }
}
