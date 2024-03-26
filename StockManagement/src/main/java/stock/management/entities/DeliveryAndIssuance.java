package stock.management.entities;

import jakarta.persistence.*;
import stock.management.enums.OperationType;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "delivery_and_issuance")
public class DeliveryAndIssuance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "owner_id")
    private SuppliersAndConsumers owner;

    @Column(nullable = false, name = "operation_time")
    private Timestamp operationTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "operation_type")
    private OperationType operationType;

    public DeliveryAndIssuance(){}
    public DeliveryAndIssuance(Integer id, SuppliersAndConsumers owner, Timestamp operationTime, OperationType operationType) {
        this.id = id;
        this.owner = owner;
        this.operationTime = operationTime;
        this.operationType = operationType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SuppliersAndConsumers getOwnerId() {
        return owner;
    }

    public void setOwnerId(SuppliersAndConsumers owner) {
        this.owner = owner;
    }

    public Timestamp getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Timestamp operationTime) {
        this.operationTime = operationTime;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryAndIssuance that = (DeliveryAndIssuance) o;
        return Objects.equals(id, that.id) && Objects.equals(owner, that.owner) && Objects.equals(operationTime, that.operationTime) && operationType == that.operationType;
    }
}
