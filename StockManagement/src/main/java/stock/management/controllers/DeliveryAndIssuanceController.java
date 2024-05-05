package stock.management.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stock.management.entities.DeliveryAndIssuance;
import stock.management.entities.Products;
import stock.management.enums.OperationType;
import stock.management.requests.GetPaginatedDeliveryAndIssuance;
import stock.management.requests.GetPaginatedProducts;
import stock.management.requests.GetPaginatedSuppliersAndConsumers;
import stock.management.responses.DeliveryAndIssuanceView;
import stock.management.services.interfaces.IDeliveryAndIssuanceService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class DeliveryAndIssuanceController {

    private final IDeliveryAndIssuanceService deliveryAndIssuanceService;

    public DeliveryAndIssuanceController(IDeliveryAndIssuanceService deliveryAndIssuanceService) {
        this.deliveryAndIssuanceService = deliveryAndIssuanceService;
    }

    @GetMapping("/deliveries-and-issuance")
    public String GetDeliveriesAndIssuance(@RequestParam(required = false, defaultValue = "1") Integer page,
                                           @RequestParam(required = false, defaultValue = "10") Integer count,
                                           @RequestParam(required = false) OperationType type,
                                           @RequestParam(required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime operationTime,
                                           Model model) {
        try {
            GetPaginatedDeliveryAndIssuance request;
            if (operationTime == null) {
                request = new GetPaginatedDeliveryAndIssuance(page, count, type, null);
            }
            else {
                request = new GetPaginatedDeliveryAndIssuance(page, count, type, Timestamp.valueOf(operationTime));
            }
            model.addAttribute("deliveriesAndIssuance", deliveryAndIssuanceService.GetDeliveriesAndIssuancePaginated(request));
            return "search_deliveries_and_issuance";
        } catch (Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to get deliveries and issuance");
            e.printStackTrace();
            System.out.println("GetDeliveriesAndIssuance failed");
            return "error";
        }
    }

    @GetMapping("/delivery-and-issuance")
    public String GetDeliveryAndIssuance(@RequestParam Integer id, Model model) {
        try {
            DeliveryAndIssuanceView deliveryAndIssuance = deliveryAndIssuanceService.GetDeliveryAndIssuance(id);
            model.addAttribute("deliveryAndIssuance", deliveryAndIssuance);
            return "delivery_and_issuance";
        } catch (Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to get delivery and issuance with id " + id);
            e.printStackTrace();
            System.out.println("GetDeliveryAndIssuance failed");
            return "error";
        }
    }

    @GetMapping("/delivery-and-issuance/new")
    public String createProductForm(Model model) {
        var deliveryAndIssuanceView =  new DeliveryAndIssuanceView();
        deliveryAndIssuanceView.setProducts();
        model.addAttribute("deliveryAndIssuance", deliveryAndIssuanceView);
        return "create_delivery_and_issuance";
    }

    @PostMapping("/delivery-and-issuance/new")
    public String AddDeliveryAndIssuance(DeliveryAndIssuanceView deliveryAndIssuance, Model model) {
        try {
            deliveryAndIssuanceService.AddDeliveryAndIssuance(deliveryAndIssuance);
            model.addAttribute("operation", "add");
            model.addAttribute("type", "deliveryAndIssuance");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to add new delivery and issuance");
            e.printStackTrace();
            System.out.println("AddDeliveryAndIssuance failed");
            return "error";
        }
    }

    @PostMapping("/delivery-and-issuance/update")
    public String UpdateDeliveryAndIssuance(DeliveryAndIssuanceView deliveryAndIssuance, Model model) {
        try {
            deliveryAndIssuanceService.UpdateDeliveryAndIssuance(deliveryAndIssuance);
            model.addAttribute("operation", "update");
            model.addAttribute("type", "deliveryAndIssuance");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("message", "Failed to update delivery and issuance");
            e.printStackTrace();
            System.out.println("UpdateDeliveryAndIssuance failed");
            return "error";
        }
    }
}
