package stock.management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stock.management.entities.Products;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.enums.ProductType;
import stock.management.requests.GetPaginatedDeliveryAndIssuance;
import stock.management.requests.GetPaginatedSuppliersAndConsumers;
import stock.management.services.interfaces.ISuppliersAndConsumersService;

@Controller
public class SuppliersAndConsumersController {

    private final ISuppliersAndConsumersService suppliersAndConsumersService;

    public SuppliersAndConsumersController(ISuppliersAndConsumersService suppliersAndConsumersService) {
        this.suppliersAndConsumersService = suppliersAndConsumersService;
    }

    @GetMapping("/suppliers-and-consumers")
    public String GetSuppliersAndConsumers(@RequestParam(required = false, defaultValue = "1") Integer page,
                                           @RequestParam(required = false, defaultValue = "10") Integer count,
                                           @RequestParam(required = false) String supplierInn,
                                           Model model) {
        try {
            var request = new GetPaginatedSuppliersAndConsumers(page, count, supplierInn);
            model.addAttribute("suppliersAndConsumers", suppliersAndConsumersService.GetPaginatedSuppliersAndConsumers(request));
            return "search_suppliers_and_consumers";
        } catch (Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to get suppliers and consumers");
            e.printStackTrace();
            System.out.println("GetSuppliersAndConsumers failed");
            return "error";
        }
    }

    @GetMapping("/supplier-and-consumer")
    public String GetSupplierAndConsumer(@RequestParam String inn, Model model) {
        try {
            SuppliersAndConsumers supplierAndConsumer = suppliersAndConsumersService.GetSuppliersAndConsumers(inn);
            model.addAttribute("supplierAndConsumer", supplierAndConsumer);
            return "supplier_and_consumer";
        } catch (Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to get supplier and consumer with inn " + inn);
            e.printStackTrace();
            System.out.println("GetSupplierAndConsumer failed");
            return "error";
        }
    }

    @GetMapping("/supplier-and-consumer/new")
    public String createProductForm(Model model) {
        model.addAttribute("supplierAndConsumer", new SuppliersAndConsumers());
        return "create_supplier_and_consumer";
    }

    @PostMapping("/supplier-and-consumer/new")
    public String AddSupplierAndConsumer(SuppliersAndConsumers supplierAndConsumer,
                                         Model model) {
        try {
            suppliersAndConsumersService.AddSuppliersAndConsumers(supplierAndConsumer);
            model.addAttribute("operation", "add");
            model.addAttribute("type", "supplierAndConsumer");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to add new supplier and consumer");
            e.printStackTrace();
            System.out.println("AddSupplierAndConsumer failed");
            return "error";
        }
    }

    @PostMapping("/supplier-and-consumer/update")
    public String UpdateSupplierAndConsumer(SuppliersAndConsumers supplierAndConsumer, Model model) {
        try {
            suppliersAndConsumersService.UpdateSuppliersAndConsumers(supplierAndConsumer);
            model.addAttribute("operation", "update");
            model.addAttribute("type", "supplierAndConsumer");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to update supplier and consumer");
            e.printStackTrace();
            System.out.println("UpdateSupplierAndConsumer failed");
            return "error";
        }
    }
}
