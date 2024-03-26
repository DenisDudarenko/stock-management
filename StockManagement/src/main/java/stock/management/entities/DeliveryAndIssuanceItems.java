package stock.management.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "delivery_and_issuance_items")
public class DeliveryAndIssuanceItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "delivery_and_issuance_id")
    private DeliveryAndIssuance deliveryAndIssuance;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Products product;

    @Column(nullable = false, name = "quantity")
    private Integer quantity;

    public DeliveryAndIssuanceItems(){}
    public DeliveryAndIssuanceItems(Integer id, DeliveryAndIssuance deliveryAndIssuance, Products product, Integer quantity) {
        this.id = id;
        this.deliveryAndIssuance = deliveryAndIssuance;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DeliveryAndIssuance getDeliveryAndIssuance() {
        return deliveryAndIssuance;
    }

    public void setDeliveryAndIssuance(DeliveryAndIssuance deliveryAndIssuance) {
        this.deliveryAndIssuance = deliveryAndIssuance;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryAndIssuanceItems that = (DeliveryAndIssuanceItems) o;
        return Objects.equals(id, that.id) && Objects.equals(deliveryAndIssuance, that.deliveryAndIssuance) && Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity);
    }
}
