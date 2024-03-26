package stock.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.management.entities.Products;
import stock.management.requests.GetPaginatedProducts;
import stock.management.services.interfaces.IProductsService;

@RestController
public class ProductsController {

    private final IProductsService productsService;
    @Autowired
    public ProductsController(IProductsService storeManagementService) {
        this.productsService = storeManagementService;
    }

    @GetMapping("/products")
    public ResponseEntity<Object> GetProducts(@RequestBody GetPaginatedProducts request) {
        var result = productsService.GetProductsPaginated(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/product")
    public ResponseEntity<Object> GetProduct(@RequestParam String name) {
        var result = productsService.GetProduct(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/product/new")
    public ResponseEntity<Object> AddProduct(@RequestBody Products product) {
         productsService.AddProduct(product);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/update")
    public ResponseEntity<Object> UpdateProduct(@RequestBody Products product) {
        productsService.UpdateProduct(product);

        return ResponseEntity.ok().build();
    }
}