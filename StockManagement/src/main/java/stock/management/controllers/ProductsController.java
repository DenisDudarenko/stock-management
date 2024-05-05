package stock.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stock.management.entities.Products;
import stock.management.entities.StoragePlace;
import stock.management.enums.MeasurementUnits;
import stock.management.enums.ProductType;
import stock.management.requests.GetPaginatedProducts;
import stock.management.services.interfaces.IProductsService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProductsController {

    private final IProductsService productsService;
    @Autowired
    public ProductsController(IProductsService storeManagementService) {
        this.productsService = storeManagementService;
    }

    @GetMapping("/products")
    public String GetProducts(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "10") Integer count,
                              @RequestParam(required = false) ProductType type,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lifeTime,
                              @RequestParam(required = false) String supplierInn,
                              Model model) {
        try {
            GetPaginatedProducts request;
            if (lifeTime == null) {
                request = new GetPaginatedProducts(page, count, type, null, supplierInn);
            }
            else {
                request = new GetPaginatedProducts(page, count, type, Timestamp.valueOf(lifeTime), supplierInn);
            }

            List<Products> products = productsService.GetProductsPaginated(request);
            model.addAttribute("products", products);
            return "search_products";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to get products");
            e.printStackTrace();
            System.out.println("GetProducts failed");
            return "error";
        }
    }

    @GetMapping("/product")
    public String GetProduct(@RequestParam String name,
                                             Model model) {
        try {
            Products product = productsService.GetProduct(name);
            model.addAttribute("product", product);
            return "product";
        }
        catch (Exception e)
        {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to get product with name " + name);
            e.printStackTrace();
            System.out.println("GetProduct failed");
            return "error";
        }
    }

    @GetMapping("/product/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Products());
        return "create_product";
    }

    @PostMapping("/product/new")
    public String AddProduct(Products product,
                             Model model) {
        try {
            productsService.AddProduct(product);

            model.addAttribute("operation", "add");
            model.addAttribute("type", "product");
            return "success";
        }
        catch (Exception e)
        {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to add new product with name " + product.getName());
            e.printStackTrace();
            System.out.println("AddProduct failed");
            return "error";
        }
    }

    @PostMapping("/product/update")
    public String UpdateProduct(Products product,
                                Model model) {
        try{
            productsService.UpdateProduct(product);

            model.addAttribute("operation", "update");
            model.addAttribute("type", "product");
            return "success";
        }
        catch (Exception e)
        {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to update new product with name " + product.getName());
            e.printStackTrace();
            System.out.println("UpdateProduct failed");
            return "error";
        }
    }
}