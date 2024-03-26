package stock.management.entities;

import jakarta.persistence.Embeddable;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContactInfo implements Serializable {
    private String address;
    private String phone;
    private String email;

    @Autowired
    public ContactInfo(){}

    @Autowired
    public ContactInfo(String address, String phone, String email)
    {
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email);
    }
}
