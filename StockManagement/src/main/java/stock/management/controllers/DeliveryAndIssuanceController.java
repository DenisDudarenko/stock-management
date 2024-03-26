package stock.management.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock.management.entities.Products;
import stock.management.requests.GetPaginatedDeliveryAndIssuance;
import stock.management.requests.GetPaginatedProducts;
import stock.management.responses.DeliveryAndIssuanceView;
import stock.management.services.interfaces.IDeliveryAndIssuanceService;

@RestController
public class DeliveryAndIssuanceController {
    private final IDeliveryAndIssuanceService deliveryAndIssuanceService;

    public DeliveryAndIssuanceController(IDeliveryAndIssuanceService deliveryAndIssuanceService) {
        this.deliveryAndIssuanceService = deliveryAndIssuanceService;
    }

    @GetMapping("/deliveries-and-issuance")
    public ResponseEntity<Object> GetDeliveriesAndIssuance(@RequestBody GetPaginatedDeliveryAndIssuance request) {
        var result = deliveryAndIssuanceService.GetDeliveriesAndIssuancePaginated(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/delivery-and-issuance")
    public ResponseEntity<Object> GetDeliveryAndIssuance(@RequestParam Integer id) {
        var result = deliveryAndIssuanceService.GetDeliveryAndIssuance(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/delivery-and-issuance/new")
    public ResponseEntity<Object> AddDeliveryAndIssuance(@RequestBody DeliveryAndIssuanceView product) {
        deliveryAndIssuanceService.AddDeliveryAndIssuance(product);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/delivery-and-issuance/update")
    public ResponseEntity<Object> UpdateDeliveryAndIssuance(@RequestBody DeliveryAndIssuanceView product) {
        deliveryAndIssuanceService.UpdateDeliveryAndIssuance(product);

        return ResponseEntity.ok().build();
    }
}
