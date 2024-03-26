package stock.management.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StoragePlace implements Serializable {
    private Integer roomNumber;
    private Integer shelfNumber;

    public StoragePlace(){}

    public StoragePlace(Integer roomNumber, Integer shelfNumber){
        this.roomNumber = roomNumber;
        this.shelfNumber = shelfNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(Integer shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoragePlace that = (StoragePlace) o;
        return Objects.equals(roomNumber, that.roomNumber) && Objects.equals(shelfNumber, that.shelfNumber);
    }
}
