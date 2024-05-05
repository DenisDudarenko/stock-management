package stock.management.responses;

import jakarta.persistence.*;
import stock.management.entities.SuppliersAndConsumers;
import stock.management.enums.OperationType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAndIssuanceView {
    private Integer id;
    
    private List<ProductView> products;

    private Timestamp operationTime;

    private OperationType operationType;

    private String inn;

    public DeliveryAndIssuanceView(){}
    public DeliveryAndIssuanceView(Integer id, Timestamp operationTime, OperationType operationType, String inn) {
        this.id = id;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.inn = inn;
    }

    public DeliveryAndIssuanceView(Integer id, List<ProductView> products, Timestamp operationTime, OperationType operationType, String inn) {
        this.id = id;
        this.products = products;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.inn = inn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProductView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductView> products) {
        this.products = products;
    }

    public void setProducts() {
        this.products = new ArrayList<>();
    }

    public Timestamp getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Timestamp operationTime) {
        this.operationTime = operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = Timestamp.valueOf(operationTime);
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
}
