package stock.management.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Entity
@Table(name = "suppliers_and_consumers")
public class SuppliersAndConsumers
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "inn")
    private String inn;

    @Column(nullable = false, name = "contact_info")
    @JdbcTypeCode(SqlTypes.JSON)
    private ContactInfo contactInfo;

    public SuppliersAndConsumers(){}

    public SuppliersAndConsumers(Integer id, String name, String inn, ContactInfo contactInfo) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.contactInfo = contactInfo;
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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuppliersAndConsumers other = (SuppliersAndConsumers) o;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.inn, other.inn)
                && Objects.equals(this.contactInfo, other.contactInfo);
    }
}
