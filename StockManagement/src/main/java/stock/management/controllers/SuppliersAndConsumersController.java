package stock.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock.management.entities.Products;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.requests.GetPaginatedProducts;
import stock.management.requests.GetPaginatedSuppliersAndConsumers;
import stock.management.services.interfaces.IProductsService;
import stock.management.services.interfaces.ISuppliersAndConsumersService;

@RestController
public class SuppliersAndConsumersController {
    private final ISuppliersAndConsumersService suppliersAndConsumersService;

    @Autowired
    public SuppliersAndConsumersController(ISuppliersAndConsumersService suppliersAndConsumersService) {
        this.suppliersAndConsumersService = suppliersAndConsumersService;
    }

    @GetMapping("/suppliers-and-consumers")
    public ResponseEntity<Object> GetSuppliersAndConsumers(@RequestBody GetPaginatedSuppliersAndConsumers request) {
        var result = suppliersAndConsumersService.GetPaginatedSuppliersAndConsumers(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/supplier-and-consumer")
    public ResponseEntity<Object> GetSupplierAndConsumer(@RequestParam String inn) {
        var result = suppliersAndConsumersService.GetSuppliersAndConsumers(inn);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/supplier-and-consumer/new")
    public ResponseEntity<Object> AddSupplierAndConsumer(@RequestBody SuppliersAndConsumers suppliersAndConsumers) {
        suppliersAndConsumersService.AddSuppliersAndConsumers(suppliersAndConsumers);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/supplier-and-consumer/update")
    public ResponseEntity<Object> UpdateSupplierAndConsumer(@RequestBody SuppliersAndConsumers suppliersAndConsumers) {
        suppliersAndConsumersService.UpdateSuppliersAndConsumers(suppliersAndConsumers);

        return ResponseEntity.ok().build();
    }
}
