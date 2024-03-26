package stock.management.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import stock.management.enums.MeasurementUnits;
import stock.management.enums.ProductType;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "type")
    private ProductType type;

    @Column(nullable = false, name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "measurement")
    private MeasurementUnits measurement;

    @Column(nullable = false, name = "lifeTime")
    private Timestamp lifeTime;

    @Column(nullable = false, name = "storage_place")
    @JdbcTypeCode(SqlTypes.JSON)
    private StoragePlace storagePlace;

    public Products(){}
    public Products(Integer id, String name, ProductType type, Integer quantity,
                    MeasurementUnits measurement, Timestamp lifeTime, StoragePlace storagePlace) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.measurement = measurement;
        this.lifeTime = lifeTime;
        this.storagePlace = storagePlace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MeasurementUnits getMeasurement() {
        return measurement;
    }

    public void setMeasurement(MeasurementUnits measurement) {
        this.measurement = measurement;
    }

    public Timestamp getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Timestamp lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Object getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(StoragePlace storagePlace) {
        this.storagePlace = storagePlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(id, products.id) && Objects.equals(name, products.name) && type == products.type && Objects.equals(quantity, products.quantity) && measurement == products.measurement && Objects.equals(lifeTime, products.lifeTime) && Objects.equals(storagePlace, products.storagePlace);
    }
}
